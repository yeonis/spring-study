package sec03.brd01;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	public BoardDAO() {
		try
		{
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/servletex");
		} catch ( Exception e)
		{
			e.printStackTrace();
		}
	}
	


public List selectAllArticles()
	{
		List articlesList = new ArrayList();
		try
		{
			conn = dataFactory.getConnection();
			String query = "SELECT function_hierarchical() AS articleNO, @level AS LEVEL, title,content,id,writeDate"  
					+ " FROM(SELECT @start_with:=0, @articleNO:=@start_with,@level:=0) tbl JOIN t_board";
					
			
			
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				//int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				ArticleVO article = new ArticleVO();
				
				article.setLevel(level);
				article.setArticleNO(articleNO);
				//article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return articlesList;
	}


}
