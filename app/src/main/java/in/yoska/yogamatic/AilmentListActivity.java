package in.yoska.yogamatic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;
import in.yoska.yogamatic.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


public class AilmentListActivity<bmivali> extends AppCompatActivity {

    //    for drawer start
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton yogaasan;
    TextView dashdisplay,bmi;
//    for drawer end

    ArrayList<YogData> filteredExcelData = new ArrayList<YogData>();
    private ArrayList<YogData> importedExcelData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_list);


        // read data from the excel sheet
        fetchExcelSheetData();

        //get data from the preferance
        getUserData();

        dashdisplay = findViewById(R.id.textview_first);
        bmi = findViewById(R.id.bmi);

        float weight = Float.parseFloat(String.valueOf(getUserWeight()));
        float height = Float.parseFloat(String.valueOf(getUserHeight()))/100;
        float bmiValue = BMICalculate(weight,height);


        bmi.setText("Your BMI Is " +String.valueOf(new DecimalFormat("0.00").format(bmiValue)));

//        bmi.setText(String.valueOf(BMI = Math.floor(BMI * 100 / 100)));


        content();
        dashdisplay.setText("Hello, " + getUserName() );
        bmi.setText("Your BMI Is " +String.valueOf(new DecimalFormat("0.00").format(bmiValue)));


        yogaasan = (ImageButton) findViewById(R.id.button_asana);

        yogaasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AilmentListActivity.this,YTRecycler.class);
                startActivity(intent);
            }
        });




        Bundle extras = getIntent().getExtras();
        if(extras != null){
            filteredExcelData = extras.<YogData>getParcelableArrayList("FILTERED_DATA");
        }
//        here is a title for drawer
        setTitle("");

        ImageButton buttonDiet = (ImageButton)findViewById(R.id.button_diet);
        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(0);
            }
        });

        ImageButton buttonRemedy = (ImageButton)findViewById(R.id.button_remedies);
        buttonRemedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(1);
            }
        });

        ImageButton buttonMudra = (ImageButton)findViewById(R.id.button_mudras);
        buttonMudra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedButton(2);
            }
        });

//        ImageButton buttonAsana = (ImageButton)findViewById(R.id.button_asana);
//        buttonAsana.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selectedButton(3);
//            }
//        });


        //        start drawer

//        here start drawer

        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.bmi:
                        Intent intent1 = new Intent(AilmentListActivity.this,bmiActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        Intent intent2 = new Intent(AilmentListActivity.this, LoginActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.viewprofile:
                        Intent intent3 = new Intent(AilmentListActivity.this, ProfileAcivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_aboutus:
                        Intent intent4 = new Intent(AilmentListActivity.this, AboutUsActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.nav_contact:
                        Intent intent5 = new Intent(AilmentListActivity.this, SupportActivity.class);
                        startActivity(intent5);
                        // it is edited with support page
                        break;
                    case R.id.action_settings:
                        Intent intent6 = new Intent(AilmentListActivity.this, SettingActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.progressreport:
                        Intent intent7 = new Intent(AilmentListActivity.this, ProgressReport.class);
                        startActivity(intent7);
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
//                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareBody =  " " +" ";
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

//    here start refresher data
public void content(){





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
//    here end refresher data




    private void getUserData() {

        final UserObject userData = (UserObject) getApplicationContext();

        userData.fetchData(this);

    }

    private void fetchExcelSheetData() {
        // working model
        ReadExcelSheet readExcelSheet = new ReadExcelSheet();
        readExcelSheet.setFileName("yogmatic_data.xls");

        try{
            importedExcelData = readExcelSheet.readSheet(AilmentListActivity.this);

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

    public float BMICalculate(float weight, float height){
        return weight / (height * height);
    }




    //    public String getSelectedAilment(){
//        final UserObject userData = (UserObject) getApplicationContext();
//        return userData.getAilment();
//    }


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

//    public void display(View view){
//        TextView greetText = (TextView)view.findViewById(R.id.textview_first);
//        MainActivity yourActivity = (MainActivity) getActivity();
//        greetText.setText(yourActivity.getUserName());
//
//    }


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