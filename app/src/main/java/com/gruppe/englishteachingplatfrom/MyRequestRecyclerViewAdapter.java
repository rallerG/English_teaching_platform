package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.RequestFragment.OnListFragmentInteractionListener;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;

import java.util.List;


public class MyRequestRecyclerViewAdapter extends  RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder> {

    private int selectedPos = RecyclerView.NO_POSITION;
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyRequestRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_request, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);

  /*      holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
      /*
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;
        */
        public final TextView studName;
        public final ToggleButton star;



        public ViewHolder(View view) {
            super(view);
            /*
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            */

            studName = view.findViewById(R.id.studName);
            star = view.findViewById(R.id.toggleStar);


            star.setOnClickListener(this);


/*            star.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
                    //  star.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_toggle_star_color1));
                } else {
                    star.setBackgroundResource(R.drawable.ic_toggle_star_color);
                }
            }
        });*/

        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }

/*
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
        */
    }
}
