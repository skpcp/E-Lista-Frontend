package com.example.bidzis.aplikacjatestowajsonrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestowaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testowa);


        final ArrayList<String> list = new ArrayList<String>();
        final String[] elo = new String[1];
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONArray[] jsonArray = {null};
        String url1 = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzWszystkich";
        final JSONArray[] finalJsonArray = {new JSONArray()};
        JsonArrayRequest request2 = new JsonArrayRequest
                (Request.Method.GET, url1, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                       finalJsonArray[0] = response;
                        Toast.makeText(getApplicationContext(), "1",
                                Toast.LENGTH_LONG).show();
                        jsonArray[0] = finalJsonArray[0];

                        if (jsonArray[0] != null) {
                            int len = jsonArray[0].length();
                            for (int i=0;i<len;i++){
                                try {
                                    list.add(jsonArray[0].get(i).toString());
                                    Toast.makeText(getApplicationContext(), jsonArray[0].get(i).toString(),
                                            Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "Timeouttt",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "1",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Bląd serwera",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Problem z połączeniem internetowym",
                                    Toast.LENGTH_LONG).show();

                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Nie znaleziono użytkownika w bazie",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });


        requestQueue.add(request2);
        jsonArray[0] = finalJsonArray[0];

        if (jsonArray[0] != null) {
            int len = jsonArray[0].length();
            for (int i=0;i<len;i++){
                try {
                    list.add(jsonArray[0].get(i).toString());
                    Toast.makeText(getApplicationContext(), jsonArray[0].get(i).toString(),
                            Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
