<%@ page import="java.net.*, java.io.*, org.json.*, java.util.*" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Product List</title></head>
<body>
<h2>Product List</h2>

<%
    URL url = new URL("http://35.154.113.145:8081/api/products");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
    }
    in.close();
    conn.disconnect();

    JSONArray products = new JSONArray(content.toString());
%>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <%
        for (int i = 0; i < products.length(); i++) {
            JSONObject product = products.getJSONObject(i);
    %>
    <tr>
        <td><%= product.getString("name") %></td>
        <td><%= product.getDouble("price") %></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>



<!-- <!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h2>Product List</h2>
    <div id="list"></div>

    <script>
        fetch('http://localhost:8080/api/products')
            .then(response => response.json())
            .then(data => {
                const listDiv = document.getElementById('list');
                listDiv.innerHTML = data.map(p => `<p>${p.name} - $${p.price}</p>`).join('');
            });
    </script>
</body>
</html> -->
