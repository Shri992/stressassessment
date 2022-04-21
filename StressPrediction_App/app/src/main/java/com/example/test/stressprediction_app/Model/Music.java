package com.example.test.stressprediction_app.Model;

public class Music {

    String Name,Duration;
    int Resource;
    boolean isEnabled = true;

    public Music(String name, String duration, int resource) {
        Name = name;
        Duration = duration;
        Resource = resource;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public int getResource() {
        return Resource;
    }

    public void setResource(int resource) {
        Resource = resource;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
