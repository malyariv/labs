<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <#function pieChart rawData>
        <#local data = [] />

        <#list rawData as i>
            <#local data = data + [i] />
        </#list>

        <#local finalData>
            <#list 0..(data?size-1) as x>
                <#compress>
                    <#if data[x]?has_content>${data[x]?c}</#if>
                    <#if x_has_next>,</#if>
                </#compress>
            </#list>
        </#local>

        <#local output =
                "https://chart.googleapis.com/chart" +
                "?cht=pc" +
                "&chs=500x500" +
                "&chco=ffffff,066639,08a15e,7acfa9" +
                "&chp=-1.6" +
                "&chd=t:0|" + finalData
        />
        <#return output?replace("|","%7c")>
    </#function>
    <script src="https://www.google.com/jsapi"></script>
    <script>
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                <#list years as y>
            ['Год', 'Россия', 'США'],
                [${y}, 1.3, 70],
//                    ['1885', 2000, 3120],
//                    ['1901', 12170, 9920]
        </#list>
        ]);
            var options = {
                title: 'Добыча нефти',
                hAxis: {title: 'Год'},
                vAxis: {title: 'Тыс. тонн'}
            };
            var chart = new google.visualization.ColumnChart(document.getElementById('oil'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>

<img src="${pieChart([10, 20, 50])}">
<div id="oil" style="width: 500px; height: 400px;"></div>
</body>
</html>