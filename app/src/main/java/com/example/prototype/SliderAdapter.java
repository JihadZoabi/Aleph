package com.example.prototype;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {R.drawable.logo, R.drawable.ic_world, R.drawable.ic_image2, R.drawable.podium};
    public String[] slide_desc = {"HEAR IT, SPEAK IT, MASTER IT."
            , "Hebrew and Arabic have many similarities.\n Spoken.li helps Israelis and Palestinians learn each other's language.",
            "With Spoken.li you can use our voice recognition feature to test improve your pronunciation.",
            "Compete with other users & use falafels to unlock new content!"};
    public String[] slide_color = {"#B681DA", "#83CFC8", "#F7E3A1", "#C1DBA3"};

    @Override
    public int getCount() {
        return slide_desc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideDescription = view.findViewById(R.id.slide_desc);
        RelativeLayout bg = view.findViewById(R.id.bg_color);
        TextView continueText = view.findViewById(R.id.continueText);

        slideImageView.setImageResource(slide_images[position]);
        slideDescription.setText(slide_desc[position]);
        bg.setBackgroundColor(Color.parseColor(slide_color[position]));

        if(position == slide_desc.length-1){
            continueText.setVisibility(View.VISIBLE);
            continueText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            });
        }

        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);

    }
}