package com.example.culture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailNew extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newdetail);

    TextView streetTitle = findViewById(R.id.s_titleDetail);
    TextView streetDetail = findViewById(R.id.streetDetail);
    streetTitle.setText(getIntent().getStringExtra("title"));
    streetDetail.setText(getIntent().getStringExtra("detail"));
  }
}
