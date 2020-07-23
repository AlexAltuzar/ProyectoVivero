package com.SQL.ProyectoVivero.Model;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Fotografia {
	
	Connection conn;
	
	private int id;
	private String url;
	private LocalDate fecha;
	private String productoNombre;
	private int productoId;
	
	public Fotografia() {
		this.id = 0;
		this.url = "";
		this.fecha = null;
		this.productoId = 0;
		this.productoNombre = "";
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	
	public String getProductoNombre() {
		return productoNombre;
	}
	
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	
	public int getProductoId() {
		return productoId;
	}
	
	public void crearFoto() {
		conn = ConnectDB.getConnection();
		try {
    		String sSQL = "insert into fotografia (foto, fecha, tipo_id) values ('"+url+"','"+fecha+"',"+productoId+")";
    		System.out.println("SQL " + sSQL);
    		Statement st = conn.createStatement();
    		st.executeUpdate(sSQL);
    	}catch(Exception e) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText("DATOS INVALIDOS");
    		alert.setContentText("Verifique que los datos ingresados sean los correctos");
    		alert.showAndWait();
    	}
		conn = ConnectDB.getDesconectar();
	
	}
	
	public void modificarFoto() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "update fotografia set nombre = '"+url+"', fecha_ingreso = '"+fecha+"', tipo_id = "+productoId+" where id = "+id;
    		Statement st = conn.createStatement();
    		System.out.println("SQL" + sSQL);
    		st.executeUpdate(sSQL);
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("DATOS INVALIDOS");
			alert.setContentText("Verifique que los datos ingresados sean los correctos");
			alert.showAndWait();
		}
		conn = ConnectDB.getDesconectar();
	}
	 public void eliminarFoto() {
		 conn = ConnectDB.getConnection();
		 try {
 			String sSQL = "delete from fotografia where id = "+id;
 			Statement st = conn.createStatement(); 
 			st.executeUpdate(sSQL);
 			
 		}catch (Exception e) {
				e.printStackTrace();
			}
 		conn = ConnectDB.getDesconectar();
	 }
}
