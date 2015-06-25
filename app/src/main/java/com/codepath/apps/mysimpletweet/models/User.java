package com.codepath.apps.mysimpletweet.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohinish on 6/19/15.
 */

/*
"user":{
        "name":"OAuth Dancer",
        "profile_sidebar_fill_color":"DDEEF6",
        "profile_background_tile":true,
        "profile_sidebar_border_color":"C0DEED",
        "profile_image_url":"http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
        "created_at":"Wed Mar 03 19:37:35 +0000 2010",
        "location":"San Francisco, CA",
        "follow_request_sent":false,
        "id_str":"119476949",
        "is_translator":false,
        "profile_link_color":"0084B4",
        },*/

public class User {
    //list atribute
    private String name;
    private long uid;
    private String screen_name;
    private String profileImageUrl;

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public long getUid() {
        return uid;
    }

    //Deserialise int json object
// User.fromJson({..})==> <Tweet>
    public  static  User fromJSON(JSONObject jsonObject){
        User user= new User();
        //extract values from the json,store them
        try {
            user.name=jsonObject.getString("name");
            user.uid=jsonObject.getLong("id");
            user.screen_name=jsonObject.getString("screen_name");
            user.profileImageUrl=jsonObject.getString("profile_image_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Return the user object
        return  user;
    }

}
