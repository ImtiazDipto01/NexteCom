package nextdot.com.nextecom.ui.widgtes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.entities.Review;


/**
 * Created by Zahan on 9/18/2016.
 */
public class Reviews extends LinearLayout {

    TextView userName, reviewText;

    public Reviews(Context context) {
        this(context, null);
    }

    public Reviews(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public Reviews(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.product_review_item, this);
        userName=(TextView) view.findViewById(R.id.user_name);
        reviewText=(TextView) view.findViewById(R.id.review_text);

    }

    public void addReview(Review review) {
        userName.setText(review.getUser_name());
        reviewText.setText(review.getReview_text());
    }

}
