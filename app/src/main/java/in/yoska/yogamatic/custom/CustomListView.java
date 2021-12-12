package in.yoska.yogamatic.custom;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import in.yoska.yogamatic.R;

public class CustomListView extends ArrayAdapter<String> {
    private String[] ailmentName;
    private int[] imageName;
    private String[] descriptionText;

    private Activity context;
    public CustomListView(Activity context, String[] ailmentName, int[] imageName, String[] descriptionText) {
        super(context, R.layout.layout_listview, ailmentName);
        this.context = context;
        this.ailmentName = ailmentName;
        this.imageName = imageName;
        this.descriptionText = descriptionText;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View conView = convertView;
        ViewHolder viewHolder = null;
        if(conView==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            conView = layoutInflater.inflate(R.layout.layout_listview, null, true);
            viewHolder = new ViewHolder(conView);
            conView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) conView.getTag();
        }

        viewHolder.textAilName.setText(ailmentName[position]);
//        viewHolder.imageVw.setImageResource(imageName[position]);
        // with picasso and resource image
//        Picasso.with(context).load(R.drawable.drawableName).into(imageView);
        //Picasso.get().load(uri_).transform(new RoundedCornersTransformation(10,10))

        // picasso image load youttube thumbnail
        String videoUrl = "https://www.youtube.com/watch?v=zpxHe8NxLmI";
        Uri uri = Uri.parse(videoUrl);
        String videoID = uri.getQueryParameter("v");
        String url = "http://img.youtube.com/vi/" + videoID + "/0.jpg";

        final Uri uri_ = Uri.parse(url);
        // rounded corners
        Picasso.get().load(uri_).transform(new RoundedCornersTransformation(10,10)).into(viewHolder.imageVw);

        viewHolder.ailDescription.setText(descriptionText[position]);

        return conView;
    }

    class ViewHolder{
        TextView textAilName;
        ImageView imageVw;
        TextView ailDescription;
        ViewHolder(View view){
            textAilName = (TextView) view.findViewById(R.id.text_view_name);
            imageVw = (ImageView) view.findViewById(R.id.image_view);
            ailDescription = (TextView) view.findViewById(R.id.text_description);
        }
    }

}
