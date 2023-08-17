import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CentralTopic extends Topic {
    public CentralTopic(String title) {
        super(title);
        floatingChildren = new ArrayList<FloatingTopic>();
        listRelationship = new ArrayList<Relationship>();
    }

    private List<FloatingTopic> floatingChildren;
    private List<Relationship> listRelationship;

    public List<Relationship> getlistRelationship() {
        return listRelationship;
    }

    public List<FloatingTopic> getFloatingChildren() {
        return floatingChildren;
    }

    // Add floating topic to central topic
    public Topic createFloatingTopic(String title) {
        FloatingTopic floatingTopic = new FloatingTopic(title); // object for class FloatingTopic
        floatingChildren.add(floatingTopic); // ArrayList add object
        return floatingTopic;
    }

    // Move Topic to Floating topic
    public void moveToFloatingTopic(Topic topicToMove, CentralTopic centralTopic) {
        centralTopic.createFloatingTopic(topicToMove.getTitle());
        this.deleteChild(topicToMove);
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

    // Remove Multiple Selected Topic

}