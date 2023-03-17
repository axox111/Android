package com.example.vkinfo;

import static com.example.vkinfo.utils.NetworkUtils.generateURL;
import static com.example.vkinfo.utils.NetworkUtils.getResponseFromURL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileLockInterruptionException;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private TextView result;

    class VKQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response){
            String firstName = null;
            String lastName = null;
            try {
                JSONObject result = new JSONObject(response);
                JSONArray jsonArray = result.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);
                firstName = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            String fullName = firstName + " " + lastName;
            result.setText(fullName);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL generateURL = generateURL(searchField.getText().toString());
                new VKQueryTask().execute(generateURL);
            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}