package TurnBall;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AllPane extends Pane{
    public Image[] picture = new Image[7];
    public Image[] changePicture = new Image[6];
    private int fit=97;
    
    public AllPane(){        
        picture[0] = new Image("/image/red.png", fit, fit, false, false);
        picture[1] = new Image("/image/yellow.png", fit, fit, false, false);
        picture[2] = new Image("/image/blue.png", fit, fit, false, false);
        picture[3] = new Image("/image/green.png", fit, fit, false, false);
        picture[4] = new Image("/image/purple.png", fit, fit, false, false);
        picture[5] = new Image("/image/pink.png", fit, fit, false, false);
        picture[6] = new Image("/image/empty.png", fit, fit, false, false);       
        
        changePicture[0] = new Image("/image/change/red.png", fit, fit, false, false);
        changePicture[1] = new Image("/image/change/yellow.png", fit, fit, false, false);
        changePicture[2] = new Image("/image/change/blue.png", fit, fit, false, false);
        changePicture[3] = new Image("/image/change/green.png", fit, fit, false, false);
        changePicture[4] = new Image("/image/change/purple.png", fit, fit, false, false);
        changePicture[5] = new Image("/image/change/pink.png", fit, fit, false, false);
        
        
        ImageView buttom = new ImageView(new Image("/image/buttom.png",535,610,false,false));
        ImageView up = new ImageView(new Image("/image/up.png"));
        ImageView down = new ImageView(new Image("/image/down.png"));
        ImageView blood = new ImageView(new Image("/image/blood.png"));
        ImageView leader = new ImageView(new Image("/image/leader.png"));
        ImageView red1 = new ImageView(new Image("/image/red1.png"));
        ImageView yellow1 = new ImageView(new Image("/image/yellow1.png"));
        
        buttom.setX(485);
        buttom.setY(-10);
        
        up.setX(0);
        up.setY(0);
        up.setFitHeight(600);
        up.setFitWidth(100);
        
        down.setX(965);
        down.setY(0);
        down.setFitHeight(600);
        down.setFitWidth(100);
        
        blood.setX(450);
        blood.setY(0);
        blood.setFitHeight(600);
        blood.setFitWidth(100);
        
        leader.setX(375);
        leader.setY(500);
        leader.setFitHeight(100);
        leader.setFitWidth(100);
        
        red1.setX(375);
        red1.setY(250);
        red1.setFitHeight(100);
        red1.setFitWidth(100);
        
        yellow1.setX(375);
        yellow1.setY(0);
        yellow1.setFitHeight(100);
        yellow1.setFitWidth(100);
        
        getChildren().addAll(buttom,up,down,leader,blood,red1,yellow1);
        
    }
}
