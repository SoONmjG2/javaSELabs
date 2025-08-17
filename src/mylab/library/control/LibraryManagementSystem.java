package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;
import java.util.Optional;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("중앙 도서관");

        addSampleBooks(library);


        printLibrarySummary(library);

        testFindBook(library);
        testCheckOut(library);
        testReturn(library);

     
        displayAvailableBooks(library);
    }

    //샘플 도서
    private static void addSampleBooks(Library library) {
        addAndNotify(library, new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        addAndNotify(library, new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        addAndNotify(library, new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        addAndNotify(library, new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        addAndNotify(library, new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        addAndNotify(library, new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
    }

    private static void addAndNotify(Library library, Book book) {
        library.addBook(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    //도서관 정보 
    private static void printLibrarySummary(Library library) {
        System.out.println("===== " + library.getName() + " =====");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
        System.out.println();
    }

    //검색
    private static void testFindBook(Library library) {
        System.out.println("===== 도서 검색 테스트 =====");

        //제목
        System.out.println("제목으로 검색 결과:");
        Optional<Book> byTitle = library.findBookByTitle("자바의 정석");
        if (byTitle.isPresent()) {
            System.out.println(byTitle.get().toString());
        } else {
            System.out.println("해당 제목의 도서를 찾을 수 없습니다.");
        }
        System.out.println();

        //저자
        System.out.println("저자로 검색 결과:");
        List<Book> byAuthor = library.findBooksByAuthor("Robert C. Martin");
        if (byAuthor.isEmpty()) {
            System.out.println("해당 저자의 도서를 찾을 수 없습니다.");
        } else {
            for (Book b : byAuthor) {
                System.out.println(b.toString());
            }
        }
        System.out.println();
    }

    //대출 테스트
    private static void testCheckOut(Library library) {
        System.out.println("===== 도서 대출 테스트 =====");
        String targetIsbn = "978-89-01-14077-4"; // 자바의 정석
        boolean ok = library.checkOutBook(targetIsbn);
        if (ok) {
            System.out.println("도서 대출 성공!");
            // 대출된 도서 정보 출력
            library.findBookByISBN(targetIsbn).ifPresent(b -> {
                System.out.println("대출된 도서 정보:");
                System.out.println(b.toString());
                System.out.println();
            });
        } else {
            System.out.println("도서 대출 실패(이미 대출 중이거나 존재하지 않습니다).");
            System.out.println();
        }

        //현재 상태
        System.out.println("도서관 현재 상태:");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
        System.out.println();
    }

    //반납 테스트
    private static void testReturn(Library library) {
        System.out.println("===== 도서 반납 테스트 =====");
        String targetIsbn = "978-89-01-14077-4"; // 자바의 정석
        boolean ok = library.returnBook(targetIsbn);
        if (ok) {
            System.out.println("도서 반납 성공!");
            library.findBookByISBN(targetIsbn).ifPresent(b -> {
                System.out.println("반납된 도서 정보:");
                System.out.println(b.toString());
                System.out.println();
            });
        } else {
            System.out.println("도서 반납 실패(존재하지 않는 도서).");
            System.out.println();
        }

        System.out.println("도서관 현재 상태:");
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
    }

    //대출 가능 도서 목록 
    private static void displayAvailableBooks(Library library) {
        System.out.println("===== 대출 가능한 도서 목록 =====");
        for (Book b : library.getAvailableBooks()) {
            System.out.println(b.toString());
            System.out.println("------------------------");
        }
    }
}
