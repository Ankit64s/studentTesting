package com.example.prodigystudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class attendance extends AppCompatActivity {
EditText present,total,percent;
String temp,pre,tot,per;
Button viewpercent;
double perc;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        present=findViewById(R.id.present);
        total=findViewById(R.id.total);
        percent=findViewById(R.id.percent);
        viewpercent=findViewById(R.id.viewpercent);
        progressDialog=new ProgressDialog(this);
        temp=getRoll();
        getData();
        new BackgroundJob().execute();

        viewpercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df=new DecimalFormat("#.##");
                double i,j;
                pre=present.getText().toString();
                tot=total.getText().toString();
                int ii=Integer.parseInt(tot);
                try{if(ii== 0){
                    percent.setText("NA");
                }
                else{
                    i=Double.parseDouble(pre);
                    j=Double.parseDouble(tot);
                    perc=(i/j);
                    perc=perc*100.0;
                    perc=Double.parseDouble(df.format(perc));
                    per=Double.toString(perc);
                    percent.setText(per);
                }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });



    }
    private String getRoll() {
        String var;
        SharedPreferences sharedPreferences=getSharedPreferences("application", Context.MODE_PRIVATE);
        var=sharedPreferences.getString("Id","");
        return var;
    }
    private void getData() {
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL2+temp);
        rq.add(new StringRequest(sb.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Data Fetched", Toast.LENGTH_SHORT).show();
                ShowJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Data Not Fetched", Toast.LENGTH_SHORT).show();
            }
        }));
    }
    public void ShowJson(String response) {
        pre="";
        tot="";
        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            pre=jo.getString(Config.KEY_Attendance);
            tot=jo.getString(Config.KEY_TotalDays);


        } catch (JSONException e) {

        }
        present.setText(pre);
        total.setText(tot);
    }
    private class BackgroundJob extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Record");
            progressDialog.setMessage("Fetching Student's Record.....");
            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }
}
