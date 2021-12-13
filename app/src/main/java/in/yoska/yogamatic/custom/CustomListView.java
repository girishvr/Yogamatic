package in.yoska.yogamatic.custom;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

import in.yoska.yogamatic.R;
import in.yoska.yogamatic.data.model.YogData;

public class CustomListView extends ArrayAdapter<YogData> {

    ArrayList<YogData> remediesData = new ArrayList<YogData>();
    private Activity context;
    public CustomListView(Activity context, ArrayList<YogData> remediesData) {
        super(context, R.layout.layout_listview, remediesData);
        this.context = context;
        this.remediesData = remediesData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        YogData remedyObject = remediesData.get(position);
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

        viewHolder.textAilName.setText(remedyObject.getAilment());
        viewHolder.ailDescription.setText(remedyObject.getDescription());

        if(remedyObject.getType().equals("video")){
            //load thumbnail
            // picasso image load youttube thumbnail
            String videoUrl = remedyObject.getImageName();//"https://www.youtube.com/watch?v=zpxHe8NxLmI";
            Uri uri = Uri.parse(videoUrl);
            String videoID = uri.getQueryParameter("v");
            String url = "http://img.youtube.com/vi/" + videoID + "/0.jpg";

            final Uri uri_ = Uri.parse(url);
            // rounded corners
            Picasso.get().load(uri_).transform(new RoundedCornersTransformation(10,10)).into(viewHolder.imageVw);

        }else{
            //load image
            int imageResourceId =  getResourceId(remedyObject.getImageName(), R.drawable.class);
            viewHolder.imageVw.setImageResource(imageResourceId);
        }


        // with picasso and resource image
//        Picasso.with(context).load(R.drawable.drawableName).into(imageView);
        //Picasso.get().load(uri_).transform(new RoundedCornersTransformation(10,10))



        return conView;
    }

    public static int getResourceId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    class ViewHolder{
        TextView textAilName;
        ImageView imageVw;
        TextView ailDescription;
        ViewHolder(@NotNull View view){
            textAilName = (TextView) view.findViewById(R.id.text_view_name);
            imageVw = (ImageView) view.findViewById(R.id.image_view);
            ailDescription = (TextView) view.findViewById(R.id.text_description);
        }
    }

}
