package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TodoListActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView recyclerView;
    TodoListAdapter adapter;
    ArrayList<TodoModel> todoList;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView);
    }

    public void setRecyclerView(ArrayList<TodoModel> list){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TodoListAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public void LoadQuery(){
        todoList = new ArrayList<>();
        DBManager dbManager = new DBManager(this);
        String[] projections = {"_id","title","description","time"};
        String[] selectionArgs = {""};
        Cursor cursor = dbManager.Query(projections, "", null, null);

        if(cursor.getCount() == 0){
            //No data..
            Toast.makeText(this, "No todo for today!!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(cursor.moveToFirst()){
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));
                    String time = cursor.getString(cursor.getColumnIndex("time"));

                    todoList.add(new TodoModel(id, title, description, time));
                }while (cursor.moveToNext());
            }
        }
        if(todoList != null && !todoList.isEmpty()){
            Collections.reverse(todoList);
            setRecyclerView(todoList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadQuery();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_button){
            startActivity(new Intent(TodoListActivity.this, DetailActivity.class));
        }
    }


}
