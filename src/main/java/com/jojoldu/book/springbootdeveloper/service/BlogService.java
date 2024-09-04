package com.jojoldu.book.springbootdeveloper.service;

import com.jojoldu.book.springbootdeveloper.domain.Article;
import com.jojoldu.book.springbootdeveloper.dto.AddArticleRequest;
import com.jojoldu.book.springbootdeveloper.dto.ArticleResponse;
import com.jojoldu.book.springbootdeveloper.dto.UpdateArticleRequest;
import com.jojoldu.book.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found : " + id));
    }

    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found : " + id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
