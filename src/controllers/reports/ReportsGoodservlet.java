package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsGoodservlet
 */
@WebServlet("/reports/good")
public class ReportsGoodservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsGoodservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        r.setGood_count(r.getGood_count() + 1);

        Good g = new Good();

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        g.setCreated_at(currentTime);
        g.setUpdated_at(currentTime);

        g.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
        g.setReport((Report)r);

        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", "いいねしました");
        em.close();

        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
