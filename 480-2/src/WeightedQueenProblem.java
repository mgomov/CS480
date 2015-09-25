import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by max on 9/24/2015.
 */
public class WeightedQueenProblem {
    public static byte[][] weights;

    public void run(){
        weights = new byte[8][8];

        String weightString = "1683742556734218618523744261857362358417815326742876135434658217";
//String weightString = "2683541734156827341111163761458273428516143276588523164787211111";
        byte qs[] = {};
        WeightedQueenNode q = new WeightedQueenNode(qs, 0, null);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                weights[i][j] = Byte.parseByte(weightString.charAt(i * 8 + j) + "");
            }
        }

        WeightedQueenNode soln = (WeightedQueenNode)UniformCostSearch.search(q);
        while(soln != null && soln.getParent() != null) {
            soln.printQueens();
            System.out.println(soln.getCost() + "\n");
            soln = (WeightedQueenNode)soln.getParent();
        }
    }

    public static BitSet getCandidatesForRow(int row, byte[] queens) {
        BitSet b = new BitSet(8);

        // same row or before, there should be queens there already
        if(row < queens.length){
            return b;
        }

        // for each placed queen, process the bitset
        for (int i = 0; i < queens.length; i++) {
            if(queens[i] == -1){
                continue;
            }

            int dist = row - i;

            // diagonals
            int posCol = queens[i] + dist;
            int negCol = queens[i] - dist;

            // 'straight down'
            b.set(queens[i]);

            // if valid, update the bitset
            if (negCol >= 0) {
                b.set(negCol);
            }
            if (posCol < 8) {
                b.set(posCol);
            }
        }

        // flip the bits, so 0 == invalid and 1 == valid
        b.flip(0, 8);
        return b;
    }
}
