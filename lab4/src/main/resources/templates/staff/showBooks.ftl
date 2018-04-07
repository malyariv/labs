<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
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
                <th>Reserved</th>
                <th>Ready</th>
                <th>Available</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <#list books as book>
                <tr>
                    <td>${book.id}</td>
                    <td>
                        <a href="/all/showBook/${book.id}"> ${book.title}</a>
                    </td>
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
                    <td>${book.ready?string('yes', 'no')}</td>
                    <td>${book.available?string('yes', 'no')}</td>
                    <td>
                        <#if !book.reserved>
                            <a href="/staff/delete/${book.id}">Delete</a>
                        </#if>
                    </td>

                </tr>
            </#list>
        </tbody>
    </table>
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