package aiboardgame;

import java.util.*;


public class main
{
    


    public static void main(String[] args) 
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("NEW GAME STARTED");
        System.out.println();
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board dimension: ");
        int dim = sc.nextInt();
        Board board = new Board(dim, '1');
        board.setBoard();
        
        AI3 AIProb = new AI3(dim);
        Prob p = new Prob(board);
        p.initialize();
        p.setObsProbs(board.getObsGrid());
        p.printProbs();
        board.printBoard();
        
        while(board.checkWinner() == '3'){
            while(true){
                System.out.println("Press 1 to select move");
                System.out.println("Press 2 to toggle fog");
                System.out.println("Press 3 to print board");
                System.out.println("Press 4 to print AI probability distribution");
                System.out.println("Press 5 to print your observations");
                System.out.print("Enter Command: ");
                int cmd = sc.nextInt();
                System.out.println();
                if(cmd == 1){
                    break;
                }
                else if(cmd == 2){
                    board.setFog();
                }
                else if(cmd == 3){
                    board.printBoard();
                }
                else if(cmd == 4){
                    p.printProbs();
                }
                else if(cmd == 5){
                    board.printPlayerObs();
                }
                else{
                    System.out.println("Invalid Command");
                }
            }
            while(true){
                System.out.print("Enter start row: ");
                int x1 = sc.nextInt();
                System.out.print("Enter start column: ");
                int y1 = sc.nextInt();
                System.out.print("Enter end row: ");
                int x2 = sc.nextInt();
                System.out.print("Enter end column: ");
                int y2 = sc.nextInt();
                if(board.isValid(x1, y1, x2, y2)){
                    board.makeMove(x1, y1, x2, y2);
                    p.setProbs();
                    p.setPieceCount(board.getPiecesArray(), board.CountPlayerPieces());
                    p.setObsProbs(board.getObsGrid());
                    p.printProbs();
                    board.printBoard();
                    board.printPlayerObs();
                    break;
                }
                System.out.println("Invalid Move");
            }
            if(board.checkWinner() == '0'){
                System.out.println("Draw!");
                return;
            }
            if(board.checkWinner() == '1'){
                System.out.println("Human Wins!");
                return;
            }
            if(board.checkWinner() == '2'){
                System.out.println("AI Wins!");
                return;
            }
            Move m = AIProb.getMove(board);
            board.makeMove(m.getx1(), m.gety1(), m.getx2(), m.gety2());
            System.out.println( "THE AI MAKES ITS MOVE!");
            p.setPieceCount(board.getPiecesArray(), board.CountPlayerPieces());
            p.setMoveProbs(m.getx2(), m.gety2());
            p.printProbs();
            board.printBoard();
            board.printPlayerObs();
        }
        if(board.checkWinner() == '0'){
            System.out.println("Draw!");
        }
        if(board.checkWinner() == '1'){
            System.out.println("Human Wins!");
        }
        if(board.checkWinner() == '2'){
            System.out.println("AI Wins!");
        }
    }

  
}

