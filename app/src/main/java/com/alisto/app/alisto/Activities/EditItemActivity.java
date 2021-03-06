package com.alisto.app.alisto.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.alisto.app.alisto.R;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditTodo;
    Spinner svPriority;
    String prevValue;
    String prevPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etEditTodo = (EditText)findViewById(R.id.etEditTodo);
        svPriority = (Spinner)findViewById(R.id.svEditPriority);
        prevValue = getIntent().getStringExtra("description");
        prevPriority = getIntent().getStringExtra("priority");
        etEditTodo.setText(prevValue);
        svPriority.setSelection(getIndex(prevPriority));
        //etEditTodo.setSelection(etEditTodo.getText().length());
        Button btnEdit = (Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onSubmit(v);
            }
        });
    }

    public void onSubmit(View v) {
        //etEditTodo = (EditText) findViewById(R.id.etEditTodo);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("prevValue",prevValue);
        data.putExtra("editValue", etEditTodo.getText().toString());
        data.putExtra("editPriority",svPriority.getSelectedItem().toString());
        data.putExtra("code", 200); // ints work too
        data.putExtra("pos",getIntent().getIntExtra("position",-1));
        // Activity finished ok, return the data
        setResult(-1, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

    private int getIndex(String value){
        int position  = 0;
        switch (value){
            case "MEDIUM":position =1;
                            break;
            case "LOW" : position = 2;

        }

        return position;
    }

}
