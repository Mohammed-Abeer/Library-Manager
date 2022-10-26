import java.io.Serializable;
/*
    The Book class holds the information regarding a single book such as its name, author & pages.
    This class is implemented to create and store book objects for the library in both server & client sides.

    @version 27 Apr 2019, 11:20 AM
    @author Ayon Shahrier, CSEDU-24th, Roll-05

    CSE-2112 OOP Project - Library Manager
 */
public class Book implements Serializable {
    private String name; //name of the book
    private String author; //author of the book
    private int numberOfPages; //the number of pages in the book
    private int isIssued;  //positive integer member id to which the book is issued to. Zero (0) if not issued.

    //Constructor for an unissued book
    public Book(String name, String author, int pages) {
        this.setName(name);
        this.setAuthor(author);
        this.setNumberOfPages(pages);
        this.setIssued(0);
    }

    //Constructor for an issued book
    public Book(String name, String author, int pages, int issued) {
        this.setName(name);
        this.setAuthor(author);
        this.setNumberOfPages(pages);
        this.setIssued(issued);
    }

    //returns the name of the book
    public String getName() {

        return name;
    }

    //sets a new name for the book
    public void setName(String name) {

        this.name = name;
    }

    //returns the name of the author
    public String getAuthor() {
        return author;
    }

    //sets a new author for the book
    public void setAuthor(String author) {

        this.author = author;
    }

    //returns the number of pages of the book
    public int getNumberOfPages() {

        return numberOfPages;
    }

    //sets a new page number for the book
    public void setNumberOfPages(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    //returns the member id for an issued book or if it is unissued
    public int isIssued() {

        return isIssued;
    }

    //sets a new issued state for the book
    public void setIssued(int issued) {

        this.isIssued = issued;
    }

    //prints all relevant information for the book
    public void printDetails() {
        System.out.println("Book Name : " + this.name);
        System.out.println("Author : " + this.author);
        System.out.println("Pages : " + this.numberOfPages);

        if(this.isIssued == 0) System.out.println("Not Issued");
        else System.out.println("Issued to member id - " + this.isIssued);
        System.out.println();
    }

    //returns all book information as string
    public String BookDetails( ) {
        String res="Book Name : ";
        res+= this.getName();
        res += "\nAuthor : " + this.getAuthor();
        res+="\nNumber of Pages : "+this.getNumberOfPages();
        res+="\n";
        return res;
    }

}