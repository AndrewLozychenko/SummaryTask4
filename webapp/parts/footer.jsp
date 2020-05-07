<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<style>
    div {
        border: 3px solid lightblue;
        border-radius: 10px;
        align-content: center;
        padding: 20px;
        margin: 20px;
    }

    h1, h3 {
        text-align: center;
    }

    h2 {
        color: red;
    }

    input[type=button], input[type=submit] {
        border-radius: 5px;
        margin: 5px;
        padding: 5px;
    }

    table {
        margin: 20px;
        padding: 10px;
        border-radius: 5px;
    }

    tr, td, th {
        margin: 2px;
        padding: 2px;
    }
</style>
<body>
<div>
    <h3><fmt:message key="labels.copyright"/></h3>
</div>
</body>
</html>