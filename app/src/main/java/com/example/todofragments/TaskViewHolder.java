package com.example.todofragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView description;
    TextView deadline;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.item_title);
        description=itemView.findViewById(R.id.item_description);
        deadline= itemView.findViewById(R.id.item_deadline);
    }

    public void onBind(Task task) {
        title.setText(task.title);
        description.setText(task.description);
        deadline.setText(task.deadline);



    }
}
