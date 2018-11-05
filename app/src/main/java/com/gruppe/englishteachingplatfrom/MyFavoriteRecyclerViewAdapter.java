package com.gruppe.englishteachingplatfrom;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gruppe.englishteachingplatfrom.FavoriteFragment.OnListFragmentInteractionListener;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFavoriteRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteRecyclerViewAdapter.ViewHolder> {

    private final List<ListProfile> mProfiles;
    private final OnListFragmentInteractionListener mListener;

    public MyFavoriteRecyclerViewAdapter(List<ListProfile> items, OnListFragmentInteractionListener listener) {
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
        holder.mLProfile = mProfiles.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);

        holder.mNameView.setText(mProfiles.get(position).getmName());
        holder.mRatingNum.setText(String.format("%.1f", Float.parseFloat(mProfiles.get(position).getmRating())));
        holder.mRatingBar.setRating(Float.parseFloat(mProfiles.get(position).getmRating()));
        holder.mTitleView.setText(mProfiles.get(position).getmTitle());
        holder.mPriceView.setText(mProfiles.get(position).getmPrice());
        holder.mImageView.setImageResource(R.mipmap.ic_launcher_foreground_student);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mLProfile);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mNameView;
        public final RatingBar mRatingBar;
        public final TextView mRatingNum;
        public final TextView mTitleView;
        public final TextView mPriceView;
        public ListProfile mLProfile;

//        public final TextView mIdView;
//        public final TextView mContentView;
//        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;   //maybe not necessary

            mImageView = view.findViewById(R.id.listImageView);
            mNameView = view.findViewById(R.id.nameView);
            mRatingBar = view.findViewById(R.id.ratingBar6);
            mRatingNum = view.findViewById(R.id.ratingNum1);
            mTitleView = view.findViewById(R.id.titleView3);
            mPriceView = view.findViewById(R.id.priceView4);

//            mIdView = (TextView) view.findViewById(R.id.item_number);
//            mContentView = (TextView) view.findViewById(R.id.content);
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

//    public class ViewHolder extends RecyclerView.ViewHolder {
////        public final View mView;
////        public final TextView mIdView;
////        public final TextView mContentView;
////        public DummyItem mItem;
////
////        public ViewHolder(View view) {
////            super(view);
////            mView = view;
////            mIdView = (TextView) view.findViewById(R.id.item_number);
////            mContentView = (TextView) view.findViewById(R.id.content);
////        }
////
////        @Override
////        public String toString() {
////            return super.toString() + " '" + mContentView.getText() + "'";
////        }
////    }
}
