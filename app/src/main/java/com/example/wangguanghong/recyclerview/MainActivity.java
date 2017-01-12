package com.example.wangguanghong.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mListDatas;
    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        initView();
        mDataAdapter=new DataAdapter(this,mListDatas);
        mRecyclerView.setAdapter(mDataAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        mRecyclerView=(RecyclerView)findViewById(R.id.test_recyclerView);
    }

    private void initData() {
        mListDatas=new ArrayList<String>();
        for(int i='A';i<'z';i++){
            mListDatas.add((char)i+"");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.staggerview:
                Intent intent=new Intent(this,StaggerActivity.class);
                startActivity(intent);
                break;
            case R.id.additem:
                mDataAdapter.addItem();
                break;
            case R.id.deleteitem:
                mDataAdapter.deleteItem();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
