package com.example.test.stressprediction_app.Model;

public class Videos {

    public String Video,VideoLink;

    public Videos(String video, String videoLink) {
        Video = video;
        VideoLink = videoLink;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public String getVideoLink() {
        return VideoLink;
    }

    public void setVideoLink(String videoLink) {
        VideoLink = videoLink;
    }
}
