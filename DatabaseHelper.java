package com.example.chat;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * the ones in ** are specific generated/user entered name
 * the ones not in ** are tags( child names ) int the database
 * methods follow the patterns of get.../set.../initialise...
 *      where initialise creates posts with their variables as empty strings
 * <p>
 *                                          DataBase Structure
 * Chat Requests
 *              *UID*
 *              *Sender/Receiver ID*
 *              *Sent or Received*
 * Groups
 *          *Group Name*
 *          *Message ID*
 *                      date
 *                      message
 *                      name
 *                      time
 * Users
 *      *User ID*
 *                  name
 *                  uid
 *                  status delete
 *                  email add
 *                  firstQuestion
 *                  firstAnswer
 *                  secondQuestion delete
 *                  secondAnswer delete
 * LostPost
 *          *Post ID*
 *                      name
 *                      uid
 *                      category
 *                      tags
 *                      date
 *                      image ????????????????
 * SellPost
 *          *Post ID*
 *                      name
 *                      uid
 *                      category
 *                      tags
 *                      price
 *                      image ???????????????????
 */

public class DatabaseHelper {
    //Users
    public static String getUID() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static String getName(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("name").toString();
    }

    public static String getStatus(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("status").toString();
    }

    public static String getFQ(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("firstQuestion").toString();
    }

    public static String getFA(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("firstAnswer").toString();
    }

    public static String getSQ(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("secondQuestion").toString();
    }

    public static String getSA(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("secondAnswer").toString();
    }


    //SellPost
    public static String getSellPostUID(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("uid").toString();
    }

    public static String[] getSellPostTags(String postID) {
        String s = FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("tags").toString();
        // TODO add the post's name to the tags also
        return s.split("\\W+");
    }


    //LostPost
    public static String getLostPostUID(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("uid").toString();
    }

    public static String[] getLostPostTags(String postID) {
        String s = FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("tags").toString();
        return s.split("\\W+");
    }

    public static String getLostPostDate(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("date").toString();
    }

    public static String getLostPostName(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("name").toString();
    }

    public static String getLostPostCategory(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("category").toString();
    }

    public static String initialiseLostPost() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("LostPost")
                .push();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("name", "");
        properties.put("uid", "");
        properties.put("category", "");
        properties.put("tags", "");
        properties.put("date", "");
        ref.setValue(properties);
    }

    public static void setLostPostName(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("name").setValue(data);
    }

    public static void setLostPostUID(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("uid").setValue(data);
    }

    public static void setLostPostCategory(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("category").setValue(data);
    }

    public static void setLostPostTags(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("tags").setValue(data);
    }

    public static void setLostPostDate(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .child(postID).child("date").setValue(data);
    }

    private static ArrayList<String> lostPosts;

    public static ArrayList<String> getAllLostPosts() {
        FirebaseDatabase.getInstance().getReference().child("LostPost")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        lostPosts = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            lostPosts.add((String) snapshot.getValue());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

//    public static ???? getLostPostImage( String postID ) {
//        return FirebaseDatabase.getInstance().getReference().child("LostPost")
//                .child( postID).child("Date").toString();
//    }
}
