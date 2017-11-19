package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.entities.Review;
import nextdot.com.nextecom.model.AddToCart;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.adapters.Constant;
import nextdot.com.nextecom.ui.adapters.DatabaseHelper;
import nextdot.com.nextecom.ui.widgtes.Reviews;
import nextdot.com.nextecom.utils.LogUtils;

import static nextdot.com.nextecom.utils.LogUtils.LOGD;

public class SingleProductActivity extends BaseActivity implements View.OnClickListener {
    final static String DEBUG_TAG = LogUtils.makeLogTag(SingleProductActivity.class);

    ViewHolder mViewHolder;
    HomeProducts homeProducts;
    String productId;
    public int count = 0 ;
    ArrayList<Review> productReview=new ArrayList<>();
    List<HomeProducts> HomeProductsList=new ArrayList<HomeProducts>();
    TextView textView, textView_cnt ;
    DatabaseHelper databaseHelper ;
    private int flag = 0 ;
    Button btn_share, btn_similar ;
    Context context = this ;
    SQLiteDatabase sqLiteDatabase ;
    public RealmConfiguration realmConfig ;
    private Realm realm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        setupActionUpNavDrawer();
        getExtraData();
        getProductInfo();
        mViewHolder = new ViewHolder(this);
        mViewHolder.takeUIReferences();
        setProductData();
        setReview();
        setListeners();
        Realm.init(this);

        //deleteinfo();

