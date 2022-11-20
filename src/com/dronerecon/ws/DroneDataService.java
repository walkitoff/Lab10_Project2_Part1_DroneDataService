package com.dronerecon.ws;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author Tyler D.
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // ##############################
        String area_id = request.getParameter("area_id");
        String tilex = request.getParameter("tilex");
        String tiley = request.getParameter("tiley");
        String totalcols = request.getParameter("totalcols");
        String totalrows = request.getParameter("totalrows");

        //********  PART 2   d. ********
        //** request.getParameter r and g **

        String r = request.getParameter("r");
        String g = request.getParameter("g");

        //** create URL obj and append "&"+ each parameter **
        //EXAMPLE http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=1&tilex=1&tiley=1&r=1&g=1


        //TODO: ASK TEACHER HOW THIS WORKS,
        // theres no use of the buffered reader
        try {
            URL url = new URL(
                        String.format("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=%s&tilex=%s&tiley=%s&r=%s&g=%s",
                                area_id, tilex, tiley, r, g)
            );

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        }catch (Exception ex){
            ex.printStackTrace();
        }

        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################
        String sDirection = ""; //empty on purpose


        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################
        int iTilex = Integer.parseInt(tilex);
        int iTiley = Integer.parseInt(tiley);
        int iTotalcols = Integer.parseInt(totalcols); //length of each row
        int iTotalrows = Integer.parseInt(totalrows); //length of each column

        //determine which row the drone is on, by use of y value.
        //if y is even it's going right
        //if y is odd it's going left
        //determine if drone is at an end point
        //if going right & iTilex == iTotalcols
        //if going left & itilex == 0
        if(iTotalrows % 2 != 0 && iTilex == (iTotalcols - 1) && iTiley == (iTotalrows - 1) ){ //drone end point is at (iTotalcols-1 , iTotalrows - 1) when # of rows is odd
                sDirection = "stop";

        }
        else if (iTotalrows % 2 == 0 && iTilex == 0 && iTiley == (iTotalrows - 1)){ //drone end point is at 0, (iTotalrows - 1)  when # of rows is even
                 sDirection = "stop";

        }
        else if(iTiley % 2 == 0){
            //drone is going right
            if(iTilex == (iTotalcols-1)){ //drone is at end point
                iTiley++;
                sDirection = "left";

            }else{//drone not at end point
                iTilex++;
                sDirection = "right";
            }
        }
        else {
            //drone is going left
            if(iTilex == 0){ //drone is at end point
                iTiley++;
                sDirection = "right";

            }else{//drone not at end point
                iTilex--;
                sDirection = "left";
            }
        }

        //alternate logic for stop
        /*
            if (iTiley == iTotalrows){
            //then drone has pasted final row
              sDirection = "stop";
              }
        */

        // ##############################
        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################
//        String sResponseJSON = "{" +
//                                    "\"area_id\":\"" + area_id + "\"," +
//                                    "\"nextTileX\":\"" + iTilex +"\"," +
//                                    "\"nextTileY\":\"" + iTiley +"\"," +
//                                    "\"direction\":\"" + sDirection +"\"" +
//                              "}";


        //alternate format
        String sfResponseJSON = String.format("{\"area_id\":\"%s\", \"nextTileX\":\"%s\", \"nextTileY\":\"%s\", \"direction\":\"%s\"}",
                                                area_id, iTilex, iTiley, sDirection);


        //out.println(sResponseJSON);
        out.println(sfResponseJSON);


    }
}

