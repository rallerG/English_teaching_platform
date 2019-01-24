package com.gruppe.englishteachingplatfrom.controller;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.MailProfile;
import com.gruppe.englishteachingplatfrom.model.Singleton;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.view.PendingRequestFragment;
import com.gruppe.englishteachingplatfrom.view.RequestFragment;
import com.gruppe.englishteachingplatfrom.view.TeacherMainActivity;

import java.util.List;

import static java.security.AccessController.getContext;


public class MyRequestRecyclerViewAdapter extends RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private final List<StudentProfile> mValues;
    int selectedItemCount;
    Context mContext;
    ImageView imageView;
    TextView studName;
    ConstraintLayout itemHolder;
    private long mLastClickTime = 0;
    private OnItemClickListener mListener;

    RecyclerView mRecyclerView;
    MyRequestRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Singleton p = Singleton.getInstance();

    public MyRequestRecyclerViewAdapter(List<StudentProfile> items) {
        mValues = items;

        selectedItemCount = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_request, parent, false);
        mContext = parent.getContext();
        final ViewHolder vHolder = new ViewHolder(view, mListener);

        imageView = view.findViewById(R.id.imageView);
        studName = view.findViewById(R.id.studName);
        itemHolder = view.findViewById(R.id.holder);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.studName.setText(mValues.get(position).getName());
        holder.imageView.setImageResource(mValues.get(position).getProfilePicture());

   /*     holder.itemHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

            }
        });*/
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;
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
        private ImageView imageView;
        private TextView studName;
        private ConstraintLayout itemHolder;

        public ViewHolder(View view, final OnItemClickListener listener) {
            super(view);

            view.setOnClickListener(this);

            imageView = view.findViewById(R.id.imageView);
            studName = view.findViewById(R.id.studName);
            itemHolder = view.findViewById(R.id.holder);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

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
