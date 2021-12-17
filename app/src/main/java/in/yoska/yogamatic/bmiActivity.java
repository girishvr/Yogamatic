package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class bmiActivity extends AppCompatActivity {


    TextView bmicalender;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);




//        String stweg = edweg.getText().toString();
//        String sthei  = edhei.getText().toString();


//        float weight = Float.parseFloat(stweg);
//        float height = Float.parseFloat(sthei)/100;
//        float bmiValue = BMICalculate(weight,height);
//


//        below line is very important for calender
//        bmicalender.setText((interpreteBMI(bmiValue))); - this line is very important




//        txtres.setText("BMI= " + bmiValue);





//        Here Bmi Calculator Start

//        public float BMICalculate(float weight, float height){
//            return weight / (height * height);
//
//        }
//


//        public String interpreteBMI(float bmiValue){
//
//            if (bmiValue < 16){
//                return "Servely Underweight";
//            }else if(bmiValue <18.5){
//                return "Underweight";
//            }else if(bmiValue < 25){
//                return  "Normal";
//            } else if (bmiValue < 30){
//                return "OverWeight";
//            }else{
//                return "obese";
//            }
//        }

//        Here Bmi Calculator End













    }
}