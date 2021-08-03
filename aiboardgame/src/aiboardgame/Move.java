package aiboardgame;

public class Move
{
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double h;
    
    public Move(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
    
    public int getx1(){
        return x1;
    }
    public int gety1(){
        return y1;
    }
    public int getx2(){
        return x2;
    }    
    public int gety2(){
        return y2;
    }

}

