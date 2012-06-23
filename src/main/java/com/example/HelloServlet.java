package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import t4p.persistence.ConnectionProvider;


public class HelloServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
         PrintWriter out = resp.getWriter();

/**/
        try{
	        Connection connectionManager = ConnectionProvider.getConnection();
	    	
			PreparedStatement statement = connectionManager
					.prepareStatement("select '1' as prueba");
	
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				
				out.write(result.getInt("prueba"));
				
			}
        }catch(Exception ex){
        	
        }
/**/
        
        out.flush();
        out.close();
    }
    
}
