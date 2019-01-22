package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherMatchesDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherPendingsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherPendingsDocument;
import com.gruppe.englishteachingplatfrom.controller.MyRequestRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.controller.MyTeacherMatchesRecyclerViewAdapter;
import com.gruppe.englishteachingplatfrom.model.Singleton;


public class PendingRequestFragment extends Fragment implements View.OnClickListener {
    ImageView profilePic;
    TextView name;
    Button accept, delete;
    String studId, studName;
    int studPicture;
    RecyclerView mRecyclerView;
    MyRequestRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Singleton p = Singleton.getInstance();
    private ProgressDialog pDialog;


    public PendingRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_pending_request, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            studId = bundle.getString("id","");
            studName = bundle.getString("name", "");
            studPicture = bundle.getInt("picture",0);
        }

        profilePic = view.findViewById(R.id.imageView);
        name = view.findViewById(R.id.name);
        name.setText(studName);
        accept = view.findViewById(R.id.accept);
        accept.setOnClickListener(this);
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(this);

        return view;
    }


    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.accept:
                pDialog = new ProgressDialog(getContext());
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                TeacherMatchesDocument teacherMatchesDocument = new TeacherMatchesDocumentImpl(p.getCurrrentTeacher().getId());
                teacherMatchesDocument.add(studId,false, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        TeacherPendingsDocument teacherPendingsDocument = new TeacherPendingsDocumentImpl(p.getCurrrentTeacher().getId());
                        teacherPendingsDocument.deleteEqualTo(studId,false, new CallbackSuccess() {
                            @Override
                            public void onCallback() {
                                if (pDialog.isShowing()){
                                    pDialog.dismiss();
                                }
                                getFragmentManager().popBackStack();
                            //    finish();
                            }
                        });
                    }
                });

                StudentMatchesDocument studentMatchesDocument = new StudentMatchesDocumentImpl(studId);
                studentMatchesDocument.add(p.getCurrrentTeacher().getId(), true, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(studId);
                        studentPendingsDocument.deleteEqualTo(p.getCurrrentTeacher().getId(),true);
                    }
                });
                break;
            case R.id.delete:
                pDialog = new ProgressDialog(getContext());
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(true);
                pDialog.show();

                TeacherPendingsDocument teacherPendingsDocument = new TeacherPendingsDocumentImpl(p.getCurrrentTeacher().getId());
                teacherPendingsDocument.deleteEqualTo(studId,false, new CallbackSuccess() {
                    @Override
                    public void onCallback() {
                        if (pDialog.isShowing()){
                            pDialog.dismiss();
                        }
                        getFragmentManager().popBackStack();
                      //  finish();
                    }
                });

                StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(studId);
                studentPendingsDocument.deleteEqualTo(p.getCurrrentTeacher().getId(),true);
                break;
        }
    }
}
