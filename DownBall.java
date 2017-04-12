package TurnBall;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class DownBall extends Thread{
    private int number[][] = new int[13][7];
    private int fit;       
    private ImageView ball[][] = new ImageView[12][5];    
    
    public DownBall(int[][] newNumber,ImageView[][] newBall,int newFit){
        number = newNumber;        
        ball = newBall;
        fit = newFit;
        count();        
    }  
    
    public void count(){
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
    
    public void down(){        
        int count[] = new int[5];
        double x[] = new double[5];
        double y[] = new double[5];
        
        for(int i = 0;i<5;i++){
            count[i]=0;
        }
        
        for(int i = 11;i>=0;i--){
            for(int j = 1;j<6;j++){
                if(number[i][j]==6 && x[j-1] == 0){
                    x[j-1] = ball[i][j-1].getX();
                    y[j-1] = ball[i][j-1].getY();                                     
                }
                else if(x[j-1] != 0){                    
                    if(number[i][j]!=6){
                        setPath(ball[i][j-1],x[j-1],y[j-1],count[j-1]);
                        count[j-1]++;
                    }
                }                
            }            
        }
    }
    
    
    public void setPath(ImageView ball, double x, double y,int count){
        Path path = new Path();
        path.getElements().add(new MoveTo(ball.getX()+47,ball.getY()+47));        
        path.getElements().add(new LineTo(x+47,(y-(count*fit)+47)));        
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(path);
        pt.setNode(ball);
        pt.setOrientation(OrientationType.NONE);
        pt.setCycleCount(1);
        pt.setAutoReverse(false);
        pt.play();                
    }
    
    public void setNumber(){        
        int change[] = new int[5];
        for(int i = 11;i>=6;i--){
            for(int j = 1;j<6;j++){                
                if(change[j-1]==1){                    
                    while(number[i+1][j]==6){                        
                        for(int k = i;k>0;k--){
                            number[k+1][j] = number[k][j];                            
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
    
    public int[][] getNumber(){
        setNumber();
        return number;
    }
}
