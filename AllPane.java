package TurnBall;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AllPane extends Pane{

    Image[] picture = new Image[7];
    Image[] changePicture = new Image[6];
    
    AllPane(){        
        picture[0] = new Image("/image/red.png", 97, 97, false, false);
        picture[1] = new Image("/image/yellow.png", 97, 97, false, false);
        picture[2] = new Image("/image/blue.png", 97, 97, false, false);
        picture[3] = new Image("/image/green.png", 97, 97, false, false);
        picture[4] = new Image("/image/purple.png", 97, 97, false, false);
        picture[5] = new Image("/image/pink.png", 97, 97, false, false);
        picture[6] = new Image("/image/empty.png", 97, 97, false, false);       
        
        changePicture[0] = new Image("/image/change/red.png", 97, 97, false, false);
        changePicture[1] = new Image("/image/change/yellow.png", 97, 97, false, false);
        changePicture[2] = new Image("/image/change/blue.png", 97, 97, false, false);
        changePicture[3] = new Image("/image/change/green.png", 97, 97, false, false);
        changePicture[4] = new Image("/image/change/purple.png", 97, 97, false, false);
        changePicture[5] = new Image("/image/change/pink.png", 97, 97, false, false);
        
        
        ImageView buttom = new ImageView(new Image("/image/buttom.png",535,610,false,false));
        ImageView up = new ImageView(new Image("/image/up.png"));
        ImageView down = new ImageView(new Image("/image/down.png"));
        
        buttom.setX(485);
        buttom.setY(0);
        
        up.setX(0);
        up.setY(0);
        up.setFitHeight(600);
        up.setFitWidth(100);
        
        down.setX(965);
        down.setY(0);
        down.setFitHeight(600);
        down.setFitWidth(100);
        
        getChildren().addAll(buttom,up,down);
        
    }
}
