<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<br>
<a href="/logout">Logout</a>
<br>
<#if role=='ROLE_ADMIN'>
Hi, Admin!
<a href="/admin">admin</a>
<#elseif role=='ROLE_STAFF'>
<a href="/staff">staff</a>
<#else>
<a href="/user">user</a>
</#if>


</body>
</html>