package com.gruppe.englishteachingplatfrom.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.view.ListFragment.OnListFragmentInteractionListener;
import com.gruppe.englishteachingplatfrom.view.PaymentActiveFragment;

import java.util.ArrayList;
import java.util.List;


public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private List<TeacherProfile> mProfiles;
    private  OnItemClickListener mListener;


    public MyFavoriteRecyclerViewAdapter(ArrayList<TeacherProfile> teacherDummies) {
        mProfiles = teacherDummies;
    }

    @Override
    public MyFavoriteRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_card_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mNameView.setText(mProfiles.get(position).getName());
        holder.mRatingNum.setText(Double.toString(mProfiles.get(position).getRating()));
        holder.mRatingBar.setRating((float) (mProfiles.get(position).getRating()));
        holder.mLanguageView.setText(mProfiles.get(position).getLanguage());
        holder.mPriceView.setText(Integer.toString(mProfiles.get(position).getPrice()) + " DKK");
        holder.mImageView.setImageResource(mProfiles.get(position).getProfilePic());
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;
        public final TextView mNameView;
        public final RatingBar mRatingBar;
        public final TextView mRatingNum;
        public final TextView mLanguageView;
        public final TextView mPriceView;
//        public TeacherProfile mLProfile;

        public ViewHolder(View view, final OnItemClickListener Listener) {
            super(view);
            mImageView = view.findViewById(R.id.listImageView);
            mNameView = view.findViewById(R.id.nameView);
            mRatingBar = view.findViewById(R.id.ratingBar6);
            mRatingNum = view.findViewById(R.id.ratingNum1);
            mLanguageView = view.findViewById(R.id.titleView3);
            mPriceView = view.findViewById(R.id.priceView4);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                        if (Listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                Listener.onItemClick(position);
                            }
                        }
                    }
            });
        }

        @Override
        public String toString() {
            return super.toString()
                    + " 'Name: " + mNameView.getText()
                    + ", Rating: " + mRatingNum.getText()
                    + ", Language: " + mLanguageView.getText()
                    + ", Price: " + mPriceView.getText()
                    + "'";
        }
    }

}