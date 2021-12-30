package in.yoska.yogamatic.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import in.yoska.yogamatic.MainActivity;
import in.yoska.yogamatic.R;
import in.yoska.yogamatic.SignUpOneFragment;
import in.yoska.yogamatic.SignUpTwoFragment;
import in.yoska.yogamatic.data.model.UserObject;

public class SignUpActivity extends AppCompatActivity {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new SignUpOneFragment());
        pagerAdapter.addFragmet(new SignUpTwoFragment());
        viewPager.setAdapter(pagerAdapter);

    }



    public void setActivityFirstData(String name, String email, String pass){
        //TODO: save this only on registration complete
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setName(name);
        userData.setEmail(email);
        userData.setPassword(pass);

    }

    public void setActivitySecondData(String dob, float wt, float ht, String ailment){
        //TODO: save this only on registration complete
        final UserObject userData = (UserObject) getApplicationContext();
        userData.setDate_of_birth(dob);
        userData.setHeight(ht);
        userData.setWeight(wt);
        userData.setAilment(ailment);
        //call activity to go to first pafe
        registrationComplete();
    }

    public void registrationComplete () {
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
    }


    public void setCurrentItem (int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }

    public void next_fragment(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }

    public void previous_fragment(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }



    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }

    }

}