package com.codepath.apps.mysimpletweet.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.mysimpletweet.R;
import com.codepath.apps.mysimpletweet.TwitterApplication;
import com.codepath.apps.mysimpletweet.TwitterClient;
import com.codepath.apps.mysimpletweet.adapters.TweetArrayAdapter;
import com.codepath.apps.mysimpletweet.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends ActionBarActivity {
    private SwipeRefreshLayout swipeContainer;

    private TwitterClient client;
    private ArrayList<Tweet> tweet;
    private ArrayAdapter atweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(true);
        bar.setIcon(R.drawable.twitter_logo);
      // bar.setBackgroundDrawable(new BitmapDrawable(BitmapFactory.decodeResource(getResources(),R.color.abc_background_cache_hint_selector_material_dark)));
        bar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        //Find the listview
        ListView ivTweet = (ListView) findViewById(R.id.lvTweets);
        //create arraylist
        tweet = new ArrayList<>();
        //construct the Adapter
        atweet = new TweetArrayAdapter(this, tweet);
        //connect adapter to listview
        ivTweet.setAdapter(atweet);
        //get the client
        client = TwitterApplication.getRestClient();  // singletone client
        populateTimeLine();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                populateTimeLine();
            }
        });
       // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }




    //send the api request to get json responce
    //
    private void populateTimeLine() {
        client.getHomeTimeLine(new JsonHttpResponseHandler(){
            //success
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.e("DEBUG",json.toString());


                //Desirialise json and add them to adapter
                //Create a models
                //load data into listview
               atweet.addAll(Tweet.fromJSONArray(json));
                swipeContainer.setRefreshing(false);

            }
            //failuar

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("DEBUG", errorResponse.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tweet) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
