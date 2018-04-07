<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Client processing</title><link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<div class="container">
    <h2 class="text-center text-primary"><b>You can perform the following operations</b></h2>
    <br>
    <div class="row">
        <div class="col-md-3">
            <a href="/staff/showClients">
                <button class="btn btn-primary btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-list"> Show all clients</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <a href="/staff/clientSearch">
                <button class="btn btn-warning btn-block" style="font-size: 125%">
                    <i class="glyphicon glyphicon-search"> Client search</i>
                </button>
            </a>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <a href="/stats/clients">
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