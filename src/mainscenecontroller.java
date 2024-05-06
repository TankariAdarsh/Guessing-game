import java.io.IOException;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class mainscenecontroller {
    private Stage stage;
    private Scene scene;
    private Parent root1;
    
    @FXML
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;

    @FXML
    void switchtolevel1(ActionEvent event) throws IOException{

        root1 = FXMLLoader.load(getClass().getResource("level1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
        
    }

    

    @FXML
    void switchtolevel2(ActionEvent event) throws IOException,Exception{
            
                Parent root2 = FXMLLoader.load(getClass().getResource("level2.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root2);
                stage.setScene(scene);
                stage.show();
            

    }

    @FXML
    void switchtolevel3(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("level3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root3);
        stage.setScene(scene);
        stage.show();

    }
    

}


