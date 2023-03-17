package com.example.simplelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bankList = findViewById(R.id.tvBanks);

        String[] banks = {"RNCB", "SBER", "VTB", "OPEN", "GENBANK", "ALFA", "TA NE POHUI LI", "KAKOI", "BANK"};
        bankList.setText("");
        for (String bank: banks){
            bankList.append(bank + "\n");
        }
    }
}