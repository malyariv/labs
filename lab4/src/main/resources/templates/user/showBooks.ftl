<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1> List of books</h1>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Authors</th>
        <th>Genres</th>
        <th>Publisher</th>
        <th>Year</th>
        <th>Pages</th>
        <th>Reserved</th>
        <th>Operations</th>
    </tr>
<#list books as book>
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
        <td>${book.reserved?string('yes', 'no')}</td>
        <td>
            <a href="/user/abort/${book.id}">Abort reservation</a>
            <br>
            <a href="/user/take/${book.id}">Take</a>
            <br>
            <a href="/user/takeBack/${book.id}">Take back</a>
        </td>
    </tr>
</#list>
</table>
<a href="/user">BACK</a>
</body>
</html>