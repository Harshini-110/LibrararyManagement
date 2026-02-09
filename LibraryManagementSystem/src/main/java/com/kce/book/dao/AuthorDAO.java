package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.AuthorBean;
import com.kce.book.util.DBUtil;

public class AuthorDAO {
	public AuthorBean getAuthor(int authorCode) {
		Connection connection=DBUtil.getConnection();
		String query="Select * from Author_Tbl where Author_code=?";
	    try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1, authorCode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			AuthorBean author=new AuthorBean();
			author.setAuthorCode(rs.getInt("Author_code"));
			author.setAuthorName(rs.getString("Author_name"));
			author.setContactNo(rs.getLong("Contact_no"));
			return author;
		}
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	public AuthorBean getAuthor(String authorName) {
		Connection connection=DBUtil.getConnection();
		String query="Select * from Author_Tbl where Author_name=?";
	    try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setString(1, authorName);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			AuthorBean author=new AuthorBean();
			author.setAuthorCode(rs.getInt("Author_code"));
			author.setAuthorName(rs.getString("Author_name"));
			author.setContactNo(rs.getLong("Contact_no"));
			return author;
		}
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
		return null;
	}
}