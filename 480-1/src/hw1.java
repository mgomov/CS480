/**
 * Created by max on 8/31/2015.
 */
public class hw1 {

    public hw1() {

    }

    public void run() {
        //SudokuBoard sudokuBoard = new SudokuBoard("684973215000510000052000090100600430090000080076008009040000120000091000001280003");
        SudokuBoard sudokuBoard = new SudokuBoard("850002400720000009004000000000107002305000900040000000000080070017000000000036040");
        sudokuBoard.printBoard();

        System.out.println("\n\nSolution: ");
        sudokuBoard.solve();
        sudokuBoard.printBoard();
    }

    public static void main(String[] args) {
        hw1 hw1 = new hw1();
        hw1.run();
    }

}
