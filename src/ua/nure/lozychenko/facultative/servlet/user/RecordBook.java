package ua.nure.lozychenko.facultative.servlet.user;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.lozychenko.facultative.DBException;
import ua.nure.lozychenko.facultative.constants.Parameters;
import ua.nure.lozychenko.facultative.db.dao.JournalDao;
import ua.nure.lozychenko.facultative.db.entity.Journal;
import ua.nure.lozychenko.facultative.db.entity.User;
import ua.nure.lozychenko.facultative.db.service.mysql.JournalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/user.recordBook")
public class RecordBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(Parameters.USER);
        JournalDao journalDao = new JournalService();
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:/" + user.getLogin() + ".pdf"));

            document.open();

            PdfPTable table = new PdfPTable(3);

            Stream.of("Course", "Teacher", "Mark")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });

            List<Journal> journal = journalDao.getByStudent(user.getId());
            journal.forEach(j -> {
                table.addCell(j.getStudent().getCourse().getName());
                table.addCell(j.getStudent().getCourse().getTeacher().getSurname() + " " +
                        j.getStudent().getCourse().getTeacher().getName());
                table.addCell(Integer.toString(j.getMark()));
            });

            document.add(table);
            document.close();

            resp.sendRedirect("download.jsp");
        } catch (DocumentException | DBException e) {
            e.printStackTrace();
        }
    }
}
