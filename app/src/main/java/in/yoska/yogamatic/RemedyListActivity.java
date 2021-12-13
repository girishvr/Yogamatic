package in.yoska.yogamatic;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import in.yoska.yogamatic.custom.CustomListView;
import in.yoska.yogamatic.data.model.YogData;

public class RemedyListActivity extends AppCompatActivity {

    ListView listRemedies;
    ArrayList<YogData> fineFilteredData = new ArrayList<YogData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedy_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            fineFilteredData = extras.<YogData>getParcelableArrayList("FINE_FILTERED_DATA");
        }


        listRemedies = (ListView) findViewById(R.id.list_view_remedy);

        CustomListView listViewAdapter = new CustomListView(this,fineFilteredData);
        listRemedies.setAdapter(listViewAdapter);

    }
}