package com.example.weatherapplicationproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView cityNameText, tempText, humText, descText, windText;
    private ImageView weatherIcon;
    private Button refreshButton;
    private EditText cityNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}