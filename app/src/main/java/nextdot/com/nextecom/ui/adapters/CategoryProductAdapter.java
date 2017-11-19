package nextdot.com.nextecom.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;

/**
 * Created by Zahan on 9/5/2016.
 */
public class CategoryProductAdapter extends BaseAdapter{
    Activity activity;
    ArrayList<HomeProducts> homeProductses=new ArrayList<>();
    LayoutInflater inflater;

    public CategoryProductAdapter(Activity activity, ArrayList<HomeProducts> homeProductses){
        this.activity=activity;
        this.homeProductses=homeProductses;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return homeProductses.size();
    }

    @Override
    public Object getItem(int i) {
        return homeProductses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class Holder{
        ImageView productImage;
        TextView productName,ratingText,salePrice,salePercentage,marketPrice,warrantyText,emiText;
        RatingBar ratingBar;

    }
    @Override
    public View getView(int positiom, View view, ViewGroup viewGroup) {
        Holder holder=new Holder();
        View convertView=inflater.inflate(R.layout.category_product_list_item,null);
        holder.productImage=(ImageView) convertView.findViewById(R.id.product_image);
        holder.productName=(TextView) convertView.findViewById(R.id.product_name);
        holder.ratingText=(TextView) convertView.findViewById(R.id.rating_text);
        holder.salePrice=(TextView) convertView.findViewById(R.id.sale_price);
        holder.salePercentage=(TextView) convertView.findViewById(R.id.sale_percentage);
        holder.marketPrice=(TextView) convertView.findViewById(R.id.market_price);
        holder.warrantyText=(TextView) convertView.findViewById(R.id.warranty_text);
        holder.emiText=(TextView) convertView.findViewById(R.id.emi_text);
        holder.ratingBar=(RatingBar) convertView.findViewById(R.id.rating_bar);

        setValues(positiom, holder);

        return convertView;
    }

    private void setValues(int positiom, Holder holder) {
        Picasso.with(activity.getApplicationContext()).load(homeProductses.get(positiom).getProduct_image()).into(holder.productImage);
        holder.productName.setText(homeProductses.get(positiom).getProduct_name());
        holder.ratingText.setText(String.valueOf(homeProductses.get(positiom).getProduct_rating()));
        holder.salePrice.setText(homeProductses.get(positiom).getProduct_sale_price());
        holder.salePercentage.setText(homeProductses.get(positiom).getProduct_sale_percent());
        holder.marketPrice.setText(homeProductses.get(positiom).getProduct_price());
        holder.warrantyText.setText(homeProductses.get(positiom).getProduct_warranty());
        holder.emiText.setText(homeProductses.get(positiom).getProduct_emi());
        holder.ratingBar.setRating(homeProductses.get(positiom).getProduct_rating());
    }
}
