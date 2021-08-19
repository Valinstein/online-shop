<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

    <title>Online-shop</title>
</head>
<body>
<h1 class="text-center">Products</h1>
<table class="table table-striped table-hover">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>Creation date</th>
    </tr>

    <#list products as product>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.creationDate}</td>
            <td>
                <form action="/product/edit" method="GET"><input type="hidden" name="id" value="${product.id}" /><button type="submit">Edit</button></form>
            </td>
            <td>
                <form action="/product/delete" method="POST"><input type="hidden" name="id" value="${product.id}" /><button type="submit">Delete</button></form>
            </td>
        </tr>
    </#list>
    <tr>
        <td>
            <form action="/product/new" method="GET"><input type="hidden"/><button type="submit">New product</button></form>
        </td>
    </tr>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>

</body>
</html>
