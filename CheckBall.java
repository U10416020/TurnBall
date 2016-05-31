package TurnBall;

public class CheckBall {
    int[][] number = new int[13][7];      
    int[][] record = new int[13][7];
    
    CheckBall(){        
    }
    
    void setBall(int[][] newNumber){
        number = newNumber;
        int countV = 0,countH=0;
        for(int j = 1; j<6;j++){
            for(int i = 6;i<12;i++){
                if(i==6){
                    H(i,j);
                }
                else{
                    V(i,j);
                    H(i,j);
                }                                
            }
        }
        change();
    }
    
    void change(){
        for(int i = 6;i<12;i++){
            for(int j = 1;j<6;j++){
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
    
    int[][] getNumber(int[][] newNumber){
        setBall(newNumber);
        return number;
    }
}
