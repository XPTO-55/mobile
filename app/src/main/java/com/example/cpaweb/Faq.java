package com.example.cpaweb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cpaweb.adapters.VersionAdapter;

import java.util.ArrayList;
import java.util.List;

public class Faq extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        setRecycleView();
    }

    private void setRecycleView() {
        VersionAdapter versionAdapter = new VersionAdapter((versionsList));
        recyclerView.setAdapter(versionAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        versionsList = new ArrayList<>();
        versionsList.add(new Versions("Android 10", "Version 10", "Api level 29", "Descrição"));
    }
}