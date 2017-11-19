package nextdot.com.nextecom.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.Category;

/**
 * Created by Zahan on 9/3/2016.
 */
public class CategoryListAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Category> categories=new ArrayList<>();
    LayoutInflater inflater;

    public CategoryListAdapter(Activity activity, ArrayList<Category> categories){
        this.activity=activity;
        this.categories=categories;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(0);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class Holder{
        ImageView icon;
        TextView name;
    }
    @Override
    public View getView(int positiom, View view, ViewGroup viewGroup) {
        Holder holder=new Holder();
        View convertView=inflater.inflate(R.layout.category_list_item,null);
        holder.icon=(ImageView) convertView.findViewById(R.id.category_icon);
        holder.name=(TextView) convertView.findViewById(R.id.category_name);
        holder.name.setText(categories.get(positiom).getName());
        return convertView;
    }
}
