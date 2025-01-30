package com.example.weatherapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView  tempText, humText, descText, windText;
    private ImageView weatherIcon;
    private Button refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descText = findViewById(R.id.descText);


        updateCloudiness("Sunny %");


    }

    private void updateCloudiness(String description){
        if (descText != null) descText.setText(description);

    }
}