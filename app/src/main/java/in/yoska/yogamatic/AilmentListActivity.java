package in.yoska.yogamatic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;
import in.yoska.yogamatic.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;


public class AilmentListActivity extends AppCompatActivity {

    //    for drawer start
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
//    for drawer end

    ArrayList<YogData> filteredExcelData = new ArrayList<YogData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_list);








        Bundle extras = getIntent().getExtras();
        if(extras != null){
            filteredExcelData = extras.<YogData>getParcelableArrayList("FILTERED_DATA");
        }
        setTitle("SELECT OPTIONS");

        Button buttonDiet = (Button)findViewById(R.id.button_diet);
        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(0);
            }
        });

        Button buttonRemedy = (Button)findViewById(R.id.button_remedies);
        buttonRemedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(1);
            }
        });

        Button buttonMudra = (Button)findViewById(R.id.button_mudras);
        buttonMudra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(2);
            }
        });

        Button buttonAsana = (Button)findViewById(R.id.button_asana);
        buttonAsana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(3);
            }
        });

        //        start drawer

//        here start drawer

        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.progressreport:

                        Intent intent = new Intent(AilmentListActivity.this, ProgressActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.bmi:
                        Intent intent1 = new Intent(AilmentListActivity.this,bmiActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        Intent intent2 = new Intent(AilmentListActivity.this, LoginActivity.class);
                        startActivity(intent2);
                        break;

//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }
//    here end drawer


//        end drawer


        // fetch list
//        ailmentsList = getResources().getStringArray(R.array.Ailments);

//        listAilments = (ListView) findViewById(R.id.listview_ailment);
//        AilmentListView listViewAdapter = new AilmentListView(this,ailmentsList);
//        listAilments.setAdapter(listViewAdapter);
//        listAilments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Log.e("Clicked ", "Clicked - "+String.valueOf(position));
//                loadTheRespectiveActivity(position);
//
//            }
//
//        });






    public void selectedButton(int buttonIndx){
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setSelectedButtonIndex(buttonIndx);
        //load a list view
        loadListView(buttonIndx);
    }
    public void loadListView(int indx){
        ArrayList<YogData> fineFilteredData = new ArrayList<YogData>();
        Intent ailmentIntent = new Intent(this, RemedyListActivity.class);

        switch (indx){
            case 0:
                fineFilteredData = getFineFilteredData("Diet");
                ailmentIntent.putExtra("SELECTED_OPTION","DIET");
                break;
            case 1:
                fineFilteredData = getFineFilteredData("Remedies");
                ailmentIntent.putExtra("SELECTED_OPTION","HOME REMEDIES & TIPS");
                break;
            case 2:
                fineFilteredData = getFineFilteredData("Yogmudras");
                ailmentIntent.putExtra("SELECTED_OPTION","YOG MUDRAS");
                break;
            case 3:
                fineFilteredData = getFineFilteredData("Yogasana");
                ailmentIntent.putExtra("SELECTED_OPTION","YOGASANA");
                break;
            default:
                System.out.println("Default of switch in MainActivity!");
        }

        ailmentIntent.putParcelableArrayListExtra("FINE_FILTERED_DATA", (ArrayList<? extends Parcelable>) fineFilteredData);
        startActivity(ailmentIntent);
    }


//    public void loadTheRespectiveActivity(int index){
//
//        ArrayList<YogData> fineFilteredData = new ArrayList<YogData>();
//        fineFilteredData = getFineFilteredData(ailmentsList[index]);
//
//        Intent remedyIntent = new Intent(AilmentListActivity.this, RemedyListActivity.class);
//        remedyIntent.putParcelableArrayListExtra("FINE_FILTERED_DATA", (ArrayList<? extends Parcelable>) fineFilteredData);
//        startActivity(remedyIntent);
//
//    }
//    public static void watchYoutubeVideo(Context context, String id){
//        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
//        Intent webIntent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("http://www.youtube.com/watch?v=" + id));
//        try {
//            context.startActivity(appIntent);
//        } catch (ActivityNotFoundException ex) {
//            context.startActivity(webIntent);
//        }
//    }

    public ArrayList<YogData> getFineFilteredData(String categoryStr){
        ArrayList<YogData> filteredData = new ArrayList<YogData>();
        for(YogData object : filteredExcelData){
            String objCategory = object.getCategory();
            if(objCategory.equals(categoryStr)){
                filteredData.add(object);
            }
        }
        return filteredData;
    }



}