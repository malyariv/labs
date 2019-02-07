<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<br>
<div class="container">
    <form role="form" name="search" action="/admin/employeeSearch" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-4"><b>Whom are you looking?</b></label>
            <div class="col-md-5">
                <input type="text" name="searchField" required class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4"><b>Choose identification parameter</b></label>
            <div class="col-md-5">
                <input type="text" name="type" list="by" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-4">
            </div>
            <div class="col-md-5">
                <input type="submit" value="Search" class="btn btn-warning btn-block" style="font-size: large">
            </div>
        </div>
    </form>
</div>


<datalist id="by">
    <option>Name</option>
    <option>Surname</option>
    <option>Email</option>
</datalist>
</body>
</html>