package nextdot.com.nextecom.ui.widgtes;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.ui.activities.SingleProductActivity;

import static nextdot.com.nextecom.utils.LogUtils.LOGD;

/**
 * Created by Zahan on 9/5/2016.
 */
public class CategoryProduct extends RelativeLayout {

    String productId;
    RelativeLayout mCategoryProductContainer;
    ImageView productImage;
    TextView productName,ratingText,salePrice,salePercentage,marketPrice,warrantyText,emiText;
    RatingBar ratingBar;

    public CategoryProduct(Context context) {
        this(context, null);
    }

    public CategoryProduct(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CategoryProduct(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view= LayoutInflater.from(context).inflate(R.layout.category_product_list_item, this);
        mCategoryProductContainer=(RelativeLayout) view.findViewById(R.id.category_product_container);
        productImage=(ImageView) view.findViewById(R.id.product_image);
        productName=(TextView) view.findViewById(R.id.product_name);
        ratingText=(TextView) view.findViewById(R.id.rating_text);
        salePrice=(TextView) view.findViewById(R.id.sale_price);
        salePercentage=(TextView) view.findViewById(R.id.sale_percentage);
        marketPrice=(TextView) view.findViewById(R.id.market_price);
        warrantyText=(TextView) view.findViewById(R.id.warranty_text);
        emiText=(TextView) view.findViewById(R.id.emi_text);
        ratingBar=(RatingBar) view.findViewById(R.id.rating_bar);
        mCategoryProductContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LOGD("clicked item", productId);
                Intent newIntent = new Intent(context, SingleProductActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("product_id",productId);
                context.startActivity(newIntent);
            }
        });


    }
    public void setCategoryProduct(HomeProducts homeProductses){

        productId=homeProductses.getProduct_id();
        Picasso.with(this.getContext().getApplicationContext()).load(homeProductses.getProduct_image()).into(productImage);
        productName.setText(homeProductses.getProduct_name());
        ratingText.setText(String.valueOf(homeProductses.getProduct_rating()));
        salePrice.setText(homeProductses.getProduct_sale_price());
        salePercentage.setText(homeProductses.getProduct_sale_percent());
        marketPrice.setText(homeProductses.getProduct_price());
        warrantyText.setText(homeProductses.getProduct_warranty());
        emiText.setText(homeProductses.getProduct_emi());
        ratingBar.setRating(homeProductses.getProduct_rating());
    }

}
