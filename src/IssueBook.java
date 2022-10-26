

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*Author- Sadnan Kibria Kawshik,Roll-15
Main method for Issue Book interface
Used for debugging Issued Book Displaying Interface
 */

public class IssueBook extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addmember.fxml"));
        primaryStage.setTitle("Add Member");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
