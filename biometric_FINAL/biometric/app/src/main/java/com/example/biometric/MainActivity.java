package com.example.biometric;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.Intent;
import android.os.Bundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {
ConstraintLayout layout;
    private static final String TAG = MainActivity.class.getName();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
layout=this.findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        myBiometricPrompt.authenticate(promptInfo);

    }
    public void onBackPressed(){finish(); }

        Executor newExecutor = Executors.newSingleThreadExecutor();

        FragmentActivity activity = this;
        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {finish(); }
                else if(errorCode == BiometricPrompt.ERROR_USER_CANCELED){finish();}
                else {
                    Log.d(TAG, "An unrecoverable error occurred");
                }
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                layout.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Authentication successfully",Toast.LENGTH_SHORT).show();
            }

//onAuthenticationFailed is called when the fingerprint doesn’t match//

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity.this, "Authentication failed",Toast.LENGTH_SHORT).show();
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Test")
               // .setNegativeButtonText("Cancel")   //if PIN authentication is not requires
                .setDeviceCredentialAllowed(true)
                .build();
    }
