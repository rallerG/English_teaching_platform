package com.gruppe.englishteachingplatfrom.controller;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ViewPagerAdapter extends PagerAdapter  {


    private ArrayList<TeacherProfile> contents;
    private Context context;
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
        return view ==(LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        container.addView(view,0);

        System.out.println("position: " + position);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setImageResource(contents.get(position).getmPicture());

        TextView name, title, rate, price;
        RatingBar rating;

        name = view.findViewById(R.id.name);
        name.setText(contents.get(position).getmName());

        title = view.findViewById(R.id.title);
        title.setText(contents.get(position).getmTitle());

        //Rating should be replaced by teacher rating
        rating = view.findViewById(R.id.rating);
        rating.setRating(Float.parseFloat(contents.get(position).getmRating()));

        rate = view.findViewById(R.id.rate);
        rate.setText(""+ rating.getRating());

        //Price should be replaced by teacher pricing
        price = view.findViewById(R.id.price);
        price.setText(contents.get(position).getmPrice() + "dkk");

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



}
