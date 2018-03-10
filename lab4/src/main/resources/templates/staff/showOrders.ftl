<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
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
        <th>Reserved by</th>
        <th>Location</th>
        <th>Ready</th>
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
        <td>${book.client.firstname} ${book.client.lastname}</td>
        <td>Section ${book.bookLocation.section}
            <br>
            Rack ${book.bookLocation.rack}
            <br>
            Shelf ${book.bookLocation.shelf}
        </td>
        <td>
            <form name="checkboxes" action="/staff/showOrders" method="post">
                <input name="ready" path="ready" value=${book.id} type="checkbox">
                <br>
                <input name="ready" path="ready" value=25 type="checkbox">
                <br>
                <input type="submit" value="Ok">
            </form>
        </td>

    </tr>
</#list>
</table>
<a href="/staff">BACK</a>

</body>
</html>