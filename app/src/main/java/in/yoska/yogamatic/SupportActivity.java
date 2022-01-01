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
        supportLink.setText(Html.fromHtml("<a style=\"color:black\" href=\"mailto:yogaomatic3@gmail.com\" >yogaomatic3@gmail.com</a>"));
        supportLink.setMovementMethod(LinkMovementMethod.getInstance());

    }
}