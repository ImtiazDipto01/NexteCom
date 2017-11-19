package nextdot.com.nextecom.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.adapters.Constant;
import nextdot.com.nextecom.ui.adapters.DataProvider;
import nextdot.com.nextecom.ui.adapters.DatabaseHelper;
import nextdot.com.nextecom.ui.adapters.WishlistAdapter;
import nextdot.com.nextecom.ui.widgtes.CategoryProduct;

import static nextdot.com.nextecom.utils.LogUtils.LOGD ;

/**
 * Created by sakib on 3/22/2017.
 */

public class WishListActivity extends BaseActivity {

    ListView wishlistview ;
    SQLiteDatabase sqLiteDatabase ;
    DatabaseHelper databaseHelper ;
    Cursor cursor ;
    WishlistAdapter wishlistAdapter ;
    RelativeLayout wishListLayout ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() == null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }*/
        setupActionUpNavDrawer();

        wishlistview = (ListView) findViewById(R.id.wishlist_product_list) ;
        databaseHelper = new DatabaseHelper(getApplicationContext()) ;
        sqLiteDatabase = databaseHelper.getReadableDatabase() ;
        wishlistAdapter = new WishlistAdapter(getApplicationContext(), R.layout.wishlist_item) ;
        wishlistview.setAdapter(wishlistAdapter);
        cursor = databaseHelper.getData(sqLiteDatabase) ;
        if(cursor != null){

            LOGD("tag : ", "asche!!");
            while(cursor.moveToNext()){
                LOGD("tag : ", "loop open");
                String id, name, price ;
                id = cursor.getString(0) ;
                name = cursor.getString(1) ;
                price = cursor.getString(2) ;
                //LOGD("tag : ", name);
                DataProvider dataProvider = new DataProvider(id,name,price,"http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017869-blue-jelly-icon-symbols-shapes-shape-square-frame.png") ;
                wishlistAdapter.add(dataProvider);
            }
        }

        wishlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int myPosition = position ;
                String getid = null;
                List myWishList = new ArrayList() ;
                myWishList.add(wishlistAdapter.getItem(myPosition));
                Iterator itr=myWishList.iterator();
                while(itr.hasNext()){
                    DataProvider st=(DataProvider) itr.next();
                    //System.out.println(st.getId());
                    //Log.d("tag: ", st.getId()) ;
                    getid = st.getId() ;
                }
                Log.d("tag: ", "damn!!") ;
                ArrayList<HomeProducts> wishlistProductes = DemoData.fetchHomeProductses() ;
                Intent intent = new Intent(getApplicationContext(), SingleProductActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("product_id",getid);
                startActivity(intent);
            }
        });
    }


    public void onResume(){
        super.onResume();
        setupActionUpNavDrawer();

        wishlistview = (ListView) findViewById(R.id.wishlist_product_list) ;
        databaseHelper = new DatabaseHelper(getApplicationContext()) ;
        sqLiteDatabase = databaseHelper.getReadableDatabase() ;
        wishlistAdapter = new WishlistAdapter(getApplicationContext(), R.layout.wishlist_item) ;
        wishlistview.setAdapter(wishlistAdapter);
        cursor = databaseHelper.getData(sqLiteDatabase) ;
        if(cursor != null){

            LOGD("tag : ", "asche!!");
            while(cursor.moveToNext()){
                LOGD("tag : ", "loop open");
                String id, name, price ;
                id = cursor.getString(0) ;
                name = cursor.getString(1) ;
                price = cursor.getString(2) ;
                //LOGD("tag : ", name);
                DataProvider dataProvider = new DataProvider(id,name,price,"http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/blue-jelly-icons-symbols-shapes/017869-blue-jelly-icon-symbols-shapes-shape-square-frame.png") ;
                wishlistAdapter.add(dataProvider);
            }
        }

        wishlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int myPosition = position ;
                String getid = null;
                List myWishList = new ArrayList() ;
                myWishList.add(wishlistAdapter.getItem(myPosition));
                Iterator itr=myWishList.iterator();
                while(itr.hasNext()){
                    DataProvider st=(DataProvider) itr.next();
                    //System.out.println(st.getId());
                    //Log.d("tag: ", st.getId()) ;
                    getid = st.getId() ;
                }
                Log.d("tag: ", "damn!!") ;
                ArrayList<HomeProducts> wishlistProductes = DemoData.fetchHomeProductses() ;
                Intent intent = new Intent(getApplicationContext(), SingleProductActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("product_id",getid);
                startActivity(intent);
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId() ;

        if(id == R.id.action_cart){
            return true ;
        }
        else if(id == R.id.action_help){
            return true ;
        }
        /*else if(id == android.R.id.home){
            finish();
        }*/
        return super.onOptionsItemSelected(item) ;
    }
}
