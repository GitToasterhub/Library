package com.library.dao.impl;


import com.library.dao.BookDAO;
import com.library.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    private static final String PREPARED_STATEMENT_ADD="INSERT INTO books ( Name, AuthorName) VALUES (? ,?)";
    private static final String PREPARED_STATEMENT_DELETE="DELETE FROM books WHERE Name=? AND AuthorName=?";
    private static final String PREPARED_STATEMENT_UPDATE="UPDATE books SET Name=?,AuthorName=? WHERE Name=? AND AuthorName=?";
    private static final String PREPARED_STATEMENT_BOOKS_OF_AUTHOR="SELECT * FROM books WHERE AuthorName=? ";
    private static final String STATEMENT_GETALL="SELECT * FROM books ";


    public BookDAOImpl(Connection connection) {
        this.connection=connection;
    }

    public void addElement(Book book)  {
        try {
            preparedStatement = connection.prepareStatement(PREPARED_STATEMENT_ADD);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthorName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateElement(Book oldBook,Book newBook) {
        try {
            preparedStatement = connection.prepareStatement(PREPARED_STATEMENT_UPDATE);
            preparedStatement.setString(1,newBook.getName());
            preparedStatement.setString(3,oldBook.getName());
            preparedStatement.setString(2,newBook.getAuthorName());
            preparedStatement.setString(4,oldBook.getAuthorName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllElements() {
        List<Book> bookList=new ArrayList<Book>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(STATEMENT_GETALL);
            while (resultSet.next()){
                int id=resultSet.getInt("BookID");
                String bookName = resultSet.getString("Name");
                String authorName = resultSet.getString("AuthorName");
                bookList.add(new Book(id,bookName,authorName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void deleteElement(Book book) {

        try {
            preparedStatement = connection.prepareStatement(PREPARED_STATEMENT_DELETE);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthorName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBookOfAuthor(String authorName) {
        List<Book> bookList=new ArrayList<Book>();
        try {
        preparedStatement = connection.prepareStatement(PREPARED_STATEMENT_BOOKS_OF_AUTHOR);
        preparedStatement.setString(1,authorName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("BookID");
            String bookName = resultSet.getString("Name");
            bookList.add(new Book(id,bookName,authorName));
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public int countAllBooks() {

        return getAllElements().size();
    }
}
