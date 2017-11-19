package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.utils.LogUtils;

import static nextdot.com.nextecom.utils.LogUtils.LOGD;

/**
 * Created by Zahan on 9/6/2016.
 */
public class SignUpActivity extends Activity {
    final static String DEBUG_TAG = LogUtils.makeLogTag(SignUpActivity.class);
    final static String FORGOT="forgot";

    String clickInfo;
    ViewHolder mViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mViewHolder = new ViewHolder(this);
        mViewHolder.takeUIReferences();
        getExtraData();
        mViewHolder.setVisibleContainer(clickInfo);
    }


    private void getExtraData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            clickInfo = extras.getString("click_info");
        } else {
            clickInfo = "";
        }
        LOGD(DEBUG_TAG, clickInfo);
    }

    public static class ViewHolder {
        Activity mActivity;
        EditText mEmail, mPassword, mForgotEmail;
        Button mSubmit, mForgotSubmit;
        RelativeLayout mSignUpContainer, mForgotContainer;

        public ViewHolder(Activity context) {
            mActivity = context;
        }

        public void takeUIReferences() {
            mEmail = (EditText) mActivity.findViewById(R.id.user_email);
            mPassword = (EditText) mActivity.findViewById(R.id.user_password);
            mSubmit = (Button) mActivity.findViewById(R.id.submit);
            mForgotEmail = (EditText) mActivity.findViewById(R.id.forgot_user_email);
            mForgotSubmit = (Button) mActivity.findViewById(R.id.forgot_password_button);
            mSignUpContainer = (RelativeLayout) mActivity.findViewById(R.id.sign_up_container);
            mForgotContainer = (RelativeLayout) mActivity.findViewById(R.id.forgot_password_container);
        }

        public void setVisibleContainer(String clickInfo) {
            if (clickInfo.equals(FORGOT)) {
                mSignUpContainer.setVisibility(View.GONE);
                mForgotContainer.setVisibility(View.VISIBLE);
            }
        }

    }

}
