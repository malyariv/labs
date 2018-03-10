<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add book</title>
</head>
<body>
<form name="addbook" action="/staff/addBook" method="post">
    <p>Title</p>
    <input title="Title" type="text" name="title">
    <p>Authors</p>
    <input title="Authors" type="text" name="authors">
    <p>Publisher</p>
    <input title="Publisher" type="text" name="publisher">
    <p>Year</p>
    <input title="Year" type="text" name="year">
    <p>Number of pages</p>
    <input title="Pages" type="text" name="pages">
    <p>Genres</p>
    <input title="Genre" type="text" name="genres">
    <p>Section</p>
    <input title="Section" type="text" name="section">
    <p>Rack</p>
    <input title="Rack" type="text" name="rack">
    <p>Shelf</p>
    <input title="Shelf" type="text" name="shelf">

    <input type="submit" value="Ok">
</form>
</body>
</html>