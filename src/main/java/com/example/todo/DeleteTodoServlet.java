
package com.example.todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteTodo")
public class DeleteTodoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idText = request.getParameter("id");

        try {
            int id = Integer.parseInt(idText);

            String sql = "DELETE FROM todos WHERE id = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            response.sendRedirect(request.getContextPath() + "/todos");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "タスクIDが正しくありません。");
        } catch (Exception e) {
            throw new ServletException("タスクの削除に失敗しました", e);
        }
    }
}
