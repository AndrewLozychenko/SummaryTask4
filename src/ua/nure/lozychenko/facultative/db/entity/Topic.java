package ua.nure.lozychenko.facultative.db.entity;

public class Topic {
    private long id;
    private String name;

    public Topic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Topic createTopic(String name) {
        return new Topic(0, name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String validate() {
        if (name == null || "".equals(name)) {
            return "name";
        }
        return null;
    }
}
