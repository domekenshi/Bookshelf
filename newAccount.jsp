<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
</head>
<body>
    <h1>新規登録画面</h1>
    ${msg}
    <br>
    <form action="/Bookshelf/NewAccount" method="post">
        ユーザー名<br> <input type="text" name="name" maxlength='20' required><br>
        <div class="form-group">
            <label>パスワード(10文字以内)</label><br> <input type="password" maxlength='10' class="form-control"
                name="pass1" id="password" required>
        </div>
        <div class="form-group">
            <label>パスワード (再確認)</label><br> <input type="password" maxlength='10' class="form-control" name="pass2"
                oninput="CheckPassword(this)" required>
        </div>

        メールアドレス<br> <input type="email" name="mailaddress" required><br> <input type="hidden"
            name="menu" value="1"> <input type="submit" value="登録する"><br>
    </form>

    <script>
					function CheckPassword(confirm) {
						// 入力値取得
						var input1 = password.value;
						var input2 = confirm.value;
						// パスワード比較
						if (input1 != input2) {
							confirm.setCustomValidity("入力値が一致しません。");
						} else {
							confirm.setCustomValidity('');
						}
					}
				</script>



</body>
</html>