package in.yoska.yogamatic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import in.yoska.yogamatic.ui.login.LoginActivity;
import in.yoska.yogamatic.ui.login.UserObject;

public class MainActivity extends AppCompatActivity {
    boolean isLoggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO: for testing
        isLoggedIn = true;

        //check log in and then load the respective views
        if (isLoggedIn){
            setDashBoardUI();
        }else{
            isLoggedIn = true;
            showLoginSignUp();
        }

        // fetch list
        String [] yogmudra = getResources().getStringArray(R.array.YogMudra);
        String [] remedies = getResources().getStringArray(R.array.Remedies);
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
            case R.id.button_asana:
                btnIndex = 2;
                break;
            case R.id.button_mudras:
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
}