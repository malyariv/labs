<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book processing</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h3 class="text-primary text-center"><b>Books processing</b></h3>
            <br>
            <a href="/staff/addBook">
                <button class="btn btn-primary btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-book"> Add new book</i>
                </button>
            </a>
            <br>
            <a href="/staff/showBooks">
                <button class="btn btn-primary btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-list"> Show all books</i>
                </button>
            </a>
            <br>
            <a href="/all/bookSearch">
                <button class="btn btn-primary btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-search"> Book search</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <h3 class="text-danger text-center"><b>Requests processing</b></h3>
            <br>
            <a href="/staff/showOrders">
                <button class="btn btn-danger btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-shopping-cart"> Show requests</i>
                </button>
            </a>
            <br>
            <a href="/staff/issue">
                <button class="btn btn-danger btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-ok"> Issue books</i>
                </button>
            </a>
            <br>
            <a href="/staff/return">
                <button class="btn btn-danger btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-repeat"> Returned books</i>
                </button>
            </a>
            <br>
            <a href="/staff/arrange">
                <button class="btn btn-danger btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-wrench"> Arrange books</i>
                </button>
            </a>
            <br>
            <a href="/staff/overdue">
                <button class="btn btn-danger btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-exclamation-sign"> Overdue books</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <h3 class="text-success text-center"><b>Reports</b></h3>
            <br>
            <a href="/stats/books">
                <button class="btn btn-success btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-stats"> Watch statistics</i>
                </button>
            </a>
        </div>
    </div>
</div>
<br>
<br>
<br>
<br>
<div class="container">
    <a href="/staff/">
        <button class="btn btn-primary" style="font-size: large">
            <i class="glyphicon glyphicon-arrow-left"></i>
            BACK
        </button>
    </a>
</div>
</body>
</html>