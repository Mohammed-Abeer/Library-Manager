

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*Author -Sadnan Kibria Kawshik, Roll-15
This is the display book controller file
All of the necessary functionalities of buttons and other functions are written in here*/


public class DisplayBookController implements Initializable {


    public TableView<Book> tableView; //JavaFx Table View to show all books in a table form
    public TableColumn<Book,String>bNameCol;
    public TableColumn<Book,String> authorCol;
    public TableColumn<Book,Integer> pagesCol;
    public Button refreshButton;
    public Button exitButton;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager client=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Initializing Book Table Columns
        bNameCol.setCellValueFactory(new PropertyValueFactory<Book,String>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        pagesCol.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numberOfPages"));

    }

    public void makeRefresh(ActionEvent actionEvent)//loads the current book database in table view
    {
        //shows the all book in a table view
        ArrayList<Book> books=client.showAllBooks(); //returns all books in file
        ObservableList<Book> tableBooks= FXCollections.observableArrayList();
        tableBooks.addAll(books);
        tableView.setItems(tableBooks);


    }
    public void closeStage()//close current stage
    {
        Stage stage=(Stage)refreshButton.getScene().getWindow();
        stage.close();
    }
    public void makeExit(ActionEvent actionEvent)//exits display book interface
    {
        closeStage();
    }
}
