package app.service;

import app.model.News;
import app.repository.NewsRepository;
import app.service.interfaces.NewsServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements NewsServiceInt {

    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void addNewNews(News news) {

    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News find(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
