package in.yoska.yogamatic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

import in.yoska.yogamatic.custom.CustomSpinner;

public class FirstFragment extends Fragment{


    CustomSpinner spinner;
    String[] ailmentsList;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        displayGreeting(view);
//        displayBMI(view);
        setUpSpinner(view);
        // Inflate the layout for this fragment
        return view;
    }


    void setUpSpinner(View view){

        ailmentsList = getResources().getStringArray(R.array.Ailments);

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_selected, ailmentsList);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner = (CustomSpinner) view.findViewById(R.id.spinner);

        spinner.setAdapter(adapter);
        spinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            public void onSpinnerOpened() {
                spinner.setSelected(true);
            }
            public void onSpinnerClosed() {
                spinner.setSelected(false);
            }
        });

        setSelectedAilment();

    }
    public void displayGreeting(View view){
        TextView greetText = (TextView)view.findViewById(R.id.textview_first);
        MainActivity yourActivity = (MainActivity) getActivity();
        greetText.setText("Hello, "+yourActivity.getUserName());

    }


//    public void displayBMI(View view){
//        MainActivity yourActivity = (MainActivity) getActivity();
//
//        TextView bmiText = (TextView)view.findViewById(R.id.textview_bmi);
//        //get weight in kg
//        double weight = yourActivity.getUserWeight();
//        //get height in meters
//        double height = yourActivity.getUserHeight();
//        double BMI = weight / (height * height);
//        BMI = Math.floor(BMI * 100) / 100;
//        bmiText.setText("Your BMI is - "+String.valueOf(BMI));
//
//    }

    public void setSelectedAilment(){
        MainActivity yourActivity = (MainActivity) getActivity();
        String selectedAilment = yourActivity.getSelectedAilment();
        int position = Arrays.asList(ailmentsList).indexOf(selectedAilment);
        spinner.setSelection(position);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity yourActivity = (MainActivity) getActivity();

        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = ailmentsList[spinner.getSelectedItemPosition()];
                yourActivity.didSelectAilment(selectedItem);

//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });
    }

}