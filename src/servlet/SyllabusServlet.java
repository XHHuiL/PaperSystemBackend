package servlet;

import service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 16307110325
 * Created on 2019/4/21.
 */
@WebServlet(name = "SyllabusServlet", value = "/syllabus")
public class SyllabusServlet extends HttpServlet {
    private QuestionService service = new QuestionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.print(service.getAllSyllabus());
    }
}
