import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * ClientManager class will connect with server and maintain
 * the necessary interactions between GUI client and server with
 * the help of ServerManager which class will echo back the necessary
 * information ClientManager wants.
 * GUI will call the necessary  methods it needs to use
 * for procuring the client's want.
 *
 * @author Mohammed Abeer Rahman
 * @version 4.0
 * @since 2019-04-29 (12:30 pm)
 */

public class ClientManager {
    private static Socket socket;
    private static ObjectInputStream ois;
    private static ObjectOutputStream ous;
    public static int id=0;
    static {
        try {
            socket = new Socket("localhost", 5000);
            ous = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The searchBooks function will get book name from GUI
     * and will search the book in Book ArrayList. If the book
     * is found in the ArrayList, will return the full book object
     * to GUI.
     */

    public ArrayList<Book> searchBooks(String name) {
        String str = "searchBooks";
        ArrayList<Book>books=new ArrayList<>();
        try {

            str = str + "\n" + name; //Concatinating command token with name
            ous.writeUTF(str);  /* passing command token for using this method */
            ous.flush();
            Object obj;
            obj =  ois.readObject();/*Getting books of that book name from ServerManager*/
            books = (ArrayList<Book>) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public String signUp(Member member) {
        String str = "signup";
        try {
            ous.writeUTF(str);
            ous.writeObject(member);
            ous.flush();
            str = ois.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public ArrayList<Book> showAllBooks() {
        String str = "showAllBooks";
        ArrayList<Book> books = null;
        try {
            ous.writeUTF(str);
            ous.flush();
            books = (ArrayList<Book>) ois.readObject();/*Getting all books  from ServerManager in the ArrayList*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public int loginAsMember(int id, String password) {
        String str = "loginasMember";
        String flag = null;
        try {
            ous.writeUTF(str);
            ous.writeInt(id);
            ous.writeUTF(password);
            ous.flush();
            flag = ois.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag.equals("Proceed")) {  /*if login is successful, GUI will proceed*/
            return id;
        } else {
            return 0;
        }
    }

    public String loginAsAdmin(int id, String password, String code) {
        String str = "loginasAdmin";
        String flag = null;
        try {
            ous.writeUTF(str);
            ous.writeInt(id);
            ous.writeUTF(password);
            ous.writeUTF(code); /*Admin will posses a special code */
            ous.flush();
            flag = ois.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag.equals("Proceed")) {
            return "Yes";     /*if login is successful, GUI will proceed*/
        } else {
            return "No";
        }
    }
    /**
     * Only Admin can use addBook method which will add books in database
     */
    public String addBook(Book book) {
        String str = "addBook";
        try {
            ous.writeUTF(str);
            ous.writeObject(book);
            str = ois.readUTF();
            ous.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * issueBook method will remove the book from the search table.
     */
    public void issueBook(int id, Book book) {
        String str = "issueBook";
        try {
            ous.writeUTF(str);  /* passing command token for using this method */
            ous.writeInt(id);
            ous.writeObject(book);
            ous.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * returnBook method will add the book again in the search table.
     */
    public void returnBook(Book book) {
        String str = "returnBook";
        try {
            ous.writeUTF(str);
            ous.writeObject(book);
            ous.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> allIssuedBooks(int id) {
        String str = "allIssuedBooks";
        ArrayList<Book> books = null;
        try {
            ous.writeUTF(str);
            ous.writeInt(id);
            ous.flush();
            books = (ArrayList<Book>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    // ???????????
    public void logout() {
        String str = "logout";
        try {
            ous.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * The searchMember function will get member name from GUI
     * and will search the member in Member ArrayList. If the member
     * is found in the ArrayList, will return the full member object
     * to GUI.
     */
    public ArrayList<Member> searchMember(String name) {
        String str = "searchMembers";
        ArrayList<Member> members = null;
        try {

            str = str + "\n" + name; //Concatinating command token with name
            ous.writeUTF(str); /* passing command token for using this method */
            ous.flush();
            Object obj;
            obj =  ois.readObject(); /*Getting members of that member name from ServerManager*/
            members = (ArrayList<Member>) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return members;
    }

    public ArrayList<Member> showAllMembers() {
        String str = "showAllMembers";
        ArrayList<Member> members = null;
        try {
            ous.writeUTF(str);
            ous.flush();
            members = (ArrayList<Member>) ois.readObject(); /*Getting all members from ServerManager in the ArrayList*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return members;
    }
}
