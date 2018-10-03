package app.service.interfaces;

import app.model.News;

import java.util.List;

public interface NewsServiceInt {
    void addNewNews(News news);
    List<News> getAllNews();
    News find(long id);
    void delete(long id);
}
