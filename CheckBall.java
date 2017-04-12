package TurnBall;

public class CheckBall {
    private int[][] number = new int[13][7];      
    private int[][] record = new int[13][7];
    
    public CheckBall(){        
    }
    
    public void setBall(int[][] newNumber){
        for(int i = 0;i<13;i++){
            for(int j = 0;j<7;j++){
                record[i][j]=0;
            }
        }
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
    
    public void change(){
        for(int i = 6;i<12;i++){
            for(int j = 1;j<6;j++){
                if(record[i][j]==1){
                    number[i][j] = 6;
                }
            }
        }
    }
    
    public void V(int i ,int j){
        if(number[i][j]==number[i+1][j]&&number[i][j]==number[i-1][j]){
            record[i][j]=1;
            record[i-1][j]=1;
            record[i+1][j]=1;
        }
    }
    
    public void H(int i ,int j){
        if(number[i][j]==number[i][j+1]&&number[i][j]==number[i][j-1]){
            record[i][j]=1;
            record[i][j-1]=1;
            record[i][j+1]=1;
        }
    }
    
    public int[][] getNumber(int[][] newNumber){
        setBall(newNumber);
        return number;
    }
    
}
