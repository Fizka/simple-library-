package com.ab.LibraryProject.AB.model;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    public static ArrayList<Book> books = null;

    public Library() {
        books = new ArrayList<>();
        books.add(new Book("Książka 1", "Jan Kowalski", 2021));
        books.add(new Book("Książka 2", "Paweł Kowalski", 2020));
        books.add(new Book("Książka 3", "Jacek Kowalski", 2022));
        books.add(new Book("Książka 4", "Krzysiek Kowalski", 2019));
        books.add(new Book("Książka 5", "Zosia Kowalska", 2002));
        books.add(new Book("Książka 6", "Katarzyna Kowalska", 2018));
        books.add(new Book("Książka 7", "Alicja Kowalska", 2020));
        books.add(new Book("Książka 8", "Maria Kowalska", 2021));
    }

    public static void addBook(Book book, ServletContext context) {
        books.add(book);
        context.setAttribute("books", books);
    }

    public static void removeBooks(List<String> title, ServletContext context) {
        title.forEach(t -> {
            Optional<Book> b = books.stream().filter(book -> t.equals(book.getTitle())).findAny();
            books.remove(b.get());
        });
        context.setAttribute("books", books);
    }


}
