package com.example.bidzis.aplikacjatestowajsonrequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UsersMenagmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_menagment);
        final Button addUser = (Button) findViewById(R.id.btAddChangeUser);
        final Button activeUser = (Button) findViewById(R.id.btActiveUser);
        final Button deactivateUser = (Button) findViewById(R.id.btActiveDeactiveUsers);
        final Button activateAcount = (Button) findViewById(R.id.btActiveAcount);
        String userString = getIntent().getStringExtra("user");
        assert addUser != null;
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString = getIntent().getStringExtra("user");
                Intent intent = new Intent(UsersMenagmentActivity.this, AddChangeUserActivity.class);
                intent.putExtra("userOB",userString);
                UsersMenagmentActivity.this.startActivity(intent);
            }
        });
        assert activeUser != null;
        activeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersMenagmentActivity.this, ListViewExampleActivity.class);
                UsersMenagmentActivity.this.startActivity(intent);
            }
        });
        assert deactivateUser != null;
        deactivateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersMenagmentActivity.this, DeactivateUsersActivity.class);
                UsersMenagmentActivity.this.startActivity(intent);
            }
        });
        assert activateAcount != null;
        activateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersMenagmentActivity.this, ActivateActivity.class);
                UsersMenagmentActivity.this.startActivity(intent);
            }
        });
    }

}

