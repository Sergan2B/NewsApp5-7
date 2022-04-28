package kg.geektech.newsapp39.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kg.geektech.newsapp39.models.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insert(News news);

    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News> sort();

}
