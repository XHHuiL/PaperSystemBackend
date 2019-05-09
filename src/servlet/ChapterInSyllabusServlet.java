package servlet;

import service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@WebServlet(name = "ChapterInSyllabusServlet", urlPatterns = "/chapter-in-syllabus/*")
public class ChapterInSyllabusServlet extends HttpServlet {
    private QuestionService service = new QuestionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String info = request.getPathInfo();
        if (info == null || info.isEmpty()) {
            out.println("error: wrong path");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(info.substring(1));
        } catch (NumberFormatException e) {
            out.println("error: wrong url");
            return;
        }
        out.print(service.getAllChapterInSyllabus(id));
    }
}
