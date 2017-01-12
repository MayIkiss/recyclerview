package com.example.wangguanghong.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggerActivity extends AppCompatActivity {
    private List<String> mListDatas;
    private RecyclerView mRecyclerView;
    private StaggerAdapter2 mStaggerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initData();
        initView();
        mStaggerAdapter=new StaggerAdapter2(this,mListDatas);
        mRecyclerView.setAdapter(mStaggerAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStaggerAdapter.setListener(new StaggerAdapter2.OnClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(getApplicationContext(),"check"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(getApplicationContext(),"check"+position,Toast.LENGTH_SHORT).show();
            }
        });
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
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.additem:
                mStaggerAdapter.addItem();
                break;
            case R.id.deleteitem:
                mStaggerAdapter.deleteItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
