package in.yoska.yogamatic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import in.yoska.yogamatic.custom.CustomListView;
import in.yoska.yogamatic.data.model.YogData;

public class RemedyListActivity extends AppCompatActivity {

    ListView listRemedies;
    ArrayList<YogData> fineFilteredData = new ArrayList<YogData>();
    //String FILE_PATH = "https://www.youtube.com/watch?v=zpxHe8NxLmI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedy_list);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            fineFilteredData = extras.<YogData>getParcelableArrayList("FINE_FILTERED_DATA");
            setTitle(extras.getString("SELECTED_OPTION"));
        }

        listRemedies = (ListView) findViewById(R.id.list_view_remedy);
        CustomListView listViewAdapter = new CustomListView(this,fineFilteredData);
        listRemedies.setAdapter(listViewAdapter);

        listRemedies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.e("Clicked ", "Clicked - "+String.valueOf(position));
                loadTheRespectiveVideo(position);

            }


        });
    }
    public void loadTheRespectiveVideo(int index){

        YogData dataObject = fineFilteredData.get(index);
        String fileLink = dataObject.getImageName();
        if (dataObject.getType().equals("video")){
//            watchYoutubeVideo(RemedyListActivity.this,"zpxHe8NxLmI");
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fileLink)));
            Log.i("Video", "Video Playing....");
        }else{
            //TODO: load image zoomed
        }

    }
}