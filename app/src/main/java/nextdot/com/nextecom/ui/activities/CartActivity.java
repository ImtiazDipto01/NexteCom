package nextdot.com.nextecom.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.ui.adapters.CartListAdapter;
import nextdot.com.nextecom.data.GlobalCartVariable;
import nextdot.com.nextecom.model.ItemModel;
import nextdot.com.nextecom.entities.HomeProducts ;
import nextdot.com.nextecom.ui.widgtes.HomeProduct;

public class CartActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private GlobalCartVariable global;
    private CartListAdapter mAdapter;
    private TextView item_total, price_total, order_total;
    private Button checkout_button;
    private LinearLayout ll_cart, ll_shipping, ll_order;
    private RelativeLayout lyt_notfound;
    ArrayList<HomeProducts> list;
    HomeProducts tmp ;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Bundle bundle = getIntent().getExtras();
        list = (ArrayList<HomeProducts>) getIntent().getSerializableExtra("arraylist");

        Log.d("ListSize","========"+list.size());

        global = (GlobalCartVariable) this.getApplication();

        item_total = (TextView) findViewById(R.id.item_total);
        price_total = (TextView) findViewById(R.id.price_total);
        order_total = (TextView) findViewById(R.id.order_total);
        lyt_notfound = (RelativeLayout) findViewById(R.id.lyt_notfound);
        ll_cart = (LinearLayout)findViewById(R.id.ll_cart);
        ll_shipping = (LinearLayout)findViewById(R.id.ll_shipping);
        ll_order = (LinearLayout)findViewById(R.id.ll_order);
        recyclerView = (RecyclerView) findViewById(R.id.cart_recyclerView);
        checkout_button = (Button) findViewById(R.id.bt_checkout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //set data and list adapter
        mAdapter = new CartListAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new CartListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, HomeProducts obj) {
                dialogCartAction(obj, position);
            }
        });

        initToolbar();

        checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAdapter.getItemCount() != 0) {
                    checkoutConfirmation();
                }
            }
        });

        setTotalPrice();

        checkLayout();

    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Cart");*/
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

    private void setTotalPrice() {
        item_total.setText(" - " + global.getCartItem() + " Items");
        price_total.setText(" $ " + global.getCartPriceTotal());
        order_total.setText(" $ " + global.getCartPriceTotal());
    }

    private void dialogCartAction(final HomeProducts model, final int position) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_cart_option);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        tmp = list.get(position) ;

        //((TextView) dialog.findViewById(R.id.title)).setText(tmp.getProduct_name());

        final TextView qty = (TextView) dialog.findViewById(R.id.quantity);
        qty.setText(model.getCartvalue() + "");
        ((ImageView) dialog.findViewById(R.id.img_decrease)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getCartvalue() > 1) {
                    model.setCartvalue(model.getCartvalue() - 1);
                    qty.setText(model.getCartvalue() + "");
                }
            }
        });
        ((ImageView) dialog.findViewById(R.id.img_increase)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setCartvalue(model.getCartvalue() + 1);
                qty.setText(model.getCartvalue() + "");
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //global.updateItemTotal(model);
                mAdapter.notifyDataSetChanged();
                setTotalPrice();
                dialog.dismiss();
            }
        });
//        ((Button) dialog.findViewById(R.id.bt_remove)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                global.removeCart(model);
//                mAdapter.notifyDataSetChanged();
//                setTotalPrice();
//                checkLayout();
//                dialog.dismiss();
//            }
//        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void checkoutConfirmation() {

        Intent i = new Intent(CartActivity.this, CheckoutTabActivity.class);
        startActivity(i);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Checkout Confirmation");
//        builder.setMessage("Are you sure continue to checkout?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                global.clearCart();
//                mAdapter.notifyDataSetChanged();
//                Snackbar.make(view, "Checkout success", Snackbar.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNegativeButton("No", null);
//        builder.show();



    }

    private void checkLayout(){

        if (mAdapter.getItemCount() == 0) {
            lyt_notfound.setVisibility(View.VISIBLE);
            ll_cart.setVisibility(View.GONE);
            ll_shipping.setVisibility(View.GONE);
            ll_order.setVisibility(View.GONE);
            checkout_button.setVisibility(View.GONE);


        } else {
            lyt_notfound.setVisibility(View.GONE);
            ll_cart.setVisibility(View.VISIBLE);
            ll_shipping.setVisibility(View.VISIBLE);
            ll_order.setVisibility(View.VISIBLE);
            checkout_button.setVisibility(View.VISIBLE);
        }




    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onRestart() {

        if (mAdapter.getItemCount() == 0 ) {
            mAdapter.notifyDataSetChanged();
            lyt_notfound.setVisibility(View.VISIBLE);
            ll_cart.setVisibility(View.GONE);
            ll_shipping.setVisibility(View.GONE);
            ll_order.setVisibility(View.GONE);
            checkout_button.setVisibility(View.GONE);
        }


        super.onRestart();
    }
}
