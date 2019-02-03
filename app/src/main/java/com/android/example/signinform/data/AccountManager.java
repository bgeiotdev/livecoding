package com.android.example.signinform.data;

import java.util.HashMap;

public class AccountManager {

    private static AccountManager instance;
    private HashMap<String,User> myUsers;

    private  AccountManager() {
        this.myUsers = new HashMap<String,User>();
    }

    public static AccountManager getInstance(){
        if (instance==null){
            instance = new AccountManager();
        }
        return instance;
    }

    public void addUser(User user){

        myUsers.put(user.getEmail(),user);
    }


    public User getUser(String userEmail){
       return  myUsers.get(userEmail);

    }
}
