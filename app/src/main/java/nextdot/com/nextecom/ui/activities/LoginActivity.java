package nextdot.com.nextecom.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nextdot.com.nextecom.R;
import nextdot.com.nextecom.utils.LogUtils;

import nextdot.com.nextecom.utils.LogUtils;

/**
 * Created by Zahan on 9/6/2016.
 */
public class LoginActivity extends Activity implements Animation.AnimationListener, View.OnClickListener {
    final static String DEBUG_TAG = LogUtils.makeLogTag(LoginActivity.class);

    ViewHolder mViewHolder;
    Animation slideIn, slideOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_login);
        mViewHolder = new ViewHolder(this);
        mViewHolder.takeUIReferences();
        setListeners();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setAnimations();
            }
        }, 2000);
    }

    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setAnimations() {
        slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
        slideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_top);
        mViewHolder.mSplashContainer.startAnimation(slideOut);
        slideOut.setAnimationListener(this);
        slideIn.setAnimationListener(this);
    }

    private void setListeners() {
        mViewHolder.mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LOGD(DEBUG_TAG, "clicked");
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });
        mViewHolder.mSignUp.setOnClickListener(this);
        mViewHolder.mForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animation == slideOut) {
            mViewHolder.mLoginContainer.setVisibility(View.VISIBLE);
            mViewHolder.mLoginContainer.startAnimation(slideIn);
        }

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == slideOut) {
            mViewHolder.mSplashContainer.setVisibility(View.GONE);

        } else if (animation == slideIn) {

        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {
        Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
        switch (view.getId()) {
            case R.id.sign_up:
                signup.putExtra("click_info", "signup");
                break;
            case R.id.forgot_password:
                signup.putExtra("click_info", "forgot");
                break;
        }
        startActivity(signup);
    }


    public static class ViewHolder {
        Activity mActivity;
        RelativeLayout mSplashContainer, mLoginContainer;
        Button mSignIn;
        TextView mSignUp, mForgotPassword;

        public ViewHolder(Activity context) {
            mActivity = context;
        }

        public void takeUIReferences() {
            mSplashContainer = (RelativeLayout) mActivity.findViewById(R.id.splash_container);
            mLoginContainer = (RelativeLayout) mActivity.findViewById(R.id.login_container);
            mSignIn = (Button) mActivity.findViewById(R.id.sign_in);
            mSignUp = (TextView) mActivity.findViewById(R.id.sign_up);
            mForgotPassword = (TextView) mActivity.findViewById(R.id.forgot_password);
        }
    }
}
