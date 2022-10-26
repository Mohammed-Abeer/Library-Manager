import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
    The FileManager class handles the database portion of the system regarding books & members

    @version 28 Apr 2019, 07:00 PM
    @author Ayon Shahrier, CSEDU-24th, Roll-05

    CSE-2112 OOP Project - Library Manager
 */


public class FileManager {
    // File containing data of books
    private static File booksfile = new File("/home/sadnan/IdeaProjects/Library Manager/src/files/books.txt");

    // File containing data of members

    private static File membersfile = new File("/home/sadnan/IdeaProjects/Library Manager/src/files/members.txt");

    // writes all the data for a book object to the books file
    private void writeToFile(File file, Book book) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(buffer);

        printWriter.println(getBookDetails(book));
        printWriter.close();
    }

    // writes all the data for a member object to the members file
    private void writeToFile(File file, Member member) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter buffer = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(buffer);

        printWriter.println(getMemberDetails(member));
        printWriter.close();
    }

    // reads a single book's data from the books file & forms and returns a Book object with it
    private Book readBookDetails(Scanner scanner) {
        String name = scanner.nextLine();
        String author = scanner.nextLine();
        int pages = scanner.nextInt();
        scanner.nextLine();
        int issued = scanner.nextInt();
        scanner.nextLine();

        return new Book(name, author, pages, issued);
    }

    // reads a single member's data from the members file & forms and returns a Member object with it
    private Member readMemberDetails(Scanner scanner) {
        String name = scanner.nextLine();
        String email = scanner.nextLine();
        int id = scanner.nextInt();
        scanner.nextLine();
        String password = scanner.nextLine();
        String admin = scanner.nextLine();
        boolean isadmin;
        if(admin == "Admin") isadmin = true;
        else isadmin = false;

        return new Member(id, password, name, email, isadmin);
    }

    // gets all the information of a book object & then forms and returns in a single String
    public String getBookDetails(Book book) {
        String res;
        res = book.getName();
        res += "\n" + book.getAuthor();
        res += "\n" + book.getNumberOfPages();
        res += "\n" + book.isIssued();
        return res;
    }

    // gets all the information of a member object & then forms and returns in a single String
    private String getMemberDetails(Member member) {
        String res;
        res = member.getName();
        res += "\n" + member.getEmail();
        res += "\n" + member.getId();
        res += "\n" + member.getPassword();
        if(member.isAdmin()) res += "\n" + "Admin";
        else res += "\n" + "Not_Admin";
        return res;
    }

    // reads all the books in the books file one by one calling the readBookDetails() method
    public ArrayList <Book> allBooks(){
        ArrayList <Book> bookList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(booksfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            bookList.add(readBookDetails(scanner));
        }
        return bookList;
    }

    // reads all the members in the members file one by one calling the readMemberDetails() method
    public ArrayList <Member> allMembers(){
        ArrayList<Member> memberList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(membersfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            memberList.add(readMemberDetails(scanner));
        }
        return memberList;
    }

    // changes the issue state of a book in the file database
    public void issueBook(Book book, int id) throws IOException {
        ArrayList <Book> books = allBooks();  // reads all the books from the file
        PrintWriter pw = new PrintWriter(booksfile);
        pw.close();  // erases all data in the file
        for(int i = 0; i < books.size(); i++) {
            if(book.getName().equals(books.get(i).getName())) { // finds the particular book
                books.get(i).setIssued(id); // changes issue state
            }
            writeToFile(booksfile, books.get(i)); // stores back all the book data
        }
    }

    // adds a new book to the books file
    public void addBook(Book book) throws IOException {
        writeToFile(booksfile, book);
    }

    // adds a new member to the members file
    public void addMember(Member member) throws IOException {
        writeToFile(membersfile, member);
    }

    // changes issue state of a book to zero (0) which indicates non-issued
    public void returnBook(Book book) throws IOException {
        issueBook(book, 0);
    }
}