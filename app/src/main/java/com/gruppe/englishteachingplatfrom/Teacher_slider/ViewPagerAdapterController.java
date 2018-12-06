package com.gruppe.englishteachingplatfrom.Teacher_slider;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.gruppe.englishteachingplatfrom.DialogBox;
import com.gruppe.englishteachingplatfrom.R;


import java.util.List;

public class ViewPagerAdapterController extends PagerAdapter  {


    private List<ViewPagerModel> contents;
    private Context context;

    public ViewPagerAdapterController(List<ViewPagerModel> contents, Context context) {

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

        container.addView(view);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setImageResource(contents.get(position).getImages());

        TextView name, deig, place;

        name = (TextView) view.findViewById(R.id.name);
        name.setText(contents.get(position).getPlace());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is the on click listerner for the objects in the viewpager
                System.out.println("works");



              Toast.makeText(context,contents.get(position).names,Toast.LENGTH_SHORT).show();

              //view.getContext().


            }
        });

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }



}
