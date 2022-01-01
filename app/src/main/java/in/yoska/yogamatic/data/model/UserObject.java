package in.yoska.yogamatic.data.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserObject extends Application {
    private String name;
    private String email;
    private String password;
    private String date_of_birth;
    private float weight;
    private float height;
    private String ailment;
    private int selectedButtonIndex;
    private boolean isLoggedIn;


    public UserObject(){

//        if(this.name==null){
//            String nm = "";//preferences.getString("username", "");
//            this.name = nm;
//        }
//       if(this.password == null){
//           String pw = "";//preferences.getString("password", "");
//           this.password = pw;
//       }
//       if(this.ailment==null) {
//           String pw = "Eyesight";//preferences.getString("password", "Eyesight");
//           this.ailment = pw;
//       }
    }

//    public void clearData(Context context) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        preferences.edit().remove("username").commit();
//        preferences.edit().remove("password").commit();
//        preferences.edit().remove("password").commit();
//    }

    public void saveData(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("username", this.name).commit();
        preferences.edit().putString("password", this.password).commit();

        preferences.edit().putFloat("height", this.height).commit();
        preferences.edit().putFloat("weight", this.weight).commit();

        preferences.edit().putBoolean("isLoggedIn", this.isLoggedIn).commit();

        preferences.edit().putString("ailment", this.ailment).commit();

    }

    public void fetchData(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (this.name == null) {
            this.name = preferences.getString("username", "");
        }

        if (this.password == null) {
            this.password = preferences.getString("password", "");
        }

        if (this.ailment == null) {
            this.ailment = preferences.getString("ailment", "Eyesight");
        }
        // new added for email and dob
        if (this.email == null){
            this.email = preferences.getString("email","");
        }
        if (this.date_of_birth == null){
            this.date_of_birth = preferences.getString("date_of_birth","");
        }
//        end added for email and dob

        if (this.weight == 0) {
            this.weight = preferences.getFloat("weight", 0);
        }
        if (this.height == 0) {
            this.height = preferences.getFloat("height", 0);
        }

        if (this.isLoggedIn == false) {
            this.isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    public void setSelectedButtonIndex(int selectedButtonIndex) {
        this.selectedButtonIndex = selectedButtonIndex;
    }

    public int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    public boolean getLoggedIn(Context context) {

        if(this.isLoggedIn == false) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getBoolean("isLoggedIn", false);
        }

        return false;
    }
}
