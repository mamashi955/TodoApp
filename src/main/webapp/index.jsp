
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>みんなのタスクボード</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <h1>みんなのタスクボード</h1>

    <div class="container">
        <div class="container-header"></div>

        <div class="container-body">
            <table>
                <thead>
                    <tr>
                        <th>番号</th>
                        <th>ユーザー</th>
                        <th>期限</th>
                        <th>タスク内容</th>
                        <th>完了</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty todos}">
                            <tr>
                                <td colspan="4">
                                    登録されているタスクはありません。
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="todo" items="${todos}">
                                <tr>
                                    <td><c:out value="${todo.id}"/></td>
                                    <td><c:out value="${todo.user}"/></td>
                                    <td><c:out value="${todo.deadline}"/></td>
                                    <td><c:out value="${todo.content}"/></td>
                                    <td>
        								<form action="${pageContext.request.contextPath}/deleteTodo" method="post"
              								onsubmit="return confirm('このタスクを削除しますか？');">
            							<input type="hidden" name="id" value="${todo.id}">
            							<button type="submit">完了</button>
        								</form>
    								</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

            <div class="button-area">
                <form action="${pageContext.request.contextPath}/todos"
                      method="get">
                    <input type="hidden" name="today" value="true">
                    <button type="submit">今日のタスク<br>のみ表示</button>
                </form>

                <form action="${pageContext.request.contextPath}/todos"
                      method="get">
                    <input type="hidden" name="sort" value="deadline">
                    <button type="submit">期限が短い<br>順でソート</button>
                </form>

                <a class="button add-button"
                   href="${pageContext.request.contextPath}/add.jsp">
                    タスク追加
                </a>
            </div>
        </div>
    </div>

</body>
</html>
