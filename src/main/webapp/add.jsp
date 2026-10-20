
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新たなタスクを追加</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <h1>みんなのタスクボード</h1>

    <div class="container">
        <div class="container-header"></div>

        <div class="container-body">
            <h2>新たなタスクを追加</h2>

            <form action="${pageContext.request.contextPath}/addTodo"
                  method="post" class="form-area">

                <div class="form-row">
                    <label for="user">ユーザー：</label>
                    <input type="text" id="user" name="user"
                           maxlength="255">
                </div>

                <div class="form-row">
                    <label for="deadline">期限：</label>
                    <input type="date" id="deadline" name="deadline">
                </div>

                <div class="form-row">
                    <label for="content">タスク内容：</label>
                    <input type="text" id="content" name="content"
                           maxlength="255" required>
                </div>

                <div class="form-submit">
                    <button type="submit">タスク追加</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
