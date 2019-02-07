<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>Hello ${client.firstname} ${client.lastname}!</b></h2>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <a href="/user/editProfile">
                <button class="btn btn-success btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-user"> My profile</i>
                </button>
            </a>
        </div>

        <div class="col-md-1">
        </div>

        <div class="col-md-2">
            <a href="/user/showBooks">
                <button class="btn btn-info btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-book"> My books</i>
                </button>
            </a>
        </div>

        <div class="col-md-1">
        </div>

        <div class="col-md-2">
            <a href="/all/bookSearch">
                <button class="btn btn-warning btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-search"> Find books</i>
                </button>
            </a>
        </div>

        <div class="col-md-1">
        </div>

        <div class="col-md-2">
            <a href="/logout">
                <button class="btn btn-danger btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-log-out"> Logout</i>
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>