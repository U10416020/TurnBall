package TurnBall;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class DownBall {
    int number[][] = new int[13][7];
    int fit;   
    
    ImageView ball[][] = new ImageView[6][5];
    ImageView ball2[][] = new ImageView[6][5];
    
    DownBall(int[][] newNumber,ImageView[][] newBall,ImageView[][] newBall2,int newFit){
        number = newNumber;
        ball2 = newBall2;
        ball = newBall;
        fit = newFit;
        count();        
    }  
    
     void count(){
        int count[] = new int[5];
        for(int j = 1;j<6;j++){
            for(int i = 6;i<12;i++){
                if(number[i][j]==6){
                    count[j-1]++;
                }                
            }            
        }
        down();        
    }
    
    void down(){
        int count[] = new int[5];
        double x[] = new double[5];
        double y[] = new double[5];
        
        for(int i = 11;i>=6;i--){
            for(int j = 1;j<6;j++){
                if(number[i][j]==6 && x[j-1] == 0){
                    x[j-1] = ball[i-6][j-1].getX();
                    y[j-1] = ball[i-6][j-1].getY();
                    System.out.println("i = "+i+"\tj = " + j +"\tX: " + x[j-1] + "\tY: " + y[j-1]);                                        
                }
                else if(x[j-1] != 0){                    
                    if(number[i][j]!=6){
                        setPath(ball[i-6][j-1],x[j-1],y[j-1],count[j-1]);
                        count[j-1]++;
                    }      
                    System.out.println("Count: " +(j-1) +" "+count[j-1]);
                }                
            }            
        }
    }
    
    
    void setPath(ImageView ball, double x, double y,int count){
        Path path = new Path();
        path.getElements().add(new MoveTo(ball.getX()+47,ball.getY()+47));        
        path.getElements().add(new LineTo(x+47,(y-(count*fit)+47)));
        System.out.println("LineX: " + (x+47)+"\tLineY: " + (y-(count*fit)+47));
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(path);
        pt.setNode(ball);
        pt.setOrientation(OrientationType.NONE);
        pt.setCycleCount(1);
        pt.setAutoReverse(false);
        pt.play();
        ball.setX(x);
        ball.setY(y-count*fit);        
    }
    
    void setNumber(){        
        int change[] = new int[5];
        for(int i = 11;i>=6;i--){
            for(int j = 1;j<6;j++){                
                if(change[j-1]==1){                    
                    while(number[i+1][j]==6){                        
                        for(int k = i;k>0;k--){
                            number[k+1][j] = number[k][j];
                            System.out.println("K: " + k);
                        }
                    }
                }
                
                if(number[i][j]==6){
                    change[j-1] = 1;                    
                }
                else{
                    change[j-1] = 0;
                }
                
            }            
        }
    }
    
    int[][] getNumber(){
        setNumber();
        return number;
    }
}
