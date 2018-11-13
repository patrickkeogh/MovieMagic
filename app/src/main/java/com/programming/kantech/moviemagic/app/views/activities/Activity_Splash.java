package com.programming.kantech.moviemagic.app.views.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.programming.kantech.moviemagic.app.R;
import com.programming.kantech.moviemagic.app.utils.Constants;
import com.programming.kantech.moviemagic.app.utils.Utils_General;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity_Splash extends AppCompatActivity {

    // Member variables for the Firebase Authorization
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mFirebaseAuthListener;

    @BindView(R.id.tv_splash_message)
    TextView tv_splash_message;

    @BindView(R.id.tv_splash_message2)
    TextView tv_splash_message2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        tv_splash_message.setText(R.string.msg_signing_in);

        createAuthListener();

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mFirebaseAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mFirebaseAuthListener);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mFirebaseAuthListener);
    }

    private void createAuthListener() {

        mFirebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // Signed In
                    tv_splash_message.setText(R.string.msg_sign_in_complete);

                    onSignedInInitialize(user);
                } else {
                    // Not signed in
                    //onSignedOutCleanup();
                    tv_splash_message.setText(R.string.msg_not_signed_in);
                    onNotSignedInInitialize();



                }
            }
        };
    }

    private void onSignedInInitialize(final FirebaseUser user) {

        if (Utils_General.isNetworkAvailable(this)) {
            tv_splash_message.setText(R.string.msg_splash_initializing);

            // This only for testing, maybe lol
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Activity_Splash.this, Activity_Main.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);


        } else {
            Utils_General.showToast(this, getString(R.string.msg_no_network));
        }


    }

    private void onNotSignedInInitialize() {

        if (Utils_General.isNetworkAvailable(this)) {
            //tv_splash_message.setText(R.string.msg_splash_initializing);


            // This only for testing, maybe lol
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Activity_Splash.this, Activity_Signin.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);


        } else {
            Utils_General.showToast(this, getString(R.string.msg_no_network));
        }


    }
}
