<!DOCTYPE html>
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
</html>
