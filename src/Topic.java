import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Topic {

    private UUID id;

    public UUID getId() {
        return id;
    }

    private String title;

    public Topic(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.children = new ArrayList<Topic>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int width;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private List<Topic> children;

    public List<Topic> getChildren() {
        return children;
    }

    // Add children to topic with title
    public Topic createChildren(String title) {
        var topic = new Topic(title);
        children.add(topic);
        return topic;
    }

    // Reorder subtopic in same topic
    public void ReOrderTopic() {
        // Swap positions of instances at index1 and index2
        Collections.swap(children, 0, 3);
    }

    // Move Children from topic 1 to Topic 2
    public static List<Topic> filterElement(Topic element, List<Topic> childrenlist) {
        return childrenlist.stream()
                .filter(item -> item != element)
                .collect(Collectors.toList());

    }

    public void deleteChild(Topic Child) {
        this.children = filterElement(Child, this.children);
    }

    public void moveFromTopicToTargetTopic(Topic parentTopic, Topic targetTopic) {
        if (parentTopic != null && targetTopic != null) {
            targetTopic.children.add(this);
            parentTopic.deleteChild(this);
        }
    }

}