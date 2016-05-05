package com.example.bidzis.aplikacjatestowajsonrequest;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserSiteActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] choices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_site);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        choices = getResources().getStringArray(R.array.choices);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1));
    }
}
