package com.example.bidzis.aplikacjatestowajsonrequest;

import android.content.Intent;
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

import java.util.Map;

public class AddChangeUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change_user);
        String s = "\"id\": 1, \"techDate\": 1462357685904, \"imie\": \"Polak\", \"nazwisko\": \"Mały\", \"email\": \"admin\", \"haslo\": \"123\", \"telefon\": \"444-444-444\", \"aktywnosc\": \"AKTYWNY\", \"rola\": {\"id\": 50, \"techDate\": 1462357685850, \"nazwa\": \"Szef\", \"uprawnienia\": [{\"id\": 50, \"techDate\": 1462357685717, \"nazwa\": \"ADMIN\"}]}\"";
        String userExa= getIntent().getStringExtra("aID");
        JSONObject userExample = null;
        try {
            userExample = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final EditText aID = (EditText) findViewById(R.id.etID);
        final EditText aImie = (EditText) findViewById(R.id.etImie);
        final EditText aNazwisko = (EditText) findViewById(R.id.etNazwisko);
        final EditText aNrTelefonu = (EditText) findViewById(R.id.etNrTelefonu);
        final EditText aGrupa = (EditText) findViewById(R.id.etGrupa);
        final EditText aEmail = (EditText) findViewById(R.id.etEmailAdress);
        final EditText aPassword = (EditText) findViewById(R.id.etPasswordR);
        final Button aDodajZmien = (Button) findViewById(R.id.btDodajZmień);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        assert aDodajZmien != null;
        final JSONObject[] finalUserExample1 = new JSONObject[1];


        assert aID != null;
        aID.setText("0");

        String url1 = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzPoId/"+userExa;
        JsonObjectRequest request2 = new JsonObjectRequest
                (Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            finalUserExample1[0] = new JSONObject(String.valueOf(response));
                            aID.setText(finalUserExample1[0].getString("id"));
                            assert aImie != null;
                            aImie.setText(finalUserExample1[0].getString("imie"));
                            assert aNazwisko != null;
                            aNazwisko.setText(finalUserExample1[0].getString("nazwisko"));
                            assert aNrTelefonu != null;
                            aNrTelefonu.setText(finalUserExample1[0].getString("telefon"));
                            assert aEmail != null;
                            aEmail.setText(finalUserExample1[0].getString("email"));
                            assert aPassword != null;
                            aPassword.setText(finalUserExample1[0].getString("haslo"));

                            JSONObject rola = finalUserExample1[0].getJSONObject("rola");
                            assert aGrupa != null;
                            aGrupa.setText(rola.getString("nazwa"));

                        } catch (JSONException e) {
                            e.printStackTrace();
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
        aDodajZmien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.84:8080/elista/uzytkownicy/zapiszUzytkownika";



                assert aGrupa != null;
                String url3 = "http://192.168.43.84:8080/elista/role/znajdzRolePoNazwie/"+aGrupa.getText().toString();
                Toast.makeText(getApplicationContext(), url3,
                        Toast.LENGTH_LONG).show();
                JsonObjectRequest request3 = new JsonObjectRequest
                        (Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    finalUserExample1[0].put("rola",response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                final JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.POST, url, finalUserExample1[0], new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    assert finalUserExample1[0] != null;
                                    finalUserExample1[0].put("id",aID.getText().toString());
                                    assert aImie != null;
                                    finalUserExample1[0].put("imie",aImie.getText().toString());
                                    assert aNazwisko != null;
                                    finalUserExample1[0].put("nazwisko",aNazwisko.getText().toString());
                                    assert aNrTelefonu != null;
                                    finalUserExample1[0].put("telefon",aNrTelefonu.getText().toString());
                                    assert aEmail != null;
                                    finalUserExample1[0].put("email",aEmail.getText().toString());
                                    assert aPassword != null;
                                    finalUserExample1[0].put("haslo",aPassword.getText().toString());
                                    finalUserExample1[0].put("aktywnosc","AKTYWNY");

                                    Toast.makeText(getApplicationContext(), "Dodano użytkownika",
                                            Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Toast.makeText(getApplicationContext(), "Timeoutek",
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




    }
}
