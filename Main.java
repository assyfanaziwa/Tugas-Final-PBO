// Assyfa Naziwa Ganandy 2017051046
// Thalia Gemi Innaya 2017051025
// Melan Caniadi 2017051031

package koperasi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Asets/view.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Koperasi Sejahtera Abadi");
        stage.getIcons().add(new Image (Objects.requireNonNull(getClass().getResourceAsStream("Asets/logoKoperasi.png"))));
        stage.setScene(scene);
        stage.show();
    }
    
  public static void main(String[] args){
        launch(args);
    }
}
