<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authentication</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <br>
                <p><b>Hello Guest!</b>
                    <br>
                    It is The Bodleian Library site. Joking! It is a site which simulates a library. Anyway, here you can find and order some books.
                </p>
                <br>
                <a href="/all/bookSearch">
                    <button class="btn btn-warning" style="font-size: large">
                        <i class="glyphicon glyphicon-search"> Book search</i>
                    </button>
                </a>
            </div>
            <div class="col-md-3 form-group">
                <form name="login" action="/login" method="post">
                    <br>
                    <label><b>Here enter your login</b></label>
                    <input title="Username" type="text" name="username" class="form-control" required>
                    <br>
                    <br>
                    <label><b>Here enter your password</b></label>
                    <input title="Password" type="password" name="password" class="form-control" required>
                    <br>
                    <br>
                    <input type="submit" class="btn btn-success btn-block" value="Ok" style="font-size: large">
                </form>
                <br>
                <a href="/registration" class="btn btn-info btn-block" style="font-size: large">Sign up</a>
            </div>
        </div>
    </div>
<div class="container">
    <h2 class="text-danger">${error!""}</h2>
</div>
<div class="container">
    <br>
    <br>
    <h2 class="text-center text-primary"><b>The most popular books</b></h2>
    <br>
    <div class="row">
        <#list top as book>
            <div class="col-md-3">
                <a href="/all/showBook/${book.id}">
                    <img src="${book.cover}" height="400" width="250">
                </a>
            </div>
            <div class="col-md-1">
            </div>
        </#list>
        </div>
    </div>
</div>
</body>
</html>