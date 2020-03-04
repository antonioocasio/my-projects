package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DnaDetailActivity extends AppCompatActivity {
    DNA dna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dna_detail);

        Intent intent = getIntent();
        dna = intent.getParcelableExtra("dna");

        if (dna != null){
            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(dna.getDescription());

        }
    }
}
