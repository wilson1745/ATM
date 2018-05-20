package com.tom.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

   private EditText edDate;
   private EditText edInfo;
   private EditText edAmount;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add);

      findViews();
   }

   public void add(View view) {

   }

   private void findViews() {
      edDate = findViewById(R.id.ed_date);
      edInfo = findViewById(R.id.ed_info);
      edAmount = findViewById(R.id.ed_amount);
   }
}
