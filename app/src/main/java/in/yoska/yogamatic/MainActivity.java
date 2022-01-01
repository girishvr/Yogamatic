package in.yoska.yogamatic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;
import in.yoska.yogamatic.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    boolean isLoggedIn = true;
    ArrayList<YogData> importedExcelData = new ArrayList<YogData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // read data from the excel sheet
        fetchExcelSheetData();

        //get data from the preferance
        getUserData();



//        Intent demo = getIntent();
//        Bundle b = demo.getExtras();
//        boolean addition = b.getBoolean("ADDITION");




            isLoggedIn = checkLoggedIn();

        //check log in and then load the respective views
        if (isLoggedIn){
            setDashBoardUI();
        }
        else {
            isLoggedIn = true;
            showLoginSignUp();
        }
    }


    public boolean checkLoggedIn(){
        final UserObject userData = (UserObject) getApplicationContext();
        return  userData.getLoggedIn(this);
    }

    public void getUserData(){
        final UserObject userData = (UserObject) getApplicationContext();
        userData.fetchData(this);
    }

    public void logout(View view){
        isLoggedIn = false;
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setLoggedIn(isLoggedIn);
        userData.saveData(this);
//        userData.clearData(this);
        showLoginSignUp();
        setDashBoardUI();

    }

    void showLoginSignUp(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    void setDashBoardUI(){
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void didSelectAilment(String selectedAilment){
        //set global data first
        setSelectedAilment(selectedAilment);

        ArrayList<YogData> filteredData = new ArrayList<YogData>();
        filteredData = getFilteredExcelData(selectedAilment);
        Intent ailmentIntent = new Intent(this, AilmentListActivity.class);
        ailmentIntent.putParcelableArrayListExtra("FILTERED_DATA", (ArrayList<? extends Parcelable>) filteredData);
        startActivity(ailmentIntent);
    }

    public String getSelectedAilment(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getAilment();
    }

    public void setSelectedAilment(String ailment){
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setAilment(ailment);
    }

    public String getUserName(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getName();
    }

    public float getUserWeight(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getWeight();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setSecondFragmentTextView(TextView tv){
        final UserObject userData = (UserObject) getApplicationContext();
        
        int btnIndex = userData.getSelectedButtonIndex();
        
        String [] buttonLabelArray = {"Diet", "Remedies","Yog Asana", "Yog Mudra"};
      
        tv.setText(buttonLabelArray[btnIndex]);

    }

    public void fetchExcelSheetData(){
        // working model
        ReadExcelSheet readExcelSheet = new ReadExcelSheet();
        readExcelSheet.setFileName("yogmatic_data.xls");
        try{
            importedExcelData = readExcelSheet.readSheet(MainActivity.this);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList<YogData> getFilteredExcelData(String ailment){
        ArrayList<YogData> filteredData = new ArrayList<YogData>();
        for(YogData object : importedExcelData){
            String objAilment = object.getAilment();
            if(objAilment.equals(ailment)){
                filteredData.add(object);
            }
        }
        return filteredData;
    }
}