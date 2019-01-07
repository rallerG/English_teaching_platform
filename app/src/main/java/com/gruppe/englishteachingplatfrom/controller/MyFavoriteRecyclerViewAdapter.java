package com.gruppe.englishteachingplatfrom.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.view.ListFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private final List<TeacherProfile> mProfiles;
    private final OnListFragmentInteractionListener mListener;

    public MyFavoriteRecyclerViewAdapter(List<TeacherProfile> items, OnListFragmentInteractionListener listener) {
        mProfiles = items;
        mListener = listener;
    }

    @Override
    public MyFavoriteRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mNameView.setText(mProfiles.get(position).getmName());
        holder.mRatingNum.setText(String.format("%.1f", Float.parseFloat(mProfiles.get(position).getmRating())));
        holder.mRatingBar.setRating(Float.parseFloat(mProfiles.get(position).getmRating()));
        holder.mTitleView.setText(mProfiles.get(position).getmTitle());
        holder.mPriceView.setText(mProfiles.get(position).getmPrice());
        holder.mImageView.setImageResource(R.mipmap.ic_launcher_foreground_student);
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;
        public final TextView mNameView;
        public final RatingBar mRatingBar;
        public final TextView mRatingNum;
        public final TextView mTitleView;
        public final TextView mPriceView;
        public TeacherProfile mLProfile;

        public ViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.listImageView);
            mNameView = view.findViewById(R.id.nameView);
            mRatingBar = view.findViewById(R.id.ratingBar6);
            mRatingNum = view.findViewById(R.id.ratingNum1);
            mTitleView = view.findViewById(R.id.titleView3);
            mPriceView = view.findViewById(R.id.priceView4);
        }

        @Override
        public String toString() {
            return super.toString()
                    + " 'Name: " + mNameView.getText()
                    + ", Rating: " + mRatingNum.getText()
                    + ", Title: " + mTitleView.getText()
                    + ", Price: " + mPriceView.getText()
                    + "'";
        }
    }
}
