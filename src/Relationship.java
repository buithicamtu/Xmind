import java.util.Random;
import java.util.UUID;

public class Relationship {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String headId;

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    private String tailId;

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Relationship(String headId, String tailId) {
        id = UUID.randomUUID().toString();
        this.headId = headId;
        this.tailId = tailId;
    }

}
