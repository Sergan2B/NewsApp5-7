package kg.geektech.newsapp39.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class News implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, createdAt;

    public News(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
