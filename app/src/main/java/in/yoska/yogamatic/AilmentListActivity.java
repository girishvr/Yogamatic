package in.yoska.yogamatic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import in.yoska.yogamatic.custom.AilmentListView;
import in.yoska.yogamatic.data.model.YogData;


public class AilmentListActivity extends AppCompatActivity {

    ListView listAilments;
    String FILE_PATH = "https://www.youtube.com/watch?v=zpxHe8NxLmI";
    ArrayList<YogData> filteredExcelData = new ArrayList<YogData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null) filteredExcelData = extras.<YogData>getParcelableArrayList("FILTERED_DATA");
        // fetch list
        String [] ailmentsList = getResources().getStringArray(R.array.Ailments);

        listAilments = (ListView) findViewById(R.id.listview_ailment);

        AilmentListView listViewAdapter = new AilmentListView(this,ailmentsList);
        listAilments.setAdapter(listViewAdapter);

        listAilments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.e("Clicked ", "Clicked - "+String.valueOf(position));
                Log.e("Clicked ", "Clicked - "+String.valueOf(id));

//                watchYoutubeVideo(AilmentListActivity.this,"zpxHe8NxLmI");
////                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(FILE_PATH)));
//                Log.i("Video", "Video Playing....");

            }


        });

    }
    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }


}