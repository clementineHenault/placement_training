package com.example.db_interaction_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Connect to the webservice to insert the new rep into the database
     * @param view
     */
    public void registerRepresentative(View view) {
        // Get the new rep's username and password from the EditTexts
        EditText usernameEd = findViewById(R.id.rep_username_ed);
        String username = usernameEd.getText().toString();
        EditText passwordEd = findViewById(R.id.rep_password_ed);
        String password = passwordEd.getText().toString();

        // Connect to the webservice and send request
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    sendRegisterRequest(username, password);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public void sendRegisterRequest(String username, String password) throws IOException {
        URL url = new URL("http://10.0.2.2:8080/Placement_side_project/register");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            // Set connection options
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.connect();

            String paramsString = "username=" + username + "&password=" + password;

            // Send the request with the username and password
            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
            writer.write(paramsString);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Receive the result
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Display result from the server in the log
            Log.d("test", "result from server: " + result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}