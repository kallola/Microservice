package com.bean.book.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
