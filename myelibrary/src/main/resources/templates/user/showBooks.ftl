<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My books</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<#if size2!=0>
    <br>
    <h2 class="text-center text-info"><b>List of my books</b></h2>
    <br>
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="bg-info">
                <th>Title</th>
                <th>Authors</th>
                <th>Genres</th>
                <th>Publisher</th>
                <th>Year</th>
                <th>Pages</th>
                <th>Reserved</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
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
                        <#if book.reserved ==true&& book.available ==true>
                            <a href="/user/cancel/${book.id}">Cancel reservation</a>
                            <br>
                        </#if>
                        <#if book.ready ==true && book.available ==true>
                            You can take it
                            <br>
                        </#if>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
<#else>
<div class="container">
    <h2 class="text-info"><b>Now you don't have any books.</b></h2>
    <br>
    <br>
</div>
</#if>
    <div class="container">
        <a href="/user">
            <button class="btn btn-primary" style="font-size: large">
                <i class="glyphicon glyphicon-arrow-left"></i>
                BACK
            </button>
        </a>
    </div>
</body>
</html>