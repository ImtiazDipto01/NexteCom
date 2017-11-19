package nextdot.com.nextecom.ui.widgtes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;

/**
 * Created by Zahan on 9/4/2016.
 */
public class HomeItem extends LinearLayout {

    LinearLayout productContainer;
    TextView title;
    public HomeItem(Context context) {
        this(context, null);
    }

    public HomeItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public HomeItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view= LayoutInflater.from(context).inflate(R.layout.home_product_layout, this);
        productContainer=(LinearLayout) view.findViewById(R.id.product_container);
        title=(TextView) view.findViewById(R.id.item_title);

    }
     public void addProduct(ArrayList<HomeProducts> homeProductses){
         for(int i=0;i<homeProductses.size();i++){
             HomeProduct homeProduct=new HomeProduct(getContext());
             productContainer.addView(homeProduct);
             homeProduct.setHomeProduct(homeProductses.get(i));
         }

     }
    public void setItemTitle(String titleText){
        title.setText(titleText);
    }
}
