package com.example.todofragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickAdd,OnClickSave {
    TaskRecycler taskRecycler = TaskRecycler.create(this);
    AddTaskFragment addTaskFragment = AddTaskFragment.create(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change(taskRecycler);
    }
    public void change(Fragment fragment){
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,fragment );
        transaction.commit();
    }

    @Override
    public void onCLickAdd() {
        change(addTaskFragment);
    }

    @Override
    public void onClickSave(Task s) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("key",s);
        taskRecycler.setArguments(bundle);
        change(taskRecycler);

    }
}
