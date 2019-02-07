<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Clients</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
    <br>
    <h2 class="text-center text-info"><b>List of clients</b></h2>
    <br>
    <table class="table table-bordered table-striped">
        <thead>
            <tr class="bg-info">
                <th>ID</th>
                <th>Login</th>
                <th>Full name</th>
                <th>Is active</th>
                <th>Reserved books</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.clientData.firstname} ${user.clientData.lastname}</td>
                    <td>${user.enabled?string('yes', 'no')}</td>
                    <td>
                            <#list user.clientData.bookSet as book>
                                "${book.title}"
                                <br>
                            </#list>
                    </td>
                    <td>
                        <a href="/delete/${user.id}">Delete</a>
                        <#if user.enabled==true>
                            <br>
                            <a href="/staff/disable/${user.id}">Disable</a>
                        <#else>
                            <br>
                            <a href="/staff/activate/${user.id}">Activate</a>
                        </#if>
                    </td>

                </tr>
            </#list>
        </tbody>
    </table>
    <div class="container">
        <a href="/staff/clients">
            <button class="btn btn-primary" style="font-size: large">
                <i class="glyphicon glyphicon-arrow-left"></i>
                BACK
            </button>
        </a>
    </div>
</body>
</html>