package com.example.prodigystudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Personal_Profile extends AppCompatActivity {
    EditText vaddr,vroll;
    TextView vname,vfname,vmname,vmob,vclass,vsection,vct,vfee,vcmnt,vsadhr, vpadhr;
    String temp;
    String sname,sfname, smname, smob,saddr,sclass,ssection,sct,sfee,scmnt,ssadhr,spadhr;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__profile);
        vname=findViewById(R.id.vname);
        vfname=findViewById(R.id.vfname);
        vmname=findViewById(R.id.vmname);
        vmob=findViewById(R.id.vmob);
        vaddr=findViewById(R.id.vaddr);
        vclass=findViewById(R.id.vclass);
        vsection=findViewById(R.id.vsection);
        vct=findViewById(R.id.vclsteach);
        vfee=findViewById(R.id.vfee);
        vcmnt=findViewById(R.id.vcomnts);
        vsadhr=findViewById(R.id.vsadhr);
        vpadhr=findViewById(R.id.vpadhr);
        vroll=findViewById(R.id.vroll);
        progressDialog=new ProgressDialog(this);
        temp=getRoll();
        getData();
        new BackgroundJob().execute();
    }

    private void getData() {
        RequestQueue rq= Volley.newRequestQueue(this);
        StringBuilder sb= new StringBuilder();
        sb.append(Config.DATA_URL1+temp);
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
        sname="";
        sfname="";
        smname="";
        smob="";
        saddr="";
        sclass="";
        ssection="";
        sct="";
        sfee="";
        scmnt="";
        ssadhr="";
        spadhr="";
        try {
            JSONObject jo=new JSONObject(response).getJSONArray(Config.KEY_RESULT).getJSONObject(0);
            sname=jo.getString(Config.KEY_Name);
            sfname=jo.getString(Config.KEY_FATHER);
            smname=jo.getString(Config.KEY_MOTHER);
            smob=jo.getString(Config.KEY_Mobile);
            saddr=jo.getString(Config.KEY_Address);
            sclass=jo.getString(Config.KEY_CLASS);
            ssection=jo.getString(Config.KEY_Section);
            sct=jo.getString(Config.KEY_CLASSTEACHER);
            sfee=jo.getString(Config.KEY_FEES);
            scmnt=jo.getString(Config.KEY_COMMENTS);
            ssadhr=jo.getString(Config.KEY_Adhar);
            spadhr=jo.getString(Config.KEY_Paradhar);

        } catch (JSONException e) {

        }
        vname.setText(sname);
        vfname.setText(sfname);
        vmname.setText(smname);
        vmob.setText(smob);
        vaddr.setText(saddr);
        vclass.setText(sclass);
        vsection.setText(ssection);
        vct.setText(sct);
        vfee.setText(sfee);
        vcmnt.setText(scmnt);
        vsadhr.setText(ssadhr);
        vpadhr.setText(spadhr);
        vroll.setText(temp);
    }

    private String getRoll() {
        String var;
        SharedPreferences sharedPreferences=getSharedPreferences("application", Context.MODE_PRIVATE);
        var=sharedPreferences.getString("Id","");
        return var;
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
