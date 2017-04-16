package com.library.service;


import com.library.dao.impl.BookDAOImpl;
import com.library.entity.Book;

import java.sql.Connection;

public class LibraryService {
    BookDAOImpl bookDAO;
    Connection connection;

    public LibraryService(Connection connection) {
        this.connection = connection;
        bookDAO = new BookDAOImpl(connection);
    }

    public void menuHandler(String command){
        Book book;
        if(command.contains("add")){
            String bookInfo = command.replace("add ","");
            book=new Book();
            book.setName(bookInfo.split(",")[0]);
            book.setAuthorName(bookInfo.split(",")[1]);
            bookDAO.addElement(book);
            System.out.println("The book was added!");
            return;
        }
        if(command.contains("show all")){
            bookDAO.getAllElements().forEach(System.out::println);
            return;
        }
        if(command.contains("delete")){
            String bookInfo = command.replace("delete ","");
            book=new Book();
            book.setName(bookInfo.split(",")[0]);
            book.setAuthorName(bookInfo.split(",")[1]);
            bookDAO.deleteElement(book);
            System.out.println("The book was deleted!");
            return;
        }
        if(command.contains("edit")){
            String bookInfo = command.replace("edit ","");
            book=new Book();
            Book newBook=new Book();
            book.setName(bookInfo.split(",")[0]);
            newBook.setName(bookInfo.split(",")[2]);
            book.setAuthorName(bookInfo.split(",")[1]);
            newBook.setAuthorName(bookInfo.split(",")[3]);
            System.out.println("The bok was edited");
            bookDAO.updateElement(book,newBook);
            return;
        }

    }


}
