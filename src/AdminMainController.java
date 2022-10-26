
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*Author -Sadnan Kibria Kawshik, Roll-15
This is the admin main controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class AdminMainController implements Initializable {
    public Button addMemberButton;//Menu Components
    public Button logOutButton;
    public JFXTextField mName;
    public JFXTextField bookName;
    public Button searchBookButton;
    public Button searchMemberButton;

    public TableView<Book> bookTableView; //Javafx Table view components to show Book Information
    public TableColumn<Book,String> titleCol;
    public TableColumn<Book,String> authorCol;
    public TableColumn<Book,Integer> pageCol;
    public TableColumn <Book,Integer>isIssuedCol;

    public TableView <Member>memberTableView; //Javafx Table view components to show Member Information
    public TableColumn<Member,Integer> idCol;
    public TableColumn <Member,String>nameCol;
    public TableColumn <Member,String>emailCol;

    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Initializing Book Table Columns
        titleCol.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        pageCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numberOfPages"));

        //Initializing Member Table Columns
        isIssuedCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("isIssued"));
        idCol.setCellValueFactory(new PropertyValueFactory<Member,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Member,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));

    }

    public void loadAddMember(ActionEvent actionEvent)//loads add member menue
    {

        loadWindow("/addmember.fxml","Add Member");

    }

    public void loadAddBook(ActionEvent actionEvent)//loads add book menue
    {

        loadWindow("/addbook.fxml","Add Book");
    }

       public void viewBookTable(ActionEvent actionEvent)//shows book information in table
    {

        loadWindow("/displaybook.fxml","Book List");
    }

    public void viewMemberTable(ActionEvent actionEvent)//shows member information in table
    {

        loadWindow("/displaymember.fxml","Members");
    }

    public void viewIssuedBooks(ActionEvent actionEvent)//shows books issued by that member
    {

        loadWindow("/displayissuebook.fxml","Issued Books");
    }
    public void loadWindow(String loc,String title) //loads different scene
    {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage primaryStage=new Stage(StageStyle.DECORATED);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeStage()//closes current stage
    {
        Stage stage=(Stage)addMemberButton.getScene().getWindow();
        stage.close();
    }

    public void logOut(ActionEvent actionEvent)//log out current admin
    {
        closeStage();
        loadWindow("/homepage.fxml","Library Manager");
    }

    public void searchBook(ActionEvent actionEvent)//shows the information of searched book
    {
        String book=bookName.getText();//book name

        ArrayList<Book> books=clientManager.searchBooks(book);//returns books of that book name

        if(books!=null)
        {
            //shows the searched book in a table view
            ObservableList<Book> tableBooks= FXCollections.observableArrayList();
            tableBooks.addAll(books);
            bookTableView.setItems(tableBooks);
        }


    }

    public void searchMember(ActionEvent actionEvent)//shows the information of searched book
    {
        String member=mName.getText();//member name
        ArrayList<Member> members=clientManager.searchMember(member);//returns members of that member name
        if(members!=null)
        {
            //shows the searched book in a table view
            ObservableList<Member> tableMembers= FXCollections.observableArrayList();
            tableMembers.addAll(members);
            memberTableView.setItems(tableMembers);
        }
    }
}

