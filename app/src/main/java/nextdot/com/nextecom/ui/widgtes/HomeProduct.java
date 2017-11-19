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
 * Created by Zahan on 9/4/2016.
 */
public class HomeProduct extends RelativeLayout {


    ImageView productImage;
    TextView saleText, name, price;
    RatingBar ratingBar;
    RelativeLayout productContainer;
    String productId;

    public HomeProduct(Context context) {
        this(context, null);
    }

    public HomeProduct(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public HomeProduct(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.home_product, this);
        productContainer = (RelativeLayout) view.findViewById(R.id.product_container);
        productImage = (ImageView) view.findViewById(R.id.product_image);
        name = (TextView) view.findViewById(R.id.product_name);
        price = (TextView) view.findViewById(R.id.product_price);
        saleText = (TextView) view.findViewById(R.id.product_sale);
        ratingBar = (RatingBar) view.findViewById(R.id.product_rating);
        productContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LOGD("clicked item", productId);
                Intent newIntent = new Intent(context, SingleProductActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                newIntent.putExtra("product_id", productId);
                context.startActivity(newIntent);
            }
        });

    }

    public void setHomeProduct(HomeProducts homeProducts) {
        productId = homeProducts.getProduct_id();
        name.setText(homeProducts.getProduct_name());
        price.setText(homeProducts.getProduct_price());
        if (homeProducts.getProduct_sale_percent().equals("")) {
            saleText.setVisibility(INVISIBLE);
        } else {
            saleText.setText(homeProducts.getProduct_sale_percent());
        }
        Picasso.with(getContext().getApplicationContext()).load(homeProducts.getProduct_image()).into(productImage);
        ratingBar.setRating(homeProducts.getProduct_rating());
    }

    public String getProductId() {
        return productId;
    }

}
