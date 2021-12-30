package in.yoska.yogamatic.ui.login;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import in.yoska.yogamatic.AboutUsActivity;
import in.yoska.yogamatic.AilmentListActivity;
import in.yoska.yogamatic.MainActivity;
import in.yoska.yogamatic.R;
import in.yoska.yogamatic.data.model.UserObject;

public class LoginActivity extends AppCompatActivity {

//    boolean isLoggedIn = true;
    private LoginViewModel loginViewModel;
    Button signUpButton;
    EditText usernameEditText;
    EditText passwordEditText;
    TextView signuplater;
//    private boolean addition=false;
//
//    public static final String ADDITION = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        signuplater = (TextView) findViewById(R.id.signuplater);


        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        signUpButton = (Button)findViewById(R.id.signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
//                startActivity(signUpIntent);
                startActivity(signUpIntent,
                        ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            }
        });


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });



        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }

                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }

                if (checkLoginCredentials()){
                    if (loginResult.getSuccess() != null) {
                        updateUiWithUser(loginResult.getSuccess());
                    }
                    setResult(Activity.RESULT_OK);

                    //Complete and destroy login activity once successful
                    finish();

                }else{
                    showLoginFailed(R.string.login_error);
                    return;
                }

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });





        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkLoginCredentials()){
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }else{
                    //  show credential failed toast
                    setResult(Activity.RESULT_CANCELED);
                    showLoginFailed(R.string.login_error);
                }
            }
        });


//        String demo1 = "demo";


        signuplater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent demo = new Intent(LoginActivity.this, AilmentListActivity.class);
//                demo.putExtra("demo1",demo1);
//                demo.putExtra(ADDITION,addition);
                startActivity(demo);
            }

        });
    }



//    public void logout(View view){
//        isLoggedIn = false;
//        final UserObject userData = (UserObject) getApplicationContext();
//        userData.setLoggedIn(isLoggedIn);
//        userData.saveData(this);
////        userData.clearData(this);
////        showLoginSignUp();
////        setDashBoardUI();
//
//    }





    public boolean checkLoginCredentials(){
        //Get global data
        final UserObject userData = (UserObject) getApplicationContext();

        return ((userData.getName().equals(usernameEditText.getText().toString())
                &(userData.getPassword().equals(passwordEditText.getText().toString()))));

    }

    private void updateUiWithUser(LoggedInUserView model) {

        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }


    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        //do nothing or exit app
//        super.onBackPressed();
    }
}