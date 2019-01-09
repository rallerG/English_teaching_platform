package com.gruppe.englishteachingplatfrom.controller;


import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.gruppe.englishteachingplatfrom.R;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;
import com.gruppe.englishteachingplatfrom.model.ViewPagerModel;


import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter implements View.OnClickListener {


    private ArrayList<TeacherProfile> contents;
    private Context context;
    LinearLayout information, expander;
    ImageView imageView;
    CardView card;
    TextView txt;
  //  int images[] = {R.drawable.profile1,R.drawable.profile1, R.drawable.profile2, R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3,R.drawable.profile3 };

    public ViewPagerAdapter(ArrayList<TeacherProfile> contents, Context context) {

        this.contents = contents;
        this.context = context;
    }

    @Override
    public int getCount() {
        return  contents.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==(FrameLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.fragment_page_frag, container, false);

        view.setOnItemClickListener(new View.OnClickListener(){
            public void onClick(View v){
                txt.setVisibility(View.VISIBLE);
            }
        });


/*
        expander = view.findViewById(R.id.expander);
        expander.setOnClickListener(this);*/

        container.addView(view,0);

        System.out.println("position: " + position);

        imageView = (ImageView) view.findViewById(R.id.pic);



        imageView.setImageResource(contents.get(position).getmPicture());


        TextView name, title, rate, price;
        RatingBar rating;

        card = view.findViewById(R.id.card);
        card.setOnClickListener(this);
    /*    information = view.findViewById(R.id.information);
        information.setVisibility(View.INVISIBLE);*/

    //    information.setOnClickListener(this);

        txt = view.findViewById(R.id.sup_txt);
        txt.setText(contents.get(position).getmInfo());
        txt.setVisibility(View.INVISIBLE);
       /* name = view.findViewById(R.id.name);
        name.setText(contents.get(position).getmName());

        title = view.findViewById(R.id.title);
        title.setText(contents.get(position).getmTitle());

        //Rating should be replaced by teacher rating
        rating = view.findViewById(R.id.rating);
        rating.setRating(Float.parseFloat(contents.get(position).getmRating()));

        rate = view.findViewById(R.id.rate);
        rate.setText(""+ rating.getRating());
*/
        //Price should be replaced by teacher pricing
    /*    price = view.findViewById(R.id.price);
        price.setText(contents.get(position).getmPrice() + "dkk");*/

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is the on click listerner for the objects in the viewpager
                System.out.println("works");

              Toast.makeText(context,contents.get(position).getmName(),Toast.LENGTH_SHORT).show();

            }
        });
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public void onClick(View v) {
       /* if(v == expander){
            System.out.println("Du trykkede på en teacher");
                information.setVisibility(View.GONE);
            } else {
                information.setVisibility(View.VISIBLE);
            }

            if(v == imageView){
            information.setVisibility(View.VISIBLE);
            }*/

    }
}
