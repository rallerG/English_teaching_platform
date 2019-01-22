package com.gruppe.englishteachingplatfrom.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
import com.gruppe.englishteachingplatfrom.model.MailProfile;
import com.gruppe.englishteachingplatfrom.model.Singleton;

import java.util.ArrayList;
import java.util.List;

public class RequestMailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView profilePic;
    TextView name;
    Button accept, delete;
    private long mLastClickTime = 0;
    String studId;
    private ProgressDialog pDialog;

    private Singleton p = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mail);

        profilePic = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        accept = findViewById(R.id.accept);
        accept.setOnClickListener(this);
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle intentData = intent.getExtras();

        studId = (String) intentData.get("studId");

        String studName = (String) intentData.get("studName");
        name.setText(studName);
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch(v.getId()) {
            case R.id.accept:
                pDialog = new ProgressDialog(this);
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
                                finish();
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
                pDialog = new ProgressDialog(this);
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
                        finish();
                    }
                });

                StudentPendingsDocument studentPendingsDocument = new StudentPendingsDocumentImpl(studId);
                studentPendingsDocument.deleteEqualTo(p.getCurrrentTeacher().getId(),true);
                break;
        }
    }
}
