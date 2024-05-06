
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import javax.naming.spi.DirStateFactory.Result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class login {
    private Stage stage;
    private Scene scene;
    private Parent root1;

    @FXML
    public TextField usernametext;

    @FXML
    private TextField passwordtext;

    @FXML 
    private Button signin;

    @FXML
    private Button signup;

    public String username1;
    public String password1;

    public void insert(ActionEvent event) throws Exception  {
        String username1 = usernametext.getText();
        String password1 = passwordtext.getText();

        
        String jdbcUrl = "jdbc:mysql://localhost:3306/project?useSSL=false";
        

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            String s="select *from players";
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery(s);
            while(rs.next())
            {
                String s1=rs.getString("username");
                String s2=rs.getString("password");
                if(s1.equals(username1) && s2.equals(password1))
                {
                    root1 = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();                
                    scene = new Scene(root1);
                    stage.setScene(scene);
                    stage.show();
                    statement.close();
                    rs.close();

                }
            }
            
        }
        catch(Exception e){
            //System.out.println("Please register first..");
        }
    }
    public void register(ActionEvent event){
        try{
            String username1 = usernametext.getText();
            String password1 = passwordtext.getText();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSl=false", "root", "root");
            String sql = "INSERT INTO players VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username1);
            preparedStatement.setString(2, password1);
            // preparedStatement.setInt(3,0);
            // preparedStatement.setInt(4,0);
            // preparedStatement.setInt(5,0);
            preparedStatement.executeUpdate();
            //System.out.println("Login information saved successfully!");
            root1 = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();                
            scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();
            connection.close();
            preparedStatement.close();
            System.out.println("Login information saved succesfully");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("The player is already registered");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(".....WARNING.....");
            alert.setContentText("The player is already registered");
            alert.show();
        }
        

    }
}

  