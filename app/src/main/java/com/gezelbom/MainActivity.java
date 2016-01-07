package com.gezelbom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Simple app that shows if the phone is connected to the internet or not
 */
public class MainActivity extends AppCompatActivity {

    ImageView onlineImage;
    ImageView offlineImage;
    TextView connectionStatusText;
    ImageButton buttonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onlineImage = (ImageView) findViewById(R.id.imageView_connection_image_online);
        offlineImage = (ImageView) findViewById(R.id.imageView_connection_image_offline);
        connectionStatusText = (TextView) findViewById(R.id.textView_connection_status);
        buttonRefresh = (ImageButton) findViewById(R.id.button_refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNetworkStatus();
            }
        });

        checkNetworkStatus();
    }

    /**
     * Get connection status from the system and if connected to network then update the buttons and
     * text to match this information
     */
    private void checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            connected();
        } else {
            offline();
        }
    }

    /**
     * Change Views to diplay offline
     */
    private void offline() {
        onlineImage.setVisibility(View.INVISIBLE);
        offlineImage.setVisibility(View.VISIBLE);
        connectionStatusText.setText("Not Connected");
    }

    /**
     * Change the views to display online
     */
    private void connected() {
        onlineImage.setVisibility(View.VISIBLE);
        offlineImage.setVisibility(View.INVISIBLE);
        connectionStatusText.setText("Connected");
    }
}
