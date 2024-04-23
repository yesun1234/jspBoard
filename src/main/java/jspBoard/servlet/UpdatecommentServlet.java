package jspBoard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CDto;
import jspbord.dao.DBConnect;
import jspbord.dao.JBoardCommentDao;
import jspbord.dao.JBoardDao;

/**
 * Servlet implementation class UpdatecommentServlet
 */
@WebServlet("/updatecomment")
public class UpdatecommentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userid = (String) session.getAttribute("userid");
		CDto dto = new CDto();
		dto.setUsername(request.getParameter("username"));
		dto.setComment(request.getParameter("comment"));
	    String id = request.getParameter("id");
	    String txt, jboard_id;
		int rs = 0;
		DBConnect db = new DBConnect();
		Connection conn;
        System.out.println(dto.toString());
        int newId = 0;
		try {
			conn = db.getConnection();
			JBoardCommentDao cdao = new JBoardCommentDao(conn);
			JBoardDao dao = new JBoardDao(conn);
			CDto cdto = cdao.selectDB(id);
			newId = cdto.getJboard_id();
			if(cdto.getUserid().equals(userid) || "admin".equals(userid)) {
			   	//���Ǿ��̵�� �ڸ�Ʈ �ۼ��� ���̵� ������ ���� �۾��� �����Ѵ�. �� �����ڵ� ������ �� �ִ�
				rs = cdao.updateDB(dto, id);  //�ڸ�Ʈ ����
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		if(rs == 0) {
			txt = "������ �߻��߽��ϴ�.";
		}else {
			txt = "�����߽��ϴ�.";
		}
		String str = "<script>alert('"+txt+"'); location.href='contents.jsp?id="+newId+"'; </script>";
		out.println(str);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
