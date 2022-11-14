package sec03.brd01;

import java.util.ArrayList;
import java.util.List;

public class BoardService 
{
	BoardDAO boardDAO;
	
	public BoardService()
	{
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles()
	{
		
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		
		return articlesList;
	}
}
