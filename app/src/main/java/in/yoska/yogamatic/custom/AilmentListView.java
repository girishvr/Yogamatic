package in.yoska.yogamatic.custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import in.yoska.yogamatic.R;

public class AilmentListView extends ArrayAdapter<String> {
    private String[] ailmentName;
    private Activity context;
    public AilmentListView(Activity context, String[] ailmentName) {
        super(context, R.layout.ailment_list_item, ailmentName);
        this.context = context;
        this.ailmentName = ailmentName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View conView = convertView;
        AilmentViewHolder viewHolder = null;
        if(conView==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            conView = layoutInflater.inflate(R.layout.ailment_list_item, null, true);
            viewHolder = new AilmentViewHolder(conView);
            conView.setTag(viewHolder);
        }else{
            viewHolder = (AilmentViewHolder) conView.getTag();
        }

        viewHolder.textAilName.setText(ailmentName[position]);
        return conView;
    }
    class AilmentViewHolder{
        TextView textAilName;
        AilmentViewHolder(View view){
            textAilName = (TextView) view.findViewById(R.id.textViewAilment);
        }
    }
}
