package com.example.buttonall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class biometric extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biometric_layout);
            myBiometricPrompt.authenticate(promptInfo);
    }


    //Create a thread pool with a single thread//

    Executor newExecutor = Executors.newSingleThreadExecutor();

    FragmentActivity activity = this;

//Start listening for authentication events//

    final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
        @Override

//onAuthenticationError is called when a fatal error occurrs//

        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
            } else {
                finish();
                Log.d(TAG, "Fingerprint Cannot Be Recognized");
            }
        }
        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
          //  Toast.makeText(biometric.this, "Fingerprint recognised successfully", Toast.LENGTH_LONG).show();
            a();
        }
        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            Toast.makeText(biometric.this, "Fingerprint not recognised", Toast.LENGTH_LONG).show();
finish();
        }
        void a(){
            Intent aa=new Intent(biometric.this,test.class);
            startActivity(aa);
        }

    });
    final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Test")
            .setDeviceCredentialAllowed(true)
            //.setNegativeButtonText("Use Pin")

         //   .setConfirmationRequired(true)
            .build();


    @Override
    public void onBackPressed() {
        finish();
    }
}
