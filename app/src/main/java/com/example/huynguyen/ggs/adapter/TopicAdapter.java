package com.example.huynguyen.ggs.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huynguyen.ggs.R;
import com.example.huynguyen.ggs.model.TopicModel;

import java.util.List;

/**
 * Created by Ha Truong on 5/9/2018.
 * This is a BoDE
 * into the com.example.huynguyen.ggs.adapter
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<TopicModel> topicList;

    public TopicAdapter(List<TopicModel> topicList) {
        this.topicList = topicList;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemTopic = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(itemTopic);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder topicViewHolder, int position) {
        TopicModel topic = topicList.get(position);
        topicViewHolder.txtTopicName.setText(topic.getTopicName());
        topicViewHolder.bgTopic.setBackgroundResource(topic.getTopicImage());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder{

        TextView txtTopicName;
        LinearLayout bgTopic;

        TopicViewHolder(View itemView) {
            super(itemView);
            txtTopicName = itemView.findViewById(R.id.txtTopicName);
            bgTopic = itemView.findViewById(R.id.bgTopic);
        }
    }
}
