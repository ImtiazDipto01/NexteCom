package nextdot.com.nextecom.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.model.AddToCart;
import nextdot.com.nextecom.model.CartItemModel;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.adapters.CartItemAdapter;

import static android.R.attr.id;

/**
 * Created by sakib on 2/28/2017.
 */

public class CartItemsActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration realmConfig ;
    private RecyclerView Cart_recyclerView;
    private CartItemAdapter cartItemAdapter ;
    private Button checkOut ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //full screen

        setContentView(R.layout.activity_cart);
        initToolbar();
        Realm.init(this);
        //SingleProductActivity singleProductActivity = new SingleProductActivity() ;
        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("addtocart.realm").build();
        realm = Realm.getInstance(realmConfig) ;


        Cart_recyclerView = (RecyclerView) findViewById(R.id.cart_recyclerView);
        cartItemAdapter = new CartItemAdapter(getApplicationContext(), getData()) ;
        Cart_recyclerView.setAdapter(cartItemAdapter);
        Cart_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItemAdapter.setmOnItemClickListener(new CartItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion, CartItemModel obj) {
                CartItemDailouge(obj, postion);
            }
        });
        //hideStatusBar();
        //setupActionUpNavDrawer();

        checkOut = (Button) findViewById(R.id.bt_checkout);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartItemAdapter.getItemCount() != 0){
                    Intent intent = new Intent(getBaseContext(), CheckoutTabActivity.class) ;
                    startActivity(intent);
                }
            }
        });
    }

    public List<CartItemModel> getData(){

        //READ REALM DATA
        List<CartItemModel> data = new ArrayList<>() ;
        RealmResults<AddToCart> addToCart = realm.where(AddToCart.class).findAll();
        if(addToCart.size() > 0){
            for(AddToCart addtocart : addToCart){
                String name = addtocart.getCart_product_name() ;
                String id = addtocart.getCart_product_id() ;
                String price = addtocart.getCart_product_price() ;
                int quantity = addtocart.getCart_product_quantity() ;
                Log.d("product name :", String.valueOf(quantity)) ;

                CartItemModel cartItemModel = new CartItemModel() ;
                cartItemModel.setCart_product_id(id);
                cartItemModel.setCart_product_name(name);
                cartItemModel.setCart_product_price(price);
                cartItemModel.setCart_Item_Quantity(quantity);
                data.add(cartItemModel) ;
            }
        }
        //READ REALM DATA
        else{
            RelativeLayout cart_empty = (RelativeLayout) findViewById(R.id.lyt_notfound);
            cart_empty.setVisibility(View.VISIBLE);
            RelativeLayout orderNow = (RelativeLayout) findViewById(R.id.layout_ordernow) ;
            orderNow.setVisibility(View.GONE);
        }
        return data ;
    }

    /*private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }*/

    private void CartItemDailouge(final CartItemModel obj, int postion){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_cart_option);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);


        final TextView qty = (TextView) dialog.findViewById(R.id.quantity);
        qty.setText(obj.getCart_Item_Quantity() + "");
        ((ImageView) dialog.findViewById(R.id.img_decrease)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (obj.getCart_Item_Quantity() > 1) {
                    obj.setCart_Item_Quantity(obj.getCart_Item_Quantity() - 1);
                    qty.setText(obj.getCart_Item_Quantity() + "");
                    AddToCart addToCart = new AddToCart() ;
                    //RealmResults<AddToCart> result = realm.where(AddToCart.class).findAll() ;

                    //UPDATE REALM DATA
                    RealmQuery<AddToCart> query = realm.where(AddToCart.class) ;
                    query.equalTo("cart_product_id", obj.getCart_product_id());
                    RealmResults<AddToCart> result = query.findAll() ;
                    if(result.size() == 1){
                        for(AddToCart cart_obj : result){
                            realm.beginTransaction();
                            cart_obj.setCart_product_quantity(obj.getCart_Item_Quantity());
                            //realm.copyToRealmOrUpdate(cart_obj);
                            realm.commitTransaction();
                        }
                    }
                    //UPDATE REALM DATA
                }
            }
        });
        ((ImageView) dialog.findViewById(R.id.img_increase)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.setCart_Item_Quantity(obj.getCart_Item_Quantity() + 1);
                qty.setText(obj.getCart_Item_Quantity() + "");
                AddToCart addToCart = new AddToCart() ;
                //RealmResults<AddToCart> result = realm.where(AddToCart.class).findAll() ;

                //UPDATE REALM DATA
                RealmQuery<AddToCart> query = realm.where(AddToCart.class) ;
                query.equalTo("cart_product_id", obj.getCart_product_id());
                RealmResults<AddToCart> result = query.findAll() ;
                if(result.size() == 1){
                    for(AddToCart cart_obj : result){
                        realm.beginTransaction();
                        cart_obj.setCart_product_quantity(obj.getCart_Item_Quantity());
                        //realm.copyToRealmOrUpdate(cart_obj);
                        realm.commitTransaction();
                    }
                }
                //UPDATE REALM DATA
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //global.updateItemTotal(model);
                /*mAdapter.notifyDataSetChanged();
                setTotalPrice();*/
                dialog.dismiss();
            }
        });
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setTitle("Cart");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onResume(){
        super.onResume();

        Log.d("resume :", "asche !") ;

        /*List<CartItemModel> data = new ArrayList<CartItemModel>() ;
        for(CartItemModel c : data){
            Log.d("cart :", String.valueOf(c.getCart_Item_Quantity())) ;
        }*/
    }

}
