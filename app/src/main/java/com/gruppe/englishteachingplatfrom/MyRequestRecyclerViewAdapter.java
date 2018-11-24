package com.gruppe.englishteachingplatfrom;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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



    List<Boolean> itemTitles;
    private int selectedPos = RecyclerView.NO_POSITION;
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    SparseBooleanArray sparseBooleanArray;
    int selectedItemCount;
    OnRecyclerItemClickListener listener;

    public MyRequestRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;

        sparseBooleanArray = new SparseBooleanArray();
        selectedItemCount = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_request, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.star.setChecked(itemTitles.get(position));


        if (sparseBooleanArray.get(position)) {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
        } else {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color);
        }
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
            if (!sparseBooleanArray.get(getAdapterPosition())) {
                sparseBooleanArray.put(getAdapterPosition(), true);
                selectedItemCount++;
//                listener.selectedItemCount(selectedItemCount); // calling the method in main activity Because: in our case mainActivity our created interface for clicklisteners
                notifyItemChanged(getAdapterPosition());
            } else // if clicked item is already selected
            {
                sparseBooleanArray.put(getAdapterPosition(), false);
                selectedItemCount--;
              //  listener.selectedItemCount(selectedItemCount); // calling the method in main activity Because: in our case mainActivity our created interface for clicklisteners
                notifyItemChanged(getAdapterPosition());
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }

    public interface OnRecyclerItemClickListener {
        public void selectedItemCount(int count);
    }
}
