import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

public class test {
    // Init Default
    @Test
    public void Default() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var topic2 = centralTopic.createChildren("Main Topic 2");
        var topic3 = centralTopic.createChildren("Main Topic 3");
        var topic4 = centralTopic.createChildren("Main Topic 4");
        assertNotNull(topic1);
        assertNotNull(topic2);
        assertNotNull(topic3);
        assertNotNull(topic4);
        assertEquals(4, centralTopic.getChildren().size());
    }

    // add Topic --> Central Topic
    @Test
    public void testAddTopicToCentralTopic() {
        var root = new CentralTopic("Central Topic");
        var topic = root.createChildren("Main Topic 1");

        assertNotNull(topic);
        assertEquals(1, root.getChildren().size());
    }

    // add Children --> Topic
    @Test
    public void testAddSubTopicToTopic() {
        var topic = new Topic("Main Topic 1");
        var child = topic.createChildren("Sub Topic 1:");

        assertNotNull(child);
        assertEquals(1, topic.getChildren().size());
    }

    // add Children --> Floating topic
    @Test
    public void testAddChildrentoFloatingTopic() {
        var floatingTopic = new FloatingTopic("Floating Topic");
        var subFloatingTopic = floatingTopic.createChildren("Sub Floating Topic 1:");

        assertNotNull(subFloatingTopic);
        assertEquals(1, floatingTopic.getChildren().size());
    }

    // add Floating topic --> Central topic
    @Test
    public void testAddFloatingTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");

        centralTopic.createFloatingTopic("Floating topic 1");
        centralTopic.createFloatingTopic("Floating topic 2");

        assertEquals(2, centralTopic.getFloatingChildren().size());
    }

    // Re-order children in same topic
    @Test
    public void testReoderList() {
        var topic = new Topic("Topic 1");

        topic.createChildren("Children 1");
        topic.createChildren("Children 2");
        topic.createChildren("Children 3");
        topic.createChildren("Children 4");
        assertEquals(4, topic.getChildren().size());

        topic.ReOrderTopic();
        for (var item : topic.getChildren()) {
            assertNotNull(item.getTitle());
        }
    }

    @Test
    // Move children from Topic 1 to Topic 2
    public void MoveChildren() {
        var topic1 = new Topic("Main Topic 1");
        var topic2 = new Topic("Main Topic 2");

        var child1 = topic1.createChildren("Children 1");
        var child2 = topic1.createChildren("Children 2");
        var child3 = topic2.createChildren("Children 3");
        var child4 = topic2.createChildren("Children 4");
        assertEquals(2, topic1.getChildren().size());
        assertEquals(2, topic2.getChildren().size());

        child1.moveFromTopicToTargetTopic(topic1, topic2);
        assertEquals(1, topic1.getChildren().size());
        assertEquals(3, topic2.getChildren().size());
    }

    // Move Topic to Floating Topic
    @Test
    public void moveTopicToFloating() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");
        var topic1 = new Topic("Main Topic 1");

        centralTopic.moveToFloatingTopic(topic1, centralTopic);
        assertEquals(1, centralTopic.getFloatingChildren().size());
    }

    // Move Floating Topic to Topic
    @Test
    public void moveFloatingtoTopic() {
        CentralTopic centralTopic = new CentralTopic("Central Topic");
        var floatingtopic1 = new FloatingTopic("Floating Topic 1");
        var floatingtopic2 = new FloatingTopic("Floating Topic 2");
        var Topic1 = new Topic("Main Topic 1");

        floatingtopic1.moveFromTopicToTargetTopic();
        assertEquals(1, centralTopic.getChildren().size());
    }

    @Test
    // Move topic 1 to children of topic 2
    public void moveTopic1ToChildOfTopic2() {
        var topic1 = new Topic("Main Topic 1");
        var topic2 = new Topic("Main Topic 2");

        var child1 = topic1.createChildren("Children 1");
        var child2 = topic1.createChildren("Children 2");
        var child3 = topic2.createChildren("Children 3");
        var child4 = topic2.createChildren("Children 4");

        topic2.AddtoTopic(topic1, topic2);
        assertEquals(3, topic2.getChildren().size());
        assertEquals(2, topic1.getChildren().size());
    }

    // Move children to Central Topic
    @Test
    public void MoveChildrenToCentral() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var child1 = topic1.createChildren("Sub topic 1");

        centralTopic.AddtoTopic(child1, centralTopic);
        assertEquals(2, centralTopic.getChildren().size());
    }

    // Remove 1 children from 1 topic
    @Test
    public void RemoveChildreninSameTopic() {
        var topic1 = new Topic("Main Topic 1");

        var child1 = topic1.createChildren("Children 1");
        var child2 = topic1.createChildren("Children 2");
        var child3 = topic1.createChildren("Children 3");

        topic1.deleteChild(child1);
        assertEquals(2, topic1.getChildren().size());
    }

    // Remove 1 topic from central topic
    @Test
    public void RemoveTopicFromCentral() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var topic2 = centralTopic.createChildren("Main Topic 2");
        var child1 = topic1.createChildren("Sub topic 1");

        centralTopic.deleteChild(topic1);
        assertEquals(0, centralTopic.getChildren().get(0).getChildren().size()); // test children in topic 1
        assertEquals(1, centralTopic.getChildren().size()); // test topic
    }

    // Test add relationship
    @Test
    public void addRelationshipOfTopic12() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var topic2 = centralTopic.createChildren("Main Topic 2");
        var child1 = topic1.createChildren("Sub topic 1");

        centralTopic.addRelationship(topic2.getId(), child1.getId());

        assertEquals(1, centralTopic.getlistRelationship().size());
    }

    // Test edit relationship of ID1 & ID2 --> ID1 & ID3
    @Test
    public void editRelationshipID() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var topic2 = centralTopic.createChildren("Main Topic 2");
        var child1 = topic1.createChildren("Sub topic 1");

        centralTopic.addRelationship(topic2.getId(), child1.getId());
        // Before editing
        assertEquals(topic2.getId(), centralTopic.getlistRelationship().get(0).getHeadId());

        // Edit relationship
        centralTopic.editRelationship(centralTopic.getlistRelationship().get(0), topic1.getId(), child1.getId());

        // After editing
        assertEquals(topic1.getId(), centralTopic.getlistRelationship().get(0).getHeadId());
    }

    // Remove relationship
    @Test
    public void removeRelationshipID() {
        var centralTopic = new CentralTopic("Central Topic");
        var topic1 = centralTopic.createChildren("Main Topic 1");
        var topic2 = centralTopic.createChildren("Main Topic 2");
        var child1 = topic1.createChildren("Sub topic 1");

        centralTopic.addRelationship(topic2.getId(), child1.getId());
        centralTopic.addRelationship(topic1.getId(), topic2.getId());

        assertEquals(2, centralTopic.getlistRelationship().size());
        // Remove relationship
        centralTopic.deleteRelationship(centralTopic.getlistRelationship().get(1));

        // After remove
        assertEquals(1, centralTopic.getlistRelationship().size());
    }

    // Add name to relationship
}
