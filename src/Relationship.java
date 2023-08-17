import java.util.Random;
import java.util.UUID;

public class Relationship {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private UUID headId;

    public UUID getHeadId() {
        return headId;
    }

    public void setHeadId(UUID headId) {
        this.headId = headId;
    }

    private UUID tailId;

    public UUID getTailId() {
        return tailId;
    }

    public void setTailId(UUID tailId) {
        this.tailId = tailId;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Relationship(UUID headId, UUID tailId) {
        id = UUID.randomUUID();
        this.headId = headId;
        this.tailId = tailId;
    }
    
}
