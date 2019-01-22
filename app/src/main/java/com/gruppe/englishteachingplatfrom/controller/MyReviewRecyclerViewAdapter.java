package com.gruppe.englishteachingplatfrom.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeacherReviewDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherReviewDocument;
import com.gruppe.englishteachingplatfrom.model.DocumentObject;
import com.gruppe.englishteachingplatfrom.model.Review;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

import java.util.List;


public class MyReviewRecyclerViewAdapter extends RecyclerView.Adapter<MyReviewRecyclerViewAdapter.ViewHolder> {

    private List<Review> review;
    Context mContext;

    public MyReviewRecyclerViewAdapter(Context mContext, List<Review> review) {
        this.mContext = mContext;
        this.review = review;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review, parent, false);
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.fragment_review,parent,false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Review feed = review.get(position);

        System.out.println("review name: " + review.get(position).getStudentId());
//       holder.mRating.setRating((float) review.get(position).getRating());
//       holder.mContent.setText(review.get(position).getContent());

        TeacherReviewDocument feedbackDocument = new TeacherReviewDocumentImpl("1");
        feedbackDocument.getAll(new CallbackList<Review>() {
            @Override
            public void onCallback(List<Review> listOfObjects) {
                review.clear();
                for (final Review feed : listOfObjects) {
                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
                    studentsDocument.get(feed.getStudentId(), new Callback<StudentProfile>() {
                        @Override
                        public void onCallback(StudentProfile object) {
                            feed.setStudentProfile(object);
                            holder.mStudName.setText(feed.getStudentProfile().getName());
                            holder.mRating.setRating((float) review.get(position).getRating());
                            holder.mContent.setText(review.get(position).getContent());
                        }
                    });
                    review.add(feed);
//                    StudentsDocument studentsDocument = new StudentsDocumentImpl();
//                    studentsDocument.get(feed.getStudentId(), new Callback<StudentProfile>() {
//                        @Override
//                        public void onCallback(StudentProfile object) {
//                            //feed.setStudentProfile(object);
//                            holder.mStudName.setText(object.getName());
//                        }
//                    });
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return review.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mStudName;
        public final RatingBar mRating;
        public final TextView mContent;
        public Review mReview;

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
