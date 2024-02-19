package com.placement;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RepServlet
 */
@WebServlet("/register")
public class RepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepDAO repDAO = new RepDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registerRep.jsp");
		dispatcher.forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the information from the form (from the post request)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Create the rep
		Representative rep = new Representative();
		rep.setUsername(username);
		rep.setPassword(password);
		
		try {
			// Insert the rep into the database
			repDAO.registerRepresentative(rep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("path: " + request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/repDetails.jsp");
		dispatcher.forward(request,  response);
	}
}