<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Title</title>
</head>
<body class="bg-light" style="font-size: large">
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