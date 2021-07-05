package com.example.culture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    TextView foodsTitle = findViewById(R.id.titleDetail);
    TextView foodsDetail = findViewById(R.id.foodDetail);
    foodsTitle.setText(getIntent().getStringExtra("title"));
    foodsDetail.setText(getIntent().getStringExtra("detail"));
  }

}
