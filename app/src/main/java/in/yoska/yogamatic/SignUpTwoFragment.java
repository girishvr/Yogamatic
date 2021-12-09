package in.yoska.yogamatic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import in.yoska.yogamatic.ui.login.SignUpActivity;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_sign_up_two, container, false);
        Button signUpBtn = (Button)view.findViewById(R.id.btn_register);

        signUpBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        SignUpActivity yourActivity = (SignUpActivity) getActivity();
        yourActivity.registrationComplete();
    }
}