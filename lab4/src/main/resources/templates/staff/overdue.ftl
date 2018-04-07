<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of books</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<#if flag==true>
    <br>
    <h2 class="text-center text-info"><b>List of books</b></h2>
    <br>
    <table class="table table-bordered table-striped">
        <thead>
        <tr class="bg-info">
            <th>ID</th>
            <th>Title</th>
            <th>Authors</th>
            <th>Genres</th>
            <th>Publisher</th>
            <th>Year</th>
            <th>Pages</th>
            <th>Deadline</th>
            <th>Client name</th>
            <th>Client email</th>
        </tr>
        </thead>
        <tbody>
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
                <td>${book.deadline}</td>
                <td>${book.client.firstname} ${book.client.lastname}</td>
                <td>${book.client.email}</td>
            </tr>
            </#list>
        </tbody>
    </table>
<#else>
    <div class="container">
        <h2 class="text-info"><b>There is no books to be overdue!</b></h2>
        <br>
        <br>
    </div>
</#if>
<div class="container">
    <a href="/staff/books">
        <button class="btn btn-primary" style="font-size: large">
            <i class="glyphicon glyphicon-arrow-left"></i>
            BACK
        </button>
    </a>
</div>
</body>
</html>