package jsboardService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import dto.BDto;
import dto.CDto;
import jspbord.dao.DBConnect;
import jspbord.dao.JBoardCommentDao;
import jspbord.dao.JBoardDao;

public class DBworks { 
	   private int limitPage;
	   private int listCount;
	   private String sname;
	   private String svalue;
	   private Connection conn;
	   private DBConnect db = new DBConnect();
	   private String id;
	   
	   public DBworks() {}
	   public DBworks(int limitPage, int listCount, String sname, String svalue) {
	      this.limitPage = limitPage;
	      this.listCount = listCount;
	      this.sname = sname;
	      this.svalue = svalue;
	   }   
	   
	   public String getId() {
	      return id;
	   }

	   public void setId(String id) {
	      this.id = id;
	   }

	   //��ü �� ����
	   public int getAllSelect() {
	      int allCount = 0;
	      try {
	         conn = db.getConnection();
	         JBoardDao dao = new JBoardDao(conn);
	         if(sname == null) {
	            allCount = dao.AllSelectDB();
	         }else {
	            allCount = dao.AllSelectDB(sname, svalue);   
	         }
	      } catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	      return allCount;
	   }
	   
	   //�Ϲ� ����¡�� �ִ� ���
	   public ArrayList<BDto> getList(){
	      ArrayList<BDto> lists = null;
	      try {
	         conn = db.getConnection();
	         JBoardDao dao = new JBoardDao(conn);
	         lists = dao.selectDB(limitPage, listCount);
	      } catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	      return lists;
	   }
	   
	   //�˻� ���
	   public ArrayList<BDto> getSearchList(){
	      ArrayList<BDto> lists = null;
	      try {
	         conn = db.getConnection();
	         JBoardDao dao = new JBoardDao(conn);
	         lists = dao.selectDB(limitPage, listCount, sname, svalue);
	      } catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	      return lists;
	   }
	   
	   //contents ����
	   public BDto getSelectOne() {
	      BDto list = null;
	      try {
	         conn = db.getConnection();
	         JBoardDao dao = new JBoardDao(conn);
	         list = dao.viewDB(getId());
	      } catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	      return list;
	   }
	   
	   //�ڸ�Ʈ ��Ϻ��� 
	   public ArrayList<CDto> getCommentList(String jboard_id) {
	      int jid = Integer.parseInt(jboard_id);
	      ArrayList<CDto> list = null;
	      try {
	         conn = db.getConnection();
	         JBoardCommentDao cdao = new JBoardCommentDao(conn);
	         list = cdao.selectDB(jid);
	      }catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	      return list;
	   }
	   
	   
	   //update (��ȸ�� ����)
	   public void getUpdate(int count) {   
	      int uid = Integer.parseInt(getId());
	      try {
	         conn = db.getConnection();
	         JBoardDao dao = new JBoardDao(conn);
	         dao.updateDB(uid, count, "hit");
	      } catch (SQLException | NamingException e) {
	         e.printStackTrace();
	      } finally {
	         db.closeConnection();
	      }
	   }
	}

