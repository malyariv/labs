<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
</head>
<body>
<h1> List of clients</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>LOGIN</th>
        <th>Enabled</th>
        <th>Books</th>
        <th>Operations</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.enabled?string('yes', 'no')}</td>
            <td>
                ${user.clientData.firstname}
            </td>
            <td>
                <a href="/delete/${user.id}">Delete</a>
                <br>
                <a href="/staff/disable/${user.id}">Disable</a>
                <br>
                <a href="/staff/activate/${user.id}">Activate</a>
            </td>

        </tr>
    </#list>
</table>
<a href="/staff">BACK</a>

</body>
</html>