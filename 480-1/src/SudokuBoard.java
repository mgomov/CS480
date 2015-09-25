import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

/**
 * Created by max on 8/31/2015.
 */
public class SudokuBoard {
    public int[][] board;

    public SudokuBoard() {
        board = new int[9][9];
    }

    public SudokuBoard(String str){
        board = new int[9][9];
        for(int i = 0; i < str.length();  i++){
            board[(int)(i / 9)][i % 9] = str.charAt(i) - 48;
        }
    }

    public SudokuBoard copy(){
        SudokuBoard cpy = new SudokuBoard();

        for(int i = 0; i < 9;  i++){
            for(int j = 0; j < 9; j++){
                cpy.board[i][j] = this.board[i][j];
            }
        }

        return cpy;
    }

    public Point firstZero(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == 0){
                    return new Point(i, j);
                }
            }
        }

        return new Point(-1, -1);
    }

    public List<Integer> candidates(Point p){
        return candidates(p.i, p.j);
    }

    public List<Integer> candidates(int i, int j){
        BitSet candidates = new BitSet();

        candidatesFromRow(candidates, i);
        candidatesFromColumn(candidates, j);
        candidatesFromSubgrid(candidates, i, j);

        List<Integer> candidatesList = new ArrayList<Integer>();
        for(int a = 0; a < 9; a++){
            if(!candidates.get(a)){
                candidatesList.add(a + 1);
            }
        }

        return candidatesList;
    }

    private void candidatesFromRow(BitSet candidates, int row){
        for(int j = 0; j < 9; j++){
            if(board[row][j] != 0) {
                candidates.set(board[row][j] - 1);
            }
        }
    }

    private void candidatesFromColumn(BitSet candidates, int col){
        for(int i = 0; i < 9; i++){
            if(board[i][col] != 0) {
                candidates.set(board[i][col] - 1);
            }
        }
    }

    private void candidatesFromSubgrid(BitSet candidates, int i, int j){
        int rows = (i / 3);
        int cols = (j / 3);

        rows *= 3;
        cols *= 3;

        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(board[rows + r][cols+ c] != 0){
                    candidates.set(board[rows + r][cols + c] - 1);
                }
            }
        }
    }

    public void solve(){
        nodeVisitCount = 0;
        solveDFS(this);
        System.out.println("Nodes visited: " + nodeVisitCount);
    }

    private static int nodeVisitCount = 0;

    private boolean solveDFS(SudokuBoard sudokuBoard){
        Point p = sudokuBoard.firstZero();
        nodeVisitCount++;
        if(p.i == -1 && p.j == -1){
            this.board = sudokuBoard.board;
            return true;
        } else {
            List<Integer> candidates = sudokuBoard.candidates(p);

            for(int i : candidates){
                SudokuBoard cpy = sudokuBoard.copy();
                cpy.board[p.i][p.j] = i;
                if(solveDFS(cpy)) {
                    return true;
                }
            }

            return false;
        }
    }

    public void printBoard() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j] == 0 ? " " : board[i][j]);
                if((j + 1) % 3 == 0 && j != 8) {
                    System.out.print("|");
                }
            }
            if((i + 1) % 3 ==0 && i != 8){
                System.out.println("\n---+---+---");
            } else {
                System.out.println();
            }
        }
    }
}
