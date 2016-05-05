package com.example.bidzis.aplikacjatestowajsonrequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText tLogin = (EditText) findViewById(R.id.etlogin);
        final TextView tvLogin = (TextView) findViewById(R.id.tvLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvPassword = (TextView) findViewById(R.id.tvPassword);

        final Button bLogin = (Button) findViewById(R.id.btLogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);




        assert bLogin != null;
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert tLogin != null;
                final String login = tLogin.getText().toString();
                String url = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzPoEmailuId/"+login;
                final String haslo = etPassword.getText().toString();

                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if(haslo.equals(response.getString("haslo"))){
                                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                        intent.putExtra("userObject",response.toString());
                                        MainActivity.this.startActivity(intent);

                                    }else{
                                        Toast.makeText(getApplicationContext(), "Nieprawidłowe hasło",
                                                Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "Nie znaleziono użytkownika",
                                            Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Toast.makeText(getApplicationContext(), "Timeout",
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
//                                Toast.makeText(getApplicationContext(), "Błąd połączenia",
//                                        Toast.LENGTH_LONG).show();

                            }
                        });


                requestQueue.add(request);

            }
        });
        assert tvRegister != null;
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}
