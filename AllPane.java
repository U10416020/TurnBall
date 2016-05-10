package TurnBall;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AllPane extends Pane{

    Image[] picture = new Image[6];
    AllPane(){        
        picture[0] = new Image("/image/red.png", 100, 100, false, false);
        picture[1] = new Image("/image/yellow.png", 100, 100, false, false);
        picture[2] = new Image("/image/blue.png", 100, 100, false, false);
        picture[3] = new Image("/image/green.png", 100, 100, false, false);
        picture[4] = new Image("/image/purple.png", 100, 100, false, false);
        picture[5] = new Image("/image/pink.png", 100, 100, false, false);
        
        ImageView asdf = new ImageView(new Image("/image/紅史.png"));
        asdf.setX(0);
        asdf.setY(0); 
        
        getChildren().add(asdf);
    }
}
