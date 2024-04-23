package jsboardService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletContext;

import jspbord.dao.DBConnect;
import jspbord.dao.JBoardImgDao;
import dto.ImgDto;

public class TrashFile {
	   
		private static final Logger logger = Logger.getLogger(TrashFile.class.getName());
		private ServletContext context;
		
		public TrashFile(ServletContext cont) throws SQLException, NamingException {
		   this.context = cont;
		   cleanupFiles();
		}	
		
		private void cleanupFiles() {
		   try(Connection conn = new DBConnect().getConnection()){
			   JBoardImgDao idao = new JBoardImgDao(conn);
			   ArrayList<ImgDto> iList = idao.selectDB("jboard_id", "0");
			   if(!iList.isEmpty()) {
				   for(ImgDto dto : iList) {
					   deleteFile(dto.getNfilename());  //���� ����
					   idao.deleteDB("nfilename", dto.getNfilename()); //db����
				   }
			   }
		   }catch(SQLException | NamingException e) {
			   logger.log(Level.SEVERE, "�������� ���� �� ����", e);   
		   }   
		}
		
		private void deleteFile(String filename) {
			//������
			
			String realPath = context.getRealPath("/uploads");
			String filePath = realPath + File.separator + filename;
			File file = new File(filePath);
			//������ �����ϴ��� Ȯ���� ��, �����ϸ� ����
			if(file.exists() && !file.isDirectory()) {
				if(file.delete()) {
					logger.info("���� ���� ���� :"+filePath);
				}else {
					logger.info("���� ���� ���� :" + filePath);
				}
			}
	 
		}
		
	}
