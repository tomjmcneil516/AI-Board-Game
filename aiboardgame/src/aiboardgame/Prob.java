package aiboardgame;

import java.util.*;
public class Prob
{
    private double[][][] grid;
    
    private int Pieces;
    private int[] pieceCount;
    
    private Board board;
    public Prob(Board board)
    {
        this.board = board;
        Pieces = board.getDim();
        pieceCount = new int[4];
        pieceCount[0] = Pieces/3;
        pieceCount[1] = Pieces/3;
        pieceCount[2] = Pieces/3;
        pieceCount[3] = (Pieces/3 - 1) * (board.getDim() - 2);
        grid = new double[4][Pieces][Pieces]; 
    }

    public double[][][] getGrid(){
        return grid;
    }
    
    public void printProbs(){
        System.out.println("----------------------------------------------------------------");
        for(int i = 0; i < board.getDim(); i++){
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Wp=%.3f | ", grid[0][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Hp=%.3f | ", grid[1][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Mp=%.3f | ", grid[2][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Pp=%.3f | ", grid[3][i][j]);
            }
            System.out.println();
            System.out.println("----------------------------------------------------------------");
        }
    }
    
    public void initialize(){
        for(int i = 0; i < board.getDim(); i++){
            if(i % 3 == 0){
                grid[0][0][i] = 1;
            }
            if(i % 3 == 1){
                grid[1][0][i] = 1;
            }
            if(i % 3 == 2){
                grid[2][0][i] = 1;
            }
        }
        for(int i = 1; i < board.getDim() - 1; i++){
            for(int j = 0; j < board.getDim(); j++){
                grid[3][i][j] = ((double)board.getDim()/3 - 1)/(double)board.getDim();
            }
        }
    }
    
    public void setFalse(int x, int y, int z){
        if(x + 1 < board.getDim() && y + 1 < board.getDim()){
            grid[z][x + 1][y + 1] = 0;
        }
        if(x + 1 < board.getDim() && y - 1 >= 0){
            grid[z][x + 1][y - 1] = 0;
        }
        if(x - 1 >= 0 && y + 1 < board.getDim()){
            grid[z][x - 1][y + 1] = 0;
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            grid[z][x - 1][y - 1] = 0;
        }
        if(x + 1 < board.getDim()){
            grid[z][x + 1][y] = 0;
        }
        if(y + 1 < board.getDim()){
            grid[z][x][y + 1] = 0;
        }
        if(x - 1 >= 0){
            grid[z][x - 1][y] = 0;
        }
        if(y - 1 >= 0){
            grid[z][x][y - 1] = 0;
        }
        grid[z][x][y] = 0;
    }
    
    private boolean[][][] setNeighbors(boolean[][][] neighbors, int x, int y, int z){
        if(z == 3){
            for(int i = 0; i < board.getDim(); i++){
                if(!(grid[3][x][i] == 0)){
                    neighbors[3][x][i] = true;
                }
                if(x + 1 < board.getDim()){
                    if(!(grid[3][x + 1][i] == 0)){
                        neighbors[3][x + 1][i] = true;
                    }
                }
                if(x - 1 > 0){
                    if(!(grid[3][x - 1][i] == 0)){
                        neighbors[3][x - 1][i] = true;
                    }
                }
            }
            return neighbors;
        }
        
        
        if(x + 1 < board.getDim() && y + 1 < board.getDim()){
            if(!(grid[z][x + 1][y + 1] == 0)){
                neighbors[z][x + 1][y + 1] = true;
            }
        }
        if(x + 1 < board.getDim() && y - 1 >= 0){
            if(!(grid[z][x + 1][y - 1] == 0)){
                neighbors[z][x + 1][ y - 1] = true;
            }
        }
        if(x - 1 >= 0 && y + 1 < board.getDim()){
            if(!(grid[z][x - 1][y + 1] == 0)){
                neighbors[z][x - 1][y + 1] = true;
            }
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            if(!(grid[z][x - 1][y - 1] == 0)){
                neighbors[z][x - 1][y - 1] = true;
            }
        }
        if(x + 1 < board.getDim()){
            if(!(grid[z][x + 1][y] == 0)){
                neighbors[z][x + 1][y] = true;
            }
        }
        if(y + 1 < board.getDim()){
            if(!(grid[z][x][y + 1] == 0)){
                neighbors[z][x][y + 1] = true;
            }
        }
        if(x - 1 >= 0){
            if(!(grid[z][x - 1][y] == 0)){
                neighbors[z][x - 1][y] = true;
            }
        }
        if(y - 1 >= 0){
            if(!(grid[z][x][y - 1] == 0)){
                neighbors[z][x][y - 1] = true;
            }
        }
        return neighbors;
    }  
    
    public void printGrid(double[][][]b){
        System.out.println();
        for(int i = 0; i < board.getDim(); i++){
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Wp=%.3f  ", b[0][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Hp=%.3f  ", b[1][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Mp=%.3f  ", b[2][i][j]);
            }
            System.out.println();
            for(int j = 0; j < board.getDim(); j++){
                System.out.printf("Pp=%.3f  ", b[3][i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }
    

    public void setObsProbs(boolean[][][] obs){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    if(obs[i][j][k] == false && board.getSpace(j, k).charAt(0) == '2'){
                        setFalse(j, k, i);
                    }
                }
            }
        }
        grid = normalize(grid, pieceCount);
        boolean[][][]neighbors = new boolean[4][board.getDim()][board.getDim()];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    if(obs[i][j][k] == true){
                        neighbors = setNeighbors(neighbors, j, k, i);
                    }
                }
            }
        }       
        ArrayList<ArrayList< ArrayList<int[]> > >states = new ArrayList< ArrayList< ArrayList<int[]> > >();
        for(int i = 0; i < 3; i++){
            ArrayList< ArrayList<int[]> >temp = new ArrayList< ArrayList<int[]> >();
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    if(neighbors[i][j][k] == true){
                        int[]cord = new int[2];
                        cord[0] = j;
                        cord[1] = k;
                        ArrayList<int[]> cordset = new ArrayList<int[]>();
                        cordset.add(cord);
                        temp.add(cordset);
                    }
                }
            }
            if(pieceCount[i] > 1){
                int size = temp.size();
                for(int j = 0; j < size; j++){
                    for(int k = j + 1; k < size; k++){
                        ArrayList<int[]> cordset = new ArrayList<int[]>();
                        int[]cord = new int[2];
                        cord[0] = temp.get(j).get(0)[0]; 
                        cord[1] = temp.get(j).get(0)[1];
                        cordset.add(cord);                      
                        cord = new int[2];
                        cord[0] = temp.get(k).get(0)[0]; 
                        cord[1] = temp.get(k).get(0)[1];
                        cordset.add(cord);
                        temp.add(cordset);
                    }
                }
            }
            states.add(temp);
        }
        ArrayList< ArrayList<int[]> >temp = new ArrayList< ArrayList<int[]> >();
        for(int j = 0; j < board.getDim(); j++){
           ArrayList<int[]> cordset = new ArrayList<int[]>();
           for(int k = 0; k < board.getDim(); k++){
               if(neighbors[3][j][k] == true){
                   int[]cord = new int[2];
                   cord[0] = j;
                   cord[1] = k;
                   cordset.add(cord);
               }
           }
           if(cordset.size()!= 0){
                   temp.add(cordset);
           }
        }
        temp = getPitStates(temp);
        states.add(temp);
        genObsProbs(states, obs);
    }
    
    private ArrayList < ArrayList<int[]> > getPitStates(ArrayList < ArrayList<int[]> >oldPitStates){
        ArrayList < ArrayList<int[]> >newPitStates = oldPitStates;
        int[] ptr = new int[oldPitStates.size()];
        if(ptr.length == 0){
            return newPitStates;
        }
        while(true){
            ArrayList<int[]>temp = new ArrayList<int[]>();
            for(int i = 0; i < ptr.length; i++){
                int[] cords = new int[2];
                cords[0] = oldPitStates.get(i).get(ptr[i])[0];
                cords[1] = oldPitStates.get(i).get(ptr[i])[1];
                temp.add(cords);
            }
            newPitStates.add(temp);
            int k = 0;
            while(k < ptr.length){
                ptr[k] = ptr[k] + 1;
                if(oldPitStates.get(ptr.length - 1).size() == ptr[ptr.length - 1]){
                    return newPitStates;
                }
                if(oldPitStates.get(k).size() == ptr[k]){
                    ptr[k] = 0;
                }
                else{
                    break;
                }
                k++;
            }
        }
    }
    
    
        
    private void genObsProbs(ArrayList< ArrayList< ArrayList<int[]> > >states, boolean[][][]obs){
        if(states.get(0).size() == 0 && states.get(1).size() == 0 && states.get(2).size() == 0 && states.get(3).size() == 0){
            return;
        }
        
        
        int w = 0;
        int h = 0;
        int m = 0;
        int p = 0;
        double[][][] result = new double[4][board.getDim()][board.getDim()];
        double finalfactor = 0;
               
        while(true){
            boolean failed = false;
            char[][]temp = new char[board.getDim()][board.getDim()];
            if(states.get(0).size() != 0){
                for(int i = 0; i < states.get(0).get(w).size(); i++){
                    temp[ states.get(0).get(w).get(i)[0] ][ states.get(0).get(w).get(i)[1] ] = 'w';
                }
            }
            if(states.get(1).size() != 0){
                for(int i = 0; i < states.get(1).get(h).size(); i++){
                    if(temp[ states.get(1).get(h).get(i)[0] ][ states.get(1).get(h).get(i)[1] ] == 0){
                        temp[ states.get(1).get(h).get(i)[0] ][ states.get(1).get(h).get(i)[1] ] = 'h';
                    }
                    else{
                        failed = true;
                    }
                }
            }
            if(failed == false){
                if(states.get(2).size() != 0){
                    for(int i = 0; i < states.get(2).get(m).size(); i++){
                        if(temp[ states.get(2).get(m).get(i)[0] ][ states.get(2).get(m).get(i)[1] ] == 0){
                            temp[ states.get(2).get(m).get(i)[0] ][ states.get(2).get(m).get(i)[1] ] = 'm';
                        }
                        else{
                            failed = true;
                        }
                    }
                }
            }
            if(failed == false){
                if(states.get(3).size() != 0){
                    for(int i = 0; i < states.get(3).get(p).size(); i++){
                        if(temp[ states.get(3).get(p).get(i)[0] ][ states.get(3).get(p).get(i)[1] ] == 0){
                            temp[ states.get(3).get(p).get(i)[0] ][ states.get(3).get(p).get(i)[1] ] = 'p';
                        }
                        else{
                            failed = true;
                        }
                    }
                }
            }
           
            if(failed == false){
                if(checkValidity(temp, obs) == false){
                    failed = true;
                }
            }

            double[][][] newGrid = new double[4][board.getDim()][board.getDim()];
            int[] pieceCount2 = new int[4];
            pieceCount2[0] = pieceCount[0]; pieceCount2[1] = pieceCount[1]; pieceCount2[2] = pieceCount[2]; pieceCount2[3] = pieceCount[3];
            
            
            if(failed == false){
                double probfactor = 1;
                for(int i = 0; i < board.getDim(); i++){
                    for(int j = 0; j < board.getDim(); j++){
                        for(int k = 0; k < 4; k++){
                            newGrid[k][i][j] = grid[k][i][j];
                        }
                    }
               }
                           
               
               for(int i = 0; i < board.getDim(); i++){
                    for(int j = 0; j < board.getDim(); j++){
                        if(temp[i][j] == 'w'){
                            newGrid[0][i][j] = 1;
                            newGrid[1][i][j] = 0;
                            newGrid[2][i][j] = 0;
                            newGrid[3][i][j] = 0;
                            pieceCount2[0] = pieceCount2[0] - 1;
                            newGrid = normalize(newGrid, pieceCount2);
                            probfactor = probfactor * grid[0][i][j];
                        }
                        else if(temp[i][j] == 'h'){
                            newGrid[0][i][j] = 0;
                            newGrid[1][i][j] = 1;
                            newGrid[2][i][j] = 0;
                            newGrid[3][i][j] = 0;
                            pieceCount2[1] = pieceCount2[1] - 1;
                            newGrid = normalize(newGrid, pieceCount2);
                            probfactor = probfactor * grid[1][i][j];
                        }
                        else if(temp[i][j] == 'm'){
                            newGrid[0][i][j] = 0;
                            newGrid[1][i][j] = 0;
                            newGrid[2][i][j] = 1;
                            newGrid[3][i][j] = 0;
                            pieceCount2[2] = pieceCount2[2] - 1;
                            newGrid = normalize(newGrid, pieceCount2);
                            probfactor = probfactor * grid[2][i][j];
                        }
                        else if(temp[i][j] == 'p'){
                            newGrid[0][i][j] = 0;
                            newGrid[1][i][j] = 0;
                            newGrid[2][i][j] = 0;
                            newGrid[3][i][j] = 1;
                            pieceCount2[3] = pieceCount2[3] - 1;
                            newGrid = normalize(newGrid, pieceCount2);
                            probfactor = probfactor * grid[3][i][j];
                        }
                    }
                }
                finalfactor = finalfactor + probfactor;
                newGrid = scale(newGrid, probfactor);
                result = merge(result, newGrid);
            }
            w++;
            if(w >= states.get(0).size()){
                w = 0;
                h++;
                if(h >= states.get(1).size()){
                    h = 0;
                    m++;
                    if(m >= states.get(2).size()){
                        m = 0;
                        p++;
                        if(p >= states.get(3).size()){
                            break;
                        }
                    }
                }
            }   
        } 
        if(finalfactor != 0){
            grid = scale(result, (1/finalfactor));
        }
        grid = normalize(grid, pieceCount);
    }
    
    private boolean checkNeighbors(int x, int y, int z, char[][] temp){
        char c;
        if(z == 0){
            c = 'w';
        }
        else if(z == 1){
            c = 'h';
        }
        else if(z == 2){
            c = 'm';
        }
        else{
            c = 'p';
        }
        
        if(x + 1 < board.getDim() && y + 1 < board.getDim()){
            if(temp[x + 1][y + 1] == c){
                return true;
            }
        }
        if(x + 1 < board.getDim() && y - 1 >= 0){
            if(temp[x + 1][y - 1] == c){
                return true;
            }
        }
        if(x - 1 >= 0 && y + 1 < board.getDim()){
            if(temp[x - 1][y + 1] == c){
                return true;
            }
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            if(temp[x - 1][y - 1] == c){
                return true;
            }
        }
        if(x + 1 < board.getDim()){
            if(temp[x + 1][y] == c){
                return true;
            }
        }
        if(y + 1 < board.getDim()){
            if(temp[x][y + 1] == c){
                return true;
            }
        }
        if(x - 1 >= 0){
            if(temp[x - 1][y] == c){
                return true;
            }
        }
        if(y - 1 >= 0){
            if(temp[x][y - 1] == c){
                return true;
            }
        }
        return false;
    }  
    
    
    private boolean checkValidity(char[][] temp, boolean[][][] obs){
        for(int k = 0; k < 4; k++){
            for(int i = 0; i < board.getDim(); i++){
                for(int j = 0; j < board.getDim(); j++){
                    if(obs[k][i][j] == true){
                          if(checkNeighbors(i, j, k, temp) == false){
                                return false;
                          }
                    }   
                }
            }
        }
        for(int i = 1; i < board.getDim() - 1; i++){
             int pitcount = board.getDim()/3;
             for(int j = 0; j < board.getDim(); j++){
                  if(temp[i][j] == 'p'){
                      pitcount--;
                  }
             }
             if(pitcount < 0){
                 return false;
             }
        }
        return true;
    }
    
    private double[][][] scale(double[][][]result, double scaler){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    result[i][j][k] = scaler * result[i][j][k];
                }
            }
        }  
        return result;
    }
    
    private double[][][] merge(double[][][]result, double[][][]adder){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    result[i][j][k] = result[i][j][k] + adder[i][j][k];
                }
            }
        }  
        return result;
    }
    
    private double[][][] coeffize(double[][][] newgrid, double[] coeff){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    if(newgrid[i][j][k] < .9999){
                        newgrid[i][j][k] = newgrid[i][j][k] * coeff[i];
                    }
                }
            }
        }
        return newgrid;
    }
    
    private double[][][] normalize(double[][][] newgrid, int[] pieceCount2){
        
        double[]sum = new double[4];
        
        for(int i = 1; i < board.getDim() - 1; i++){
            int pitcount = board.getDim()/3 - 1;
            double pitprob = 0;
            for(int j = 0; j < board.getDim(); j++){
                if(newgrid[3][i][j] > .9999){
                    pitcount--;
                }
                else{
                    pitprob += newgrid[3][i][j];
                } 
            }
            for(int j = 0; j < board.getDim(); j++){
                if(newgrid[3][i][j] < .9999){
                    newgrid[3][i][j] = newgrid[3][i][j] * pitcount;
                    if(pitprob > 0){
                        newgrid[3][i][j] = newgrid[3][i][j] / pitprob;
                    }
                }
            }
        }
        
        
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < board.getDim(); j++){
                for(int k = 0; k < board.getDim(); k++){
                    if(newgrid[i][j][k] < .9999){
                        sum[i] += newgrid[i][j][k];
                    }
                }
            }
            if(sum[i] > 0){
                for(int j = 0; j < board.getDim(); j++){
                    for(int k = 0; k < board.getDim(); k++){
                        if(newgrid[i][j][k] < .9999){
                            newgrid[i][j][k] = newgrid[i][j][k] * (double)pieceCount2[i] / sum[i];
                        }
                    }
                }
            }
        }
        return newgrid;
    }
    
    public void setProbs(){
        double[][][]probs2 = new double[4][board.getDim()][board.getDim()];
        
        for(int i = 0; i < board.getDim(); i++){
            for(int j = 0; j < board.getDim(); j++){
                probs2[0][i][j] = (1 - (1/(double)Pieces)) * grid[0][i][j] + getAdjProb(i, j, 0);
                probs2[1][i][j] = (1 - (1/(double)Pieces)) * grid[1][i][j] + getAdjProb(i, j, 1);
                probs2[2][i][j] = (1 - (1/(double)Pieces)) * grid[2][i][j] + getAdjProb(i, j, 2);
                probs2[3][i][j] = grid[3][i][j];
            }
        }
        grid = probs2;
        printProbs();
    }
    
    public void setPieceCount(int[]newPieceCount, int newPieces){
        pieceCount = newPieceCount;
        Pieces = newPieces;
    }
    
    public void setMoveProbs(int x, int y){
            grid[0][x][y] = 0;
            grid[1][x][y] = 0;
            grid[2][x][y] = 0;
            grid[3][x][y] = 0;
        if(board.getSpace(x, y).charAt(0) == '2'){
            normalize(grid, pieceCount);
            setObsProbs(board.getObsGrid());
        }
        else if(board.getSpace(x, y).equals("Pit")){
            grid[3][x][y] = 1;
            normalize(grid, pieceCount);
        }
        else if(board.getSpace(x, y).substring(1).equals("Wumpus")){
            grid[0][x][y] = 1;
        }
        else if(board.getSpace(x, y).substring(1).equals("Hero")){
            grid[1][x][y] = 1;
        }
        else if(board.getSpace(x, y).substring(1).equals("Mage")){
            grid[2][x][y] = 1;
        }
        Pieces = board.CountPlayerPieces();
        
    }
    
    
    
    private double getAdjProb(int x, int y, int z){
        double count = 0;
        if(grid[0][x][y] == 1 || grid[1][x][y] == 1 || grid[2][x][y] == 1){
            return 0;
        }
        if(x + 1 < board.getDim() && y + 1 < board.getDim()){
            count += grid[z][x + 1][y + 1] / (Pieces * getAdjCount(x + 1, y + 1, z));
        }
        if(x + 1 < board.getDim() && y - 1 >= 0){
            count += grid[z][x + 1][y - 1] / (Pieces * getAdjCount(x + 1, y - 1, z));
        }
        if(x - 1 >= 0 && y + 1 < board.getDim()){
            count += grid[z][x - 1][y + 1] / (Pieces * getAdjCount(x - 1, y + 1, z));
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            count += grid[z][x - 1][y - 1] / (Pieces * getAdjCount(x - 1, y - 1, z));
        }
        if(x + 1 < board.getDim()){
            count += grid[z][x + 1][y] / (Pieces * getAdjCount(x + 1, y, z));
        }
        if(y + 1 < board.getDim()){
            count += grid[z][x][y + 1] / (Pieces * getAdjCount(x, y + 1, z));
        }
        if(x - 1 >= 0){
            count += grid[z][x - 1][y] / (Pieces * getAdjCount(x - 1, y, z));
        }
        if(y - 1 >= 0){
            count += grid[z][x][y - 1] / (Pieces * getAdjCount(x, y - 1, z));
        }
        return count;
    }
    
    private int getAdjCount(int x, int y, int z){
        int count = 0;
        if(x + 1 < board.getDim() && y + 1 < board.getDim()){
            if(grid[0][x + 1][y + 1] == 1 || grid[1][x + 1][y + 1] == 1 || grid[2][x + 1][y + 1] == 1){
            }
            else
            count++;
        }
        if(x + 1 < board.getDim() && y - 1 >= 0){
            if(grid[0][x + 1][y - 1] == 1 || grid[1][x + 1][y - 1] == 1 || grid[2][x + 1][y - 1] == 1){
            }
            else
            count++;
        }
        if(x - 1 >= 0 && y + 1 < board.getDim()){
            if(grid[0][x - 1][y + 1] == 1 || grid[1][x - 1][y + 1] == 1 || grid[2][x - 1][y + 1] == 1){
            }
            else
            count++;
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            if(grid[0][x - 1][y - 1] == 1 || grid[1][x - 1][y - 1] == 1 || grid[2][x - 1][y - 1] == 1){
            }
            else
            count++;
        }
        if(x + 1 < board.getDim()){
            if(grid[0][x + 1][y] == 1 || grid[1][x + 1][y] == 1 || grid[2][x + 1][y] == 1){
            }
            else
            count++;
        }
        if(y + 1 < board.getDim()){
            if(grid[0][x][y + 1] == 1 || grid[1][x][y + 1] == 1 || grid[2][x][y + 1] == 1){
            }
            else
            count++;
        }
        if(x - 1 >= 0){
            if(grid[0][x - 1][y] == 1 || grid[1][x - 1][y] == 1 || grid[2][x - 1][y] == 1){
            }
            else
            count++;
        }
        if(y - 1 >= 0){
            if(grid[0][x][y - 1] == 1 || grid[1][x][y - 1] == 1 || grid[2][x][y - 1] == 1){
            }
            else
            count++;
        }
        return count;
    }
}
