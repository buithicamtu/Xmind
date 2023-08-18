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

    public Topic getFloatingTopicByID(String floatingTopicID) {
        for (var item : this.getFloatingChildren()) {
            if (item.getId() == floatingTopicID) {
                return item;
            }
            item.getTopicByID(floatingTopicID);
        }
        return null;
    }

    // Add relationship between 2 topic
    public void addRelationship(String id1, String id2) {
        this.listRelationship.add(new Relationship(id1, id2));
    }

    // Edit relationship
    public void editRelationship(Relationship relationshipToMove, Topic headTopic, Topic tailTopic) {
        relationshipToMove.setHeadId(headTopic.getId());
        relationshipToMove.setTailId(tailTopic.getId());
    }

    // Remove relationship
    // public static List<Relationship> removeRelationshipByID(Relationship element,
    // List<Relationship> list) {
    // return list.stream()
    // .filter(item -> item != element)
    // .collect(Collectors.toList());

    // }

    // public void deleteRelationship(Relationship... relationshipToMove) {
    // for (var item : relationshipToMove) {
    // this.listRelationship = removeRelationship(item, this.listRelationship);
    // }
    // }
    public void removeRelationshipByID(Relationship... relationshipToMove) {
        for (var element : relationshipToMove) {
            List<Relationship> filteredTopic = listRelationship.stream().filter(item -> item != element)
                    .collect(Collectors.toList());
            this.listRelationship = filteredTopic;
        }
    }

    // move Floating Topic --> Topic
    public void removeFloatingChildsByID(String... floatingTopicsID) {
        for (var element : floatingTopicsID) {
            List<Topic> filteredTopics = floatingChildren.stream()
                    .filter(item -> item.getId() != element)
                    .collect(Collectors.toList());
            this.floatingChildren = filteredTopics;
        }
    }

    public void moveFloatingTopicToTopic(String floatingTopicIdToMove, Topic newParentTopic) {
        Topic floatingTopicSelected = getFloatingTopicByID(floatingTopicIdToMove);
        newParentTopic.addChild(floatingTopicSelected);
        this.removeFloatingChildsByID( floatingTopicIdToMove);
    }

}