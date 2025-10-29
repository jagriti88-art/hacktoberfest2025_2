import java.util.*;

class Book {
    String bookId;
    String title;
    String author;
    int availableCopies;

    public Book(String bookId, String title, String author, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}

class Member {
    String memberId;
    String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

class Library {
    private Book[] books = new Book[100];
    private Member[] members = new Member[100];
    private int bookCount = 0;
    private int memberCount = 0;

    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount++] = book;
            System.out.println("Book added successfully");
        } else {
            System.out.println("Library is full");
        }
    }

    public void registerMember(Member member) {
        if (memberCount < members.length) {
            members[memberCount++] = member;
            System.out.println("Member registered successfully!");
        } else {
            System.out.println("Member list is full! Cannot register more members.");
        }
    }

    public void issueBook(String bookId, String memberId) {
        Book bookToIssue = null;
        Member member = null;

        for (int i = 0; i < bookCount; i++) {
            if (books[i].bookId.equals(bookId)) {
                bookToIssue = books[i];
                break;
            }
        }

        for (int i = 0; i < memberCount; i++) {
            if (members[i].memberId.equals(memberId)) {
                member = members[i];
                break;
            }
        }

        if (bookToIssue != null && member != null) {
            if (bookToIssue.availableCopies > 0) {
                bookToIssue.availableCopies--;
                System.out.println("Book Issued Successfully to " + member.name);
            } else {
                System.out.println("No copies available for book: " + bookToIssue.title);
            }
        } else {
            System.out.println("Book or Member not found!");
        }
    }

    public void displayBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            Book b = books[i];
            System.out.println("BookID: " + b.bookId +
                               ", Title: " + b.title +
                               ", Author: " + b.author +
                               ", Available Copies: " + b.availableCopies);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Available Copies: ");
                    int copies = sc.nextInt();
                    sc.nextLine();
                    library.addBook(new Book(bookId, title, author, copies));
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.registerMember(new Member(memberId, name));
                    break;

                case 3:
                    System.out.print("Enter Book ID to Issue: ");
                    String bId = sc.nextLine();
                    System.out.print("Enter Member ID: ");
                    String mId = sc.nextLine();
                    library.issueBook(bId, mId);
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    System.out.println("Thankyou");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
