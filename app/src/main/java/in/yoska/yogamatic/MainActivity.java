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
    boolean isLoggedIn = false;
    ArrayList<YogData> importedExcelData = new ArrayList<YogData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        fetchExcelSheetData();

        //TODO: for testing
        isLoggedIn = true;

        //check log in and then load the respective views
        if (isLoggedIn){
            setDashBoardUI();
        }else{
            isLoggedIn = true;
            showLoginSignUp();
        }

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


    public void didSelectButton(View view){
        int btnIndex = 0;
        switch (view.getId()){
            case R.id.button_diet:
                btnIndex = 0;
                break;
            case R.id.button_remedies:
                btnIndex = 1;
                break;
            case R.id.button_mudras:
                btnIndex = 2;
                break;
            case R.id.button_asana:
                btnIndex = 3;
                break;

            default:
                btnIndex = 0;
        }

        selectedButton(btnIndex);

    }
    public void selectedButton(int buttonIndx){
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setSelectedButtonIndex(buttonIndx);
        //load a list view
        loadListView(buttonIndx);
    }
    public void loadListView(int indx){
        ArrayList<YogData> filteredData = new ArrayList<YogData>();
        switch (indx){
            case 0:
                filteredData = getFilteredExcelData("Diet");
                break;
            case 1:
                filteredData = getFilteredExcelData("Remedies");
                break;
            case 2:
                filteredData = getFilteredExcelData("Yogmudras");
                break;
            case 3:
                filteredData = getFilteredExcelData("Yogasana");
                break;
            default:
                System.out.println("Default of switch in MainActivity!");
        }

        Intent ailmentIntent = new Intent(this, AilmentListActivity.class);
        ailmentIntent.putParcelableArrayListExtra("FILTERED_DATA", (ArrayList<? extends Parcelable>) filteredData);
        startActivity(ailmentIntent);
    }
    public String getUserName(){
        final UserObject userData = (UserObject) getApplicationContext();
        return userData.getName();
    }
    //TODO: uncomment when real data available
    public float getUserWeight(){
        final UserObject userData = (UserObject) getApplicationContext();
//        return userData.getWeight();
        return (float)100.0;

    }
    public float getUserHeight(){
        final UserObject userData = (UserObject) getApplicationContext();
//        return userData.getHeight();
        return (float)1.6;
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

    public ArrayList<YogData> getFilteredExcelData(String category){
        ArrayList<YogData> filteredData = new ArrayList<YogData>();
        for(YogData object : importedExcelData){
            String objCategory = object.getCategory();
            if(objCategory.equals(category)){
                filteredData.add(object);
            }
        }
        return filteredData;
    }
}