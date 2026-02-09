package com.kce.book.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kce.book.bean.AuthorBean;
import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook")) {
			String result=addBook(request);
			if(result.equals("SUCCESS")) {
				response.sendRedirect("menu.html");
			}
			else if(result.equals("INVALID")) {
				response.sendRedirect("Invalid.html");
			}
			else if(result.equals("FAILURE")) {
				response.sendRedirect("failure.html");
			}
		}
		else if(operation.equals("Search")) {
			String isbn=request.getParameter("isbn");
			BookBean bookBean=viewBook(isbn);
			if(bookBean==null) {
				response.sendRedirect("Invalid.html");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("book",bookBean);
				RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
				rd.forward(request, response);

				}
		}
	}
	
	public String addBook(HttpServletRequest request) {
	    String isbn = request.getParameter("isbn");
	    String bookName = request.getParameter("bookName");
	    String bookType = request.getParameter("bookType");
	    String authorName = request.getParameter("authorName");
	    String cost = request.getParameter("cost");

	    AuthorDAO dao = new AuthorDAO();
	    AuthorBean author = dao.getAuthor(authorName);

	    if (author == null) {
	        return "INVALID";   // author not found in DB
	    }

	    BookBean bookbean = new BookBean();
	    bookbean.setIsbn(isbn);
	    bookbean.setBookName(bookName);
	    bookbean.setBookType(bookType.charAt(0));
	    bookbean.setCost(Float.parseFloat(cost));
	    bookbean.setAuthor(author);

	    return new Administrator().addBook(bookbean);
	}

    public BookBean viewBook(String isbn) {
    	Administrator administrator=new Administrator();
    	return administrator.viewBook(isbn);
    }
}