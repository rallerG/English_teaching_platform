package com.gruppe.englishteachingplatfrom.controller;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherFeedbackDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherFeedbackDocument;
import com.gruppe.englishteachingplatfrom.model.Feedback;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.ArrayList;
import java.util.List;


public class MyFeedbackRecyclerViewAdapter extends RecyclerView.Adapter<MyFeedbackRecyclerViewAdapter.ViewHolder> {

    private List<Feedback> feedback;
    Context mContext;
    private ConstraintLayout feedback_element;

    public MyFeedbackRecyclerViewAdapter(Context mContext,List<Feedback> feedback) {
        this.mContext = mContext;
        this.feedback = feedback;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feedback, parent, false);
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.fragment_feedback,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Feedback feed = feedback.get(position);

        TeacherFeedbackDocument feedbackDocument = new TeacherFeedbackDocumentImpl("1");
        feedbackDocument.getAll(new CallbackList<Feedback>() {
            @Override
            public void onCallback(List<Feedback> listOfObjects) {

                for (final Feedback feed : listOfObjects) {
                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                    studentsDocument.get(feed.getStudentId(), new Callback<StudentProfile>() {
                        @Override
                        public void onCallback(StudentProfile object) {
                            //feed.setStudentProfile(object);
                            holder.mStudName.setText(object.getName());
                        }
                    });
                    holder.mRating.setRating((float) feed.getRating());
                    holder.mContent.setText(feed.getContent());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedback.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mStudName;
        public final RatingBar mRating;
        public final TextView mContent;
        public Feedback mFeedback;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mStudName =  view.findViewById(R.id.studName);
            mRating = view.findViewById(R.id.rating);
            mContent = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString()
                    + " 'Name: " + mStudName.getText()
                    + ", Rating: " + mRating.getRating()
                    + ", Title: " + mContent.getText()
                    + "'";
        }
    }
}
