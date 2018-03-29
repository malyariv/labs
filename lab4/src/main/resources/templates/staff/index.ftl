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
            <h3 class="text-info text-center"><b>Books</b></h3>
            <br>
            <a href="/staff/addBook">
                <button class="btn btn-info btn-block" style="font-size: large">
                    Add new book
                </button>
            </a>
            <br>
            <a href="/staff/showBooks">
                <button class="btn btn-info btn-block" style="font-size: large">
                    Show all books
                </button>
            </a>
            <br>
            <a href="/staff/arrange">
                <button class="btn btn-info btn-block" style="font-size: large">
                    Arrange books
                </button>
            </a>
        </div>

        <div class="col-md-1">
        </div>

        <div class="col-md-3">
            <h3 class="text-warning text-center"><b>Clients</b></h3>
            <br>
            <a href="/staff/showClients">
                <button class="btn btn-warning btn-block" style="font-size: large">
                    Show clients
                </button>
            </a>
            <br>
            <a href="/staff/showOrders">
                <button class="btn btn-warning btn-block" style="font-size: large">
                    Show orders
                </button>
            </a>
            <br>
            <a href="/staff/overdue">
                <button class="btn btn-warning btn-block" style="font-size: large">
                    Overdue books
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <br>
            <br>
            <br>
            <br>
            <a href="/logout">
                <button class="btn btn-success btn-block" style="font-size: large">
                    Logout
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>