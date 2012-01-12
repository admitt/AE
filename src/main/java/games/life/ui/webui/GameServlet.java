package games.life.ui.webui;

import games.life.Life;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println(getServletContext().getRealPath("/start"));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println(getServletContext().getRealPath("/start"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String example = req.getParameter("example");
        Life life = new Life(readLife(example == null ? "/life4" : example));
        req.getSession().setAttribute("life", life);
        req.setAttribute("state", life.toString().replaceAll("\n", " "));
        req.getRequestDispatcher("draw.jsp").forward(req, resp);
    }

    private String[] readLife(final String fileName) throws IOException {
        return IOUtils.readLines(getServletContext().getResourceAsStream(fileName)).toArray(new String[0]);
    }
}
