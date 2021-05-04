package br.com.modulo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("p");
        System.out.println("Testt1");
        System.out.println("HEYYZ");
        setContentView(R.layout.activity_main);
    }
}