//情報を登録する方法だけを書いた設計書

package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task_dto;
import utils.Task_dao;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        //CSRF対策 _tokenの価が変だと登録できない
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = Task_dao.createEntityManager();
            em.getTransaction().begin();

            //DTOのインスタンス
            Task_dto m = new Task_dto();

            //価（タスク内容）をフォームから受け取って、String変数に格納
            String content = request.getParameter("content");
            //価をカラムにセットする
            m.setContent(content);

            //現在日時の情報を持つ日付型のオブジェクトを取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            //取得したオブジェクトをカラムにセットする
            m.setCreated_at(currentTime);
            m.setUpdated_at(currentTime);

            //DBにセーブ
            em.persist(m);
            //DBでコミット
            em.getTransaction().commit();
            //接続を切る
            em.close();

            //別のサーブレットとかに処理を移す
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}

