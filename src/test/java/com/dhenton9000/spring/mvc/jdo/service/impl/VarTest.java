/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.spring.mvc.jdo.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dhenton
 */
public class VarTest {
    
    public static void main(String[] args)
    {

        ClassPathXmlApplicationContext xmlContext = 
                new ClassPathXmlApplicationContext("classpath*:prop-test.xml");
        
         ComboPooledDataSource dataSource = (ComboPooledDataSource)
                 xmlContext.getBean("dataSource");
         
        System.out.println("'"+ dataSource.getUser()+"' '"+dataSource.getPassword()+"'");
         
        try {
            dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(VarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         URI u = xmlContext.getBean("dbUrl",URI.class);
         
        
        String t = (String) xmlContext.getBean("connString" );
        System.out.println("connString "+t);
        System.out.println("uri "+u.toString());
        
//        try {
//             Connection conn = dataSource.getConnection();
//        } catch (SQLException ex) {
//            Logger.getLogger(VarTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
       //  System.out.println("data base env'"+System.getenv("DATABASE_URL")+"'");
    }
}
