package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Parent root = FXMLLoader.load(getClass().getResource("../style/sample.fxml"));
        primaryStage.setTitle("CAT-Voice");
        primaryStage.setResizable(false);
        //primaryStage.setScene(new Scene(root, 460, 625));
        primaryStage.show();
    }

}
