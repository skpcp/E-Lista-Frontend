package com.example.bidzis.aplikacjatestowajsonrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Response.*;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        final EditText Imie = (EditText) findViewById(R.id.etName);
        final EditText Nazwisko = (EditText) findViewById(R.id.etSurname);
        final EditText Numer = (EditText) findViewById(R.id.etPhoneNumber);
        final EditText Email = (EditText) findViewById(R.id.etEmailRegi);
        final EditText Haslo = (EditText) findViewById(R.id.etHaslo);
        final Button Zarejestruj = (Button) findViewById(R.id.etRejestracja);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        final JSONObject[] uzytkownik = {new JSONObject()};

        String url = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzPoId/1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                uzytkownik[0] = response;

            }

        }, new ErrorListener() {

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
        requestQueue.add(request);
        final String url2 = "http://192.168.43.84:8080/elista/uzytkownicy/zapiszUzytkownika";
        assert Zarejestruj != null;
        Zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest request3 = new JsonObjectRequest
                        (Request.Method.POST, url2, uzytkownik[0], new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    uzytkownik[0].put("id",0);

                                    assert Imie != null;
                                    uzytkownik[0].put("imie",Imie.getText().toString());
                                    assert Nazwisko != null;
                                    uzytkownik[0].put("nazwisko",Nazwisko.getText().toString());
                                    assert Numer != null;
                                    uzytkownik[0].put("telefon",Numer.getText().toString());
                                    assert Email != null;
                                    uzytkownik[0].put("email",Email.getText().toString());
                                    assert Haslo != null;
                                    uzytkownik[0].put("haslo",Haslo.getText().toString());
                                    uzytkownik[0].put("aktywnosc","OCZEKUJACY");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(getApplicationContext(), "eloelo",
                                        Toast.LENGTH_LONG).show();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
                                    Toast.makeText(getApplicationContext(), "Bląd serweraaaa",
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
                requestQueue.add(request3);
            }
        });

    }
}
