package aiboardgame;

import java.util.*;

class Checker implements Comparator<Board>
  {
    private int m;
    public Checker(int m){
        this.m = m;
    }
    @Override
    public int compare(Board b1, Board b2){
        if(b1.getH() <= b2.getH()){
            return m * 1;
        }
        else
            return m * -1;
        }
    }
