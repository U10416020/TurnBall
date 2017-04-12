package TurnBall;

import java.security.SecureRandom;

public class FirstBall {
    private int[][] number = new int[13][7];    
    private SecureRandom random = new SecureRandom();
    
    public FirstBall(){     
        setFirstUpBall();
        setFirstDownBall();
    }
    
    public void setFirstUpBall(){
        for(int i = 0;i<6;i++){
            for(int j = 0;j<7;j++){
                number[i][j] = -1;
            }
        }
        for(int i = 0;i<6;i++){
            for(int j = 1;j<6;j++){
                number[i][j] = random.nextInt(6);                
            }
        }
    }
    
    public void setFirstDownBall(){
        for(int i = 6;i<12;i++){
            for(int j = 0;j<7;j++){
                number[i][j] = -1;
            }
        }
        for(int i = 6;i<12;i++){
            for(int j = 1;j<6;j++){
                number[i][j] = random.nextInt(6);                
            }            
        }
        checkFirst();   
    }
    
    public void checkFirst(){
        int change=0, count=0;
        for(int i = 6;i<12;i++){
            for(int j = 1;j<6;j++){
                if(number[i][j] == number[i-1][j]){
                    count++;                    
                }
                if(number[i][j] == number[i+1][j]){
                    count++;                    
                }
                if(count>=2){                    
                    do{
                        change = random.nextInt(6);
                    }while(change==number[i-1][j]||change == number[i+1][j]||change == number[i][j-1]||change == number[i][j+1]);
                    number[i][j] = change;
                }
                count=0;
                if(number[i][j] == number[i][j-1]){
                    count++;                    
                }
                if(number[i][j] == number[i][j+1]){
                    count++;                    
                }
                if(count>=2){                    
                    do{
                        change = random.nextInt(6);
                    }while(change==number[i-1][j]||change == number[i+1][j]||change == number[i][j-1]||change == number[i][j+1]);
                    number[i][j] = change;
                }
                count=0;
            }          
        }
    }
    
    public int[][] getNumber(){
        return number;
    }
}
