<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Upload File Request Page</title>
</head>
<body>
<form method="POST" action="/file/upload" enctype="multipart/form-data">
    File to upload: <input type="file" name="myfile">
    <br>
    <input type="submit" value="Press here to upload the file!">
</form>
</body>
</html>