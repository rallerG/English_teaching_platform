package com.gruppemagnus.stackviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<ViewPagerModel> contents;
    private Context context;

    public ViewPagerAdapter(List<ViewPagerModel> contents, Context context) {

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
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_contents, container, false);

        container.addView(view);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv);
        imageView.setImageResource(contents.get(position).getImages());

        TextView name, deig, place;

        name = (TextView) view.findViewById(R.id.name);
        name.setText(contents.get(position).getPlace());

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }



}
