<%@ page contentType="text/html; UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript">
        var xmlhttp;
        function loadXMLDoc(url)
        {
            xmlhttp=null;
            if (window.XMLHttpRequest)
            {// code for all new browsers
                xmlhttp=new XMLHttpRequest();
            }
            else if (window.ActiveXObject)
            {// code for IE5 and IE6
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            if (xmlhttp!=null)
            {
                xmlhttp.onreadystatechange=state_Change;
                xmlhttp.open("GET",url,true);
                xmlhttp.send(null);
            }
            else
            {
                alert("Your browser does not support XMLHTTP.");
            }
        }

        function state_Change()
        {
            if (xmlhttp.readyState==4)
            {// 4 = "loaded"
                if (xmlhttp.status==200)
                {// 200 = OK
                    document.write(xmlhttp.responseText);
                }
                else
                {
                    alert("Problem retrieving XML data");
                }
            }
        }
    </script>
</head>
<body>
<h2>Hello World!</h2>
<input type="button" onclick="loadXMLDoc('ajax.action')" value="Send"/>
</body>
</html>
