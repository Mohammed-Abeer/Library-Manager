

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
/*Author -Sadnan Kibria Kawshik, Roll-15
This is the add book controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class AddBookController implements Initializable {
    public JFXTextField title;/*Javafx GUI Components*/
    public JFXTextField author;
    public JFXButton saveButton;
    public JFXButton cancelButton;
    public JFXTextField pages;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addBook(ActionEvent actionEvent)//method add book in GUI
    {
        /*Components to store information of GUI fields to
        create a book object
         */
        String bookTitle=title.getText();
        String bookAuthor=author.getText();
        String bookPages= pages.getText();
        int page=Integer.parseInt(bookPages);

        //to check if user has left any field empty
        boolean flag=bookTitle.isEmpty()||bookAuthor.isEmpty()||bookPages.isEmpty();

        if(flag)
        {
            /*Alert object to tell user to fill up all fileds*/
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }
        else
        {
            Book book =new Book(bookTitle, bookAuthor,page);//Desired Book object to store in database
            String result=clientManager.addBook(book);//result string will indicate if we can add books of different name
            if(result.equals("ok"))
            {
                //To notify user that book has been added
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Book Add Information");
                alert.setContentText("Congratulations Book Added Successfully");
                alert.showAndWait();
            }
            closeStage();
        }
    }
    public void closeStage()//method to close current stage
    {
        Stage stage=(Stage)title.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent)//method for cancel button in GUI
    {
        closeStage();

    }

}
