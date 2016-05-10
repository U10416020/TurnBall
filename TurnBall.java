package TurnBall;

import java.security.SecureRandom;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Eva
 */
public class TurnBall extends Application {
    //剪貼簿    
    ClipboardContent content = new ClipboardContent();
    ImageView first = new ImageView();    
    int count = 0;
    double changeX,changeY, exChangeX, exChangeY;
    
    @Override
    public void start(Stage primaryStage) {
        AllPane pane = new AllPane();
        ImageView[][] ball = new ImageView[5][6];
        SecureRandom random = new SecureRandom();
        
        for(int i = 0;i<5;i++){
            for(int j = 0;j<6;j++){
                int number = random.nextInt(6);
                ball[i][j] = new ImageView();
                ball[i][j].setImage(pane.picture[number]);
                ball[i][j].setFitHeight(80);
                ball[i][j].setFitWidth(80);
                ball[i][j].setX(500+i*80);
                ball[i][j].setY(0+j*80);                
                ball[i][j].addEventHandler(MouseEvent.DRAG_DETECTED,onDragDetectedEventHandler);
                ball[i][j].addEventHandler(DragEvent.DRAG_DONE,onDragDoneEventHandler);
                ball[i][j].addEventHandler(DragEvent.DRAG_OVER,onDragOverEventHandler);
                pane.getChildren().add(ball[i][j]);
            }
        }
        
        Scene scene = new Scene(pane, 1000, 600);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //偵測拖曳事件(來源)
    EventHandler onDragDetectedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Detected");
            first = (ImageView)event.getSource();      
            
            //開始拖曳作業
            Dragboard db = first.startDragAndDrop(TransferMode.ANY);         
            
            //圖片暫存剪貼簿            
            content.putImage(first.getImage());
            
            db.setContent(content);
            
            event.consume();
        }
    };
    
    //完成拖曳(來源)
    EventHandler onDragDoneEventHandler = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            System.out.println("DragDone");
            
            //if(event.getTransferMode()==TransferMode.MOVE){
                ImageView source = (ImageView)event.getSource();
                //source.setImage(content1.getImage());
                
            //}
            event.consume();
        }
    };
    
    //拖曳至上面(目標)
    EventHandler onDragOverEventHandler = new EventHandler<DragEvent>() {
        @Override
        public void handle(DragEvent event) {
            System.out.println("DragOver");
            if(event.getDragboard().hasImage()){
                System.out.println("!!!!");
                event.acceptTransferModes(TransferMode.ANY);
                ImageView source = (ImageView)event.getSource();
                System.out.println("SourceX: " + source.getX()+"\tSourceY: " + source.getY());
                changeX=first.getX();
                changeY=first.getY();
                exChangeX=source.getX();
                exChangeY=source.getY();
                
                source.setX(changeX);
                source.setY(changeY);
                
                first.setX(exChangeX);
                first.setY(exChangeY);
                
                System.out.println("FirstX: " + first.getX() + "\nFirstY: " + first.getY());                
                
            }
            event.consume();            
        }
    };
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
