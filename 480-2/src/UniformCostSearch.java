import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by max on 9/21/2015.
 */
public class UniformCostSearch {

    public static UniformCostSearchNode search(UniformCostSearchNode root){
        // priority queue implemented by minheap under the hood
        PriorityQueue<UniformCostSearchNode> frontier = new PriorityQueue<UniformCostSearchNode>();

        // hash set for fast verification of closed nodes
        HashSet<UniformCostSearchNode> closed = new HashSet<UniformCostSearchNode>();

        closed.add(root);
        frontier.add(root);

        while(frontier.size() != 0){
            UniformCostSearchNode current = frontier.remove();
            if(current.isGoalState()){
                System.out.println("UCS Nodes explored: " + closed.size());
                return current;
            }

            for(UniformCostSearchNode neighbor : current.getNeighbors()){
                if(!closed.contains(neighbor)) {
                    if (!frontier.contains(neighbor)) {
                        frontier.add(neighbor);
                    }
                }
            }
        }

        System.out.println("UCS Search failed, nodes explored: " + closed.size());
        // search failed
        return null;
    }

    public static UniformCostSearchNode searchBnB(UniformCostSearchNode root){
        // priority queue implemented by minheap under the hood
        PriorityQueue<UniformCostSearchNode> frontier = new PriorityQueue<UniformCostSearchNode>();

        // hash set for fast verification of closed nodes
        HashSet<UniformCostSearchNode> closed = new HashSet<UniformCostSearchNode>();

        int upperBoundCost = Integer.MAX_VALUE;

        frontier.add(root);

        while(frontier.size() != 0){
            UniformCostSearchNode current = frontier.remove();
            if(current.isGoalState()){
                System.out.println("UCS B&B Nodes explored: " + closed.size());
                return current;
            }

            // implicitly pruning the frontier
            if(current.getCost() >= upperBoundCost){
                // skipping due to bounding
                continue;
            }

            closed.add(current);

            PriorityQueue<UniformCostSearchNode> toAdd = new PriorityQueue<UniformCostSearchNode>();
            for(UniformCostSearchNode neighbor : current.getNeighbors()){
                toAdd.add(neighbor);
            }

            while(toAdd.size() != 0) {
                UniformCostSearchNode neighbor = toAdd.remove();
                if (neighbor.getCost() < upperBoundCost) {
                    if (neighbor.isGoalState()) {
                        // bounding
                        upperBoundCost = neighbor.getCost();
                    }
                    frontier.add(neighbor);
                }
            }
        }

        System.out.println("UCS B&B Search failed, nodes explored: " + closed.size());
        // search failed
        return null;
    }
}
