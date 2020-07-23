package com.SQL.ProyectoVivero.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class TipoProducto {
	
	Connection conn;
	private String nombre;
	private int id;
	
	
	public TipoProducto(){
		this.id = 0;
		this.nombre = "";
	}
	 
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 
	public String getNombre() {
		return nombre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void crearTipoProducto() {
		conn = ConnectDB.getConnection();
		try {
	    	String sSQL = "INSERT INTO tipo (nombre)" 
					+ " VALUES ('"+nombre+"')";
			System.out.println("SQL " + sSQL);
			Statement st = conn.createStatement(); 
			st.executeUpdate(sSQL);
	    }catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("DATOS INVALIDOS");
			alert.setContentText("Verifique que los datos ingresados sean los correctos");
			alert.showAndWait();
		}
		conn = ConnectDB.getDesconectar();
	}
	
	public void editarTipoProducto() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "update tipo set nombre = '"+nombre+ "' where id = "+id;
			System.out.println("SQL = "+sSQL);
			Statement st = conn.createStatement(); 
			st.executeUpdate(sSQL);		
		}catch(Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
	}
	
	public void eliminarTipoProducto() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "delete from tipo where id = "+id;
			Statement st = conn.createStatement(); 
			st.executeUpdate(sSQL);
		
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		try {
			String sSQL2 = "delete from producto where tipo_id = '"+id+"'";
			Statement st2 = conn.createStatement(); 
			st2.executeUpdate(sSQL2);
		
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		try {
			String sSQL2 = "delete from riego where tipo_id = '"+id+"'";
			Statement st2 = conn.createStatement(); 
			st2.executeUpdate(sSQL2);
		
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		conn = ConnectDB.getDesconectar();
	}
	
	
}
