import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by max on 9/24/2015.
 */
public class WeightedQueenNode implements UniformCostSearchNode, Comparable{
    private int cost;
    private int hashCode;
    public byte[] queens;
    private BitSet openQueens;
    private ArrayList<WeightedQueenNode> neighbors;
    public WeightedQueenNode parent;
    private static int[] primes = {2129, 2531, 2617, 3989, 4139, 4657, 5003, 7919};
    public WeightedQueenNode(byte[] queens, int cost, WeightedQueenNode parent) {
        this.queens = queens;
        this.cost = cost;
        this.parent = parent;

        // queens.length should give the next row
        openQueens = WeightedQueenProblem.getCandidatesForRow(queens.length, queens);

        // does this work as a hashcode? time will tell
        hashCode = 0;
        for(int i = 0; i < queens.length; i++){
            hashCode += (int)queens[i] * primes[i];
        }
    }

    @Override
    public UniformCostSearchNode getParent() {
        return parent;
    }

    @Override
    public boolean isGoalState() {
        return queens.length == 8;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public ArrayList<? extends UniformCostSearchNode> getNeighbors() {
        if(neighbors == null){
            neighbors = new ArrayList<WeightedQueenNode>(openQueens.cardinality());
            for (int i = openQueens.nextSetBit(0); i >= 0; i = openQueens.nextSetBit(i+1)) {

                byte queen = (byte)i;

                byte[] neighborQueens = new byte[queens.length + 1];
                for(int j = 0; j < queens.length; j++){
                    neighborQueens[j] = queens[j];
                }
                neighborQueens[queens.length] = queen;
                neighbors.add(new WeightedQueenNode(neighborQueens, cost + WeightedQueenProblem.weights[queens.length][i], this));

                if (i == Integer.MAX_VALUE) {
                    break; // or (i+1) would overflow
                }
            }
        }
        return neighbors;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof WeightedQueenNode){
            if(((WeightedQueenNode)other).queens.length == this.queens.length){
                for(int i = 0; i < queens.length; i++){
                    if(queens[i] != ((WeightedQueenNode)other).queens[i]){
                        return false;
                    }
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode(){
        return hashCode;
    }

    public void printNeighbors(){
        getNeighbors();
        for(WeightedQueenNode n : neighbors){
            for(byte b : n.queens){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printQueens(){
        for(int i = 0; i < queens.length; i++){
            System.out.print(queens[i] + " " );
        }
        System.out.println();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof WeightedQueenNode){
             return this.cost - ((WeightedQueenNode)o).cost;
        }
        return -1;
    }
}
