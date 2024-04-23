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


@WebServlet("/cdel")
public class Cdel extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String jboard_id = request.getParameter("jboard_id");
		int chit = Integer.parseInt(request.getParameter("chit"));
		String txt, link;
		String userid = (String) session.getAttribute("userid");
		int rs = 0;
		DBConnect db = new DBConnect();
		CDto dto = new CDto();
		Connection conn;
		
		try {
			conn = db.getConnection();
			JBoardCommentDao cdao = new JBoardCommentDao(conn);
			JBoardDao dao = new JBoardDao(conn);
			dto = cdao.selectDB(id);
			if(dto.getUserid().equals(userid) || "admin".equals(userid)) {
			   	//���Ǿ��̵�� �ڸ�Ʈ �ۼ��� ���̵� ������ ���� �۾��� �����Ѵ�. �� �����ڵ� ������ �� �ִ�
				rs = cdao.deleteDB(id);  //�ڸ�Ʈ ����
				dao.updateDB(Integer.parseInt(jboard_id), chit-1, "chit"); //�ڸ�Ʈ ���ڸ� �ϳ� ����
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		if(rs == 0) {
			txt = "������ �߻��߽��ϴ�.";
		}else {
			txt = "�����߽��ϴ�.";
		}
		String str = "<script>alert('"+txt+"'); location.href='contents.jsp?id="+jboard_id+"'; </script>";
		out.println(str);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
