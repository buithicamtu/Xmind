import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Topic {

    private String id;

    public String getId() {
        return id;
    }

    public Topic(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.children = new ArrayList<Topic>();
    }

    private String title;

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

    // add children to Topic
    public void addChild(Topic ...topics) {
        for (var topic : topics) {
            children.add(topic);
        }
    }

    public Topic getTopicByID(String topicID) {
        for (var item : this.getChildren()) {
            if (item.getId() == topicID) {
                return item;
            }
            item.getTopicByID(topicID);
        }
        return null;
    }

    // Reorder subtopic in same topic
    public void ReOrderTopic() {
        // Swap positions of instances at index1 and index2
        Collections.swap(children, 0, 3);
    }

    // Move Children from topic 1 to Topic 2
    // public static List<Topic> filterElement(Topic element, List<Topic>
    // childrenlist) {
    // return childrenlist.stream()
    // .filter(item -> item.getId() != element)
    // .collect(Collectors.toList());

    // }

    // public void removeChild(Topic Child) {
    // this.children = filterElement(Child, this.children);
    // }

    public void  removeChildByID(String... childsID) {
        for (var element : childsID) {
            List<Topic> filteredTopic = children.stream().filter(item -> item.getId() != element)
                    .collect(Collectors.toList());

            this.children = filteredTopic;
        }
    }

    public void moveFromTopicToTargetTopic(Topic parentTopic, Topic targetTopic) {
        if (parentTopic != null && targetTopic != null) {
            targetTopic.children.add(this);
            parentTopic.removeChildByID(this.getId());
        }
    }

    // Move Topic to Floating topic
    public void moveTopicToFloatingTopic(CentralTopic centralTopic) {
        Topic newFloatingTopic = this;
        centralTopic.addFloatingChildren(newFloatingTopic);
        centralTopic.removeChildByID(this.getId());
    }

}