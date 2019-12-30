package app.web.servlets;

import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/all")
public class AllServlet extends HttpServlet {

    private static final String FILE_PATH = "C:\\Users\\Dimitar\\IdeaProjects\\JavaWeb\\JavaEEIntro\\src\\main\\java\\webapp\\views\\all.html";

    private final FileUtil fileUtil;

    @Inject
    public AllServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }
}
