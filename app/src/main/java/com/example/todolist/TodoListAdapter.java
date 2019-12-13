package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    Context context;
    ArrayList<TodoModel> todoList;

    TodoListAdapter(Context context, ArrayList<TodoModel> list) {
        this.todoList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Returning view
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Setting text
        holder.todoTitle.setText(todoList.get(position).getNoteTitle());
        holder.todoDescription.setText(todoList.get(position).getNoteDesrp());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView todoTitle;
        TextView todoDescription;
        ImageView edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTitle = itemView.findViewById(R.id.toDoTitle);
            todoDescription = itemView.findViewById(R.id.toDoDescription);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.edit){
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", todoList.get(getAdapterPosition()).getNoteTitle());
                intent.putExtra("description", todoList.get(getAdapterPosition()).getNoteDesrp());
                intent.putExtra("_id",todoList.get(getAdapterPosition()).getNoteId());
                intent.putExtra("time",todoList.get(getAdapterPosition()).getTime());
                context.startActivity(intent);
            }
            else if(v.getId() == R.id.delete){
                deleteTodo(todoList.get(getAdapterPosition()));
                ((TodoListActivity)context).LoadQuery();
            }

        }

        public void deleteTodo(TodoModel item) {
            DBManager dbManager = new DBManager(context);
            String[] selectionArgs = {item.getNoteId() + ""};
            dbManager.Delete("_id=?", selectionArgs);
            Toast.makeText(context, "Todo deleted!!", Toast.LENGTH_SHORT).show();

        }

    }


}
