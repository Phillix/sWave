<%@page import="Dtos.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%User currentUser = (User)session.getAttribute("user");
          if (currentUser == null) {
              response.sendRedirect("login.jsp?refer=upload.jsp");
          }
          else {
              if (!currentUser.isIsAdmin()) {
                  response.sendRedirect("noperm.jsp");
              }
          }
            String skin = "swave";
            if (currentUser != null) {
                skin = currentUser.getSkin();
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="macgril/css/skins/<%=skin%>/<%=skin%>.css"/>
        <title>Upload</title>
        <script src="macgril/js/dom.js"></script>
        <script src="js/ajax_uploader.js"></script>
        <script>
            function showSizes() {
                var count = 0;
                var theFiles = $("fileSelector").files;
                for (var i = 0; i < theFiles.length; i++)
                    count += theFiles[i].size;
                $("fileSizes").innerHTML = "Size of Upload: " + Math.round(count/1024/1024) + "MB (MAX 100MB)";
            }
        </script>
    </head>
    <body style="color:#e3e3e3; text-shadow: 0px 0px 10px #000;">
        <h1>Admin Panel</h1>
        <h3>System</h3>
        <h5>CPUs: <%=sWave.Server.CPUs%></h5>
        <h5>JVM Heap: <%=sWave.Server.JVMHEAP%></h5>
        <h3>Upload Tracks</h3>
        <h5><u>Note: Only MP3 format files under 16MB can be uploaded.</u></h5>
        <h5><u>Note: You may upload up to 100MB at a time.</u></h5>
        <input id="fileSelector" type="file" name="songBlob" accept="audio/mpeg" onchange="showSizes()" multiple/><br/>
        <span id="fileSizes"></span>
        <button onclick="uploadSongs()">Upload</button>
        <progress id="uploadProgress" max="100" value="0"></progress>
        <span id="progressInfo"></span>
    </body>
</html>
