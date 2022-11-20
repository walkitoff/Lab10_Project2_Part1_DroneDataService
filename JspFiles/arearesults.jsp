<%@ page import="com.dronerecon.ws.AreaGridTile"%>
<%@ page import="com.dronerecon.ws.DBManager"%>
<%@ page import="java.util.ArrayList"%>

<html>
    <head>
        <style>
            body {
                background-size: 20px 20px;
                background-image: linear-gradient(to right, grey 1px, transparent 1px),
                                linear-gradient(to bottom, grey 1px, transparent 1px);
                animation: background 40s linear infinite;
            }
        
            @keyframes background {
                0% {background-position: 0% 100%; }
                
            }
            #wrapper{
                color: rgb(255, 255, 255);
                border: 2px solid white;
                border-radius: 5px;
                margin: 0% 10% 5% 10%;
                font-size: x-large;
                padding: 5%;
                background: rgb(0,0,0,.5);
                text-shadow: -3px 3px 5px rgb(9, 9, 9);
                text-align: center;
            }
            h3, h4 {
                margin: 0%;
            }

        </style>
        <title>Area Results</title>
    </head>
    <body>
        <% 
            String sArea_id = request.getParameter("area_id");
            DBManager oDBManager = new DBManager();
            oDBManager.DBLocation = "C:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\dronereconportal\\db\\dronedata.sqlite";

            ArrayList<AreaGridTile> lstTiles = oDBManager.readAreaGridTiles(sArea_id);
            AreaGridTile agtRmax = lstTiles.get(0); 
            AreaGridTile agtGmax = lstTiles.get(0); 
            

            //find Highest red and green
            for(int i = 0; i < lstTiles.size(); i++){

                if(agtRmax.r < lstTiles.get(i).r){
                    agtRmax = lstTiles.get(i);
                }
                if(agtGmax.g < lstTiles.get(i).g){
                    agtGmax = lstTiles.get(i);
                }
            }

        %>
        <br>
        <button 
            style="
                border: 3px outset darkgrey; 
                border-radius: 5px;
                cursor: pointer;
                font-weight: bold;
                background-color: darkgrey;" 
            onclick = "window.location.href='areasearch.jsp';" 
            type="button" 
            ><-Back to Area_ID Search</button>
        
        <h1 style="text-align:center;
        background-color:rgb(19, 19, 19);
        color:white;
        margin: 0% 10% 0% 10%;
        border: 1px solid;
        border-radius: 10px;
        padding: 10px;
        box-shadow: 5px 10px gray;
        "
        >AREA ID: <%=sArea_id%></h1><br>
        <div id = "wrapper">
            <h2>GRID WITH HIGHEST <span style="color:red;">RED</span> COUNT IS:</h2>
            <h4>[ X: <%=agtRmax.x%> ], [ Y: <%=agtRmax.y%> ] with <span style="color: red;"><%=agtRmax.r%>r</h4><br><br>
            <h2>GRID WITH HIGHEST <span style="color: rgb(7, 255, 7);">GREEN</span> COUNT IS:</h2>
            <h4>[ X: <%=agtGmax.x%> ], [ Y: <%=agtGmax.y%> ] with <span style="color: rgb(7, 255, 7);"><%=agtGmax.g%>g</span></h4>
        </div>
      
    </body>
</html>