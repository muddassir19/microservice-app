<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <h2>Add Product</h2>
    <!-- <form action="${pageContext.request.contextPath}/addProduct" method="post"> -->
    <!-- <form action="/product-ui-1.0.0/addProduct" method="post"> -->
    <form action="<%= request.getContextPath() %>/addProduct" method="post">
        Name: <input type="text" name="name"><br>
        Price: <input type="text" name="price"><br>
        <input type="submit" value="Add">
    </form>
</body>
</html>
