package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.entities.HomeProducts;
import nextdot.com.nextecom.testdata.DemoData;
import nextdot.com.nextecom.ui.widgtes.CategoryProduct;

/**
 * Created by sakib on 3/15/2017.
 */

public class SimilarProductActivity extends BaseActivity {

    private Toolbar toolbar ;
    ViewHolder mViewHolder ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_product);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() == null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }*/
        setupActionUpNavDrawer();
        mViewHolder = new ViewHolder(this) ;
        mViewHolder.takeUIReferences();
        addsimilarproduct();
    }

    private void addsimilarproduct(){
        ArrayList<HomeProducts> similarHomeProductes = DemoData.fetchHomeProductses() ;
        for(int i = 0 ; i < similarHomeProductes.size() ; i++){
            CategoryProduct categoryProduct = new CategoryProduct(getApplicationContext()) ;
            categoryProduct.setCategoryProduct(similarHomeProductes.get(i));
            mViewHolder.mSimilarProductList.addView(categoryProduct);
        }
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

    public static class ViewHolder{
        Activity mActivity ;
        LinearLayout mSimilarProductList ;

        public ViewHolder(Activity context){
            this.mActivity = context ;
        }

        public void takeUIReferences(){
            mSimilarProductList = (LinearLayout) mActivity.findViewById(R.id.similar_product_list) ;
        }
    }
}
