<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<#--<form name="registration" action="/admin/registration" method="post">-->
<body class="bg-light" style="font-size: large">
<div class="container">
    <#if error??>
        <h2 class="text-danger">${error}</h2>
    </#if>
    <div class="row">
        <div class="col-md-4 form-group">
            <form name="registration" action="/admin/registration" method="post">
                <br>
                <label><b>Here enter employee's username</b></label>
                <input title="Username" type="text" name="username" class="form-control" required>
                <br>
                <br>
                <label><b>Here enter employee's password</b></label>
                <input title="Password" type="password" name="password" class="form-control" required>
                <br>
                <br>
                <label><b>Here enter employee's first name</b></label>
                <input title="First name" type="text" name="firstname" class="form-control" required>
                <br>
                <br>
                <label><b>Here enter employee's last name</b></label>
                <input title="Last name" type="text" name="lastname" class="form-control" required>
                <br>
                <br>
                <label><b>Here enter employee's email</b></label>
                <input title="Email" type="email" name="email" class="form-control" required>
                <br>
                <br>
                <input type="submit" value="Ok" class="btn btn-success btn-block" style="font-size: large">
            </form>
        </div>
    </div>
</div>
</body>
</html>