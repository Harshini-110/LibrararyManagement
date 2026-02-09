package com.kce.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kce.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	PrintWriter out= response.getWriter();
	HttpSession session =request.getSession();
	BookBean bookBean=(BookBean)session.getAttribute("book");
	out.print("<html><body>");
	out.print("Book title :" +bookBean.getBookName());
	out.print("Author Name : "+bookBean.getAuthor().getAuthorName());
	out.print("Author Contact : "+bookBean.getAuthor().getContactNo());
	out.print("Book ISBN : "+bookBean.getIsbn());
	out.print("</body></html>");
	
	
	}

}