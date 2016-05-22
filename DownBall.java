package TurnBall;

import javafx.scene.image.ImageView;

public class DownBall {
    int number[][] = new int[13][7];
    ImageView ball[][] = new ImageView[6][5];
    DownBall(int[][] newNumber,ImageView[][] newBall){
        number = newNumber;
        ball = newBall;
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
        double x[] = new double[5];
        double y[] = new double[6];
        for(int i = 11;i>=6;i--){
            for(int j = 1;j<6;j++){
                if(number[i][j]==6){
                    x[j-1] = ball[i][j].getX();
                    y[i-6] = ball[i][j].getY();
                    
                }
                
            }            
            
        }
    }
}
