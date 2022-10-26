import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*Author -Sadnan Kibria Kawshik, Roll-15
This is the admin main controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class AdminLoginController implements Initializable {
    public JFXTextField uID;
    public JFXPasswordField password;/*Javafx GUI Components*/
    public JFXPasswordField aCode;
    public JFXButton enterButton;
    public JFXButton cancelButton;
    private ClientManager client=new ClientManager();
    //Object of ClientManager to access methods of Library in a Server-Client System
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void makeLogin(ActionEvent actionEvent)//method to add member in GUI
    {
        //getting useful information to admin login
        String id= uID.getText();
        String pass=password.getText();
        String code=aCode.getText();//A special code that only admin knows.By default it is "kaayab"


        boolean flag=id.isEmpty() ||pass.isEmpty()||code.isEmpty();//to check if user has left any field empty
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
            //A string to grant a member login as admin if it is Yes
            String permit=client.loginAsAdmin(Integer.parseInt(id),pass,code);
            if(permit.equals("Yes"))
            {
                //To inform admin that he has logged in successfully
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Member Login Information");
                alert.setContentText("Congratulations You Have Logged In Successfully");
                alert.showAndWait();
                closeStage();
                loadWindow("/adminmain.fxml","Admin Menue"); //Loads Admin Menue

            }
            else
            {
                //To inform admin that his login has failed
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Member Login Information");
                alert.setContentText("Invalid User ID or Password");
                alert.showAndWait();
                closeStage();
                loadWindow("/homepage.fxml","Library Manager");//Loads Homepage if login failed

            }
        }
    }

    public void closeStage()//method to close current stage
    {
        Stage stage=(Stage)uID.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent)//Cancel method
    {
        closeStage();
        loadWindow("/homepage.fxml","Library Manager");//Loads Home Page
    }
    public void loadWindow(String loc,String title) //method to load scene
    {

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
