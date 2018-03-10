<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
</head>
<body>
edit of ${user.username}
<form name="update" action="/user/editProfile" method="post">
    <p>Username</p>
    <input title="Username" type="text" name="username" placeholder="${user.username}">
    <p>Password</p>
    <input title="Password" type="password" name="password" placeholder="****">
    <p>First name</p>
    <input title="First name" type="text" name="firstname" placeholder="${user.clientData.firstname}">
    <p>Last name</p>
    <input title="Last name" type="text" name="lastname" placeholder="${user.clientData.lastname}">
    <p>Email</p>
    <input title="Email" type="text" name="email" placeholder="${user.clientData.email}">
    <input type="submit" value="Ok">
</form>
</body>
</html>