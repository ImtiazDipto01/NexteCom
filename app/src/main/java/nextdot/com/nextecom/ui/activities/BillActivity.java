package nextdot.com.nextecom.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nextdot.com.nextecom.R;

public class BillActivity extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        next = (Button)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CheckoutTabActivity tabs = (CheckoutTabActivity) (BillActivity.this).getParent();

                tabs.checkTabBool(false);
                tabs.getTabHost().setCurrentTab(1);




            }
        });


    }


}
