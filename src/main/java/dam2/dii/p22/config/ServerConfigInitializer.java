package dam2.dii.p22.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ServerConfigInitializer
 *
 */
public class ServerConfigInitializer implements ServletContextListener {

  /**
   * Default constructor.
   */
  public ServerConfigInitializer() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see ServletContextListener#contextDestroyed(ServletContextEvent)
   */
  public void contextDestroyed(ServletContextEvent event) {
    // TODO Auto-generated method stub
  }

  /**
   * @see ServletContextListener#contextInitialized(ServletContextEvent)
   */
  public void contextInitialized(ServletContextEvent event) {
    String realPath = event.getServletContext().getRealPath("");
    Config.getConfig().setGlobalPath(realPath);
  }

}
