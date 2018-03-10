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
        <th>ID</th>
        <th>Title</th>
        <th>Authors</th>
        <th>Genres</th>
        <th>Publisher</th>
        <th>Year</th>
        <th>Pages</th>
        <th>Reserved</th>
        <th>Available</th>
        <th>Operations</th>
    </tr>
<#list books as book>
    <tr>
        <td>${book.id}</td>
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
        <td>${book.available?string('yes', 'no')}</td>
        <td>
            <a href="/delete/${book.id}">Delete</a>
            <br>
            <a href="/staff/disable/${book.id}">Disable</a>
            <br>
            <a href="/staff/activate/${book.id}">Activate</a>
        </td>

    </tr>
</#list>
</table>
<a href="/staff">BACK</a>

</body>
</html>