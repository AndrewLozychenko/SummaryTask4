package ua.nure.lozychenko.facultative.db.entity;

import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;
import ua.nure.lozychenko.facultative.constants.Messages;

public class Course {
    private long id;
    private String name;
    private User teacher;
    private Topic topic;
    private String begin;
    private String end;
    private int partyLimit;

    public Course(long id, String name, User teacher, Topic topic, String begin, String end, int partyLimit) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.topic = topic;
        this.begin = begin;
        this.end = end;
        this.partyLimit = partyLimit;
    }

    public static Course createCourse(String name, Topic topic, User teacher, String begin, String end, int partyLimit) {
        return new Course(0, name, teacher, topic, begin, end, partyLimit);
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getPartyLimit() {
        return partyLimit;
    }

    public void setPartyLimit(int partyLimit) {
        this.partyLimit = partyLimit;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", topic=" + topic +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", partyLimit=" + partyLimit +
                '}';
    }

    public String validate() {
        if (name == null || "".equals(name)) {
            return "name" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        }
        if (begin == null || "".equals(begin)) {
            return "begin" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        }
        if (end == null || "".equals(end)) {
            return "end" + Messages.ERROR_FIELD_CAN_NOT_BE_EMPTY;
        }
        if (!validateDate(begin, end)) {
            return Messages.ERROR_WRONG_DATE;
        }
        return null;
    }

    private boolean validateDate(String begin, String end) {
        String[] b = begin.split("-");
        String[] e = end.split("-");

        if (Integer.parseInt(b[0]) <= Integer.parseInt(e[0])) {
            if (Integer.parseInt(b[1]) <= Integer.parseInt(e[1])) {
                if (Integer.parseInt(b[2]) < Integer.parseInt(e[2])) {
                    return true;
                }
            }
        }
        return false;
    }
}