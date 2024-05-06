import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.lang.Math;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Duration;
import java.io.IOException;
public class level1controller extends App implements Initializable {
    private String x;
    private int y;
    private int max=5;
    private int m=30;
    private int min = 1;
    private int numberToGuess=(int) (Math.random()*(m-min+1)+min);
    private boolean Guessed = false;
    private Timeline countdownTimer;
    @FXML
    private Label cluetext;

    @FXML
    private TextField resulttext;

    @FXML
    private Label timerlabel;

    @FXML
    private Button next;

    @FXML
    private Button playagain;

    int i=1;
    @FXML
    public void checkGuess(ActionEvent event) {
        if(countdownTimer==null){
            setTimer();
        }
        x = resulttext.getText();
        int currentGuess = Integer.parseInt(x);
        if(currentGuess<numberToGuess ){
            
            cluetext.setText(i+"."+" The guessed number is Too Low");
            i++;
        }
        else if(currentGuess>numberToGuess){
           
            cluetext.setText(i+"."+" The guessed number is Too High");
            i++;
        }
        else if (currentGuess==numberToGuess){
            login obj=new login();
            String username = obj.username1;
            System.out.println(username);

            int timeleft = 20-remainingSeconds;
            if(timeleft==-1){
                cluetext.setText("You made it in first go..");
                Guessed=true;
                if(Guessed=true){
                    next.setVisible(true);
                }
            }
            else{
                cluetext.setText("Congratulations! You guessed the number in "+timeleft+"seconds");
                Guessed = true;
                resulttext.setEditable(false);
                stopTimer();
                if(Guessed == true){
                    next.setVisible(true);
                }
            }
        }
        resulttext.setFocusTraversable(Guessed);
        max--;
    }
    public void hint(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(".....HINT.....");
        int z = numberToGuess;
        int m1=0;
        int m2=4;
        int index=(int) (Math.random()*(m2-m1+1)+m1);
        y = numberToGuess*numberToGuess;
        String a[]=new String[4];
        a[0]=y+"";
        a[1]=sumofdigits(z);
        a[2]=Binaryform(z);
        a[3]=evenorodd(z);
        if(max<=3){
            switch(index){
                case 0:
                    alert.setContentText("The square of the number is:"+a[0]);
                    alert.show();
                    break;
                case 1:
                    alert.setContentText("The sum of digits of the number is:"+a[1]);
                    alert.show();
                    break;
                case 2:
                    alert.setContentText("The binary of the number is:"+a[2]);
                    alert.show();
                    break;
                case 3:
                alert.setContentText("The number is:"+a[3]);
                    alert.show();
                    break;
            }
        }
        else{
            alert.setContentText("Unlocks after two chances");
            alert.show();
        }
    }
    public void switchtohome(ActionEvent event) throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("mainscene.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }
    public void setTimer(){
    countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
    countdownTimer.setCycleCount(Timeline.INDEFINITE);
    countdownTimer.playFromStart();
    }
    int remainingSeconds=21;
    private void updateTimer() {
        remainingSeconds--;
        if (remainingSeconds <= 0 || max==0) {
            if(remainingSeconds<=0){
                resulttext.setEditable(false);
                cluetext.setText("Hahaa Time is up! The number Guessed is "+numberToGuess);
                stopTimer();
                if(Guessed==false){
                    playagain.setVisible(true);
                }
            }
            else{
                resulttext.setEditable(false);
                cluetext.setText("Hahaa number of guesses execeeded The number Guessed is "+numberToGuess);
                stopTimer();
            }
        }
        Platform.runLater(() -> {
            String x = remainingSeconds+"";
            timerlabel.setText(x);
        });
    }

    private void stopTimer() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
    }
    public void nextlevel(ActionEvent event)throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("level2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }
    
    public void playagain(ActionEvent e )throws IOException{
        Parent root2 = FXMLLoader.load(getClass().getResource("level1.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root2);
        stage.setScene(scene);
        stage.show();
    }
    public String sumofdigits(int y){
        int sum=0;
        while(y>0){
            int r= y%10;
            sum=sum+r;
            y=y/10;
        }
        return sum+"";
    }
    public String Binaryform(int z){
        String res="";
        while (z > 0) {
            int r = z % 2;
            res=res+r;
            z = z/ 2;
        }
        StringBuffer sb = new StringBuffer(res);
        StringBuffer res1 = sb.reverse();
        return res1+"";
    }
    public String evenorodd(int z){
        if(z%2!=0){
            return "odd";
        }
        else{
            return "even";
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        start();
    }
    public void start(){
        next.setVisible(false);
        playagain.setVisible(false);
    }
}





