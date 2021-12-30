package in.yoska.yogamatic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class SupportActivity extends AppCompatActivity {
    TextView supportLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        supportLink = findViewById(R.id.supportlink);
        supportLink.setText(Html.fromHtml("<a href=\"mailto:yogaomatic3@gmail.com\">Contact Us</a>"));
        supportLink.setMovementMethod(LinkMovementMethod.getInstance());

    }
}