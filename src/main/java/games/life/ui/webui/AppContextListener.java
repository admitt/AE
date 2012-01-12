package games.life.ui.webui;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String realPath = servletContext.getRealPath("/start");
        System.out.println(realPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
