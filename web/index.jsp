<%@ page import="java.io.File" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="com.team.web.filters.RequestLoggingFilter" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
<h1>Welcome to ATM logs!</h1>
        <%
            try {
                File myObj = new File(RequestLoggingFilter.currentFilePath);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.isEmpty()) {
                        out.println("<hr>");
                    }
                    out.println(data + "<br>");
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                out.println("An error occurred.");
                e.printStackTrace();
            }
        %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>