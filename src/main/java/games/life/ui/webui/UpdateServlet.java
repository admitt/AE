package games.life.ui.webui;

import games.life.Life;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Life life = (Life) session.getAttribute("life");
        if (life == null) {
            return;
        }
        life = life.next();
        session.setAttribute("life", life);
        resp.setContentType("text/html");
        resp.getWriter().println(life.toString().replaceAll("\n", " "));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
