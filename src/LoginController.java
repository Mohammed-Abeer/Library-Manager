

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/*Author -Sadnan Kibria Kawshik, Roll-15
This is the Home Page controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class LoginController implements Initializable
{
    public JFXTextField uID;
    public JFXPasswordField password; //JavaFx GUI components for Member Login Menu
    public JFXButton enterButton;
    public JFXButton cancelButton;
    public AnchorPane rootPane;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void makeLogin(ActionEvent actionEvent)//method for member login
    {
        String id=uID.getText();
        int userID=Integer.parseInt(id); //getting id and password from member to login
        String pass=password.getText();
        boolean flag=id.isEmpty() ||pass.isEmpty(); //flag to check if any flag is empty
        if(flag)
        {
            //Informing user that he has left something empty using alert
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }
        else
        {
            int permit=clientManager.loginAsMember(userID,pass);//Returns user ID if ID and password matches
            if(permit==userID)
            {
                ClientManager.id=permit; //stores the id for current member
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Member Login Information");
                alert.setContentText("Congratulations You Have Logged In Successfully");
                alert.showAndWait();
                closeStage();
                loadWindow("/membermain.fxml","Member Menue");//loading the member menu

            }
            else
            {
                //Informing user that id or password is wrong
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Member Login Information");
                alert.setContentText("Invalid User ID or Password");
                alert.showAndWait();
                closeStage();
                loadWindow("/homepage.fxml","Library Manager");

            }
        }
    }
    public void closeStage()//closes the current scene
    {
        Stage stage=(Stage)uID.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent)//cancels the login page
    {
        closeStage();
        loadWindow("/homepage.fxml","Library Manager");
    }
    public void loadWindow(String loc,String title) {//loads new window
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage primaryStage=new Stage(StageStyle.DECORATED);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(parent,650,600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
