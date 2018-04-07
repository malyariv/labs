<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>Hello ${employee.firstname} ${employee.lastname}!</b></h2>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <br>
            <a href="/staff/books">
                <button class="btn btn-primary btn-block" style="font-size: 250%">
                    <i class="glyphicon glyphicon-book"> Books</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <br>
            <a href="/staff/clients">
                <button class="btn btn-warning btn-block" style="font-size: 250%">
                    <i class="glyphicon glyphicon-user"> Clients</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <br>
            <a href="/logout">
                <button class="btn btn-danger btn-block" style="font-size: 250%">
                    <i class="glyphicon glyphicon-log-out"> Logout</i>
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>