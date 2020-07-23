package com.SQL.ProyectoVivero.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Usuario {
	private String user;
	private String password;
	
	Connection conn;
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
    
    public boolean verificarDatos() {
    	conn = ConnectDB.getConnection();
    	if(conn!=null) {
    		try {
    			
        		String sSQL = "select name, password from usuario\r\n" + 
        				"where name = '"+user+"'"
        				+ "and password = '"+password+"'";
        		Statement st = conn.createStatement();
        		ResultSet rs = st.executeQuery(sSQL);
        		rs.next();
        		if(rs.getString(1).equals(user) && rs.getString(2).equals(password)) {
        			conn = ConnectDB.getDesconectar();
    				return true;
    			}
        	}catch(Exception e){
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("ERROR");
    			alert.setHeaderText("CONTRASEÑA O USUARIO INVALIDO");
    			alert.setContentText("Verifique que el usuario o la contraseña ingresados sean los correctos");
    			alert.showAndWait();
        	}
     		conn = ConnectDB.getDesconectar();
    	}
    	
    	return false;
    }
}
