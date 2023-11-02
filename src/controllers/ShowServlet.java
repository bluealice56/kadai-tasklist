//詳細画面の動作についての設計書

package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task_dto;
import utils.Task_dao;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Task_dao.createEntityManager();

        // 該当のIDのメッセージ1件のみをデータベースから取得
        //request.getParameter("id")でURLに記載されたIDの価をString型で取得
        //Integer.parseIntで、String型の価をint型に変える。
        //整数の値でem.findメソッドで検索して、DTO変数に代入する。
        Task_dto m = em.find(Task_dto.class, Integer.parseInt(request.getParameter("id")));

        //接続を切る。
        em.close();

        // メッセージデータをリクエストスコープにセットしてshow.jspを呼び出す
        request.setAttribute("message", m);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/show.jsp");
        rd.forward(request, response);
    }
}
