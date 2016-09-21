package br.inatel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class AcessoDB {
	public static Connection conexao = null;
	public Statement stmt = null;
	public  ResultSet result;
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
	public static final String JDBC_DATABASE = "mydb";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PASSWORD = "root";
	
	
	public String conectar()  {
            if(conexao!=null) return "DB OK";
            try {
                Class.forName(JDBC_DRIVER);
                        conexao = DriverManager.getConnection(
        		JDBC_URL + JDBC_DATABASE,
        		JDBC_USER,	JDBC_PASSWORD);
            } catch (ClassNotFoundException ex) {
                return "DB ERRO!";
            } catch (SQLException ex) {
                return "DB ERRO!";
            }
            return "DB OK!";
	}
	
    public static void desconectar(Connection conn, PreparedStatement pstm, ResultSet rs) {
    	try {
    		
    		if (rs != null) rs.close();
    		if (pstm != null) pstm.close();
    		if (conn != null) conn.close();

    	} catch (Exception e) {
    		System.out.println("Exce��o, causa:" + e.getClass());
    		e.printStackTrace();
    	}
    }
}
