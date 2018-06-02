package com.tom.atm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TransActivity extends AppCompatActivity {

   OkHttpClient client = new OkHttpClient();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_trans);

      //new TransTask().execute("http://atm201605.appspot.com/h");

      Request request = new Request.Builder()
              .url("http://atm201605.appspot.com/h")
              .build();
      Call call = client.newCall(request);
      call.enqueue(new Callback() {
         @Override
         public void onFailure(Call call, IOException e) {
            //告知使用者連線失敗
         }

         @Override
         public void onResponse(Call call, Response response) throws IOException {
            String json = response.body().string();

            Log.d("OKHTTP", json);
            //解析JSON
            parseJSON(json);
         }
      });
   }

   private void parseJSON(String json) {
   }

   class TransTask extends AsyncTask<String, Void, String> {

      @Override
      protected String doInBackground(String... strings) {
         StringBuilder sb = new StringBuilder();

         try {
            URL url = new URL(strings[0]);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while(line != null) {
               Log.d("HTTP", line);
               sb.append(line);
               line = in.readLine();
            }
         } catch (MalformedURLException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }

         return sb.toString();
      }

      @Override
      protected void onPostExecute(String s) {
         super.onPostExecute(s);

         Log.d("HTTP", s);
         parseJSON(s);

      }

      private void parseJSON(String s) {

      }
   }
}
