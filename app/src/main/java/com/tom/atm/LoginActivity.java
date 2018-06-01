package com.tom.atm;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

   Button btnlog, btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

       btnlog = findViewById(R.id.btnlog);
       btncancel = findViewById(R.id.btncancel);

       btnlog.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             login(view);
          }
       });

       btncancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             cancel(view);
          }
       });

       /*EditText edUserid = findViewById(R.id.ed_userid);
       EditText edPasswd = findViewById(R.id.ed_passwd);
       SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
       edUserid.setText(setting.getString("PREF_USERID", ""));
       edPasswd.setText(setting.getString("PREF_PASSWD", ""));*/
    }

    public void login(View view) {
       EditText edUserid = findViewById(R.id.ed_userid);
       EditText edPasswd = findViewById(R.id.ed_passwd);
       String uid = edUserid.getText().toString();
       String pw = edPasswd.getText().toString();

       String url = new StringBuilder("http://atm201605.appspot.com/login?uid=")
               .append(uid)
               .append("&pw=")
               .append(pw)
               .toString();

       new LoginTask().execute(url);

       /*if(uid.equals("jack") && pw.equals("1234")) { //登入成功
            SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
            setting.edit().putString("PREF_USERID", uid).apply();
            setting.edit().putString("PREF_PASSWD", pw).apply();

            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            getIntent().putExtra("LOGIN_USERID", uid);
            getIntent().putExtra("LOGIN_PASSWD", pw);
            setResult(RESULT_OK, getIntent());
            finish();
        }

        else {  //登入失敗
            new AlertDialog.Builder(this)
                        .setTitle("Atm")
                        .setMessage("登入失敗")
                        .setPositiveButton("OK", null)
                        .show();
        }*/
    }

    public void cancel(View view){
      finish();
    }

    class LoginTask extends AsyncTask<String, Void, Boolean> {

       //strings = url = strings[0]
       @Override
       protected Boolean doInBackground(String... strings) {
          boolean logon = false;

          try {
             URL url = new URL(strings[0]);
             InputStream is = url.openStream();
             int data = is.read();
             Log.d("HTTP", String.valueOf(data));
             if(data == 49) {
                logon = true;
             }
             is.close();
          } catch (MalformedURLException e) {
             e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          }

          return logon;
       }

       @Override
       protected void onPostExecute(Boolean aBoolean) {
          super.onPostExecute(aBoolean);

          if(aBoolean) {
             //儲存帳號至SharedPreferences
             //結束本畫面 回到MainActivity
             Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
             setResult(RESULT_OK, getIntent());
             finish();
          }
          else {
             new AlertDialog.Builder(LoginActivity.this)
                     .setTitle("Atm")
                     .setMessage("Login Failed")
                     .setPositiveButton("OK", null)
                     .show();
          }
       }
    }
}
