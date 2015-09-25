import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by max on 9/21/2015.
 */
public class UniformCostSearch {

    public static UniformCostSearchNode search(UniformCostSearchNode root){
        // priority queue implemented by minheap under the hood
        PriorityQueue<UniformCostSearchNode> frontier = new PriorityQueue<UniformCostSearchNode>();

        // hash set for fast verification of closed nodes
        HashSet<UniformCostSearchNode> closed = new HashSet<UniformCostSearchNode>();

        frontier.add(root);

        while(frontier.size() != 0){
            UniformCostSearchNode current = frontier.remove();
            if(current.isGoalState()){
                System.out.println("Nodes explored: " + closed.size());
                return current;
            }
            closed.add(current);

            for(UniformCostSearchNode neighbor : current.getNeighbors()){
                if(!closed.contains(neighbor)) {
                    if (!frontier.contains(neighbor)) {
                        frontier.add(neighbor);
                    } else {
                        Iterator<UniformCostSearchNode> itr = frontier.iterator();
                        UniformCostSearchNode itrNode = itr.next();

                        for(; itr.hasNext(); itrNode = itr.next()) {
                            if (itrNode.equals(neighbor)) {
                                if(itrNode.getCost() > neighbor.getCost()){
                                    itrNode.setCost(neighbor.getCost());
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Search failed, nodes explored: " + closed.size());
        // search failed
        return null;
    }

    /* unfinished */
    public static UniformCostSearchNode searchBnB(UniformCostSearchNode root){
        // priority queue implemented by minheap under the hood
        PriorityQueue<UniformCostSearchNode> frontier = new PriorityQueue<UniformCostSearchNode>();

        // hash set for fast verification of closed nodes
        HashSet<UniformCostSearchNode> closed = new HashSet<UniformCostSearchNode>();

        frontier.add(root);

        // upper bound for branch and bound
        int upperBound = Integer.MAX_VALUE;

        while(frontier.size() != 0){
            UniformCostSearchNode current = frontier.remove();
            if(current.isGoalState()){
                if(current.getCost() < upperBound){
                    upperBound = current.getCost();
                }
            }
            closed.add(current);
            for(UniformCostSearchNode neighbor : current.getNeighbors()){
                if(!closed.contains(neighbor)) {
                    if (!frontier.contains(neighbor)) {
                        frontier.add(neighbor);
                    } else {
                        Iterator<UniformCostSearchNode> itr = frontier.iterator();
                        UniformCostSearchNode itrNode = itr.next();

                        for(; itr.hasNext(); itrNode = itr.next()) {
                            if (itrNode.equals(neighbor)) {
                                if(itrNode.getCost() > neighbor.getCost()){
                                    itrNode.setCost(neighbor.getCost());
                                }
                            }
                        }
                    }
                }
            }
        }

        // search failed
        return null;
    }
}
