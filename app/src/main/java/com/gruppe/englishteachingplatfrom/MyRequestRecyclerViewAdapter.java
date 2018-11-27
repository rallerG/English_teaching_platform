package com.gruppe.englishteachingplatfrom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MyRequestRecyclerViewAdapter extends  RecyclerView.Adapter<MyRequestRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private int selectedPos = RecyclerView.NO_POSITION;
    private final List<MailProfile> mValues;
    SparseBooleanArray sparseBooleanArray;
    int selectedItemCount;
    Activity mail;
    Context mContext;
    private ArrayList<MailProfile> mails = new ArrayList<MailProfile>();
    TextView studName, content;
    ToggleButton star;
    TableLayout item;
    ConstraintLayout itemHolder;

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

//        name.setText(mValues.get(vHolder.getAdapterPosition()).getStudName());
//        content.setText(mValues.get(vHolder.getAdapterPosition()).getContent());

    //    mail = new request_mail();
    //    mail.setContentView(R.layout.activity_request_mail);

     //   vHolder.item.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
          //      Intent intent = new Intent(mContext, request_mail.class);

               // TextView name = mail.findViewById(R.id.name);
               // TextView content = mail.findViewById(R.id.content);
              //  ToggleButton starMail = mail.findViewById(R.id.starMail);
        content = view.findViewById(R.id.FeedContent);
        studName = view.findViewById(R.id.studName);
        star = view.findViewById(R.id.toggleStar);
        item = view.findViewById(R.id.item);
        itemHolder = view.findViewById(R.id.holder);
   //         }
     //   });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.star.setChecked(itemTitles.get(position));

      //  studName = view.findViewById(R.id.studName);
        studName.setText(mValues.get(position).getStudName());
        content.setText(mValues.get(position).getContent());


       // notifyItemChanged(getAdapterPosition());

        if(mValues.get(position).getFavorite()){
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
        } else {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color);
        }

        if (sparseBooleanArray.get(position)) {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color1);
            mValues.get(position).setFavorite(true);
        } else {
            holder.star.setBackgroundResource(R.drawable.ic_toggle_star_color);
            mValues.get(position).setFavorite(false);
        }
        if(mValues.get(position).getVisited()){
            itemHolder.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorVisited));
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked on: " + mValues.get(position).getStudName() + "Favorite: " + mValues.get(position).getFavorite() + " Visited: " + mValues.get(position).getVisited(),Toast.LENGTH_SHORT).show();
                mValues.get(position).setVisited(true);
                Intent intent = new Intent(mContext, request_mail.class);
                intent.putExtra("studName", mValues.get(position).getStudName());
                intent.putExtra("favorite", mValues.get(position).getFavorite());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) { }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        private TableLayout item;
        private TextView studName, content;
        private ToggleButton star;

        public ViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            content = view.findViewById(R.id.FeedContent);
            item = view.findViewById(R.id.item);
            studName = view.findViewById(R.id.studName);
            star = view.findViewById(R.id.toggleStar);
            star.setOnClickListener(this);


//            studName.setText(getAdapterPosition());

        }

        @Override
        public void onClick(View v ) {
            if (!sparseBooleanArray.get(getAdapterPosition())) {
                sparseBooleanArray.put(getAdapterPosition(), true);
                selectedItemCount++;
                notifyItemChanged(getAdapterPosition());
            } else {
                sparseBooleanArray.put(getAdapterPosition(), false);
                selectedItemCount--;
                notifyItemChanged(getAdapterPosition());
            }


        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
