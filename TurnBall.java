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
    double changeX,changeY, exChangeX, exChangeY;
    int sizeX = 510, sizeY=10,fit=97;
    //CheckBall checkBall;
    AllPane pane;
    CheckBall checkBall;
    DownBall downBall;
    @Override
    public void start(Stage primaryStage) {
        pane = new AllPane();
        pane.setStyle("-fx-background-color: black;");
        FirstBall firstBall = new FirstBall();        
                
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
        
        Scene scene = new Scene(pane, 1030, 600);
        
        primaryStage.setTitle("Hello World!");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*
    void vertical(){
        int countV = 0,countH=0;
        for(int j = 1; j<6;j++){
            for(int i = 1;i<7;i++){
                V(i,j);
                H(i,j);                
            }
        }
        change();
    }
    void change(){
        for(int i = 0;i<8;i++){
            for(int j = 0;j<7;j++){
                if(record[i][j]==1){
                    number[i][j] = 6;
                }
            }
        }
    }
    
    void V(int i ,int j){
        if(number[i][j]==number[i+1][j]&&number[i][j]==number[i-1][j]){
            record[i][j]=1;
            record[i-1][j]=1;
            record[i+1][j]=1;
        }
    }
    
    void H(int i ,int j){
        if(number[i][j]==number[i][j+1]&&number[i][j]==number[i][j-1]){
            record[i][j]=1;
            record[i][j-1]=1;
            record[i][j+1]=1;
        }
    }
    */
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
            
            //vertical();
            
            checkBall = new CheckBall(number);
            number = checkBall.getNumber();
            //
            
            for(int i = 0;i<6;i++){
                for(int j = 0;j<5;j++){   
                    ball[i][j].setX(sizeX+j*fit);
                    ball[i][j].setY(sizeY+i*fit);  
                    ball[i][j].setImage(pane.picture[number[i+6][j+1]]);
                }
            }
            
            downBall = new DownBall(number,ball);
            //checkBall = new CheckBall(number);
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
                    if(first.getImage() == pane.picture[k]){
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
