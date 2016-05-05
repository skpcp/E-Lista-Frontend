package com.example.bidzis.aplikacjatestowajsonrequest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ActivateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        final String[] elo = new String[1];
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONArray[] jsonArray = {null};
        String url1 = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzPoAktywnosci/NIEAKTYWNY";
        final JSONArray[] finalJsonArray = {new JSONArray()};
        JsonArrayRequest request2 = new JsonArrayRequest
                (Request.Method.GET, url1, null, new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        finalJsonArray[0] = response;
//                        Toast.makeText(getApplicationContext(), "1",
//                                Toast.LENGTH_LONG).show();
                        jsonArray[0] = finalJsonArray[0];
                        ArrayList<String> wartosc = new ArrayList<>();
                        if (jsonArray[0] != null) {
                            int len = jsonArray[0].length();
                            for (int i=0;i<len;i++){
                                try {
                                    JSONObject pomoc = (JSONObject) jsonArray[0].get(i);

                                    wartosc.add(i,pomoc.get("id") +"\n"+ pomoc.getString("imie")+" "+pomoc.getString("nazwisko")+"\n"+"Status: "+pomoc.getString("aktywnosc"));
                                    // list.add(jsonArray[0].get(i).toString());
//                                    Toast.makeText(getApplicationContext(), jsonArray[0].get(i).toString(),
//                                            Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        final ListView listview = (ListView) findViewById(R.id.listview);
                        Iterator it = wartosc.iterator();
                        // String[] values = (String[]) wartosc.toArray();
                        final ArrayList<String> list = new ArrayList<String>();
                        while ( it.hasNext( ) ) {
                            list.add((String) it.next());
                        }
//                        for (int i = 0; i < wartosc; ++i) {
//                            list.add(values[i]);
//                        }
                        final StableArrayAdapter adapter = new StableArrayAdapter(ActivateActivity.this,
                                android.R.layout.simple_list_item_1, list);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                final String aTekst = ((TextView)view).getText().toString();
                                final String aId = String.valueOf(aTekst.charAt(0));
                                final JSONObject[] uzytkownik = {new JSONObject()};

                                String url = "http://192.168.43.84:8080/elista/uzytkownicy/pobierzPoId/"+aId;

                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        assert uzytkownik != null;
                                        uzytkownik[0] = response;

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
                                requestQueue.add(request);
                                final String url2 = "http://192.168.43.84:8080/elista/uzytkownicy/aktywujPoId/"+aId;
                                JsonObjectRequest request3 = new JsonObjectRequest
                                        (Request.Method.PUT, url2, uzytkownik[0], new Response.Listener<JSONObject>() {


                                            @Override
                                            public void onResponse(JSONObject response) {

                                                Toast.makeText(getApplicationContext(), "Użytkownik aktywowany",
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

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
