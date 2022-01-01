package in.yoska.yogamatic;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.sql.Date;

import in.yoska.yogamatic.ui.login.SignUpActivity;

//import android.icu.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpTwoFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText etDob, etHeight, etWeight;

    public SignUpTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpTwoFragment newInstance(String param1, String param2) {
        SignUpTwoFragment fragment = new SignUpTwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    View view;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_sign_up_two, container, false);
        etDob = (EditText) view.findViewById(R.id.et_dob);
        etHeight = (EditText)view.findViewById(R.id.et_height);
        etWeight = (EditText)view.findViewById(R.id.et_weight);

        Button signUpBtn = (Button)view.findViewById(R.id.btn_register);

        String [] remedies = getResources().getStringArray(R.array.Ailments);
        ArrayAdapter <String> adaptor = new ArrayAdapter<String>(view.getContext(), android.R.layout.select_dialog_item, remedies);

        signUpBtn.setOnClickListener(this);

        setDateTextField();


        return view;

    }

    private void setDateTextField() {
        etDob.setOnClickListener(this);
        etDob.setInputType(InputType.TYPE_NULL);
        etDob.requestFocus();
    }

    public void datePickerDialogShow(){
        SignUpActivity yourActivity = (SignUpActivity) getActivity();

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select you date of birth");
        MaterialDatePicker<Long> picker = builder.build();
        picker.show(yourActivity.getSupportFragmentManager(), picker.toString());

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override public void onPositiveButtonClick(Long selection) {
                String selectedDate = String.valueOf(selection);
                Date date = new Date(selection);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    selectedDate = formatter.format(date);
                }

                etDob.setText(selectedDate);
            }
        });


    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.et_dob) {
            datePickerDialogShow();
        } else if(v.getId() == R.id.btn_register){
            getAllTextViewData();
        }

    }


    public void getAllTextViewData(){

        if(etDob.getText().toString().length()==0 || etWeight.getText().toString().length()==0
        || etHeight.getText().toString().length()==0 ){
            Activity activity = getActivity();
            Toast.makeText(activity ,getString(R.string.signup_error), Toast.LENGTH_LONG).show();
        }else{
            String dob = etDob.getText().toString();
            float wt = Float.valueOf(etWeight.getText().toString());
            float ht = Float.valueOf(etHeight.getText().toString());
            String ailmnt = "";
            // save and go back to Log In
            SignUpActivity yourActivity = (SignUpActivity) getActivity();
            yourActivity.setActivitySecondData(dob,wt,ht,ailmnt);

        }

    }

}