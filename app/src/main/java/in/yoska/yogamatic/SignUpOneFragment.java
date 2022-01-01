package in.yoska.yogamatic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import in.yoska.yogamatic.ui.login.SignUpActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpOneFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText etName,etEmail,etPassword,etConPassword;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpOneFragment newInstance(String param1, String param2) {
        SignUpOneFragment fragment = new SignUpOneFragment();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_sign_up_one, container, false);
        Button continueBtn = (Button)view.findViewById(R.id.btn_continue);
        etName = (EditText)view.findViewById(R.id.et_name);
        etEmail = (EditText)view.findViewById(R.id.et_email);
        etPassword = (EditText)view.findViewById(R.id.et_password);
        etConPassword = (EditText)view.findViewById(R.id.et_repassword);


        continueBtn.setOnClickListener(this);
        return view;

    }


    @Override
    public void onClick(View v) {
        boolean shouldProceed = getAllText(etEmail);
        if(shouldProceed){
            SignUpActivity yourActivity = (SignUpActivity) getActivity();
            yourActivity.setCurrentItem (1, true);
        }
    }





    public boolean getAllText(EditText etEmail){
        String pw1 = etPassword.getText().toString();

        if (pw1.length()<=5){
            showPasswordErrorMsg(true);
            return false;
        }

        String pw2 = etConPassword.getText().toString();

        if(!(pw1.equals(pw2))){
            //if password mis-match
            showPasswordErrorMsg(false);
            return false;
        }



        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

//        String emailInput = etEmail.getText().toString();
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            SignUpActivity yourActivity = (SignUpActivity) getActivity();
            yourActivity.setActivityFirstData(name, email, pw1);
            return true;
        }else {
//            Toast.makeText(this, "Enter Valid Email address", Toast.LENGTH_SHORT).show();
            emailvalidation(true);
            return false;
        }
        // Save the data
    }



    public void emailvalidation(boolean wrongEmail){
        Activity activity = getActivity();
        if (wrongEmail){
            Toast.makeText(activity,"Enter Your Valid Email Address",Toast.LENGTH_LONG).show();
        }else{

        }
    }






    public void showPasswordErrorMsg(boolean shortPassword){
        //show toaster
        Activity activity = getActivity();
        if (shortPassword) {
            Toast.makeText(activity, getString(R.string.password_length_error), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(activity,getString(R.string.password_error), Toast.LENGTH_LONG).show();
        }


    }
}