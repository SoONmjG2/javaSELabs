package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;
import java.util.Optional;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library("�߾� ������");

        addSampleBooks(library);


        printLibrarySummary(library);

        testFindBook(library);
        testCheckOut(library);
        testReturn(library);

     
        displayAvailableBooks(library);
    }

    //���� ����
    private static void addSampleBooks(Library library) {
        addAndNotify(library, new Book("�ڹ� ���α׷���", "���ڹ�", "978-89-01-12345-6", 2022));
        addAndNotify(library, new Book("��ü������ ��ǰ� ����", "����ȣ", "978-89-01-67890-1", 2015));
        addAndNotify(library, new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        addAndNotify(library, new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        addAndNotify(library, new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        addAndNotify(library, new Book("�ڹ��� ����", "���ü�", "978-89-01-14077-4", 2019));
    }

    private static void addAndNotify(Library library, Book book) {
        library.addBook(book);
        System.out.println("������ �߰��Ǿ����ϴ�: " + book.getTitle());
    }

    //������ ���� 
    private static void printLibrarySummary(Library library) {
        System.out.println("===== " + library.getName() + " =====");
        System.out.println("��ü ���� ��: " + library.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
        System.out.println();
    }

    //�˻�
    private static void testFindBook(Library library) {
        System.out.println("===== ���� �˻� �׽�Ʈ =====");

        //����
        System.out.println("�������� �˻� ���:");
        Optional<Book> byTitle = library.findBookByTitle("�ڹ��� ����");
        if (byTitle.isPresent()) {
            System.out.println(byTitle.get().toString());
        } else {
            System.out.println("�ش� ������ ������ ã�� �� �����ϴ�.");
        }
        System.out.println();

        //����
        System.out.println("���ڷ� �˻� ���:");
        List<Book> byAuthor = library.findBooksByAuthor("Robert C. Martin");
        if (byAuthor.isEmpty()) {
            System.out.println("�ش� ������ ������ ã�� �� �����ϴ�.");
        } else {
            for (Book b : byAuthor) {
                System.out.println(b.toString());
            }
        }
        System.out.println();
    }

    //���� �׽�Ʈ
    private static void testCheckOut(Library library) {
        System.out.println("===== ���� ���� �׽�Ʈ =====");
        String targetIsbn = "978-89-01-14077-4"; // �ڹ��� ����
        boolean ok = library.checkOutBook(targetIsbn);
        if (ok) {
            System.out.println("���� ���� ����!");
            // ����� ���� ���� ���
            library.findBookByISBN(targetIsbn).ifPresent(b -> {
                System.out.println("����� ���� ����:");
                System.out.println(b.toString());
                System.out.println();
            });
        } else {
            System.out.println("���� ���� ����(�̹� ���� ���̰ų� �������� �ʽ��ϴ�).");
            System.out.println();
        }

        //���� ����
        System.out.println("������ ���� ����:");
        System.out.println("��ü ���� ��: " + library.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
        System.out.println();
    }

    //�ݳ� �׽�Ʈ
    private static void testReturn(Library library) {
        System.out.println("===== ���� �ݳ� �׽�Ʈ =====");
        String targetIsbn = "978-89-01-14077-4"; // �ڹ��� ����
        boolean ok = library.returnBook(targetIsbn);
        if (ok) {
            System.out.println("���� �ݳ� ����!");
            library.findBookByISBN(targetIsbn).ifPresent(b -> {
                System.out.println("�ݳ��� ���� ����:");
                System.out.println(b.toString());
                System.out.println();
            });
        } else {
            System.out.println("���� �ݳ� ����(�������� �ʴ� ����).");
            System.out.println();
        }

        System.out.println("������ ���� ����:");
        System.out.println("��ü ���� ��: " + library.getTotalBooks());
        System.out.println("���� ���� ���� ��: " + library.getAvailableBooksCount());
        System.out.println("���� ���� ���� ��: " + library.getBorrowedBooksCount());
    }

    //���� ���� ���� ��� 
    private static void displayAvailableBooks(Library library) {
        System.out.println("===== ���� ������ ���� ��� =====");
        for (Book b : library.getAvailableBooks()) {
            System.out.println(b.toString());
            System.out.println("------------------------");
        }
    }
}
