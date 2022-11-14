package sec03.brd08;

import java.net.URLEncoder;
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
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/servletex");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 생성자 end
	
	public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query =  "SELECT tb.articleNo, tb.parentNo, fnc.level,tb.title,tb.content,tb.imageFileName,tb.writeDate,tb.id" //db의 query 구문
         /*DB의 query*/   + " FROM(SELECT function_hierarchical() AS articleNO, parentNO, @level AS LEVEL, title, content, imageFileName,writeDate, id"
                     + " FROM (SELECT @start_with:=0,@articleNO:=@start_with, @level:=0) tbl JOIN t_board) fnc"
                     + " left outer join t_board tb ON tb.articleNo = fnc.articleNo";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				String imageFileName = rs.getString("imageFileName");
				
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				article.setImageFileName(imageFileName);
				article.setParentNO(parentNO);
				articlesList.add(article);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
	} // selectAllArticles end
	
	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "select max(articleNO) from t_board";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1)+1); //rs.getInt(1) 첫번째 컬럼에서 데이터 받아옴
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	} // getNewArticleNO end
	
	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();
		
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content =article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+" VALUES(?,?,?,?,?,?)";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  articleNO);
			pstmt.setInt(2,  parentNO);
			pstmt.setString(3,  title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6,  id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleNO;
	} // insertNewArticle end
	
	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		
		try {
			conn = dataFactory.getConnection();
			
			String query = "select articleNO, parentNO, title, content, NVL(imageFileName, 'null') as imageFileName, id, writeDate"
							+ " from t_board"
							+ " where articleNO=?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  articleNO);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			int _articleNO = rs.getInt("articleNO");
			int _parentNO = rs.getInt("parentNO");
			String _title = rs.getString("title");
			String _content = rs.getString("content");
			String _imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "utf-8");
				if(_imageFileName.equals("null")) {
					_imageFileName = null;
				}
			String _id = rs.getString("id");
			Date _writeDate = rs.getDate("writeDate");
		
			article.setArticleNO(_articleNO);
			article.setParentNO(_parentNO);
			article.setTitle(_title);
			article.setContent(_content);
			article.setImageFileName(_imageFileName);
			article.setId(_id);
			article.setWriteDate(_writeDate);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return article;
	} // selectArticle end
	
	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board set title=?, content=?, ";
				if(imageFileName != null && imageFileName.length() != 0) {
					query += "imageFileName=?";
				}
				query += " where articleNO=?";
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  title);
			pstmt.setString(2, content);
			if(imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3,  imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // updateArticle end
	
	public void deleteArticle(List<ArticleVO> articleNO) {
		try {
			System.out.println("deleteArticle 호출");
			
			for(int i=0; i<articleNO.size(); i++) {
				conn = dataFactory.getConnection();
				String query = "";
					query = "DELETE FROM t_board" + " WHERE articleNO = ?";
					System.out.println(query);
					pstmt = conn.prepareStatement(query);
					
					ArticleVO a = articleNO.get(i);
					int articleNumber = a.getArticleNO();
					pstmt.setInt(1, articleNumber);
					pstmt.executeUpdate();
					pstmt.close();
					conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // deleteArticle end
	
	public List<ArticleVO> selectRemovedArticles(int articleNO, int parentNO) { // 삭제할 articleNO가 들어있다 
		List<ArticleVO> articleReNOList = new ArrayList<ArticleVO>();
		
		
		try {
			conn = dataFactory.getConnection();
			String query = "";

			if(parentNO == 0) { // 자기 자신의 글인 경우 -> 여러 개 반환    parentNO가 0인건 자기만 있다는거
				query = "SELECT articleNO, parentNO, title, content, imageFileName, id, writeDate from t_board where articleNO =? || parentNO =? ";
				pstmt = conn.prepareStatement(query);
				System.out.println(query);
				
				pstmt.setInt(1, articleNO);
				pstmt.setInt(2, articleNO);
				
			} else { // 답글인 경우 -> 하나만 반환    parentNO이 0이 아닌 애들은 답변이니까 
				query = "SELECT articleNO, parentNO, title, content, imageFileName, id, writeDate from t_board where articleNO = ? ";
				pstmt = conn.prepareStatement(query);
				System.out.println(query);
				
				pstmt.setInt(1,  articleNO);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int _articleNO = rs.getInt("articleNO");
				int _parentNO = rs.getInt("parentNO");
				String _title = rs.getString("title");
				String _content = rs.getString("content");
				String _id = rs.getString("id");
				Date _writeDate = rs.getDate("writeDate");
				String _imageFileName = rs.getString("imageFileName");
				
				ArticleVO article = new ArticleVO();
				article.setArticleNO(_articleNO);
				article.setTitle(_title);
				article.setContent(_content);
				article.setId(_id);
				article.setWriteDate(_writeDate);
				article.setImageFileName(_imageFileName);
				article.setParentNO(_parentNO);
				articleReNOList.add(article);
			}
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleReNOList;
	} // selectRemovedArticles end
}
