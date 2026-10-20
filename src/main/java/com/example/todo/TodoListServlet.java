
package com.example.todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todos")
public class TodoListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, Object>> todos = new ArrayList<>();

        String sql = "SELECT id, content, \"user\", deadline, \"createdAt\" "
                   + "FROM todos";

        // 今日のタスクだけ表示する
        if ("true".equals(request.getParameter("today"))) {
            sql += " WHERE deadline = CURRENT_DATE";
        }

        // 期限が近い順に並べる
        if ("deadline".equals(request.getParameter("sort"))) {
            sql += " ORDER BY deadline ASC NULLS LAST, id DESC";
        } else {
            sql += " ORDER BY id DESC";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> todo = new HashMap<>();

                todo.put("id", rs.getInt("id"));
                todo.put("content", rs.getString("content"));
                todo.put("user", rs.getString("user"));
                todo.put("deadline", rs.getDate("deadline"));
                todo.put("createdAt", rs.getDate("createdAt"));

                todos.add(todo);
            }

        } catch (Exception e) {
            throw new ServletException("タスク一覧の取得に失敗しました", e);
        }

        request.setAttribute("todos", todos);
        request.getRequestDispatcher("/index.jsp")
               .forward(request, response);
    }
}
