package nextdot.com.nextecom.ui.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import nextdot.com.nextecom.R;

@SuppressWarnings({ "deprecation" })
public class CheckoutTabActivity extends TabActivity implements AppCompatCallback {

    private AppCompatDelegate delegate;
    TabHost tabHost;
    Boolean current = true;

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        //let's leave this empty, for now
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        // let's leave this empty, for now
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_checkout_tab);


        delegate = AppCompatDelegate.create(this, this);

        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.activity_checkout_tab);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Finally, let's add the Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.checkout_toolbar);
        delegate.setSupportActionBar(toolbar);
        ActionBar actionBar = delegate.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Checkout");

        Resources ressources = getResources();
        tabHost = getTabHost();





        // Android tab

        TabSpec a = tabHost.newTabSpec("BILLING");
                a.setIndicator("BILLING");

        Intent intentAndroid = new Intent(this, BillActivity.class);
                a.setContent(intentAndroid);

        // Apple tab

        TabSpec b = tabHost.newTabSpec("SHIPPING/PAYMENT");
        b.setIndicator("SHIPPING/PAYMENT");


        Intent intentApple = new Intent(this, ShipActivity.class);
        b.setContent(intentApple);





        // add all tabs
        tabHost.addTab(a);
        tabHost.addTab(b);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FF0000")); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#BDBFC1"));
            tv.setTextSize(14);
        }

//                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTextSize(14);
        tabHost.setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = (int) (45 * this.getResources().getDisplayMetrics().density);
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = (int) (45 * this.getResources().getDisplayMetrics().density);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

                if(tabHost.getCurrentTab()==0 && !current) {

                    tabHost.setCurrentTab(1);
                }

                else if (tabHost.getCurrentTab()==1 && current){

                    tabHost.setCurrentTab(0);
                }

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FF0000")); // unselected
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#BDBFC1"));
                    tv.setTextSize(14);
                }

//                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF")); // selected
                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setTextSize(14);
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            }
        });


        //set Windows tab as default (zero based)

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

    public void checkTabBool(Boolean b){
        current = b;
    }


}
