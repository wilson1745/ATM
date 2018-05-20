package com.tom.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_finance);

      ListView list = findViewById(R.id.list);

      //MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
      MyDBHelper helper = MyDBHelper.getInstance(this);

      Cursor c = helper.getReadableDatabase().query(
              "exp", null, null, null, null, null, "cdate ASC");
      SimpleCursorAdapter adapter = new SimpleCursorAdapter(
              this,
              R.layout.finance_row,
              c,
              new String[] {"cdate", "info", "amount"},
              new int[] {R.id.item_cdate, R.id.item_info, R.id.item_amount},
              0);
      list.setAdapter(adapter);

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      //左下浮動鈕
      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            startActivity(new Intent(FinanceActivity.this, AddActivity.class));
         }
      });
   }

}
