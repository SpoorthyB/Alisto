package com.alisto.app.alisto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditTodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etEditTodo = (EditText)findViewById(R.id.etEditTodo);
        String username = getIntent().getStringExtra("text");
        etEditTodo.setText(username);
    }

}
