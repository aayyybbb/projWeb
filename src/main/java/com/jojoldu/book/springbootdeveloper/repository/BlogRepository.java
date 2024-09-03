package com.jojoldu.book.springbootdeveloper.repository;


import com.jojoldu.book.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
