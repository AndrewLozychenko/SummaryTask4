package ua.nure.lozychenko.facultative.servlet.course;

import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.constants.Requests;
import ua.nure.lozychenko.facultative.db.dao.CourseDao;
import ua.nure.lozychenko.facultative.db.dao.JournalDao;
import ua.nure.lozychenko.facultative.db.dao.StudentDao;
import ua.nure.lozychenko.facultative.db.entity.Journal;
import ua.nure.lozychenko.facultative.db.entity.Student;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.CourseService;
import ua.nure.lozychenko.facultative.db.service.mysql.JournalService;
import ua.nure.lozychenko.facultative.db.service.mysql.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course.join")
public class Join extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Parameters.USER);
        long courseId = Long.parseLong(req.getParameter(Parameters.COURSE));

        CourseDao courseDao = new CourseService();
        StudentDao studentDao = new StudentService();
        JournalDao journalDao = new JournalService();
        try {
            Student student = Student.createStudent(user, courseDao.get(courseId));
            studentDao.save(student);
            journalDao.save(Journal.createJournal(studentDao.get(student.getUser().getId(), student.getCourse().getId()), 0));

            resp.sendRedirect(Requests.COURSE_LIST +
                    "filter=" + req.getSession().getAttribute(Parameters.CURRENT_FILTER) +
                    "&teacher=" + req.getSession().getAttribute(Parameters.CURRENT_TEACHER) +
                    "&topic=" + req.getSession().getAttribute(Parameters.CURRENT_TOPIC));
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
