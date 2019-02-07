<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List of books</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<#if flag>
    <br>
    <h2 class="text-center text-info"><b>We have the following books for you</b></h2>
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
                <#if role=='ROLE_STAFF'>
                    <th>Reserved</th>
                    <th>Ready</th>
                    <th>Available</th>
                    <th class="text-white">Operations</th>
                </#if>
                <#if role=='ROLE_USER'>
                <th class="text-white">Operations</th>
                </#if>

            </tr>
        </thead>
        <tbody>
            <#list bookList as book>
                <tr>
                    <td>
                        <a href="/all/showBook/${book.id}">${book.title}</a>
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
                    <#if role='ROLE_STAFF'>
                        <td>${book.reserved?string('yes', 'no')}</td>
                        <td>${book.ready?string('yes', 'no')}</td>
                        <td>${book.available?string('yes', 'no')}</td>
                        <td>
                            <#if !book.reserved>
                                <a href="/staff/delete/${book.id}">Delete</a>
                            </#if>
                        </td>
                    </#if>
                    <#if role='ROLE_USER'>
                        <td>
                            <#if !book.reserved>
                                <a href="/user/reserve/${book.id}">Reservation</a>
                            </#if>
                        </td>
                    </#if>


                </tr>
            </#list>
        </tbody>
    </table>

<#else>
    <div class="container">
        <h2 class="text-info"><b>Sorry! We have not found any matches. Try again!</b></h2>
        <br>
        <br>
    </div>
</#if>
    <div class="container">
        <a href="/">
            <button class="btn btn-primary" style="font-size: large">
                <i class="glyphicon glyphicon-arrow-left"></i>
                BACK
            </button>
        </a>
    </div>
</body>
</html>