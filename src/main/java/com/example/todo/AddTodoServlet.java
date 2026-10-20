package com.example.todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addTodo")
public class AddTodoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String content = request.getParameter("content");
        String user = request.getParameter("user");
        String deadlineText = request.getParameter("deadline");

        LocalDate deadline = null;

        if (deadlineText != null && !deadlineText.isEmpty()) {
            deadline = LocalDate.parse(deadlineText);
        }

        String sql = "INSERT INTO todos (content, \"user\", deadline, \"createdAt\") "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, content);
            pstmt.setString(2, user);

            if (deadline != null) {
                pstmt.setObject(3, deadline);
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }

            pstmt.setObject(4, LocalDate.now());

            pstmt.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/todos");

        } catch (SQLException e) {
            throw new ServletException("タスクの保存に失敗しました", e);
        }
    }
}