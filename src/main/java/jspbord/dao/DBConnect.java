package jspbord.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnect {
	   public Connection conn = null;
	   private InitialContext initContext;

	   public DBConnect() {
	   }

	   public Connection getConnection() throws SQLException, NamingException {
	      if (this.conn == null || this.conn.isClosed()) {
	         this.initContext = new InitialContext();
	         DataSource ds = (DataSource)this.initContext.lookup("java:/comp/env/jdbc/javaboard");
	         this.conn = ds.getConnection();
	         System.out.println("db\uc811\uc18d \uc131\uacf5");
	      }

	      return this.conn;
	   }

	   public void closeConnection() {
	      try {
	         if (this.conn != null && !this.conn.isClosed()) {
	            this.conn.close();
	            System.out.println("db\ub97c \ub2eb\uc558\uc2b5\ub2c8\ub2e4.");
	         }
	      } catch (SQLException var5) {
	         var5.printStackTrace();
	      } finally {
	         this.conn = null;
	      }

	   }
	}