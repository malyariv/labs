<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>Hello Admin!</b></h2>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <a href="/admin/showEmployees">
                <button class="btn btn-primary btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-list"> List of employees</i>
                </button>
            </a>
            <br>
            <a href="/admin/employeeSearch">
                <button class="btn btn-warning btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-search"> Find an employee</i>
                </button>
            </a>
        </div>

        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <a href="/admin/registration">
                <button class="btn btn-success btn-block" style="font-size: large">
                    <i class="glyphicon glyphicon-user"> New employee</i>
                </button>

            </a>
        </div>
        <div class="col-md-1">
        </div>

        <div class="col-md-3">
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