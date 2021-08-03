package aiboardgame;

public class AI2 extends AI
{
    public AI2(int d){
        super(d);
    }
        
    @Override
    public void evaluateH(Board b){
        if(b.CountAgentPieces() == 0 && b.CountPlayerPieces() == 0){
            b.setH(50);
        }
        else if(b.CountAgentPieces() == 0){
            b.setH(-1000);
        }
        else if(b.CountPlayerPieces() == 0){
            b.setH(100000);
        }
        else{
            double Score1 = 90 * (double)b.CountAgentPieces() / ((double)b.CountAgentPieces() + (double)b.CountPlayerPieces());
            double Score2 = 0;
            double Score3 = 0;
            for(int i = 0; i < b.getDim(); i++){
                for(int j = 0; j < b.getDim(); j++){
                    String s = b.getSpace(i, j);
                    if(s.charAt(0) == b.getPlayerTurn()){
                        Score2 += getBoardScore(b, i, j);             
                    }
                    else if((s.charAt(0) != 'E') && (s.charAt(0) != 'P')){
                        Score3 += getBoardScore(b, i, j);
                    }
                }
            }
            double Score4 = 10 * Score3 /(Score2 + Score3);
            b.setH(Score1 + Score4);
          
        }
    }
    private static double getBoardScore(Board b, int i, int j){
        double spaceScore = 0;
        if((i + 1) < b.getDim()){
              spaceScore += compareSpaces(b, i, j, i + 1, j);
        }
        if(((i + 1) < b.getDim()) && ((j + 1) < b.getDim())){
              spaceScore += compareSpaces(b, i, j, i + 1, j + 1);
        }
        if(((i + 1) < b.getDim()) && ((j - 1) >= 0)){
              spaceScore += compareSpaces(b, i, j, i + 1, j - 1);
        }
        if((i - 1) >= 0){
              spaceScore += compareSpaces(b, i, j, i - 1, j);
        }
        if(((i - 1) >= 0) && ((j + 1)< b.getDim())){
              spaceScore += compareSpaces(b, i, j, i - 1, j + 1);
        }
        if(((i - 1) >= 0) && ((j - 1) >= 0)){
              spaceScore += compareSpaces(b, i, j, i - 1, j - 1);
        }
        if((j + 1) < b.getDim()){
              spaceScore += compareSpaces(b, i, j, i, j + 1);
        }                  
        if((j - 1) >= 0){
               spaceScore += compareSpaces(b, i, j, i, j - 1);
        } 
        return spaceScore;
        
    }
    private static int compareSpaces(Board b, int x1, int y1, int x2, int y2){
        if(b.getSpace(x2, y2).equals("Empty")){
            return 1;
        }
        else if(b.getSpace(x2, y2).equals("Pit")){
            return 0;
        }
        else if(b.getSpace(x2, y2).charAt(0) == b.getSpace(x1, y1).charAt(0)){ 
            if(b.getSpace(x2, y2).substring(1).equals(b.getSpace(x1, y1).substring(1))){
                return 2;
            }
            else{
                return 4;
            }
        }
        else if(b.getSpace(x2, y2).substring(1).equals("Wumpus")){
            if(b.getSpace(x1, y1).substring(1).equals("Hero")){
                return 6;
            }
            else if(b.getSpace(x1, y1).substring(1).equals("Wumpus")){
                return 1;
            }
            else{
                return -10;
            }
        }
        else if(b.getSpace(x2, y2).substring(1).equals("Hero")){
            if(b.getSpace(x1, y1).substring(1).equals("Mage")){
                return 6;
            }
            else if(b.getSpace(x1, y1).substring(1).equals("Hero")){
                return 1;
            }
            else{
                return -10;
            }
        }
        else if(b.getSpace(x2, y2).substring(1).equals("Mage")){
            if(b.getSpace(x1, y1).substring(1).equals("Wumpus")){
                return 6;
            }
            else if(b.getSpace(x1, y1).substring(1).equals("Mage")){
                return 1;
            }
            else{
                return -10;
            }
        }
        return 0;
    }

    
}
