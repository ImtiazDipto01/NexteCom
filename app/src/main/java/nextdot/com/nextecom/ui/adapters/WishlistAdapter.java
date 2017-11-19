package nextdot.com.nextecom.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nextdot.com.nextecom.R;

/**
 * Created by sakib on 3/22/2017.
 */

public class WishlistAdapter extends ArrayAdapter {

    List list = new ArrayList() ;
    public WishlistAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class LayoutHandeler{
        TextView pro_name, pro_price ;
        ImageView pro_img ;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object) ;

    }

    @Override
    public int getCount() {
        return list.size() ;
    }

    @Nullable
    /*@Override
    public Object getItem(int position) {
        return list.get(position);
    }*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView ;
        LayoutHandeler layoutHandeler ;

        if(view == null){

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            view = layoutInflater.inflate(R.layout.wishlist_item, parent, false) ;
            layoutHandeler = new LayoutHandeler() ;
            layoutHandeler.pro_name = (TextView) view.findViewById(R.id.wish_product_name) ;
            layoutHandeler.pro_price = (TextView) view.findViewById(R.id.wish_product_price) ;
            layoutHandeler.pro_img = (ImageView) view.findViewById(R.id.wish_product_image) ;
            view.setTag(layoutHandeler);

        }
        else{

            layoutHandeler = (LayoutHandeler) view.getTag() ;
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position) ;
        layoutHandeler.pro_name.setText(dataProvider.getName());
        layoutHandeler.pro_price.setText(dataProvider.getPrice());
        Picasso.with(this.getContext().getApplicationContext()).load(dataProvider.getProduct_image()).into(layoutHandeler.pro_img);

        return view;
    }
}
