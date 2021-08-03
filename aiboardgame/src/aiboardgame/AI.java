package aiboardgame;

import java.lang.*;
import java.util.*;
public class AI
{
    private int d;
    public AI(int d){
        this.d = d - 1;
    }
    public Move getMove(Board b){
        ArrayList<Move> m = getLegalMoves(b);
        double maxValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for(int i = 0; i < m.size(); i++){
            Board b2 = makeMove(m.get(i), b);
            evaluateH(b2);
            double value = Math.max(b2.getH(), minimax(b2, d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
            if(value > maxValue){
                maxValue = value;
                bestMove = m.get(i);
            }
        }
        return bestMove;
    }
    public void evaluateH(Board b){
        Random rand = new Random();
        double d = rand.nextInt(100);
        b.setH(d);
    }
    private double minimax(Board b, int depth, double alpha, double beta){
        if(b.checkWinner() != '3' || depth == 0){
            return b.getH();
        }
        if(b.getPlayerTurn() == '2'){
            double value = Double.NEGATIVE_INFINITY;
            PriorityQueue<Board> Queue = new PriorityQueue<>(new Checker(1));
            ArrayList<Move> moves = getLegalMoves(b);
            for(int i = 0; i < moves.size(); i++){
               Board b2 = makeMove(moves.get(i), b);
               evaluateH(b2);
               Queue.add(b2);
            }
            while(Queue.size() != 0){
                Board b3 = Queue.poll();
                value = Math.max(value, minimax(b3, depth - 1, alpha, beta));
                alpha = Math.max(alpha, value);
                if(alpha >= beta){
                    break;
                }
            }
            return value;
        }
        else{
            double value = Double.POSITIVE_INFINITY;
            PriorityQueue<Board> Queue = new PriorityQueue<>(new Checker(-1));
            ArrayList<Move> moves = getLegalMoves(b);
            for(int i = 0; i < moves.size(); i++){
                Board b2 = makeMove(moves.get(i), b);
                evaluateH(b2);
                Queue.add(makeMove(moves.get(i), b));
            }
            while(Queue.size() != 0){
                value = Math.min(value, minimax(Queue.poll(), depth - 1, alpha, beta));
                beta = Math.min(beta, value);
                if(alpha >= beta){
                    break;
                }
            }
            return value;
        }
    }
    private Board makeMove(Move m, Board b){
        Board newBoard = new Board(b.getDim(), b.getPlayerTurn());
        for(int i = 0; i < b.getDim(); i++){
            for(int j = 0; j < b.getDim(); j++){
                newBoard.setSpace(i, j, b.getSpace(i, j));
            }
        }
        newBoard.makeMove(m.getx1(), m.gety1(), m.getx2(), m.gety2());
        return newBoard;
    }
    private ArrayList<Move> getLegalMoves(Board b){
        ArrayList<Move> moves = new ArrayList<Move>();
        for(int i = 0; i < b.getDim(); i++){
            for(int j = 0; j < b.getDim(); j++){
                if(b.getSpace(i, j).charAt(0) == b.getPlayerTurn()){
                    if(b.isValid(i, j, i + 1, j)){
                        moves.add(new Move(i, j, i + 1, j));
                    }
                    if(b.isValid(i, j, i + 1, j + 1)){
                        moves.add(new Move(i, j, i + 1, j + 1));
                    }
                    if(b.isValid(i, j, i + 1, j - 1)){
                        moves.add(new Move(i, j, i + 1, j - 1));
                    }
                    if(b.isValid(i, j, i - 1, j)){
                        moves.add(new Move(i, j, i - 1, j));
                    }
                    if(b.isValid(i, j, i - 1, j + 1)){
                        moves.add(new Move(i, j, i - 1, j + 1));
                    }
                    if(b.isValid(i, j, i - 1, j - 1)){
                        moves.add(new Move(i, j, i - 1, j - 1));
                    }
                    if(b.isValid(i, j, i, j + 1)){
                        moves.add(new Move(i, j, i, j + 1));
                    }                  
                    if(b.isValid(i, j, i, j - 1)){
                        moves.add(new Move(i, j, i, j - 1));
                    } 
                }
            }
        }
        return moves;
    }
}
