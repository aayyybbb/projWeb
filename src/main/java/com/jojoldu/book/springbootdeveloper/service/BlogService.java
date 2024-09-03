package com.jojoldu.book.springbootdeveloper.service;

import com.jojoldu.book.springbootdeveloper.domain.Article;
import com.jojoldu.book.springbootdeveloper.dto.AddArticleRequest;
import com.jojoldu.book.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
