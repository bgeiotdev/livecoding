package com.android.example.signinform.data;

public class User {

    private String email;
    private String lastName;
    private String firstname;


    /**
     * Constructeur 3 parametres
     * @param email
     * @param lastName
     * @param firstname
     */
    public User(String email, String lastName, String firstname) {
        this.email = email;
        this.lastName = lastName;
        this.firstname = firstname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



}
