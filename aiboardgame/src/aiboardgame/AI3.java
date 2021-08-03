package aiboardgame;

public class AI3 extends AI
{
    public AI3(int d){
        super(d);
    }
    @Override
    public void evaluateH(Board b){
        if(b.CountAgentPieces() == 0 && b.CountPlayerPieces() == 0){
            b.setH(50);
        }
        else if(b.CountAgentPieces() == 0){
            b.setH(0);
        }
        else if(b.CountPlayerPieces() == 0){
            b.setH(100);
        }
        else{
            double Score = 100 * (double)b.CountAgentPieces() / ((double)b.CountAgentPieces() + (double)b.CountPlayerPieces());
            b.setH(Score);
        }
        
    }
}
