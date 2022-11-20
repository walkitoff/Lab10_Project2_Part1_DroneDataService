<%
//a.	areasearch.jsp:  It has a text box input for area ID and a submit button that submits to a second JSP.
//b.	arearesults.jsp:
%>
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
    </style>
    <title>Area_ID Search</title>
  </head>
  <body>

      <br />
      <h2 style="text-align:center;
                background-color:rgb(19, 19, 19);
                color:white;
                margin: 0% 10% 0% 10%;
                border: 1px solid;
                border-radius: 10px;
                padding: 10px;
                box-shadow: 5px 10px gray;
                "
                >AREA_ID SEARCH</h2>

      <!-- ###################################################
      Create a form here where the action is set to "drone_sim.jsp".
      Set a text input with name as "area_id".
      Set a text input with name as "colcount".
      Set a text input with name as "rowcount".
      Create a button with text similar to this: "Create Area Grid".
      ################################################### -->
    <form 
      style = "
        border: 6px outset;
        border-radius: 10px; 
        background: lightgray; 
        margin: 20px 30% 0px 30%;
        padding: 20px;
        
        " 
      action="arearesults.jsp"
    >
        <table style = "font-size: x-large;">
        <hr>

        
        <tr>
          <td>
            Area ID: 
          </td>
          <td>
            <input style = "border: 2px inset;" name="area_id" type="text">
          </td>
        </tr>

        <tr>
        </tr>

        <tr>
          <td></td>
            <td style = "float: right; margin-right: 10px;">
              <input style = "border: 2px outset; " type="submit" value="Get Results">
              
            </td>
        </tr>

        <hr>
      </table>
      <hr>
    </form>
      
  </body>
</html>