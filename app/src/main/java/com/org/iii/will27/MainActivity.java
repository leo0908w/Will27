package com.org.iii.will27;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

    }

    public void test1(View v) {
        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute("will", "brad", "aaa", "bbb");
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("will", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... names) {
            int i = 0;

            for (String name : names) {
                Log.v("will", "doInBackground: " + name);
                publishProgress(i, i+10, i+100);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
                i++;
            }
            return "ok";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0] + " : " + values[1] + " : " + values[2]);
            Log.v("will", "onProgressUpdate");
        }

        @Override
        protected void onPostExecute(String  result) {
            super.onPostExecute(result);
            Log.v("will", "onPostExecute" + result);
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
            Log.v("will", "onCancelled");
        }
    }
}
