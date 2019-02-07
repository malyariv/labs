<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>${book.title}</b></h2>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <img src="${book.cover}" height="400" width="250">
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-6">
            <table class="table">
                <tbody>
                    <tr>
                        <td><b>Author</b></td>
                        <td>
                            <#list  book.authors as a>
                            ${a.fullname}
                                <br>
                            </#list>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Genres</b></td>
                        <td>
                        <#list  book.genres as g>
                        ${g.name}
                            <br>
                        </#list>
                        </td>
                    </tr>
                    <tr>
                        <td><b>Publisher</b></td>
                        <td>${book.publisher}</td>
                    </tr>
                    <tr>
                        <td><b>Year</b></td>
                        <td>${book.year}</td>
                    </tr>
                    <tr>
                        <td><b>Pages</b></td>
                        <td>${book.pages}</td>
                    </tr>
                </tbody>

            </table>
        </div>
    </div>
    <br>
    <br>
    <br>
    <a href="/">
        <button class="btn btn-primary" style="font-size: large">
            <i class="glyphicon glyphicon-arrow-left"></i>
            BACK
        </button>
    </a>
</div>

</body>
</html>