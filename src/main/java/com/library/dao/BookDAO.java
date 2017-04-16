package com.library.dao;

import com.library.entity.Book;

import java.util.List;

public interface BookDAO extends GeneralDAO<Book> {
    public List<Book> getBookOfAuthor(String authorName);
    public int countAllBooks();
}
