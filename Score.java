package org.uob.a1;

public class Score{
    private int startingScore;
    private int roomVisited; 
    private int puzzleSolved; 
    private final int PUZZLE_VALUE = 10;

    public Score(int startingScore){
        this.startingScore = startingScore; 
        this.roomVisited = 0; 
        this.puzzleSolved = 0;  
    }
    public void visitRoom(){
        roomVisited++;
    }
    public void solvePuzzle(){
        puzzleSolved++;
    }
    public double getScore(){
        return startingScore - roomVisited + (puzzleSolved*PUZZLE_VALUE);
    }

}
    
