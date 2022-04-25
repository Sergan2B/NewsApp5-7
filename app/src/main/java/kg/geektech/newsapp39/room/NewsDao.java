package kg.geektech.newsapp39.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kg.geektech.newsapp39.models.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert //Аннатация решает здесь в room библеотеке
    void insert(News news);

    @Delete
    void delete(News news);


    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News> sort();

}
