package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import in.yoska.yogamatic.custom.ReadExcelSheet;
import in.yoska.yogamatic.data.model.UserObject;
import in.yoska.yogamatic.data.model.YogData;

public class bmiActivity extends AppCompatActivity {

    TextView bmiIndi,bmiCal;
    ImageView bmipic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        bmiIndi = (TextView) findViewById(R.id.bmiindicator);
        bmipic = findViewById(R.id.bmipic);

        bmiCal = (TextView) findViewById(R.id.bmicalender);
        float weight = Float.parseFloat(String.valueOf(getUserWeight()));
        float height = Float.parseFloat(String.valueOf(getUserHeight()))/100;
        float bmiValue = BMICalculate(weight,height);

        bmiIndi.setText("Your BMI Is " +String.valueOf(new DecimalFormat("0.00").format(bmiValue)));

        bmiCal.setText((interpreteBMI(bmiValue)));
        

        if (interpreteBMI(bmiValue).equals("Underweight")){
            bmipic.setImageResource(R.drawable.underweight);
        }else if (interpreteBMI(bmiValue).equals("Servely Underweight")){
            bmipic.setImageResource(R.drawable.underweight);
        }else if (interpreteBMI(bmiValue).equals("Normal")){
            bmipic.setImageResource(R.drawable.healthyweight);
        }else if (interpreteBMI(bmiValue).equals("OverWeight")){
            bmipic.setImageResource(R.drawable.overweight);
        }else if (interpreteBMI(bmiValue).equals("obese type1")){
            bmipic.setImageResource(R.drawable.obesetype1);
        }else if (interpreteBMI(bmiValue).equals("obese type2")){
            bmipic.setImageResource(R.drawable.obesetype2);
        }else {
            bmipic.setImageResource(R.drawable.obesetype3);
        }


        // read data from the excel sheet
        fetchExcelSheetData();

        //get data from the preferance
        getUserData();



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
            ArrayList<YogData> importedExcelData = readExcelSheet.readSheet(bmiActivity.this);
        }catch (IOException e){ 
            e.printStackTrace();
        }
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


    public String interpreteBMI(float bmiValue){

        if (bmiValue < 16){
            return "Servely Underweight";
        }else if(bmiValue <18.5){
            return "Underweight";
        }else if(bmiValue < 25){
            return  "Normal";
        } else if (bmiValue < 30){
            return "OverWeight";
        }else if (bmiValue < 35){
            return "obese type1";
        } else if (bmiValue < 40){
            return "obese type2";
        } else {
            return "obese type3";
        }
    }


}