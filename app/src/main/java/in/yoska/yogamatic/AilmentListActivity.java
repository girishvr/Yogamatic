package in.yoska.yogamatic;

import android.os.Bundle;
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
        int [] imageIds = {R.drawable.fruits_and_veggies,
                R.drawable.carrot,
                R.drawable.fruits_and_veggies,
                R.drawable.bananas_and_milk,
                R.drawable.fruits_and_veggies,
                R.drawable.fish,
                R.drawable.avocado,
                R.drawable.fruits_and_veggies,
                R.drawable.carrots,
                R.drawable.fruits};


        listAilments = (ListView) findViewById(R.id.listview_ailment);
        CustomListView listViewAdapter = new CustomListView(this,yogmudra,imageIds,remedies);
        listAilments.setAdapter(listViewAdapter);
    }
}