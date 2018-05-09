package com.example.huynguyen.ggs;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.huynguyen.ggs.adapter.TopicAdapter;
import com.example.huynguyen.ggs.model.TopicData;
import com.example.huynguyen.ggs.model.TopicModel;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity {

    private TopicAdapter topicAdapter;
    private RecyclerView rvTopicList;
    private RecyclerView.LayoutManager layoutManager;
    private List<TopicModel> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }


    private void initView() {
        rvTopicList = findViewById(R.id.rvListTopic);
        rvTopicList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvTopicList.setLayoutManager(layoutManager);
        rvTopicList.setItemAnimator(new DefaultItemAnimator());
        topicList = new ArrayList<>();
        for (int i = 0 ; i < TopicData.topicImageList.length; i ++){
            TopicModel topicModel = new TopicModel(TopicData.topicNameList[i], TopicData.topicImageList[i]);
            topicList.add(topicModel);
        }
        topicAdapter = new TopicAdapter(topicList);
        rvTopicList.setAdapter(topicAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
