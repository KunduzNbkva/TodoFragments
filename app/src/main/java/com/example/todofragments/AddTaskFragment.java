package com.example.todofragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {
    EditText title;
    EditText description;
    EditText deadline;
    Button saveButton;
    OnClickSave onClickSave;

    public static AddTaskFragment create(OnClickSave listener) {
        AddTaskFragment fragment = new AddTaskFragment();
        fragment.onClickSave = listener;
        return fragment;
    }


    public AddTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title=view.findViewById(R.id.title);
        description=view.findViewById(R.id.description);
        deadline=view.findViewById(R.id.deadline);
        saveButton=view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString().trim();
                }

                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString().trim();
                }

                task.deadline = deadline.getText().toString();
                onClickSave.onClickSave(task);
            }
        });


    }
    public void showMessage(String s){
        Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
    }
}


