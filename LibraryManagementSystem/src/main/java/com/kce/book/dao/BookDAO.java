package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.BookBean;
import com.kce.book.util.DBUtil;

public class BookDAO {
	public int createBook(BookBean bookBean) {
		Connection connection=DBUtil.getConnection();
		String query="Insert into BOOK_Table VALUES(?,?,?,?,?)";
	    try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setString(1,bookBean.getIsbn());
		ps.setString(2, bookBean.getBookName());
		ps.setInt(3,bookBean.getAuthor().getAuthorCode());
		ps.setString(4,String.valueOf(bookBean.getBookType()));
		ps.setFloat(5,bookBean.getCost());
		int row=ps.executeUpdate();
		if(row>0) {
			return 1;
		}
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
		return 0;	
	}
	public BookBean fetchBook(String isbn) {
		Connection connection=DBUtil.getConnection();
		String query="Select * from BOOK_Table where ISBN=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				BookBean bookbean=new BookBean();
				bookbean.setIsbn(rs.getString("ISBN"));
				bookbean.setBookName(rs.getString("Book_title"));
				bookbean.setBookType(rs.getString("Book_type").charAt(0));
				bookbean.setAuthor(new AuthorDAO().getAuthor(rs.getInt("Author_code")));
		        bookbean.setCost(rs.getFloat("Book_cost"));
		        return bookbean;
			}
		    }catch(SQLException e) {
		    	e.printStackTrace();
		    }
		return null;
	}

}