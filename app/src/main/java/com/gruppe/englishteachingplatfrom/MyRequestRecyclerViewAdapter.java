package com.gruppe.englishteachingplatfrom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gruppe.englishteachingplatfrom.RequestFragment.OnListFragmentInteractionListener;
import com.gruppe.englishteachingplatfrom.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


public class MyRequestRecyclerViewAdapter extends  RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {


    private  ItemClickListener itemClickListener;
    List<Boolean> itemTitles;
    private int selectedPos = RecyclerView.NO_POSITION;
    private final List<MailProfile> mValues;
    SparseBooleanArray sparseBooleanArray;
    int selectedItemCount;
    OnRecyclerItemClickListener listener;
    Activity mail;
    Context mContext;
    private ArrayList<MailProfile> mails = new ArrayList<MailProfile>();

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



    //    mail = new request_mail();
    //    mail.setContentView(R.layout.activity_request_mail);

     //   vHolder.item.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
          //      Intent intent = new Intent(mContext, request_mail.class);

               /* TextView name = mail.findViewById(R.id.name);
                TextView content = mail.findViewById(R.id.content);
                ToggleButton starMail = mail.findViewById(R.id.starMail);

                name.setText(mValues.get(vHolder.getAdapterPosition()).getStudName());
                content.setText(mValues.get(vHolder.getAdapterPosition()).getContent());

                if(mValues.get(vHolder.getAdapterPosition()).getFavorite()){
                    starMail.setBackgroundResource(R.drawable.ic_toggle_star_color1);
                } else {
                    starMail.setBackgroundResource(R.drawable.ic_toggle_star_color);
                }
*/
   //         }
     //   });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.star.setChecked(itemTitles.get(position));


        if (sparseBooleanArray.get(position)) {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
        } else {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color);
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on: " + mValues.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, request_mail.class);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TableLayout item;
        private TextView studName;
        private ToggleButton star;

        public ViewHolder(View view) {
            super(view);
            /*
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            */

            view.setOnClickListener(this);


            item = view.findViewById(R.id.item);
            studName = view.findViewById(R.id.studName);
            star = view.findViewById(R.id.toggleStar);
            star.setOnClickListener(this);

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
            if(v == item) {
                itemClickListener.onClick(v, getAdapterPosition());
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnRecyclerItemClickListener {
        public void selectedItemCount(int count);
    }
}
