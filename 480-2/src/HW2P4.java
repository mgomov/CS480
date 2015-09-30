/**
 * Created by max on 9/29/15.
 */
public class HW2P4 {
    public static void main(String[] args){
        int h = 25;
        for(double d = 0.05; d <= 0.5; d += 0.05){
            System.out.println(d + "," + BFSExploredNodes(h, d) + "," + DFSExploredNodes(h, d));
        }
    }

    public static int BFSExploredNodes(int h, double p){
        return (int) (Math.pow(2.0f, h - 1.0f) + (int)(1.0/p));
    }

    public static double DFSExploredNodes(int h, double p) {
        if(h == 0){
            return 1.0d;
        }
        double q = 1.0d - Math.pow(1.0d - p, Math.pow(2.0d, (double)(h - 1)));

        return (1d + (1d - q) * DFSExploredNodes(h - 1, p) + q * ((Math.pow(2d, h) - 1d) + DFSExploredNodes(h - 1, p)));
    }
}
