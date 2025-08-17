package mylab.library.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
	private String name;
    private final List<Book> books;
    
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void addBook(Book book) {
        if (book != null) books.add(book);
    }
    
    //제목 검색
    public Optional<Book> findBookByTitle(String title) {
        if (title == null) return Optional.empty();
        String q = title.trim().toLowerCase();
        return books.stream()
                .filter(b -> b.getTitle() != null && b.getTitle().toLowerCase().equals(q))
                .findFirst();
    }

    //저자 검색
    public List<Book> findBooksByAuthor(String author) {
        if (author == null) return Collections.emptyList();
        String q = author.trim().toLowerCase();
        return books.stream()
                .filter(b -> b.getAuthor() != null && b.getAuthor().toLowerCase().equals(q))
                .collect(Collectors.toList());
    }

    //ISBN검색 
    public Optional<Book> findBookByISBN(String isbn) {
        if (isbn == null) return Optional.empty();
        String q = isbn.trim();
        return books.stream()
                .filter(b -> q.equals(b.getIsbn()))
                .findFirst();
    }

    //ISBN
    public boolean checkOutBook(String isbn) {
        return findBookByISBN(isbn)
                .map(Book::checkOut)
                .orElse(false);
    }


    public boolean returnBook(String isbn) {
        return findBookByISBN(isbn)
                .map(b -> { b.returnBook(); return true; })
                .orElse(false);
    }

    //대출 가능 도서 목록
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    //전체 도서 목록(읽기)
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(books);
    }

    //수량 
    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        return (int) books.stream().filter(Book::isAvailable).count();
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
    
}
