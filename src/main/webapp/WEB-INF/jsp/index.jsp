<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chuck Norris quotes</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            width: 150px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Random Chuck Noris Jokes</h2>
<br>
<textarea rows="10" cols="10">${chuckjoke}</textarea>
<br><br>

<form method="post" action="/save">
    <input type ="hidden" name="id" value="">
    <input type ="text" name="joke" value="${chuckjoke}">
    <input type="submit" value="Save">
</form>

<br><br>
<h2>Previous Chuck Norris jokes/facts</h2>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>Result</th>
        <th>Action</th>
    </tr>
    <c:forEach var = "lists" items = "${listitems}">
        <tr>
            <td>${lists.getId()}</td>
            <td>${lists.getResult()}</td>
            <td>
                <a href="/delete/${lists.getId()}"><img src="../../img/delete.jpg" alt="delete_image" ></a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
