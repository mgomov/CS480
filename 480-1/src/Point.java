/**
 * Created by max on 8/31/2015.
 */
public class Point {
    public int i;
    public int j;

    public Point(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString(){
        return "(" + i + ", " + j + ")";
    }

}
