package com.tom.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
   public static final int RC_LOGIN = 1;
   boolean logon = false;
   String[] func = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
   int[] icons = {
           R.drawable.func_balance,
           R.drawable.func_history,
           R.drawable.func_news,
           R.drawable.func_finance,
           R.drawable.func_exit
   };

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //使用GridView
      GridView grid = findViewById(R.id.grid);
      final IconAdapter gAdapter = new IconAdapter();
      /*ArrayAdapter gAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);*/
      grid.setAdapter(gAdapter);
      /*grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this,
                    gAdapter.getItem(i).toString(), Toast.LENGTH_LONG).show();
         }
      });*/
      grid.setOnItemClickListener(this);

      //使用Spinner
      Spinner notify = findViewById(R.id.spinner);
      final ArrayAdapter nAdapter = ArrayAdapter.createFromResource(
              this, R.array.notify_array, android.R.layout.simple_spinner_item);
      nAdapter.setDropDownViewResource(
              android.R.layout.simple_spinner_dropdown_item);
      notify.setAdapter(nAdapter);
      notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this,
                    nAdapter.getItem(position).toString(), Toast.LENGTH_LONG).show();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
      });

      //使用ListView
      ListView list = findViewById(R.id.list);
      ArrayAdapter adapter = new ArrayAdapter(
              this, android.R.layout.simple_list_item_1, func);
      list.setAdapter(adapter);

      //測試TestActivity
      //startActivity(new Intent(this, TestActivity.class));

      if (!logon) { //如未登入, 則開啟LoginActivity
         Intent intent = new Intent(this, LoginActivity.class);
         //startActivity(intent);
         startActivityForResult(intent, RC_LOGIN);
      }
   }

   class IconAdapter extends BaseAdapter{

      @Override
      public int getCount() {
         return func.length;
      }

      @Override
      public Object getItem(int position) {
         return func[position];
      }

      @Override
      public long getItemId(int position) {
         return icons[position];
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
         View row = convertView;
         if (row == null){
            row = getLayoutInflater().inflate(R.layout.item_row, null);
            ImageView image = row.findViewById(R.id.item_image);
            TextView text = row.findViewById(R.id.item_text);
            image.setImageResource(icons[position]);
            text.setText(func[position]);
         }
         return row;
      }
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();
      if (id == R.id.action_setting) {
         return true;
      }
      return super.onOptionsItemSelected(item);

   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == RC_LOGIN) {
         if (resultCode == RESULT_OK) {
            String uid = data.getStringExtra("LOGIN_USERID");
            String pw = data.getStringExtra("LOGIN_PASSWD");
            Log.d("RESULT", uid + "/" + pw);
         } else {
            finish();
         }
      }
   }

   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      /*switch (position) {
         case 0:
            break;
         case 1:
            break;
         case 2:
            break;
         case 3:
            break;
         case 4:
            finish();
            break;
      }*/

      switch ((int)id) {
         case R.drawable.func_balance:
            break;
         case R.drawable.func_history:
            startActivity(new Intent(this, TransActivity.class));
            break;
         case R.drawable.func_news:
            break;
         case R.drawable.func_finance:
            startActivity(new Intent(this, FinanceActivity.class));
            break;
         case R.drawable.func_exit: //結束
            finish();
            break;
      }
   }
}