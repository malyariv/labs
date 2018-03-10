<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of books</title>
</head>
<body>
<h1>We have the following books</h1>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Authors</th>
        <th>Genres</th>
        <th>Publisher</th>
        <th>Year</th>
        <th>Pages</th>
        <#if role=='ROLE_USER'>
        <th>Operations</th>
        </#if>
    </tr>
<#list bookList as book>
    <tr>
        <td>${book.title}</td>
        <td>
            <#list  book.authors as a>
            ${a.fullname}
                <br>
            </#list>
        </td>
        <td>
            <#list  book.genres as g>
            ${g.name}
                <br>
            </#list>
        </td>
        <td>${book.publisher}</td>
        <td>${book.year}</td>
        <td>${book.pages}</td>
        <#if role='ROLE_USER'>
        <td>
            <a href="/user/reserve/${book.id}">Reservation</a>
        </td>
        </#if>
    </tr>
</#list>
</table>
<a href="/">BACK</a>
</body>
</html>