

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*Author- Sadnan Kibria Kawshik,Roll-15
Main menu for admin main menu interface
Used for debugging Admin Main Interface
 */

public class AdminMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("adminmain.fxml"));
        primaryStage.setTitle("Admin Menu");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
