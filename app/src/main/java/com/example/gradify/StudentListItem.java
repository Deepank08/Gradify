package com.example.gradify;

/**
 * Written by team Gradify.
 */

public class StudentListItem {

    @com.google.gson.annotations.SerializedName("rollno")
    private String rollNo;

    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    @com.google.gson.annotations.SerializedName("name")
    private String name;

    @com.google.gson.annotations.SerializedName("fname")
    private String fname;

    @com.google.gson.annotations.SerializedName("coursecode")
    private String coursecode;

    @com.google.gson.annotations.SerializedName("session")
    private String session;

    @com.google.gson.annotations.SerializedName("branchcode")
    private String branchcode;

    @com.google.gson.annotations.SerializedName("email")
    private String email;


    /**
     * Item constructor
     */
    public StudentListItem() {

    }

    public String getRollNo(){
        return rollNo;
    }

    public String getID(){
        return mId;
    }

    public String getName() {
        return name;
    }

    public String getFname(){
        return fname;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getSesion(){
        return session;
    }

    public String getBranchCode(){
        return branchcode;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof StudentListItem && ((StudentListItem) o).mId == mId;
    }
}
