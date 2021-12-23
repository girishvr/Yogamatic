package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;

public class ProfileAcivity extends AppCompatActivity {


    TextView username,useremail,dob,userweight,userheight,userailment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acivity);

        // read data from the excel sheet
        fetchExcelSheetData();

        //get data from the preferance
        getUserData();


        username = findViewById(R.id.user);
        useremail = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        userweight = findViewById(R.id.weight);
        userheight = findViewById(R.id.height);
        userailment = findViewById(R.id.ailment);

        username.setText(getUserName());
        useremail.setText(getUserEmail());
        dob.setText(getUserDob());
        userweight.setText(String.valueOf(getUserWeight()) + " KG");
        userheight.setText(String.valueOf(getUserHeight()) + " CM");
        userailment.setText(getSelectedAilment());










    }

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


}