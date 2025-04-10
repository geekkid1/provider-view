package com.example.demo;

public class UpdatedFeedbackData extends FeedbackData {
    boolean updated;

    public UpdatedFeedbackData(FeedbackData fd, boolean updated) {
        super(fd.id, fd.productName, fd.content, fd.metaData);
        this.updated = updated;
    }
}
