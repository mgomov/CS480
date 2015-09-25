import java.util.ArrayList;

/**
 * Created by max on 9/21/2015.
 */
public interface UniformCostSearchNode {
    public  UniformCostSearchNode getParent();
    public boolean isGoalState();
    public int getCost();
    public ArrayList<? extends UniformCostSearchNode> getNeighbors();

    void setCost(int cost);
}
