

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*Author- Sadnan Kibria Kawshik,Roll-15
Main menu for member main menu interface
Used for debugging Admin Main Interface
 */
public class MemberMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("membermain.fxml"));
        primaryStage.setTitle("Member Menu");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
