package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;

public class EditProfile extends AppCompatActivity {
//    boolean isLoggedIn = true;

    EditText wtt,htt,dtb;

    TextView username,useremail,userweight,userheight,userailment;
    Button bckbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        // read data from the excel sheet
//        fetchExcelSheetData();


        //get data from the preferance
        getUserData();

        wtt = findViewById(R.id.wtt);
        htt = findViewById(R.id.htt);
        bckbtn = findViewById(R.id.bckbtn);

        username = findViewById(R.id.user);
//        useremail = findViewById(R.id.email);
//        dtb = findViewById(R.id.dtb);
        userweight = findViewById(R.id.weight);
        userheight = findViewById(R.id.height);
        userailment = findViewById(R.id.ailment);

        username.setText(getUserName());
//        useremail.setText(getUserEmail());
//        dtb.setText(getUserDob());
        wtt.setText(String.valueOf(getUserWeight()));
        htt.setText(String.valueOf(getUserHeight()));
        userailment.setText(getSelectedAilment());



        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float wt = Float.parseFloat(wtt.getText().toString());
                float ht = Float.parseFloat(htt.getText().toString());
//                String ail = (userailment.getText().toString());
//                String db = (dtb.getText().toString());


                final UserObject userData = (UserObject) getApplicationContext();
                userData.setHeight(ht);
                userData.setWeight(wt);
//                userData.setDate_of_birth(db);



//                Intent back = new Intent(EditProfile.this,AilmentListActivity.class);
                Toast.makeText(getApplicationContext(), "Your Profile Is updated", Toast.LENGTH_SHORT).show();
//                startActivity(back);


            }
        });

    }


    private void getUserData() {
        final UserObject userData = (UserObject) getApplicationContext();
        userData.fetchData(this);
    }

//    private void fetchExcelSheetData() {
//        // working model
//        ReadExcelSheet readExcelSheet = new ReadExcelSheet();
//        readExcelSheet.setFileName("yogmatic_data.xls");
//        try{
//            ArrayList<YogData> importedExcelData = readExcelSheet.readSheet(EditProfile.this);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

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