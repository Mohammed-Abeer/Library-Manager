

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
This is the display member controller file
All of the necessary functionalities of buttons and other functions are written in here*/


public class DisplayMemberController implements Initializable {


    public TableView<Member> tableView; //JavaFx Table View to show all members in a table form
    public TableColumn<Member,Integer> idCol;
    public TableColumn<Member,String>nameCol;
    public TableColumn <Member,String>emailCol;
    public TableColumn<Member,Integer> adminCol;
    public Button refreshButton;
    public Button exitButton;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager client=new ClientManager();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Initializing Member Table Columns
        idCol.setCellValueFactory(new PropertyValueFactory<Member,Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Member,String>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Member,String>("email"));
        adminCol.setCellValueFactory(new PropertyValueFactory<Member,Integer>("isAdmin"));

    }

    public void makeRefresh(ActionEvent actionEvent)//loads the current member database in table view
    {
        //shows the all member in a table view
        ArrayList<Member>members=client.showAllMembers(); //returns all members in file
        ObservableList<Member>tableMembers= FXCollections.observableArrayList();
        tableMembers.addAll(members);
        tableView.setItems(tableMembers);

    }
    public void closeStage()//close current stage
    {
        Stage stage=(Stage)refreshButton.getScene().getWindow();
        stage.close();
    }
    public void makeExit(ActionEvent actionEvent)//exits display member interface
    {
        closeStage();
    }
}
