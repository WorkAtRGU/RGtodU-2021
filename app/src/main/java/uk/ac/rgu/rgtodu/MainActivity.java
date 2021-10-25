package uk.ac.rgu.rgtodu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

/**
 * The MainActivity of the app that hosts the NavHostFragment that displays all
 * of the Fragments for this app
 */
public class MainActivity extends AppCompatActivity {

    // TAG to be used when logging
    private static final String TAG = "TODO MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "on create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "on stop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "on save instance state");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on destroy");
    }
}