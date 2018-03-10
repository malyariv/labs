<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>
<h1> List of employees</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>LOGIN</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Operations</th>
    </tr>
<#list emps as em>
    <tr>
        <td>${em.id}</td>
        <td>${em.username}</td>
        <td>${em.employeeData.firstname}</td>
        <td>${em.employeeData.lastname}</td>
        <td>
            <a href="/admin/delete/${em.id}">Delete</a>
        </td>
    </tr>
</#list>
</table>
<a href="/admin">BACK</a>
</body>
</html>