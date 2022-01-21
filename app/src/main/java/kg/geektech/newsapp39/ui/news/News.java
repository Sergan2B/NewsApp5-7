package kg.geektech.newsapp39.ui.news;

import java.io.Serializable;
import java.util.ArrayList;

public class News extends ArrayList<News> implements Serializable {
    private String title, createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public News(String title, String createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
