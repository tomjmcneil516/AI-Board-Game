package aiboardgame;

import java.util.Random;

public class Board
{
    private double h;
    private String[][] BoardPosition;
    private int Dim;
    private char PlayerTurn;  //1 is Player, 2 is Agent
    private boolean FOG = true;
    
    Board(int Dim, char PlayerTurn){
        this.Dim = Dim;
        this.PlayerTurn = PlayerTurn;
        BoardPosition = new String[Dim][Dim];
    }    
    public void setBoard(){
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                BoardPosition[i][j] = "Empty";
            }
        }
        setPits();
        setPieces();
    }
    public void setFog(){
        if(FOG == true){
            FOG = false;
        }
        else{
            FOG = true;
        }
    }
    public void printBoard(){
        System.out.println();
        for(int k = 0; k < Dim; k++){
            System.out.print(k + "\t");
        }
        System.out.println("\n");
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].equals("Empty") || (FOG == true && PlayerTurn != BoardPosition[i][j].charAt(0))){
                    System.out.print("-----\t");
                }
                else if(BoardPosition[i][j].equals("Pit")){
                    System.out.print("-" + BoardPosition[i][j] + "-\t");
                }
                else if(BoardPosition[i][j].substring(1).equals("Wumpus")){
                    System.out.print(BoardPosition[i][j].substring(0,5) + "\t");
                }
                else{
                    System.out.print(BoardPosition[i][j] + "\t");
                }
                if(j == (Dim - 1)){
                    System.out.print(i);
                }
            }
            System.out.print("\n\n");
        }
        System.out.println();
    }
    public void setPits(){
        Random rand = new Random();
        for(int i = 1; i < Dim - 1; i++){
            int pitsCount = Dim / 3 - 1;
            while(pitsCount != 0){
                int j = rand.nextInt(Dim);
                if(!BoardPosition[i][j].equals("Pit")){
                    BoardPosition[i][j] = "Pit";
                    pitsCount--;
                }
            }
        }
    }
    public void setPieces(){
        for(int i = 0; i < Dim; i++){
            if(i % 3 == 0){
                BoardPosition[0][i] = "1Wumpus";
                BoardPosition[Dim - 1][i] = "2Wumpus";
            }
            if(i % 3 == 1){
                BoardPosition[0][i] = "1Hero";
                BoardPosition[Dim - 1][i] = "2Hero";
            }
            if(i % 3 == 2){
                BoardPosition[0][i] = "1Mage";
                BoardPosition[Dim - 1][i] = "2Mage";
            }
        }
    }
    
    public boolean[][][] getObsGrid(){
        boolean[][][]b = new boolean[4][Dim][Dim];
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].charAt(0) == '2'){
                    setGrid(b, i, j);
                }
            }
        }
        return b;
    }
    
    public boolean[][][] setGrid(boolean[][][]b, int x, int y){
        if(x + 1 < Dim && y + 1 < Dim){
            b = updateGrid(b, x, y, x + 1, y + 1);
        }
        if(x + 1 < Dim && y - 1 >= 0){
            b = updateGrid(b, x, y, x + 1, y - 1);
        }
        if(x - 1 >= 0 && y + 1 < Dim){
            b = updateGrid(b, x, y, x - 1, y + 1);
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            b = updateGrid(b, x, y, x - 1, y - 1);
        }
        if(x + 1 < Dim){
            b = updateGrid(b, x, y, x + 1, y);
        }
        if(y + 1 < Dim){
            b = updateGrid(b, x, y, x, y + 1);
        }
        if(x - 1 >= 0){
            b = updateGrid(b, x, y, x - 1, y);
        }
        if(y - 1 >= 0){
            b = updateGrid(b, x, y, x, y - 1);
        }
        return b;
    }
    public boolean[][][] updateGrid(boolean[][][]b, int x1, int y1, int x2, int y2){
        if(BoardPosition[x2][y2].equals("1Wumpus")){
                b[0][x1][y1] = true;
            }
            else if(BoardPosition[x2][y2].equals("1Hero")){
                b[1][x1][y1] = true;
            }
            else if(BoardPosition[x2][y2].equals("1Mage")){
                b[2][x1][y1] = true;
            }
            else if(BoardPosition[x2][y2].equals("Pit")){
                b[3][x1][y1] = true;
            }
        return b;
    }
    
    public void printPlayerObs(){
        System.out.println();
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].charAt(0) == '1'){
                    getPlayerObs(i, j);
                }
            }
        }
        System.out.println();
    }
     private void getPlayerObs(int x, int y){
        if(x + 1 < Dim && y + 1 < Dim){
            updatePlayerObs(x, y, x + 1, y + 1);
        }
        if(x + 1 < Dim && y - 1 >= 0){
            updatePlayerObs(x, y, x + 1, y - 1);
        }
        if(x - 1 >= 0 && y + 1 < Dim){
            updatePlayerObs(x, y, x - 1, y + 1);
        }
        if(x - 1 >= 0 && y - 1 >= 0){
            updatePlayerObs(x, y, x - 1, y - 1);
        }
        if(x + 1 < Dim){
            updatePlayerObs(x, y, x + 1, y);
        }
        if(y + 1 < Dim){
            updatePlayerObs(x, y, x, y + 1);
        }
        if(x - 1 >= 0){
            updatePlayerObs(x, y, x - 1, y);
        }
        if(y - 1 >= 0){
            updatePlayerObs(x, y, x, y - 1);
        }
    }
    private void updatePlayerObs(int x1, int y1, int x2, int y2){
        if(BoardPosition[x2][y2].equals("2Wumpus")){
                System.out.println(x1 + "," + y1 + " DETECTS A WUMPUS NEARBY");
            }
            else if(BoardPosition[x2][y2].equals("2Hero")){
                System.out.println(x1 + "," + y1 + " DETECTS A HERO NEARBY");
            }
            else if(BoardPosition[x2][y2].equals("2Mage")){
                System.out.println(x1 + "," + y1 + " DETECTS A MAGE NEARBY");
            }
            else if(BoardPosition[x2][y2].equals("Pit")){
                System.out.println(x1 + "," + y1 + " DETECTS A PIT NEARBY");
            }
    }
    
    public void printGrid(boolean[][][]b){
        System.out.println();
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                System.out.printf("Wp=" + b[0][i][j] + " ");
            }
            System.out.println();
            for(int j = 0; j < Dim; j++){
                System.out.printf("Hp=" + b[1][i][j] + " ");
            }
            System.out.println();
            for(int j = 0; j < Dim; j++){
                System.out.printf("Mp=" + b[2][i][j] + " ");
            }
            System.out.println();
            for(int j = 0; j < Dim; j++){
                System.out.printf("Pp=" + b[3][i][j] + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
    
    public char getPlayerTurn(){
        return PlayerTurn;
    }
    public int getDim(){
        return Dim;
    }
    public String getSpace(int x, int y){
        return BoardPosition[x][y];
    }
    public void setSpace(int x, int y, String s){
        BoardPosition[x][y] = s;
    }
    public void makeMove(int x1, int y1, int x2, int y2){
        String temp = BoardPosition[x1][y1];
        BoardPosition[x1][y1] = "Empty";
        if(BoardPosition[x2][y2].equals("Empty")){
            BoardPosition[x2][y2] = temp;
        }
        else if(BoardPosition[x2][y2].substring(1).equals("Wumpus")){
            if(temp.substring(1).equals("Hero")){
                BoardPosition[x2][y2] = temp;
            }
            else if(temp.substring(1).equals("Wumpus")){
                BoardPosition[x2][y2] = "Empty";
            }
        }
        else if(BoardPosition[x2][y2].substring(1).equals("Hero")){
            if(temp.substring(1).equals("Mage")){
                BoardPosition[x2][y2] = temp;
            }
            else if(temp.substring(1).equals("Hero")){
                BoardPosition[x2][y2] = "Empty";
            }
        }
        else if(BoardPosition[x2][y2].substring(1).equals("Mage")){
            if(temp.substring(1).equals("Wumpus")){
                BoardPosition[x2][y2] = temp;
            }
            else if(temp.substring(1).equals("Mage")){
                BoardPosition[x2][y2] = "Empty";
            }
        }
        if(PlayerTurn == '1'){
            PlayerTurn = '2';
        }
        else{
            PlayerTurn = '1';
        }
    }
    public boolean isValid(int x1, int y1, int x2, int y2){
        if((x2 > x1 + 1) || (y2 > y1 + 1) || (x2 < x1 - 1) || (y2 < y1 - 1)){
            return false;
        }
        if(BoardPosition[x1][y1].charAt(0) != PlayerTurn){
            return false;
        }
        if(x2 >= 0 && y2 >= 0 && x2 < Dim && y2 < Dim){
            if(BoardPosition[x2][y2].charAt(0) != PlayerTurn){
                return true;
            }
        }
        return false;
    }
    public char checkWinner(){
        int PlayerPieces = CountPlayerPieces();
        int AgentPieces = CountAgentPieces();
        if(PlayerPieces == 0 && AgentPieces == 0){
            return '0';
        }
        else if(AgentPieces == 0){
            return '1';
        }
        else if(PlayerPieces == 0){
            return '2';
        }
        return '3';
    }
    public int[] getPiecesArray(){
        int[]piecesArray = new int[4];
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].equals("1Wumpus")){
                    piecesArray[0] = piecesArray[0] + 1;
                }
                else if(BoardPosition[i][j].equals("1Hero")){
                    piecesArray[1] = piecesArray[1] + 1;
                }
                else if(BoardPosition[i][j].equals("1Mage")){
                    piecesArray[2] = piecesArray[2] + 1;
                }
            }
        }
        piecesArray[3] = (Dim/3 - 1) * (Dim - 2);;
        return piecesArray;
    }
    
    public int CountPlayerPieces(){
        int pieces = 0;
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].charAt(0) == '1'){
                    pieces++;
                }
            }
        }
        return pieces;
    }
    public int CountAgentPieces(){
        int pieces = 0;
        for(int i = 0; i < Dim; i++){
            for(int j = 0; j < Dim; j++){
                if(BoardPosition[i][j].charAt(0) == '2'){
                    pieces++;
                }
            }
        }
        return pieces;
    }
    public double getH(){
        return h;
    }
    public void setH(double h){
        this.h = h;
    }
}
