package in.yoska.yogamatic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import in.yoska.yogamatic.custom.CustomListView;

public class AilmentListActivity extends AppCompatActivity {

    ListView listAilments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailment_list);

        // fetch list
        String [] yogmudra = getResources().getStringArray(R.array.Remedies);
        String [] remedies = getResources().getStringArray(R.array.Remedies);
        int [] imageIds = {R.drawable.diet_asthama_fruits,
                R.drawable.diet_cancer_beans,
                R.drawable.diet_stress_avocado,
                R.drawable.diet_asthama_fruits,
                R.drawable.remedies_asthma_adulsa,
                R.drawable.diet_asthama_fruits,
                R.drawable.diet_asthama_nuts,
                R.drawable.diet_stress_avocado,
                R.drawable.diet_asthama_fruits,
                R.drawable.diet_asthama_nuts};


        listAilments = (ListView) findViewById(R.id.listview_ailment);
        CustomListView listViewAdapter = new CustomListView(this,yogmudra,imageIds,remedies);
        listAilments.setAdapter(listViewAdapter);

        listAilments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.e("Clicked ", "Clicked - "+String.valueOf(position));
                Log.e("Clicked ", "Clicked - "+String.valueOf(id));
\
            }
        });

    }
}