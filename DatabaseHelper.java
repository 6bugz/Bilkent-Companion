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
 *                  email
 *                  question
 *                  answer
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

    public static String getEmail(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("email").toString();
    }

    public static String getQuestion(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("question").toString();
    }

    public static String getAnswer(String uid) {
        return FirebaseDatabase.getInstance().getReference().child("Users").child(uid)
                .child("answer").toString();
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

        return ref.toString();
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
        return lostPosts;
    }

    //SellPost
    public static String getSellPostUID(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("uid").toString();
    }

    public static String[] getSellPostTags(String postID) {
        String s = FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("tags").toString();
        return s.split("\\W+");
    }

    public static String getSellPostDate(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("date").toString();
    }

    public static String getSellPostName(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("name").toString();
    }

    public static String getSellPostCategory(String postID) {
        return FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("category").toString();
    }

    public static String initialiseSellPost() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("SellPost")
                .push();
        HashMap<String, String> properties = new HashMap<>();
        properties.put("name", "");
        properties.put("uid", "");
        properties.put("category", "");
        properties.put("tags", "");
        properties.put("date", "");
        ref.setValue(properties);

        return ref.toString();
    }

    public static void setSellPostName(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("name").setValue(data);
    }

    public static void setSellPostUID(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("uid").setValue(data);
    }

    public static void setSellPostCategory(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("category").setValue(data);
    }

    public static void setSellPostTags(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("tags").setValue(data);
    }

    public static void setSellPostDate(String postID, String data) {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .child(postID).child("date").setValue(data);
    }

    private static ArrayList<String> sellPosts;

    public static ArrayList<String> getAllSellPosts() {
        FirebaseDatabase.getInstance().getReference().child("SellPost")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sellPosts = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            sellPosts.add((String) snapshot.getValue());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        return sellPosts;
    }


//    public static ???? getSellPostImage( String postID ) {
//        return FirebaseDatabase.getInstance().getReference().child("SellPost")
//                .child( postID).child("Date").toString();
//    }
}
