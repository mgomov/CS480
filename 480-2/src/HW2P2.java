import java.util.Random;

/**
 * Created by max on 9/21/2015.
 */
public class HW2P2 {
    public static void main(String[] args){
        WeightedQueenProblem problem = new WeightedQueenProblem();
        //generateBoard();
        problem.run();
    }

    public static void generateBoard(){
        int nums[]= {1, 2, 3, 4, 5, 6, 7, 8};
        Random rnd = new Random();
        for(int i = 0; i < 8; i++){
            for(int l = 0; l < 30; l++){
                int idx1 = rnd.nextInt(8);
                int idx2 = rnd.nextInt(8);
                int tmp = nums[idx1];
                nums[idx1] = nums[idx2];
                nums[idx2] = tmp;
            }

            for(int j = 0; j < 8; j++){
                System.out.print(nums[j]);
            }
            //System.out.println();
        }
    }
}
