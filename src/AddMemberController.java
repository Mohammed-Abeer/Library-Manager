import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/*Author -Sadnan Kibria Kawshik, Roll-15
This is the add member controller file
All of the necessary functionalities of buttons and other functions are written in here*/

public class AddMemberController implements Initializable {
    public JFXTextField name;/*Javafx GUI Components*/
    public JFXTextField email;
    public JFXButton saveButton;
    public JFXButton cancelButton;
    public JFXTextField password;
    //Object of ClientManager to access methods of Library in a Server-Client System
    private ClientManager clientManager=new ClientManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addMember(ActionEvent actionEvent)//method to add member in GUI
    {
        /*Components to store information of GUI fields to
        create a member object
         */

        String mName=name.getText();
        String mPassword=password.getText();
        String mEmail=email.getText();
        //to check if user has left any field empty
        boolean flag=mName.isEmpty()||mPassword.isEmpty()||mEmail.isEmpty();

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
            Member member=new Member(mPassword,mName,mEmail);//creating new member of that name
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

        }
    }
    public void closeStage()//method to close current stage
    {
        Stage stage=(Stage)name.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent)//method for cancel button in GUI
    {
        closeStage();

    }
}
