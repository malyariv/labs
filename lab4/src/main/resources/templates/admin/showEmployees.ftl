<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
    <br>
    <h2 class="text-center text-info"><b>List of employees</b></h2>
    <br>
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="bg-info">
                <th>ID</th>
                <th>LOGIN</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <#list emps as em>
                <tr>
                    <td>${em.id}</td>
                    <td>${em.username}</td>
                    <td>${em.employeeData.firstname}</td>
                    <td>${em.employeeData.lastname}</td>
                    <td>
                        <a href="/admin/delete/${em.id}">Delete</a>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
    <div class="container">
        <a href="/admin">
            <button class="btn btn-primary" style="font-size: large">
                <i class="glyphicon glyphicon-arrow-left"></i>
                BACK
            </button>
        </a>
    </div>
</body>
</html>