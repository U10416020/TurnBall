package TurnBall;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class TurnBall extends Application {
    //剪貼簿    
    ClipboardContent content = new ClipboardContent();
    ImageView first = new ImageView();        
    int[][] number = new int[13][7];
    int[][] record = new int[13][7];
    ImageView[][] ball = new ImageView[6][5];
    ImageView[][] ball2 = new ImageView[6][5];
    double changeX,changeY, exChangeX, exChangeY;
    int sizeX = 510, sizeY=15,fit=97;
    //CheckBall checkBall;
    AllPane pane;
    CheckBall checkBall;
    DownBall downBall;
    @Override
    public void start(Stage primaryStage) {
        pane = new AllPane();
        pane.setStyle("-fx-background-color: black;");
        FirstBall firstBall = new FirstBall();   
        
        ImageView test = new ImageView();
        test.setImage(pane.picture[0]);
        test.setX(-100);
        test.setY(-100);
        
        pane.getChildren().add(test);
                
        number = firstBall.getNumber();        
        
        for(int i = 0;i<6;i++){
            for(int j = 0;j<5;j++){                
                ball[i][j] = new ImageView();
                ball[i][j].setImage(pane.picture[number[i+6][j+1]]);
                ball[i][j].setFitHeight(fit);
                ball[i][j].setFitWidth(fit);
                ball[i][j].setX(sizeX+j*fit);
                ball[i][j].setY(sizeY+i*fit);                
                ball[i][j].addEventHandler(MouseEvent.DRAG_DETECTED,onDragDetectedEventHandler);
                ball[i][j].addEventHandler(DragEvent.DRAG_DONE,onDragDoneEventHandler);
                ball[i][j].addEventHandler(DragEvent.DRAG_OVER,onDragOverEventHandler);
                pane.getChildren().add(ball[i][j]);
                System.out.print(number[i+6][j+1]+" ");
            }
            System.out.println();
        }
        
        for(int i = 0;i<6;i++){
            for(int j = 0;j<5;j++){
                ball2[i][j] = new ImageView();
                ball2[i][j].setImage(pane.picture[number[i][j+1]]);
                ball2[i][j].setFitHeight(fit);
                ball2[i][j].setFitWidth(fit);
                ball2[i][j].setX(sizeX+j*fit);
                ball2[i][j].setY(0-(6-i)*fit);
                pane.getChildren().add(ball2[i][j]);
            }
        }
        
        Scene scene = new Scene(pane, 1030, 600);
        
        primaryStage.setTitle("Tower Of Saviors");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    void inverse(){
        for(int i = 0;i<6;i++){
            for(int j = 0;j<5;j++){   
                ball[i][j].setX(sizeX+j*fit);
                ball[i][j].setY(sizeY+i*fit);  
                ball[i][j].setImage(pane.picture[number[i+6][j+1]]);
            }
        }
    }
    
    //偵測拖曳事件(來源)
    EventHandler onDragDetectedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Detected");
            first = (ImageView)event.getSource();      
            
            for(int i = 0;i<6;i++){
                if(first.getImage() == pane.picture[i]){
                    first.setImage(pane.changePicture[i]);
                }
            }
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
            
            //vertical();
            
            checkBall = new CheckBall();            
            number = checkBall.getNumber(number);
            
            inverse();           
            
            downBall = new DownBall(number,ball,ball2,fit);
            
            number = downBall.getNumber();
            
            for(int i = 6;i<12;i++){
                for(int j = 1;j<6;j++){
                    System.out.print(number[i][j]+" ");
                }
                System.out.println();
            }
            
            //inverse();
            
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
                
                changeX=first.getX();
                changeY=first.getY();
                exChangeX=source.getX();
                exChangeY=source.getY();
                
                source.setX(changeX);
                source.setY(changeY);
                
                first.setX(exChangeX);
                first.setY(exChangeY);
                
                int i = 6+(int)(exChangeY-sizeY)/fit;
                int j = (int)(exChangeX-sizeX)/fit;
                
                for(int k = 0; k<6;k++){
                    if(first.getImage() == pane.changePicture[k]){
                        number[i][j+1] = k;
                    }
                }
                
                System.out.println("exChange I: " + i +" J: " + j+1 + " number: " + number[i+1][j+1]);
                
                i = 6+(int)(changeY-sizeY)/fit;
                j = (int)(changeX-sizeX)/fit;                
                
                for(int k = 0; k<6;k++){
                    if(source.getImage() == pane.picture[k]){
                        number[i][j+1] = k;
                    }
                }
                
                System.out.println("Change I: " + i +" J: " + j+1 + " number: " +number[i][j+1]);
                
                for( i = 6;i<12;i++){
            for( j = 1;j<6;j++){
                System.out.print(number[i][j]+" ");
            }
            System.out.println();
        }
                
            }
            event.consume();            
        }
    };
    
    public static void main(String[] args) {
        launch(args);
    }    
}
