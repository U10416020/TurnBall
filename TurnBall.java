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
    private ClipboardContent content = new ClipboardContent();
    private ImageView first = new ImageView();        
    private int[][] number = new int[13][7];
    private int[][] record = new int[13][7];
    private ImageView[][] ball = new ImageView[12][5];    
    private double changeX,changeY, exChangeX, exChangeY;
    private int sizeX = 510, sizeY=5,fit=97;
    
    AllPane pane;
    FirstBall firstBall;
    CheckBall checkBall;
    DownBall downBall;
    
    @Override
    public void start(Stage primaryStage) {
        pane = new AllPane();
        pane.setStyle("-fx-background-color: black;");
        firstBall = new FirstBall();   
        
        ImageView test = new ImageView();
        test.setImage(pane.picture[0]);
        test.setX(-100);
        test.setY(-100);
        
        pane.getChildren().add(test);
                
        number = firstBall.getNumber();        
        System.out.println();
        
        for(int i = 0;i<12;i++){
            for(int j = 0;j<5;j++){                
                ball[i][j] = new ImageView();
                ball[i][j].setImage(pane.picture[number[i][j+1]]);
                ball[i][j].setFitHeight(fit);
                ball[i][j].setFitWidth(fit); 
                
                pane.getChildren().add(ball[i][j]);
                System.out.print(number[i][j+1]+" ");
                
                if(i >=6){
                    ball[i][j].setX(sizeX+j*fit);
                    ball[i][j].setY(sizeY+(i-6)*fit);
                    ball[i][j].addEventHandler(MouseEvent.DRAG_DETECTED,onDragDetectedEventHandler);
                    ball[i][j].addEventHandler(DragEvent.DRAG_DONE,onDragDoneEventHandler);
                    ball[i][j].addEventHandler(DragEvent.DRAG_OVER,onDragOverEventHandler);
                } 
                else{
                    ball[i][j].setX(sizeX+j*fit);
                    ball[i][j].setY(0-(6-i)*fit);
                }
            }
            System.out.println();
        }
        
        Scene scene = new Scene(pane, 1030, 600);        
        primaryStage.setTitle("Tower Of Saviors");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void inverse(){
        System.out.println("Inverse: ");
        for(int i = 6;i<12;i++){
            for(int j = 0;j<5;j++){   
                ball[i][j].setX(sizeX+j*fit);
                ball[i][j].setY(sizeY+(i-6)*fit);  
                ball[i][j].setImage(pane.picture[number[i][j+1]]);
                System.out.print(number[i][j+1]+" ");
            }
            System.out.println();
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
            
            checkBall = new CheckBall(); 
            
            number = checkBall.getNumber(number);
            inverse();            
            downBall = new DownBall(number,ball,fit);            
                            
            number = downBall.getNumber();
            
            System.out.println("ChangeBall: ");
            for(int i = 6;i<12;i++){
                for(int j = 1;j<6;j++){
                    System.out.print(number[i][j]+" ");
                }
                    System.out.println();
            }
            
            firstBall.setFirstUpBall();
            number = firstBall.getNumber();
            System.out.println("Random:");
            for(int i = 0;i<12;i++){
                for(int j = 1;j<6;j++){
                    System.out.print(number[i][j]+" ");
                }
                System.out.println();
            }
            
            ImageView source = (ImageView)event.getSource();
            
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
                
                i = 6+(int)(changeY-sizeY)/fit;
                j = (int)(changeX-sizeX)/fit;                
                
                for(int k = 0; k<6;k++){
                    if(source.getImage() == pane.picture[k]){
                        number[i][j+1] = k;
                    }
                }
            }
            event.consume();            
        }
    };
    
    public static void main(String[] args) {
        launch(args);
    }    
}
