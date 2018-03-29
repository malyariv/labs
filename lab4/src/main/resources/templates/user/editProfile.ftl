<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>The following data can be updated</b></h2>
<br>
<div class="container">
    <form name="update" action="/user/editProfile" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-4"><b>Username</b></label>
            <div class="col-md-5">
                <input title="Username" type="text" name="username" placeholder="${user.username}" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4"><b>Password</b></label>
            <div class="col-md-5">
                <input title="Password" type="password" name="password" placeholder="****" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4"><b>First name</b></label>
            <div class="col-md-5">
                <input title="First name" type="text" name="firstname" placeholder="${user.clientData.firstname}" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4"><b>Last name</b></label>
            <div class="col-md-5">
                <input title="Last name" type="text" name="lastname" placeholder="${user.clientData.lastname}" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4"><b>Email</b></label>
            <div class="col-md-5">
                <input title="Email" type="text" name="email" placeholder="${user.clientData.email}" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-4">
            </div>
            <div class="col-md-5">
                <input type="submit" value="Update" class="btn btn-success btn-block" style="font-size: large">
            </div>
        </div>


        <#--<p>Username</p>-->
        <#--<input title="Username" type="text" name="username" placeholder="${user.username}">-->
        <#--<p>Password</p>-->
        <#--<input title="Password" type="password" name="password" placeholder="****">-->
        <#--<p>First name</p>-->
        <#--<input title="First name" type="text" name="firstname" placeholder="${user.clientData.firstname}">-->
        <#--<p>Last name</p>-->
        <#--<input title="Last name" type="text" name="lastname" placeholder="${user.clientData.lastname}">-->
        <#--<p>Email</p>-->
        <#--<input title="Email" type="text" name="email" placeholder="${user.clientData.email}">-->
        <#--<input type="submit" value="Ok">-->
    </form>
    <br>
    <br>
    <div class="container">
        <a href="/user">
            <button class="btn btn-primary" style="font-size: large">
                <i class="glyphicon glyphicon-arrow-left"></i>
                BACK
            </button>
        </a>
    </div>
</body>
</html>