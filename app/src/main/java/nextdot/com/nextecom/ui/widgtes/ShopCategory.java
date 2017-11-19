package nextdot.com.nextecom.ui.widgtes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.Adverts;

import static nextdot.com.nextecom.utils.LogUtils.LOGD;

/**
 * Created by Zahan on 9/4/2016.
 */
public class ShopCategory extends RelativeLayout {

    RelativeLayout shopCategoryContainer;
    String addId;
    ImageView addImage;
    TextView title,info;

    public String getAddId() {
        return addId;
    }

    public ShopCategory(Context context) {
        this(context, null);
    }

    public ShopCategory(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ShopCategory(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view=LayoutInflater.from(context).inflate(R.layout.shop_category_layout, this);
        shopCategoryContainer=(RelativeLayout) view.findViewById(R.id.shop_category);
        title=(TextView) view.findViewById(R.id.add_title);
        info=(TextView) view.findViewById(R.id.add_info);
        addImage=(ImageView) view.findViewById(R.id.add_image);
        shopCategoryContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LOGD("clicked add banner", getAddId());
            }
        });
    }
    public void setAdd(Adverts advert){
        addId=advert.getId();
        title.setText(advert.getTitle());
        info.setText(advert.getInfo());
        Picasso.with(getContext().getApplicationContext()).load(advert.getImage()).into(addImage);
    }
}
