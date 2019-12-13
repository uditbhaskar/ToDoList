package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TodoModel item = null;
    EditText todoTitle, todoDescription;
    Button saveButton;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        todoTitle = findViewById(R.id.toDoTitle);
        todoDescription = findViewById(R.id.toDoDescription);
        saveButton = findViewById(R.id.add_button);

        if(getIntent().getExtras()!=null){
            item = new TodoModel(getIntent().getExtras().getInt("_id"),
                    getIntent().getExtras().getString("title"),
                    getIntent().getExtras().getString("description"),
                    getIntent().getExtras().getString("time"));

            todoTitle.setText(item.getNoteTitle());
            todoDescription.setText(item.getNoteDesrp());

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todoTitle.getText().toString().length() > 0
                        && todoDescription.getText().toString().length() > 0) {
                    if(item!=null){
                        updateTodo();
                    }
                    else if(item == null){
                        addTodo();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "First add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addTodo() {
        DBManager dbManager = new DBManager(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", todoTitle.getText().toString().trim());
        contentValues.put("description", todoDescription.getText().toString().trim());
        contentValues.put("time", System.currentTimeMillis() + "");

        long id = dbManager.Insert(contentValues);
        if (id > 0) {
            Toast.makeText(this, "Your todo item has been added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to add!!!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public void updateTodo() {
        DBManager dbManager = new DBManager(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", todoTitle.getText().toString().trim());
        contentValues.put("description", todoDescription.getText().toString().trim());
        contentValues.put("time", System.currentTimeMillis() + "");
        String[] selectionArgs = {item.getNoteId()+""};

        long id = dbManager.Update(contentValues,"_id=?", selectionArgs);

        if (id > 0) {
            Toast.makeText(this, "Your todo item has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Unable to update!!!", Toast.LENGTH_SHORT).show();
        }

        finish();
    }




}
