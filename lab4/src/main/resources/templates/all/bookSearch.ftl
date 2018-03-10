<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>books</title>
</head>
<body>
<form name="search" action="/all/bookSearch" method="post">
    <p>What are you looking? </p>
    <input type="text" name="searchField">
    <p>Choose identification parameter</p>
    <input type="text" name="type" list="by" required>
    <input type="submit" value="Search">
</form>
<datalist id="by">
    <option>Title</option>
    <option>Author</option>
    <option>Genre</option>
</datalist>
</body>
</html>