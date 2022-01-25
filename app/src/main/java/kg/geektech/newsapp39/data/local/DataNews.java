package kg.geektech.newsapp39.data.local;

import java.util.ArrayList;

import kg.geektech.newsapp39.models.News;

public class DataNews {
    private ArrayList<News> newsArrayList = new ArrayList<>();
    public ArrayList<News> getNewsArrayList() {
        return newsArrayList;
    }

    public DataNews() {
        for (int i = 1; i < 15; i++) {
            //newsArrayList.add(new News("Title: " + i, 100));
        }
    }
}
