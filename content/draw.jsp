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
        }).frequency(0.1);
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
            token.trim().split("").each(function (c) {
                e.insert({
                    bottom: c == "*" ? $("bug").clone(true) : $("cross").clone(true)
                })
            })
        });
        console.log(new Date() - start);
    }
</script>

<table id="result">
</table>

<table style="display: none;">
    <tr style="display: none;">
        <td id="bug"><img src="bug.png" alt="bug"/></td>
    </tr>
    <tr style="display: none;">
        <td id="cross"><img src="cross.png" alt="cross"/></td>
    </tr>
</table>
</body>
</html>