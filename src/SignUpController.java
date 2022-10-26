

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*Author -Sadnan Kibria Kawshik, Roll-15
This is the Home Page controller file
All of the necessary functionalities of buttons and other functions are written in here*/


public class SignUpController implements Initializable {

    public JFXTextField name;
    public JFXTextField password; //JavaFx GUI components for Member Login Menu
    public JFXTextField email;

    public JFXButton saveButton;
    public JFXButton cancelButton;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

   public void makeSignUp(ActionEvent actionEvent) {//method to sign up
        String mName = name.getText(); //getting components required to sign up from the text field
        String mPassword = password.getText();
        String mEmail = email.getText();
        Member member;


        boolean flag = mName.isEmpty()  || mPassword.isEmpty() || mEmail.isEmpty(); //flag to check if any field is empty

        if (flag) {
            //Informing user that he has left a field empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
        }
        else {
            member=new Member(mPassword,mName,mEmail);//creating new member of that name
            String result=clientManager.signUp(member);//A string that will return if member is added or not
            System.out.println(result);
            if(result.equals("ok"))
            {
                //Informing that Member is added
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Member Add Information");
                alert.setContentText("Congratulations Member Added Successfully.\nYour Member ID is : "+member.getId());
                alert.showAndWait();
            }
            closeStage();

            loadWindow("/homepage.fxml", "Library Manager");

        }
    }

    public void cancel(ActionEvent actionEvent) {//cancels the stage
        closeStage();
        loadWindow("/homepage.fxml","Library Manager");
       // clientManager.logout();
    }

    public void closeStage() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }

    public void loadWindow(String loc, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage primaryStage = new Stage(StageStyle.DECORATED);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
