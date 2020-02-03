package com.example.todofragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskRecycler extends Fragment {
    RecyclerView recyclerTask;
    TaskAdapter taskAdapter;
    ArrayList<Task> tasks = new ArrayList<>();
    Button addTask;
    OnClickAdd onClickAdd;


    public static TaskRecycler create(OnClickAdd listener) {
        TaskRecycler fragment = new TaskRecycler();
        fragment.onClickAdd = listener;
        return fragment;
    }
    public TaskRecycler() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (savedInstanceState != null) {
            tasks.add((Task)savedInstanceState.getSerializable("key"));
        }
        //ArrayList<Task> savedTasks = Storage.read(getContext());
        //tasks = savedTasks;

        return inflater.inflate(R.layout.fragment_task_recycler, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerTask = view.findViewById(R.id.taskRecycler);
        taskAdapter = new TaskAdapter(tasks);
        recyclerTask.setAdapter(taskAdapter);
        addTask = view.findViewById(R.id.addButton);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdd.onCLickAdd();

            }
        });
        Bundle bundle =getArguments();
        if (bundle != null) {
            tasks.add((Task)bundle.getSerializable("key"));
            taskAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 42) {
            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
            taskAdapter.notifyDataSetChanged();
            Storage.save(tasks, getContext());
        }
    }
}
