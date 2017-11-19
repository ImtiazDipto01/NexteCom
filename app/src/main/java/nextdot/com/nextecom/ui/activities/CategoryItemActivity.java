package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.Adverts;
import nextdot.com.nextecom.entities.Banner;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.adapters.CategoryProductAdapter;
import nextdot.com.nextecom.ui.widgtes.CategoryProduct;
import nextdot.com.nextecom.ui.widgtes.ShopCategory;
import nextdot.com.nextecom.utils.LogUtils;

/**
 * Created by Zahan on 9/4/2016.
 */
public class CategoryItemActivity extends BaseActivity {
    final static String DEBUG_TAG = LogUtils.makeLogTag(CategoryItemActivity.class);

    ViewHolder mViewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);
        setupActionUpNavDrawer();
        mViewHolder = new ViewHolder(this);
        mViewHolder.takeUIReferences();
        addBanners();
        addAdverts();
        addProducts();
    }

    private void addProducts() {
        ArrayList<HomeProducts> homeProductses=DemoData.fetchHomeProductses();
        for(int i=0;i<homeProductses.size();i++){
            CategoryProduct categoryProduct=new CategoryProduct(getApplicationContext());
            categoryProduct.setCategoryProduct(homeProductses.get(i));
            mViewHolder.mProductList.addView(categoryProduct);
        }
    }

    private void addAdverts() {
        ArrayList<Adverts> advertses = DemoData.fetchAdverts();
        for (int i = 0; i < advertses.size(); i++) {
            ShopCategory shopCategory = new ShopCategory(getApplicationContext());
            shopCategory.setAdd(advertses.get(i));
            mViewHolder.mAddContainer.addView(shopCategory);
        }
    }

    private void addBanners() {
        ArrayList<Banner> banners = DemoData.fetchBanners();
        for (int i = 0; i < banners.size(); i++) {
            ImageView banner = new ImageView(getApplicationContext());
            banner.setPadding(4, 4, 4, 4);
            Picasso.with(getApplicationContext()).load(banners.get(i).getImage()).into(banner);
            mViewHolder.mBannerContainer.addView(banner);
        }
    }

    public static class ViewHolder {

        Activity mActivity;
        HorizontalScrollView mBannerScrollView;
        LinearLayout mBannerContainer, mHomeContainer, mAddContainer;
        LinearLayout mProductList;

        public ViewHolder(Activity context) {
            mActivity = context;
        }

        public void takeUIReferences() {
            mBannerScrollView = (HorizontalScrollView) mActivity.findViewById(R.id.banner_scroll_view);
            mBannerContainer = (LinearLayout) mActivity.findViewById(R.id.banner_container);
            mHomeContainer = (LinearLayout) mActivity.findViewById(R.id.home_container);
            mAddContainer = (LinearLayout) mActivity.findViewById(R.id.add_container);
            mProductList = (LinearLayout) mActivity.findViewById(R.id.product_list);
        }
    }
}
