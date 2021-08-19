<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online-Shop</title>
</head>
<body>
<div>
    <h2>Add new product</h2>
    <form action="/product/new" method="POST">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>