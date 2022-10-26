import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *ServerManager will handle multi threading in case of multiple clients.
 * It will call the methods needed for server-client connection.
 *
 * @author Mohammed Abeer Rahman
 * @version 3.0
 * @since 2019-04-29(12:30am)
 */
public class ServerManager implements Runnable {
    private final ObjectInputStream ois;
    private final ObjectOutputStream ous;
    private final Socket s;

    public ServerManager(Socket s, ObjectInputStream ois, ObjectOutputStream ous) {
        this.s = s;
        this.ois = ois;
        this.ous = ous;
    }

    public Library library = new Library();

    @Override
    public void run() {
        while (true) {
            try {

                String command = ois.readUTF();  //command string from client manager that will decide which method will
                                                // run
                String[] cmd = command.split("\n") ; //string array that keeps the splitted strings

                if (cmd[0].equals("searchBooks")) {
                    try {
                        ArrayList<Book> results = library.searchBooks(cmd[1]);//searching books of name stored in cmd[1]
                        ous.writeObject(results);//returning search results
                        ous.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (cmd[0].equals("searchMembers")) {
                    try {
                        ArrayList<Member> results = library.searchMembers(cmd[1]);//searching members of name stored in cmd[1]
                        ous.writeObject(results);//returning member results
                        ous.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (command.equals("signup")) {
                    Member member = (Member)ois.readObject();
                    String result;
                    if(library.addMember(member)) result = "ok";
                    else result = "use different email";
                    ous.writeUTF(result);
                    ous.flush();
                }
                else if (command.equals("showAllBooks")) {
                    try {
                        ArrayList<Book> results = library.allBooks();  /* searching all books
                                                                       from library */

                        ous.writeObject(results);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("showAllMembers")) {
                    try {
                        ArrayList<Member> results = library.allMembers(); /* searching all members
                                                                           from library */

                        ous.writeObject(results);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("loginasMember")) {
                    try {
                        int id = ois.readInt();
                        String pw = ois.readUTF();
                        boolean isMember = library.loginasMember(id, pw);  /* checking info

                                                                              for login */
                        if (!isMember) {
                            ous.writeUTF("Do not proceed"); /*if login input doesn't match from database
                                                               then sending message  to ClientManager not
                                                               to proceed*/

                        }
                        else {
                            ous.writeUTF("Proceed");
                        }
                        ous.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("loginasAdmin")) {
                    try {
                        int id = ois.readInt();
                        String pw = ois.readUTF();
                        String code = ois.readUTF();                                             /* Admin possess
                                                                                                special code. */

                        boolean isMember = library.loginasAdmin(id, pw, code);

                        if (!isMember) {      /*if login input doesn't match from database
                                                then sending message  to ClientManager not
                                                 to proceed*/
                            ous.writeUTF("Do not proceed");
                        }
                        else {
                            ous.writeUTF("Proceed");
                        }
                        ous.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equals("addBook")) {    //adding book to library
                    Book book = (Book)ois.readObject();
                    String result;
                    if(library.addBook(book))
                    {
                        result="ok";
                    }
                    else
                    {
                        result="Error";
                    }
                    ous.writeUTF(result);
                    ous.flush();
                }
                else if (command.equals("issueBook")) {
                    int id = ois.readInt();
                    Book book = (Book)ois.readObject();
                    library.issueBook(book, id);
                }
                else if (command.equals("returnBook")) {
                    Book book = (Book)ois.readObject();
                    library.returnBook(book);
                }
                else if (command.equals("allIssuedBooks")) {
                    int id = ois.readInt();
                    try {
                        ArrayList <Book> results = library.allIssuedBooks(id);
                        ous.writeObject(results);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    library.allIssuedBooks(id);
                }
                else if (command.equals("logout")) {
                    break;
                }
            }
            catch (IOException | ClassNotFoundException e) {
            }
        }

    }
}