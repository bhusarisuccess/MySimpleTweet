package com.codepath.apps.mysimpletweet.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweet.R;
import com.codepath.apps.mysimpletweet.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetArrayAdapter extends ArrayAdapter<Tweet>{


    public TweetArrayAdapter(Context context,  List<Tweet> tweet) {
        super(context, android.R.layout.simple_expandable_list_item_1, tweet);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. get the tweets
        Tweet tweet = getItem(position);
        //2.inflait the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);

        }

        //3.initialise the view item

        ImageView ivprofilePhoto = (ImageView) convertView.findViewById(R.id.ivProfilePhoto);
        TextView tvname = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvbody = (TextView) convertView.findViewById(R.id.tvBody);
       // ivprofilePhoto.setImageResource(0);
        //4.set the item into the view
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivprofilePhoto);
        tvbody.setText(tweet.getBody());
        tvname.setText(tweet.getUser().getName());
        return convertView;
    }
}
