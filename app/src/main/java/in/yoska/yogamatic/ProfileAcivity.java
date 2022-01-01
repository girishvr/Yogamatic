package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;
import in.yoska.yogamatic.ui.login.SignUpActivity;

public class ProfileAcivity extends AppCompatActivity {


    TextView username,useremail,dob,userweight,userheight,userailment;

    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acivity);

        // read data from the excel sheet
        fetchExcelSheetData();

        //get data from the preferance
        getUserData();

        btn = findViewById(R.id.editbtn);

        username = findViewById(R.id.user);
//        useremail = findViewById(R.id.email);
//        dob = findViewById(R.id.dob);
        userweight = findViewById(R.id.weight);
        userheight = findViewById(R.id.height);
        userailment = findViewById(R.id.ailment);

        username.setText(getUserName());
//        useremail.setText(getUserEmail());
//        dob.setText(getUserDob());
//        userweight.setText(String.valueOf(getUserWeight()) );
//        userheight.setText(String.valueOf(getUserHeight()) );
        userailment.setText(getSelectedAilment());

        content();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(ProfileAcivity.this,EditProfile.class);
                startActivity(edit);
            }
        });







    }
//    here refresh data start
public void content(){
    userweight.setText(String.valueOf(getUserWeight()) );
    userheight.setText(String.valueOf(getUserHeight()) );

    refresh(1000);
}

    private void refresh(int millisecond){
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };

        handler.postDelayed(runnable,millisecond);

    }


//    here refresh data end




    private void getUserData() {
        final UserObject userData = (UserObject) getApplicationContext();
        userData.fetchData(this);
    }

    private void fetchExcelSheetData() {
        // working model
        ReadExcelSheet readExcelSheet = new ReadExcelSheet();
        readExcelSheet.setFileName("yogmatic_data.xls");
        try{
            ArrayList<YogData> importedExcelData = readExcelSheet.readSheet(ProfileAcivity.this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public String getUserName(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getName();
    }
    public float getUserWeight(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getWeight();

    }
    public float getUserHeight(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getHeight();
    }
    public String getSelectedAilment(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getAilment();
    }
    public String getUserEmail(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getEmail();
    }
    public String getUserDob(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getDate_of_birth();
    }


//    public void setActivitySecondData(float wt1, float ht1) {
//        final UserObject userData = (UserObject) getApplicationContext();
//        userData.setHeight(ht1);
//        userData.setWeight(wt1);
//        registrationComplete();
//    }
//
//    private void registrationComplete() {
//        startActivity(new Intent(ProfileAcivity.this, AilmentListActivity.class));
//    }
}