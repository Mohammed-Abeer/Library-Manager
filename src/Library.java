import java.io.IOException;
import java.util.ArrayList;
/*
    The Library class performs various tasks at the server side requested by a client

    @version 28 Apr 2019, 11:00 PM
    @author Ayon Shahrier, CSEDU-24th, Roll-05

    CSE-2112 OOP Project - Library Manager
 */

public class Library {
    private FileManager fileManager = new FileManager(); // A FileManager object to perform tasks with file
    private ArrayList <Book> books = fileManager.allBooks(); // Loads all the stored books from files
    private ArrayList <Member> members = fileManager.allMembers(); // Loads all the stored members from files

    // sign-up option to add a new member to the system
    public boolean addMember(Member member) {
        for(int i = 0; i < members.size(); i++) {
            // a member must have a unique email to sign-up for an account
            if(members.get(i).getEmail().equals(member.getEmail())) // false indicates email already in use
                return false;
        }
        member.setId(members.size() + 1);  // giving a unique id to member
        members.add(member);
        try {
            fileManager.addMember(member);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean issueBook(Book book, int id) {
        //System.out.println("come to lib");
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getName().equals(book.getName())) {
                if(books.get(i).isIssued() > 0) return false; // book already issued
                books.get(i).setIssued(id);
                try {
                    fileManager.issueBook(book, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return true;
    }

    // returns all the books in the library
    public ArrayList <Book> allBooks() {
        return books;
    }

    // returns all the issued books for an individual member
    public ArrayList <Book> allIssuedBooks(int id) {
        ArrayList <Book> result = new ArrayList<>();
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).isIssued() == id) {
                result.add(books.get(i));
            }
        }
        return result;
    }

    // returns all the members info in the library
    public ArrayList <Member> allMembers() {
        return members;
    }

    // searches for a book with a given substring and returns them
    public ArrayList <Book> searchBooks(String bookName) {
        ArrayList <Book> results = new ArrayList<>();
        boolean notFound=true; //Boolean to check book found or not
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getName().equals(bookName)) {
                results.add(books.get(i));
                notFound=false;
            }
        }
        if(notFound) {
            Book book=new Book("Not Found","Not Found",0);
            results.add(book);
        }
        return results;
    }
    // searches for a member with a given substring and returns them
    public ArrayList <Member> searchMembers(String memberName) {
        ArrayList <Member> results = new ArrayList<>();
        boolean notFound=true; //boolean to check if the member exists
        for(int i = 0; i < members.size(); i++) {
            if(members.get(i).getName().equals(memberName)) {
                results.add(members.get(i));
                notFound=false;
            }
        }
        if(notFound) {
            Member member=new Member("Not Found","Not Found","Not Found");
            results.add(member);
        }
        return results;
    }
    // adds a new book to the system
    public boolean addBook(Book book) {
        for (Book value : books) {
            if (value.getName().equals(book.getName())) return false; // book already exists
        }
        books.add(book);
        try {
            fileManager.addBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // changes an issued book's state to non-issued
    public void returnBook(Book book) {
        for (Book value : books) {
            if (value.getName().equals(book.getName())) {
                value.setIssued(0);
                try {
                    fileManager.returnBook(book);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // checks id & password with members database for login action
    public boolean loginasMember(int id, String password) {
        for (Member member : members) {
            if (!member.isAdmin()) {
                if (member.getPassword().equals(password) && member.getId() == id) {  // a member is found
                    return true;
                }
            }
        }
        return false; // no member description matches
    }

    // admin code match action
    public boolean loginasAdmin(int id, String password, String code) {
        if(!code.equals("kaayab")) return false; // admin code does not match
        return loginasMember(id, password); // admin code matches, so checks for
    }
}