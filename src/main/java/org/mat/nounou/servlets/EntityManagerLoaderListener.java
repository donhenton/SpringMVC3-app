package org.mat.nounou.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Initialize EntityManager Factory For Heroku, try to find the environment
 * property DATABASE_URL, and transform it into a valid jdbc URL to initialize
 * properly the DB.
 *
 * https://gist.github.com/mlecoutre/4088178
 *
 * @author mlecoutre
 */
//@WebListener
public class EntityManagerLoaderListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(EntityManagerLoaderListener.class);

    private static EntityManagerFactory emf;
     

    public EntityManagerLoaderListener() {
    }

    

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("WebListener start entity manager");

        String databaseUrl = System.getenv("DATABASE_URL");
        StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
        String dbVendor = st.nextToken(); //if DATABASE_URL is set
        String userName = st.nextToken();
        String password = st.nextToken();
        String host = st.nextToken();
        String port = st.nextToken();
        String databaseName = st.nextToken();
        String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", 
                host, port, databaseName);
        Map<String, String> properties = new HashMap<String, String>();

        logger.debug("SET JDBC URL TO " + jdbcUrl);
        properties.put("javax.persistence.jdbc.url", jdbcUrl);
        properties.put("javax.persistence.jdbc.user", userName);
        properties.put("javax.persistence.jdbc.password", password);
        properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.connection.username", userName);
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", jdbcUrl);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        
        emf = Persistence.createEntityManagerFactory("Restaurant_PU", properties);
    }

    
      
    
    
    /**
     * Close the entity manager
     *
     * @param event ServletContextEvent not used
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    /**
     * Create the EntityManager
     *
     * @return EntityManager
     */
    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }

}
