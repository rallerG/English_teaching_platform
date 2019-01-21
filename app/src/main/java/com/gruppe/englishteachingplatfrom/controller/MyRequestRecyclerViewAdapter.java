package com.gruppe.englishteachingplatfrom.controller;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.MailProfile;
import com.gruppe.englishteachingplatfrom.view.RequestMailActivity;

import java.util.List;


public class MyRequestRecyclerViewAdapter extends RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private final List<MailProfile> mValues;
    SparseBooleanArray sparseBooleanArray;
    int selectedItemCount;
    Context mContext;
    TextView studName, content;
    ToggleButton star;
    ConstraintLayout itemHolder;
    private long mLastClickTime = 0;

    public MyRequestRecyclerViewAdapter(Context mContext, List<MailProfile> items) {
        this.mContext = mContext;
        mValues = items;

        sparseBooleanArray = new SparseBooleanArray();
        selectedItemCount = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_request, parent, false);

        final ViewHolder vHolder = new ViewHolder(view);

        content = view.findViewById(R.id.FeedContent);
        studName = view.findViewById(R.id.studName);
        star = view.findViewById(R.id.toggleStar);
        itemHolder = view.findViewById(R.id.holder);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.studName.setText(mValues.get(position).getStudName());
        holder.content.setText(mValues.get(position).getContent());


        if (mValues.get(position).getFavorite()) {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
        } else {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color);
        }
        if (mValues.get(position).getVisited()) {
            itemHolder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorVisited));
        }

        holder.itemHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Toast.makeText(mContext, "Clicked on: " + mValues.get(position).getStudName() + "Favorite: " + mValues.get(position).getFavorite() + " Visited: " + mValues.get(position).getVisited(), Toast.LENGTH_SHORT).show();
                mValues.get(position).setVisited(true);
                Intent intent = new Intent(mContext, RequestMailActivity.class);
                intent.putExtra("studName", mValues.get(position).getStudName());
                intent.putExtra("favorite", mValues.get(position).getFavorite());
                intent.putExtra("content", mValues.get(position).getContent());
//                itemHolder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorVisited));
                holder.itemHolder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorVisited));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TableLayout item;
        private TextView studName, content;
        private ToggleButton star;
        private ConstraintLayout itemHolder;

        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            content = view.findViewById(R.id.FeedContent);
            studName = view.findViewById(R.id.studName);
            star = view.findViewById(R.id.toggleStar);
            star.setOnClickListener(this);
            itemHolder = view.findViewById(R.id.holder);
        }

        @Override
        public void onClick(View v) {

            if (!sparseBooleanArray.get(getAdapterPosition())) {
                sparseBooleanArray.put(getAdapterPosition(), true);
                mValues.get(getAdapterPosition()).setFavorite(true);
                notifyItemChanged(getAdapterPosition());
            } else {
                sparseBooleanArray.put(getAdapterPosition(), false);
                mValues.get(getAdapterPosition()).setFavorite(false);
                notifyItemChanged(getAdapterPosition());
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
