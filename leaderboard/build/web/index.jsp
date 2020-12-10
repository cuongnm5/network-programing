<%-- 
    Document   : index
    Created on : Oct 12, 2020, 11:55:22 PM
    Author     : supreme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <title>Leaderboard</title>
    </head>
    <body class="container" style="text-align: center;">
        <table id="ranking" class="table">
            <tr id="problems">
                <%=request.getAttribute("problems")%>
            </tr>
            <div>
                <%=request.getAttribute("users")%>
            </div>
        </table>
        
    </body>
    <footer>
        <button class="btn btn-primary" onclick="openPage('index.html')">
            Quay lại
        </button>
    </footer>
    <script>
    function openPage(pageUrl) {
        window.location.href = pageUrl;
    }
</script>
</html>
