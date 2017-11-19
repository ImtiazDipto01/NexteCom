package nextdot.com.nextecom.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import nextdot.com.nextecom.R;
import nextdot.com.nextecom.data.GlobalCartVariable;
import nextdot.com.nextecom.model.AddToCart;

public class ShipActivity extends AppCompatActivity {

    TextView paypal_txt, amazon_txt ;
    Button confirmButton;
    ImageButton check_international, check_local ;
    private GlobalCartVariable global;
    private int flag = 1 ;
    private LinearLayout localPayment, internationalPayment ;
    private Realm realm ;
    private RealmConfiguration realmConfig ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);

        Realm.init(this);
        realmConfig = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("addtocart.realm").build();
        realm = Realm.getInstance(realmConfig) ;

        localPayment = (LinearLayout) findViewById(R.id.local_payment) ;
        internationalPayment = (LinearLayout) findViewById(R.id.international_payment) ;

        global = (GlobalCartVariable) this.getApplication();

        check_international = (ImageButton) findViewById(R.id.btn_internation);
        check_local = (ImageButton) findViewById(R.id.btn_local) ;


        check_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_local.setImageResource(R.drawable.checkbox_background_new);
                check_international.setImageResource(R.drawable.white_background);
                internationalPayment.setVisibility(View.GONE);
                localPayment.setVisibility(View.VISIBLE);
                flag = 0 ;

                Spinner myspinner = (Spinner) findViewById(R.id.local_spinner);
                ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(ShipActivity.this,
                        R.layout.spinner_textview, getResources().getStringArray(R.array.spinner_array));
                //spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinner.setAdapter(spinner_adapter);
            }
        });

        check_international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                check_local.setImageResource(R.drawable.checkbox_background_new);
//                check_international.setImageResource(R.drawable.white_background);
//                flag = 0 ;

                if(flag == 0){
                    check_international.setImageResource(R.drawable.checkbox_background_new);
                    check_local.setImageResource(R.drawable.white_background);
                    localPayment.setVisibility(View.GONE);
                    internationalPayment.setVisibility(View.VISIBLE);
                    flag = 1 ;
                }
            }
        });


        confirmButton = (Button) findViewById(R.id.confirm_btn);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                global.clearCart();
//                Toast.makeText(ShipActivity.this, "Checkout Success!", Toast.LENGTH_SHORT).show();
//                finish();
                dailougeAction();
            }
        });

    }

    private void dailougeAction()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        //dialog.setContentView(R.layout.dilouge_shipment_method);
        dialog.setContentView(R.layout.dilouge_shipment_method);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);

        /*paypal_txt = (TextView) findViewById(R.id.paypal);
        //paypal_txt = (TextView) findViewById(R.id.paypal) ;
        amazon_txt = (TextView) findViewById(R.id.amazon) ;*/

        /*paypal_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShipActivity.this, "Checkout Success!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });*/
        /*amazon_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShipActivity.this, "Checkout Success!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });*/
    }

    public void paypal_onClick(View view){
        Toast.makeText(ShipActivity.this, "Checkout Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        //DELETE REALM DATA
        RealmResults<AddToCart> results = realm.where(AddToCart.class).findAll() ;
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        //DELETE REALM DATA
    }
    public void amazon_onClick(View view){
        Toast.makeText(ShipActivity.this, "Checkout Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        //DELETE REALM DATA
        RealmResults<AddToCart> results = realm.where(AddToCart.class).findAll() ;
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        //DELETE REALM DATA
    }
}

