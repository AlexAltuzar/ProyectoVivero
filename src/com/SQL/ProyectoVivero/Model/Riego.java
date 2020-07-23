package com.SQL.ProyectoVivero.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Riego {
	Connection conn;
	
	private int id;
	private LocalDate fecha;
	private int productoId;
	private String productoNombre;
	
	public Riego() {
		this.id = 0;
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
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	
	public int getProductoId() {
		return productoId;
	}
	
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	
	public String getProductoNombre() {
		return productoNombre;
	}
	
	public void crearRiego() {
		conn = ConnectDB.getConnection();
		try {

    		String sSQL = "insert into riego (fecha, producto_id) values ('"+fecha+"','"+productoId+"')";
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
	
	public void modificarRiego() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = " update riego set fecha = '"+fecha+"', producto_id = '"+productoId+"' where id = "+id;
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
	public void eliminarRiego() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = " delete from riego where id = "+id;
			System.out.println("SQL " + sSQL);
 			Statement st = conn.createStatement(); 
 			st.executeUpdate(sSQL);
 			
 		}catch (SQLException e) {
				
				e.printStackTrace();
			}
 		conn = ConnectDB.getDesconectar();
	 }
	
}
