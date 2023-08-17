import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CentralTopic extends Topic {
    public CentralTopic(String title) {
        super(title);
        floatingChildren = new ArrayList<Topic>();
        listRelationship = new ArrayList<Relationship>();
    }

    private List<Topic> floatingChildren;
    private List<Relationship> listRelationship;

    public List<Relationship> getlistRelationship() {
        return listRelationship;
    }

    public List<Topic> getFloatingChildren() {
        return floatingChildren;
    }

    // Add floating topic to central topic
    // public Topic createFloatingTopic(String title) {
    // Topic floatingTopic = new Topic(title); // object for class FloatingTopic
    // floatingChildren.add(floatingTopic); // ArrayList add object
    // return floatingTopic;
    // }

    // Add children to floating topic
    public void addFloatingChildren(Topic topic) {
        floatingChildren.add(topic);
    }

    // Add relationship between 2 topic
    public void addRelationship(UUID id1, UUID id2) {
        this.listRelationship.add(new Relationship(id1, id2));
    }

    // Edit relationship
    public void editRelationship(Relationship relationshipToMove, UUID headID, UUID tailID) {
        relationshipToMove.setHeadId(headID);
        relationshipToMove.setTailId(tailID);
    }

    // Remove relationship
    public static List<Relationship> removeRelationship(Relationship element, List<Relationship> list) {
        return list.stream()
                .filter(item -> item != element)
                .collect(Collectors.toList());

    }

    public void deleteRelationship(Relationship... relationshipToMove) {
        for (var item : relationshipToMove) {
            this.listRelationship = removeRelationship(item, this.listRelationship);
        }
    }

    public void removeFloatChild(Topic Child) {
        this.floatingChildren = filterElement(Child, this.floatingChildren);
    }
    public void moveFloatingTopicToTopic(Topic floatingTopicToMove,Topic newParentTopic) {
        this.removeFloatChild(floatingTopicToMove);
        newParentTopic.addChild(floatingTopicToMove);
    }

}