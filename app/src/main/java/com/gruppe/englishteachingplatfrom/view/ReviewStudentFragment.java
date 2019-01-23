package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherReviewDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherReviewDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Review;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

import java.util.List;

public class ReviewStudentFragment extends Fragment implements View.OnClickListener {

    private String mParam1;
    private String mParam2;
    private long mLastClickTime = 0;

    private Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;

    private RatingBar ratingBar;
    private EditText content;
    private Button send;

    private String teacherid;

    public ReviewStudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewStudentFragment newInstance(String param1, String param2) {
        ReviewStudentFragment fragment = new ReviewStudentFragment();
        Bundle args = new Bundle();
        args.putString("id", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("id","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_student, container, false);
        ratingBar = view.findViewById(R.id.ratingbar);
        content = view.findViewById(R.id.content);
        send = view.findViewById(R.id.send_review);
        send.setOnClickListener(this);
        teacherid = mParam1;

        return view;
    }

    @Override
    public void onClick(View view) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (view.getId()) {
            case R.id.send_review :
                StudentProfile studentProfile = p.getCurrrentStudent();
                Review review = new Review(studentProfile,((double) ratingBar.getRating()),content.getText().toString());

                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                TeacherReviewDocument teacherReviewDocument = new TeacherReviewDocumentImpl(teacherid);
                teacherReviewDocument.add(review, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        if (pDialog.isShowing()){
                            pDialog.dismiss();
                        }
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });

                TeachersDocument teachersDocument = new TeachersDocumentImpl();
                teachersDocument.get(teacherid, new Callback<TeacherProfile>() {
                    @Override
                    public void onCallback(final TeacherProfile object) {
                        TeacherReviewDocument teacherReviewDocument1 = new TeacherReviewDocumentImpl(teacherid);
                        teacherReviewDocument1.getAll(new CallbackList<Review>() {
                            @Override
                            public void onCallback(List<Review> listOfObjects) {
                                double totalRating = 0;
                                for (Review review : listOfObjects) {
                                    totalRating += review.getRating();
                                }

                                if (listOfObjects.size() == 0) {
                                    object.setRating((totalRating/1));
                                }
                                else {
                                    object.setRating((totalRating/listOfObjects.size()));
                                }
                                TeachersDocument teachersDocument2 = new TeachersDocumentImpl();
                                teachersDocument2.update(teacherid,object);
                            }
                        });


                    }
                });
                break;
        }
    }
}
