package com.example.huynguyen.ggs.model;

/**
 * Created by Ha Truong on 5/9/2018.
 * This is a BoDE
 * into the com.example.huynguyen.ggs.model
 */

public class TopicModel {
    private String topicName;
    private int topicImage;

    public TopicModel(String topicName, int topicImage) {
        this.topicName = topicName;
        this.topicImage = topicImage;
    }

    public String getTopicName() {
        return topicName;
    }

    public TopicModel setTopicName(String topicName) {
        this.topicName = topicName;
        return this;
    }

    public int getTopicImage() {
        return topicImage;
    }

    public TopicModel setTopicImage(int topicImage) {
        this.topicImage = topicImage;
        return this;
    }
}