        //Checking the Product already in Favorite list OR not
        final Button fav_btn  = (Button) findViewById(R.id.favbtn) ;
        String id_check = homeProducts.getProduct_id() ;
        databaseHelper = new DatabaseHelper(context) ;
        sqLiteDatabase = databaseHelper.getReadableDatabase() ;
        Cursor cursor = databaseHelper.getuserinfo(id_check, sqLiteDatabase) ;
        if(cursor.moveToFirst()){
            int index1 = cursor.getColumnIndex(Constant.PRODUCT_ID) ;
            String new_id = cursor.getString(index1) ;
            LOGD("data1 :", id_check);
            LOGD("data2 :", new_id);
            if(id_check.equals(new_id)){
                flag = 1 ;
                fav_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fav_filled, 0, 0, 0);
                LOGD("main :", "TRUE");
            }
        }
        //Checking the Product already in Favorite list OR not


        //Realm Creating Structure

        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("addtocart.realm").build();
        realm = Realm.getInstance(realmConfig) ;
        //Realm Creating Structure


        // Add To Cart Button Implementation
        Button bAddtoCart = (Button) findViewById(R.id.add_to_cart) ;
        bAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // INSERT REALM DATA
                String id = homeProducts.getProduct_id() ;
                String name = homeProducts.getProduct_name() ;
                String price = homeProducts.getProduct_sale_price() ;
                realm = Realm.getInstance(realmConfig) ;
                if(realm.isEmpty()){
                    Log.d("tag :", "yes empty") ;

                    realm.beginTransaction();
                    AddToCart addToCart_new = realm.createObject(AddToCart.class) ;
                    addToCart_new.setCart_product_id(id);
                    addToCart_new.setCart_product_name(name);
                    addToCart_new.setCart_product_price(price);
                    addToCart_new.setCart_product_quantity(1);
                    realm.commitTransaction();
                    //new_hot_number++ ;
                }
                else{
                    Log.d("tag :", "not empty") ;
                    RealmQuery<AddToCart> query = realm.where(AddToCart.class) ;
                    query.equalTo("cart_product_id", id);
                    RealmResults<AddToCart> result = query.findAll() ;
                    //AddToCart addToCart = result.first() ;
                    if(result.size() > 0){
                        Log.d("tag :", "found") ;
                        Toast.makeText(getApplicationContext(), "this product already in cart",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Log.d("tag :", "not found") ;
                        realm.beginTransaction();
                        AddToCart addToCart_new = realm.createObject(AddToCart.class) ;
                        addToCart_new.setCart_product_id(id);
                        addToCart_new.setCart_product_name(name);
                        addToCart_new.setCart_product_price(price);
                        addToCart_new.setCart_product_quantity(1);
                        realm.commitTransaction();
                        //new_hot_number++ ;
                    }
                }
                // INSERT REALM DATA

                invalidateOptionsMenu();
            }
        });
        // Add To Cart Button Implementation


        /*final Button fav_btn  = (Button) findViewById(R.id.favbtn) ;*/

        //Favourites Button Implementation
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0){
                    fav_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fav_filled, 0, 0, 0);
                    flag = 1 ;
                    adddata();
                }
                else{
                    fav_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fav_one, 0, 0, 0);
                    flag = 0 ;
                    deleteinfo();
                }
            }
        });
        //Favourites Button Implementation


        // share button Implementation
        btn_share = (Button) findViewById(R.id.share_btn) ;
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND) ;
                share.setType("text/plain") ;
                share.putExtra(Intent.EXTRA_SUBJECT, "Nexte-com") ;
                share.putExtra(Intent.EXTRA_TEXT, "Nexte-com Mobile App") ;
                startActivity(Intent.createChooser(share, "Share Via"));
            }
        });
        // share button Implementation


        //simillar Product Implementation
        btn_similar = (Button) findViewById(R.id.similar_btn) ;
        btn_similar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(v.getContext(), SimilarProductActivity.class) ;
                startActivity(myintent);
            }
        });
        // similar product Implementation

    }

    //dataBase Delete and Add Operation
    public void deleteinfo(){
        databaseHelper = new DatabaseHelper(context) ;
        sqLiteDatabase = databaseHelper.getReadableDatabase() ;
        String id = homeProducts.getProduct_id() ;
        databaseHelper.DeleteData(id, sqLiteDatabase);
    }

    public void adddata(){
        String id = homeProducts.getProduct_id() ;
        String name = homeProducts.getProduct_name() ;
        String price = homeProducts.getProduct_sale_price() ;
        databaseHelper = new DatabaseHelper(context) ;
        sqLiteDatabase = databaseHelper.getWritableDatabase() ;
        databaseHelper.AddData(id, name, price, sqLiteDatabase);
        Log.d("data : ", name) ;
    }
    //dataBase Delete and Add Operation


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_cart) {
            return true;
        } else if (id == R.id.action_help) {
            return true;
        }*/

        if(id == R.id.action_help)
        {
            //return true ;
            Intent intent = new Intent(this, WishListActivity.class) ;
            startActivity(intent);
        }

        if(id == R.id.action_cart)
        {
            Intent intent = new Intent(this, CartActivity.class) ;
            intent.putExtra("arraylist", (Serializable) HomeProductsList);
//            intent.putExtra("homeproducts",HomeProductsList.toString()) ;
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList("mylist", HomeProductsList);
//            intent.putExtras(bundle);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /*private Drawable buildCounterDrawable(int count, int backgroundImageId)
    {
        LayoutInflater Inflater = LayoutInflater.from(this) ;
        View view = Inflater.inflate(R.layout.menuitem_layout, null) ;
        view.setBackgroundResource(backgroundImageId);


        if(count == 0)
        {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel) ;
            counterTextPanel.setVisibility(View.GONE);

        }
        else
        {
            if(count == 1){
                homeProducts.setCartvalue(1);
//                HomeProductsList.add(homeProducts);
//                Log.d("value :", String.valueOf(homeProducts.getCartvalue())) ;
            }
            textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);

    }
    private void doIncrease() {

        count++;
        invalidateOptionsMenu();
        HomeProductsList.add(homeProducts);
    }*/

    private void setReview() {
        productReview=DemoData.fetchProductReview(productId);
        for(int i=0;i<productReview.size();i++){
            Reviews reviews=new Reviews(this);
            reviews.addReview(productReview.get(i));
            mViewHolder.mReviewContainer.addView(reviews);
        }
        if (productReview.size()==0){
            mViewHolder.mReviewTitle.setText("No review yet");
        }
        LOGD(DEBUG_TAG,String.valueOf(productReview.size()));
    }

    private void setListeners() {
        mViewHolder.mButtonSpec.setOnClickListener(this);
        mViewHolder.mButtonDetails.setOnClickListener(this);
    }

    private void setProductData() {
        Picasso.with(this.getApplicationContext()).load(homeProducts.getProduct_image()).into(mViewHolder.mProductImage);
        mViewHolder.mProductName.setText(homeProducts.getProduct_name());
        mViewHolder.mMarketPrice.setText(homeProducts.getProduct_price());
        mViewHolder.mSalePrice.setText(homeProducts.getProduct_sale_price());
        mViewHolder.mRatingBar.setRating(homeProducts.getProduct_rating());
        mViewHolder.mRatingText.setText(homeProducts.getProduct_sale_percent());
        showSpecDetails(homeProducts.getProduct_specification(), true);
    }

    private void getProductInfo() {
        homeProducts = DemoData.fetchHomeProduct(productId);
    }

    private void getExtraData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productId = extras.getString("product_id");
        } else {
            productId = "";
        }
        LOGD(DEBUG_TAG, productId);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_spec:
                showSpecDetails(homeProducts.getProduct_specification(), true);
                break;
            case R.id.button_details:
                showSpecDetails(homeProducts.getProduct_details(), false);
                break;
        }
    }



    private void showSpecDetails(String product_specification, boolean spec) {
        mViewHolder.mDescriptionText.setText(product_specification);
        if (spec) {
            mViewHolder.mButtonSpecBar.setBackgroundColor(getResources().getColor(R.color.nav_drawer_background));
            mViewHolder.mButtonDetailsBar.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            mViewHolder.mButtonDetailsBar.setBackgroundColor(getResources().getColor(R.color.nav_drawer_background));
            mViewHolder.mButtonSpecBar.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }




    public static class ViewHolder{

        Activity mActivity;
        ImageView mProductImage;
        TextView mProductName, mSalePrice, mMarketPrice, mSalePercentage, mRatingText, mDescriptionText,mReviewTitle;
        RatingBar mRatingBar;
        Button mButtonSpec, mButtonDetails, mAddToCart;
        View mButtonSpecBar, mButtonDetailsBar;
        LinearLayout mReviewContainer;


        public ViewHolder(Activity context) {
            mActivity = context;
        }

        public void takeUIReferences() {
            mProductImage = (ImageView) mActivity.findViewById(R.id.product_image);
            mProductName = (TextView) mActivity.findViewById(R.id.product_name);
            mSalePrice = (TextView) mActivity.findViewById(R.id.sale_price);
            mMarketPrice = (TextView) mActivity.findViewById(R.id.market_price);
            mSalePercentage = (TextView) mActivity.findViewById(R.id.sale_percentage);
            mRatingText = (TextView) mActivity.findViewById(R.id.rating_text);
            mDescriptionText = (TextView) mActivity.findViewById(R.id.description_text);
            mRatingBar = (RatingBar) mActivity.findViewById(R.id.rating_bar);
            mButtonSpec = (Button) mActivity.findViewById(R.id.button_spec);
            mButtonDetails = (Button) mActivity.findViewById(R.id.button_details);
            mButtonSpecBar = mActivity.findViewById(R.id.button_spec_bar);
            mButtonDetailsBar = mActivity.findViewById(R.id.button_details_bar);
            mReviewContainer = (LinearLayout) mActivity.findViewById(R.id.review_container);
            mReviewTitle=(TextView) mActivity.findViewById(R.id.title_review);
        }
    }
}
