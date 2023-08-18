import java.util.ArrayList;
import java.util.List;

class Node {
    String name;
    List<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }
}

public class MindMap {
    Node root;

    public MindMap(String rootName) {
        this.root = new Node(rootName);
    }

    public void deleteNode(String nodeName) {
        deleteNode(root, nodeName);
    }

    private boolean deleteNode(Node currentNode, String nodeName) {
        List<Node> children = currentNode.getChildren();
        for (var item : children) {
            
        } {
            if (children.get(i).name.equals(nodeName)) {
                children.remove(i);
                return true;
            } else {
                if (deleteNode(children.get(i), nodeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MindMap mindMap = new MindMap("Trending News Panel");
        // Add nodes and build the mind map structure
        // ...

        mindMap.deleteNode("Ad Topics");
        mindMap.deleteNode("Information Card");
        mindMap.deleteNode("Carousel");

        // Print the modified mind map structure
        // ...
    }
}
