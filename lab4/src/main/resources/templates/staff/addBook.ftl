<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add book</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body class="bg-light" style="font-size: large">
<div class="container">
    <div class="row">
        <div class="col-md-4 form-group">
            <form name="addbook" action="/staff/addBook" method="post" enctype="multipart/form-data">
                <br>
                <label><b>Title</b></label>
                <input title="Title" type="text" name="title" class="form-control" required>
                <br>
                <label><b>Authors</b></label>
                <input title="Authors" type="text" name="authors" class="form-control" required>
                <br>
                <br>
                <label><b>Publisher</b></label>
                <input title="Publisher" type="text" name="publisher" class="form-control" required>
                <br>
                <label><b>Year</b></label>
                <input title="Year" type="text" name="year" class="form-control" required>
                <br>
                <label><b>Number of pages</b></label>
                <input title="Pages" type="text" name="pages" class="form-control" required>
                <br>
                <label><b>Genres</b></label>
                <input title="Genre" type="text" name="genres" class="form-control" required>
                <br>
                <label><b>Section</b></label>
                <input title="Section" type="text" name="section" class="form-control" required>
                <br>
                <label><b>Rack</b></label>
                <input title="Rack" type="text" name="rack" class="form-control" required>
                <br>
                <label><b>Shelf</b></label>
                <input title="Shelf" type="text" name="shelf" class="form-control" required>
                <br>
                <label><b>Cover upload</b></label>
                <input type="file" name="file" class="btn" style="font-size: large">
                <br>
                <input type="submit" value="Ok" class="btn btn-success btn-block" style="font-size: large">
            </form>
            <br>
            <br>
            <a href="/staff/books">
                <button class="btn btn-primary" style="font-size: large">
                    <i class="glyphicon glyphicon-arrow-left"></i>
                    BACK
                </button>
            </a>
        </div>
    </div>
</div>

</body>
</html>