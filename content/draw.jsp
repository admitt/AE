<html>
<head>
    <script type="text/javascript" src="prototype.js"></script>
</head>
<body>

<script type="text/javascript">
    document.observe("dom:loaded", function () {
        draw("<%=request.getAttribute("state")%>");
        new Ajax.PeriodicalUpdater('', 'update', {
            onSuccess:function (t) {
                draw(t.responseText)
            }
        }).frequency(0.5);
    });

    function draw(data) {
        var start = new Date();
        var parent = $('result');
        parent.childElements().each(function (e) {
            e.remove();
        });
        var tokens = data.split(" ");
        tokens.each(function (token) {
            var e = Element("tr");
            parent.insert({
                bottom:e
            });
            token.split("").each(function (c) {
                e.insert({
                    bottom:"<td>" + c + "</td>"
                })
            })
        });
        console.log(new Date() - start);
    }
</script>

<table id="result"></table>
</body>
</html>