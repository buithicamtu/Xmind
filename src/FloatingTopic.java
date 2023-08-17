import java.util.ArrayList;
import java.util.List;

public class FloatingTopic extends Topic {
    public FloatingTopic(String title) {
        super(title);
    }

    private List<FloatingTopic> floatingChildren;

    public List<FloatingTopic> getFloatingChildren() {
        return floatingChildren;
    }

    // Add children to floating topic
    public FloatingTopic createFloatingChildren(String Title) {
        var floatingChild = new FloatingTopic(Title);
        floatingChildren.add(floatingChild);
        return floatingChild;
    }
}