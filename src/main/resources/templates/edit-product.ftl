<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online-Shop</title>
</head>
<body>

<div class="container">
    <h2>Edit product</h2>
    <form action="/product/edit" method="POST">
        <input type="hidden" name="id" value="${product.id}" />
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${product.name}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" value="${product.price}"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>