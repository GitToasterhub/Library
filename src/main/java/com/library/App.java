package com.library;


import com.library.dao.impl.BookDAOImpl;
import com.library.service.LibraryService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App
{
    private static String DRIVER="com.mysql.jdbc.Driver";
    private static String URL="jdbc:mysql://localhost/library";

    public static void main( String[] args )
    {
        Connection connection=null;
        try {
            Class.forName(DRIVER);
            connection= DriverManager.getConnection(URL,"root","root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LibraryService libraryService=new LibraryService(connection);
        BookDAOImpl bookDAO=new BookDAOImpl(connection);

        String command;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the library!");
        System.out.println("There are commands,you should use to access library");
        System.out.println("Enter 'add {BookName},{BookAuthor}' -to add the book to the library ");
        System.out.println("Enter 'edit {BookName},{BookAuthor},{newBookName},{newBookAuthor} ' -to edit the book of the library ");
        System.out.println("Enter 'delete {BookName}  ' -to delete the book from the library ");
        System.out.println("Enter 'show all  ' -to get all the books printed ");
        System.out.println("Example:show all-will print all,  add 15-year-old Captian,Charles Dickens  -will add stated book to the library database");
        command=scanner.nextLine();
        while (command!=null){
            libraryService.menuHandler(command);
            command=scanner.nextLine();
        }

        System.out.println( bookDAO.getBookOfAuthor("J.Rowling").get(0) );
    }
}
