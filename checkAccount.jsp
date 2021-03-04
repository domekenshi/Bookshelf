<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録決定画面</title>
</head>
<body>

    ${user.userName}<br>
    ${user.address}<br>
    <br>
    <form action="/NewAccount" method="post">

         <input type="hidden" name="menu" value="2">
          <input type="submit" value="OK">

    </form>
    <a href="/Bookshelf/NewAccount">戻る</a>
</body>
</html>