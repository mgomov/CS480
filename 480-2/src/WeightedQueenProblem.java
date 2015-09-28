import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by max on 9/24/2015.
 */
public class WeightedQueenProblem {
    public static int[][] weights = {{2885,3391,1494,2404,981,1554,2512,3399},
            {3208,3417,3243,2684,164,1352,2673,1206},
            {450,559,2806,2632,344,2711,978,2073},
            {3235,3437,3398,1389,2916,2816,2407,793},
            {2240,3390,2322,2322,2461,662,2320,2661},
            {346,1719,127,607,1123,1735,576,904},
            {987,2834,3007,2501,3365,1578,422,1792},
            {1937,503,3308,113,122,2289,1765,2476}};

    public void run() {


        String weightString = "1683742556734218618523744261857362358417815326742876135434658217";
//String weightString = "2683541734156827341111163761458273428516143276588523164787211111";
        byte qs[] = {};
        WeightedQueenNode q = new WeightedQueenNode(qs, 0, null);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                weights[i][j] = Byte.parseByte(weightString.charAt(i * 8 + j) + "");
            }
        }


        WeightedQueenNode soln = (WeightedQueenNode)UniformCostSearch.search(q);

        while(soln != null && soln.getParent() != null) {
            soln.printQueens();
            System.out.println(soln.getCost() + "\n");
            soln = (WeightedQueenNode)soln.getParent();
        }


        q = new WeightedQueenNode(qs, 0, null);
        soln = (WeightedQueenNode)UniformCostSearch.searchBnB(q);


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
