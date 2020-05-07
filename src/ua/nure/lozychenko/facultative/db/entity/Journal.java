package ua.nure.lozychenko.facultative.db.entity;

public class Journal {
    private long id;
    private Student student;
    private int mark;

    public Journal(long id, Student student, int mark) {
        this.id = id;
        this.student = student;
        this.mark = mark;
    }

    public static Journal createJournal(Student student, int mark) {
        return new Journal(0, student, mark);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", student=" + student +
                ", mark=" + mark +
                '}';
    }
}
