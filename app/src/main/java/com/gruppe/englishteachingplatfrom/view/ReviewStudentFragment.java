package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherReviewDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherReviewDocument;
import com.gruppe.englishteachingplatfrom.model.Review;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReviewStudentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewStudentFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;

    private RatingBar ratingBar;
    private EditText content;
    private Button send;

    private String teacherid;

    private OnFragmentInteractionListener mListener;

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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_student, container, false);
        ratingBar = view.findViewById(R.id.ratingbar);
        content = view.findViewById(R.id.content);
        send = view.findViewById(R.id.send_review);

        Bundle bundle = this.getArguments();
        if (bundle != null)
            teacherid = bundle.getString("id");
        return view;
    }

    @Override
    public void onClick(View view) {
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
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
