

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/*Author -Sadnan Kibria Kawshik, Roll-15
This is the member main controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class MemberMainController implements Initializable {
    public Button searchButton; //Menu Components
    public TextField bookSearch; //Text field to get book name
    public TextArea bookInfo;  //Text Area to print book information
    public Button issueButton;
    public JFXTextField bookName;
    public Button renewButton;
    public Button submissionButton;
    public HBox bookInformation;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.setDepth(bookInformation,1);

    }

    public void viewBookTable(ActionEvent actionEvent) //displays all the books in table view
    {

        loadWindow("/displaybook.fxml","Book List");

    }

    public void search(ActionEvent actionEvent) //searches a book and prints the information in a text are
    {
        String book=bookSearch.getText(); //gets the book name
        ArrayList<Book>books=clientManager.searchBooks(book);
        String bookDetail=""; //a string to store the book information in this string
        for(int i=0;i<books.size();i++)
        {
            bookDetail+=books.get(i).BookDetails(); //stores the information in the string
        }

        bookInfo.setText(bookDetail); //printing the information
    }

    public void viewIssuedBooks(ActionEvent actionEvent) //displays all issued book by that member
    {
        loadWindow("/displayissuebook.fxml","Issued Books");

    }

    public void settingsHandler(ActionEvent actionEvent) {
    }

    public void issueBook(ActionEvent actionEvent) //method to issue a book by the logged in member
    {
        String book_name=bookSearch.getText(); //searches the book by name
        //Conformation if he wants to store or not
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Issue Operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to issue "+book_name+"\n"+"?");
        Optional<ButtonType> response=alert.showAndWait();
        if(response.get()==ButtonType.OK)//if response is ok it will proceed
        {
            String book=bookSearch.getText();
            ArrayList<Book>books=clientManager.searchBooks(book);
            for(int i=0;i<books.size();i++)
            {
                clientManager.issueBook(ClientManager.id,books.get(i)); //issues all the books of that name
            }
        }
        
    }

    public void bookRenew(ActionEvent actionEvent) {
    }

    public void bookSubmission(ActionEvent actionEvent) {
    }

    public void closeStage()//closes the current stage
    {
        Stage stage=(Stage)searchButton.getScene().getWindow();
        stage.close();
    }
    public void loadWindow(String loc,String title) {//loads a new window
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
    public void makeLogOut(ActionEvent actionEvent)
    {
        closeStage();
        loadWindow("/homepage.fxml","Library Manager");
    }
}
