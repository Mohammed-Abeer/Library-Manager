

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*Author- Sadnan Kibria Kawshik,Roll-15
Main menu for add member interface
Used for debugging Member Adding Interface
 */
public class AddMember extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addmember.fxml"));//loads book adding interface
        primaryStage.setTitle("Add Member");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
