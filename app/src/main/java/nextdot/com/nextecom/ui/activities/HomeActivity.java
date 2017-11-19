package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.Banner;
import nextdot.com.nextecom.entities.HomeItems;
import nextdot.com.nextecom.model.AddToCart;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.widgtes.HomeItem;
import nextdot.com.nextecom.utils.LogUtils;

import static nextdot.com.nextecom.utils.LogUtils.LOGD;


/**
 * Created by Zahan on 9/3/2016.
 */
public class HomeActivity extends BaseActivity {
    final static String DEBUG_TAG = LogUtils.makeLogTag(HomeActivity.class);

    ViewHolder mViewHolder;
    private Realm realm ;
    private RealmConfiguration realmConfig ;
    private TextView textView_cnt ;
    int count = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupNavDrawer();
        mViewHolder=new ViewHolder(this);
        mViewHolder.takeUIReferences();
        addBanners();
        addProducts();
        setListeners();

        Realm.init(this);
        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("addtocart.realm").build();
        realm = Realm.getInstance(realmConfig) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        /*MenuItem menuItem = menu.findItem(R.id.action_cart) ;
        menuItem.setIcon(buildCounterDrawable(count, R.drawable.cart2));*/

        final View cartValue = menu.findItem(R.id.action_cart).getActionView();
        textView_cnt = (TextView) cartValue.findViewById(R.id.txtCount);
        RealmResults<AddToCart> result = realm.where(AddToCart.class).findAll();
        count = result.size() ;
        updateHotCount(count);

        cartValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartItemsActivity.class) ;
                //intent.putExtra("arraylist", (Serializable) HomeProductsList);
                startActivity(intent);
                /*Intent intent = new Intent(getApplicationContext(), CartActivity.class) ;
                intent.putExtra("arraylist", (Serializable) HomeProductsList);
                startActivity(intent);*/
            }
        });
        return true;
    }

    public void updateHotCount(final int new_hot_number) {

        if(new_hot_number > 0){
            textView_cnt.setVisibility(View.VISIBLE);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView_cnt.setText(new_hot_number +"");
            }
        });
    }

    private void setListeners() {
        mViewHolder.mShopCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LOGD(DEBUG_TAG,"shop category clicked");
            }
        });
    }


    private void addProducts() {
        ArrayList<HomeItems> homeItems=DemoData.fetchHomeItems();
        for(int i=0;i<homeItems.size();i++){
            HomeItem homeItem=new HomeItem(getApplicationContext());
            mViewHolder.mHomeContainer.addView(homeItem);
            homeItem.setItemTitle(homeItems.get(i).getTitle());
            homeItem.addProduct(homeItems.get(i).getHomeProductses());
        }
    }

    private void addBanners() {
        ArrayList<Banner> banners=DemoData.fetchBanners();
        for(int i=0;i<banners.size();i++){
            ImageView banner=new ImageView(getApplicationContext());
            banner.setPadding(4,4,4,4);
            Picasso.with(getApplicationContext()).load(banners.get(i).getImage()).into(banner);
            mViewHolder.mBannerContainer.addView(banner);
        }
    }



    public static class ViewHolder{

        Activity mActivity;
        HorizontalScrollView mBannerScrollView;
        LinearLayout mBannerContainer,mHomeContainer;
        RelativeLayout mShopCategory;

        public ViewHolder(Activity context){
            mActivity=context;
        }

        public void takeUIReferences(){
            mBannerScrollView=(HorizontalScrollView) mActivity.findViewById(R.id.banner_scroll_view);
            mBannerContainer=(LinearLayout) mActivity.findViewById(R.id.banner_container);
            mHomeContainer=(LinearLayout) mActivity.findViewById(R.id.home_container);
            mShopCategory=(RelativeLayout) mActivity.findViewById(R.id.shop_category);
        }
    }

    public void onResume(){
        super.onResume();
        invalidateOptionsMenu();

    }
}
