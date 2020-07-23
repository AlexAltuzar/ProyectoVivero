package com.SQL.ProyectoVivero.Model;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectDB {
	public static Connection conn;
	//IP de Erik en mi casa: 193.168.0.12
	//IP de Erik con su telefono: 192.168.43.144
	private static String driver;
	private static String url;
	private static String user;
	private static String pass;
	
	public ConnectDB(String driver, String url, String user, String pass) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	public static Connection getConnection() {
		if(driver=="com.microsoft.sqlserver.jdbc.SQLServerDriver") {
			try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url,user,pass);
	            /*if (conn!=null)
					System.out.print("Se ha conectado ");*/
			}catch (Exception e) { 
	            Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("ERROR");
	       		alert.setHeaderText("");
	       		alert.setContentText("Verifique que el servicio de SQLSERVER este funcionando correctamente");
	       		alert.showAndWait();
	        }
		}else if(driver=="com.mysql.jdbc.Driver"){
			try {
	            Class.forName(driver);
	            conn = DriverManager.getConnection(url,user,pass);
			}catch (Exception e) { 
	            Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("ERROR");
	       		alert.setHeaderText("");
	       		alert.setContentText("Verifique que el servicio de MySQL este funcionando correctamente");
	       		alert.showAndWait();
	        } 
		}
		return conn;
	}
	
	public static Connection getDesconectar() {
		conn = null;
		/*if (conn == null) {
			System.out.print("Se ha desconectado \n");
		}*/
		return conn;
	}
}
