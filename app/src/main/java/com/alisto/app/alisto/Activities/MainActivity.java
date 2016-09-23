package com.alisto.app.alisto.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;
import com.alisto.app.alisto.Adapters.TodoAdapter;
import com.alisto.app.alisto.Models.TodoModel;
import com.alisto.app.alisto.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ArrayList<String> todoItems;
    ArrayList<TodoModel> todoItemRows;
    //ArrayAdapter<String> itemsAdapter;
    TodoAdapter itemsAdapter;
    ListView lvTodoList;
    EditText etEditText;
    final int  REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateArrayItems();
        lvTodoList = (ListView)findViewById(R.id.lvTodoList);
        lvTodoList.setAdapter(itemsAdapter);
        etEditText = (EditText)findViewById(R.id.etNewtodo);
        lvTodoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                TodoModel item = todoItemRows.get(position);
                todoItemRows.remove(position);
                itemsAdapter.notifyDataSetChanged();
                deleteModelData(item.description);
                return true;
            }
        });
        lvTodoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(MainActivity.this, EditItemActivity.class);
                in.putExtra("position", i);
                in.putExtra("text", lvTodoList.getItemAtPosition(i).toString());
                startActivityForResult(in, REQUEST_CODE);
            }
        });
        ActiveAndroid.initialize(this);
    }
    public void populateArrayItems(){
        //readitems();
        todoItemRows = TodoModel.getMockTodos();
        itemsAdapter = new TodoAdapter(this,todoItemRows);
    }

    private void readitems(){

        TodoModel todos = new TodoModel();
        todoItemRows = new ArrayList<TodoModel>();
        List<TodoModel> queryResult = todos.getAll();
        Iterator<TodoModel> iter = queryResult.iterator();
        while(iter.hasNext()) {
            todoItemRows.add((iter.next()));
        }

    }

    private void setModelData(){
        TodoModel todos = new TodoModel();

        for (TodoModel each:todoItemRows) {
            todos.description = each.description;
            todos.priority = each.priority;
            todos.save();
        }
    }

    private void updateModelData(String prevValue, String desc){

        TodoModel todos = new TodoModel();
        TodoModel result = todos.findRowdesc(prevValue);
        result.description = desc;
        result.save();
    }

    private void deleteModelData(String desc){
        TodoModel todos = new TodoModel();
        todos.deleteItemDesc(desc);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            TodoModel editValue = new TodoModel("abc","HIGH",new Date()); //= data.getExtras().get("editValue");
            int pos = data.getExtras().getInt("pos");
            todoItemRows.set(pos, editValue);
            itemsAdapter.notifyDataSetChanged();
            String prevValue = data.getExtras().getString("prevValue");
            //updateModelData(prevValue, editValue);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds todoItems to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View view) {
        if(etEditText.getText().toString()!="") {
            itemsAdapter.add((TodoModel)etEditText.getText());
            etEditText.setText("");
            setModelData();
        }
    }
}
