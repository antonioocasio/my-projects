package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements DnaAdapter.DnaAdapterOnClickHandler {
    private static final String TAG = "DEBUG";
    private final static String  URL_STRING = "http://api.plos.org/search?q=title:DNA";
    private ProgressBar progressBar;
    private DnaAdapter dnaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        dnaAdapter = new DnaAdapter(this);
        recyclerView.setAdapter(dnaAdapter);

    }

    @Override
    public void onClick(DNA dna) {
        Intent detailIntent = new Intent(this, DnaDetailActivity.class);
        detailIntent.putExtra("dna", dna);
        startActivity(detailIntent);
    }

    public void findAPIsButtonClick(View v) {
        DownloadData downloadData = new DownloadData(this);
        downloadData.execute(URL_STRING);
    }

    private static class DownloadData extends AsyncTask<String, Void, ArrayList<DNA>> {
        private WeakReference<MainActivity> activityReference;

        public DownloadData(MainActivity activityReference){
            this.activityReference = new WeakReference<>(activityReference);
        }

        @Override
        protected void onPreExecute() {
            MainActivity mainActivity = activityReference.get();
            mainActivity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<DNA> doInBackground(String... strings) {
            // declares all the variables
            String title, description, date;
            DNA dna = new DNA(null, null, null, null);
            String jsonData = "";
            ArrayList<DNA> dnaArray = new ArrayList<>();
            HttpURLConnection urlConnection = null;

            try {
                //opens connection to url
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                // gets data from urlConnection
                InputStream inputStream = urlConnection.getInputStream();

                // reads inputStream
                Scanner sc = new Scanner(inputStream);
                sc.useDelimiter("\\A");

                // checks if scanner has input
                boolean hasInput = sc.hasNext();

                if (hasInput){
                    // puts data into jsonData arrayList
                    jsonData = sc.next();
                }

                // gets the jsonObject from jsonData which is from the URL
                JSONObject jsonObject = new JSONObject(jsonData);

                // gets the first object which is "response"
                JSONObject jsonResponse = jsonObject.getJSONObject("response");

                // gets the array "docs" from the object "response"
                JSONArray jsonDocs = jsonResponse.getJSONArray("docs");

                // gets all elements from the jsonDocs array
                for (int i = 0; i < jsonDocs.length(); i++){
                    JSONObject docsObject = jsonDocs.getJSONObject(i);

                    // gets the date from "docs"
                    date = docsObject.getString("publication_date");

                    // gets title from "docs"
                    title = docsObject.getString("title_display");

                    // gets the "abstract" array from "docs" then gets the first element from array
                    description = docsObject.getJSONArray("abstract").getString(0);

                    // creates new dna object with the info we got
                    dna = new DNA(title, date, description, URL_STRING);

                    // adds the new dna object to the dnaArray
                    dnaArray.add(dna);
                }

                return dnaArray;

            } catch (Exception ex){
                // if there is an error display message to the logcat
                Log.d("error", ex.toString());

            } finally {
                if (urlConnection != null){
                    // once its finished running, close the connection
                    urlConnection.disconnect();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<DNA> dnas) {
            MainActivity activity = activityReference.get();


            if (activity == null || activity.isFinishing()) return;
            activity.progressBar.setVisibility(View.GONE);

            if(dnas != null){
                activity.dnaAdapter.setDnaData(dnas);
            }
        }
    }
}
