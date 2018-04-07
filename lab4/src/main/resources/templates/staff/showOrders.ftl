<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<#if flag2==true>
    <br>
    <h2 class="text-center text-info"><b>List of books</b></h2>
    <br>
    <form name="checkboxes" action="/staff/showOrders" method="post" class="form-group">
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
                <th>Reserved by</th>
                <th>Location</th>
                <th>Ready</th>
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
                    <td>${book.client.firstname} ${book.client.lastname}</td>
                    <td>
                        Section <b>${book.bookLocation.section}</b>
                        <br>
                        Rack <b>${book.bookLocation.rack}</b>
                        <br>
                        Shelf <b>${book.bookLocation.shelf}</b>
                    </td>
                    <td>
                        <div class="checkbox">
                            <label>
                                <input name="ready" path="ready" value=${book.id} type="checkbox">
                            <br>
                            </label>
                        </div>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
        <div class="form-group">
            <div class="col-md-10">
            </div>
            <div class="col-md-2">
                <input type="submit" value="Ok" class="btn btn-success btn-block" style="font-size: large">
            </div>
        </div>
    </form>
<#else>
    <div class="container">
        <h2 class="text-info"><b>There is no books to be prepared!</b></h2>
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