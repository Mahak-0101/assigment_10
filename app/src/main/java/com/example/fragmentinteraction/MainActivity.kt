// MainActivity.java
package com.example.userapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName, etEmail;
    DBHelper db;
    RecyclerView recyclerView;
    List<String> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnFetch = findViewById(R.id.btnFetch);
        recyclerView = findViewById(R.id.recyclerView);

        db = new DBHelper(this);
        userList = new ArrayList<>();
        adapter = new UserAdapter(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnInsert.setOnClickListener(v -> {
            boolean success = db.insertUser(etName.getText().toString(), etEmail.getText().toString());
            showToast(success, "Insert");
        });

        btnUpdate.setOnClickListener(v -> {
            boolean success = db.updateUser(
                Integer.parseInt(etId.getText().toString()),
                etName.getText().toString(),
                etEmail.getText().toString()
            );
            showToast(success, "Update");
        });

        btnDelete.setOnClickListener(v -> {
            boolean success = db.deleteUser(Integer.parseInt(etId.getText().toString()));
            showToast(success, "Delete");
        });

        btnFetch.setOnClickListener(v -> fetchData());
    }

    private void showToast(boolean success, String action) {
        Toast.makeText(this, action + (success ? " successful" : " failed"), Toast.LENGTH_SHORT).show();
    }

    private void fetchData() {
        userList.clear();
        Cursor cursor = db.getAllUsers();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            userList.add(id + ": " + name + " - " + email);
        }
        adapter.notifyDataSetChanged();
    }
}
