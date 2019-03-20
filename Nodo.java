import java.util.ArrayLis;

public class Node{
    
    public Node parent;
    public String label = "";
    public ArrayList<Node> children;

    public Node(String label, ArrayList<Node>children){
        this.label = label;
        this.children = children;
    }
}
