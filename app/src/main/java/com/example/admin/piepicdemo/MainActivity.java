package com.example.admin.piepicdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        private  PieView mpieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpieView= (PieView) findViewById(R.id.pieView);
        mpieView.setOverRunner("10%","90%",1,9,0xFFff9900,0xFF3c78d8);
    }
}
