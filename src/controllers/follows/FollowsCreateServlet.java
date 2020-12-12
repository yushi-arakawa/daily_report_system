package controllers.follows;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowsCreateServlet
 */
@WebServlet("/follows/create")
public class FollowsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        Follow f = new Follow();

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        f.setCreated_at(currentTime);
        f.setUpdated_at(currentTime);

        f.setA_employee((Employee)request.getSession().getAttribute("login_employee"));
        f.setB_employee((Employee)r.getEmployee());

        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", "フォローしました");
        em.close();

        response.sendRedirect(request.getContextPath() + "/follows/index");
    }

}
