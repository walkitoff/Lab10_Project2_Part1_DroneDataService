package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PortalDBService extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

		// ############
		// Get 5 parameter values from the request object (these are passed in from part 1 code).
		// "area_id" : String type
		// "tilex" : int type (Reference part 1 of Project 2 for converting string to int).
		// "tiley"
		// "r"
		// "g"
		// ############
        String area_id = request.getParameter("area_id");
        String tilex = request.getParameter("tilex");
        String tiley = request.getParameter("tiley");
        String r = request.getParameter("r");
        String g = request.getParameter("g");

		// ############
		// Instantiate a DBManager instance.
		// ############
        DBManager oDBManager = new DBManager();

        // Set DB location (Currently uses current DB file name and adds direct path from C drive before it).
       // oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;
        oDBManager.DBLocation = "C:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\dronereconportal\\db\\dronedata.sqlite";

        // ############
		// Call insertAreaGridTile on db manager object and pass the 5 values from above.
        // ############
        oDBManager.insertAreaGridTile(
                            area_id,
                            Integer.parseInt(tilex),
                            Integer.parseInt(tiley),
                            Integer.parseInt(r),
                            Integer.parseInt(g)
        );

        // Response with confirmation of DB record written.
        out.println("{\"success\":true}");
    }
}

