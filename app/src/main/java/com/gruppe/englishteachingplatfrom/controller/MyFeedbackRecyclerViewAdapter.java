package com.gruppe.englishteachingplatfrom.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.FeedbackProfile;

import java.util.List;


public class MyFeedbackRecyclerViewAdapter extends RecyclerView.Adapter<MyFeedbackRecyclerViewAdapter.ViewHolder> {

    private final List<FeedbackProfile> feedback;
    Context mContext;

    public MyFeedbackRecyclerViewAdapter(Context mContext,List<FeedbackProfile> feedback) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mFeedback = feedback.get(position);
        holder.mStudName.setText(feedback.get(position).getStudentProfile().getName());
        holder.mRating.setRating((float) feedback.get(position).getRating());
        holder.mContent.setText(feedback.get(position).getContent());

   /*     holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mFeedback);
                }
            }
        });*/
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
        public FeedbackProfile mFeedback;

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
