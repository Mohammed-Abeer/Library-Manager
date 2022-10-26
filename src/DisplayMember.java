

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*Author- Sadnan Kibria Kawshik,Roll-15
Main method for display member interface
Used for debugging Member Displaying Interface
 */
public class DisplayMember extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("displaymember.fxml"));
        primaryStage.setTitle("Member Table");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
