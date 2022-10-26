

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*Author -Sadnan Kibria Kawshik, Roll-15
This is the Home Page controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class HomeController implements Initializable {
    public Button admin; //JavaFx GUI components for home menu
    public Button member;
    public Button signUp;
    public Button exit;

    //Object of ClientManager to access methods of Library in a Server-Client System

    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void makeExit(ActionEvent actionEvent)//Exits the total program
    {
        clientManager.logout();
        System.exit(0);
    }

    public void makeSignUp(ActionEvent actionEvent)//Opens the SignUp interface
    {
        closeStage();
        loadWindow("/signup.fxml","Sign Up");
    }

    public void memberLogin(ActionEvent actionEvent) //Opens the Member Login Interface
    {
        closeStage();
        loadWindow("/login.fxml","Member Login");
    }

    public void loginAsAdmin(ActionEvent actionEvent) //Opens the admin Login interface
    {

        closeStage();
        loadWindow("/adminlogin.fxml","Admin Login");
    }
    public void loadWindow(String loc,String title) { //Loads a new scene
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
    public void closeStage() //Closes the current stage
    {
        Stage stage=(Stage)admin.getScene().getWindow();
        stage.close();
    }
}
