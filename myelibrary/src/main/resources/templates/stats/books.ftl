<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart'],'language': 'ru'});
        google.charts.setOnLoadCallback(drawChart1);
        google.charts.setOnLoadCallback(drawChart2);

        function drawChart1() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Genre');
            data.addColumn('number', '%');
            data.addRows([
                <#list plotCounter?keys as key>
                    ['${key}',  ${plotCounter[key]}],
                </#list>
            ]);
            var options = {'title':'The fractions of different genres',
                'color':'black',
                'width':600,
                'height':450,
            };
            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div1'));
            chart.draw(data, options);
        }
        function drawChart2() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Genre');
            data.addColumn('number', '%');
            data.addRows([
            <#list plotDemand?keys as key>
                ['${key}',  ${plotDemand[key]}],
            </#list>
            ]);
            var options = {'title':'The fractions of different genres',
                'width':600,
                'height':450,
            };
            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
            chart.draw(data, options);
        }
    </script>
</head>
<body class="bg-light" style="font-size: large">
<br>
<h2 class="text-center text-primary"><b>Statistics</b></h2>
<br>
<div class="container">
    <h3 class="text-center text-primary"><b>Genre statistics</b></h3>
    <br>
    <div class="row">
        <div class="col-md-6">
            <div id="chart_div1" style="width: 80%; height: 80%;"></div>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-4">
            <table class="table table-bordered table-striped">
                <thead>
                <tr class="bg-info">
                    <th class="text-center">Genre</th>
                    <th class="text-center">Number of books</th>
                    <th class="text-center">Fraction, %</th>
                </tr>
                </thead>
                <tbody>
                    <#list statsCounter?keys as key>
                    <tr>
                        <td style="font-size: medium"><b><i>${key}</i></b></td>
                        <td style="font-size: medium" class="text-center">${quantityCounter[key]}</td>
                        <td style="font-size: medium" class="text-center">${statsCounter[key]}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>

    <h3 class="text-center text-primary"><b>Reservation statistics</b></h3>
    <br>
    <div class="row">
        <div class="col-md-6">
            <div id="chart_div2" style="width: 400px; height: 240px;"></div>
        </div>
        <div class="col-md-1">
        </div>
        <div class="col-md-4">
            <table class="table table-bordered table-striped">
                <thead>
                <tr class="bg-info">
                    <th class="text-center">Genre</th>
                    <th class="text-center">Number of books</th>
                    <th class="text-center">Fraction, %</th>
                </tr>
                </thead>
                <tbody>
                <#list statsDemand?keys as key>
                <tr>
                    <td style="font-size: medium"><b><i>${key}</i></b></td>
                    <td style="font-size: medium" class="text-center">${quantityDemand[key]}</td>
                    <td style="font-size: medium" class="text-center">${statsDemand[key]}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <br>
    <h3 class="text-center text-primary"><b>Top 5 popular books</b></h3>
    <br>
    <table class="table table-bordered table-striped">
        <thead>
        <tr class="bg-info">
            <th class="text-center">Cover</th>
            <th class="text-center">Id</th>
            <th class="text-center">Title</th>
            <th class="text-center">Author(-s)</th>
            <th class="text-center">Genre(-s)</th>
            <th class="text-center">Number of reservation</th>
        </tr>
        </thead>
        <tbody>
        <#list books as book>
        <tr>
            <td><img src="${book.cover}" height="200" width="130"></td>
            <td style="font-size: medium" class="text-center">${book.id}</td>
            <td style="font-size: medium" class="text-center">${book.title}</td>
            <td style="font-size: medium" class="text-center">
                <#list  book.authors as a>
                ${a.fullname}
                    <br>
                </#list>
            </td>
            <td style="font-size: medium" class="text-center">
                <#list  book.genres as g>
                ${g.name}
                    <br>
                </#list>
            </td>
            <td style="font-size: medium" class="text-center">${book.counter}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>