package aiboardgame;

public class AI4 extends AI
{
    public AI4(int d){
        super(d);
    }
    @Override
    public void evaluateH(Board b){
            double Score = b.CountAgentPieces() - b.CountPlayerPieces();
            b.setH(Score);
    }
}
