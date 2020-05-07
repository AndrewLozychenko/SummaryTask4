package ua.nure.lozychenko.facultative.db.entity;

public class Student {
    private long id;
    private User user;
    private Course course;

    public Student(long id, User user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static Student createStudent(User user, Course course) {
        return new Student(0, user, course);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                "user=" + user +
                ", course=" + course +
                '}';
    }
}
