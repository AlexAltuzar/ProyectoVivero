package com.SQL.ProyectoVivero.Model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Producto {
	
	Connection conn;
	
	private int id;
	private String nombre;
	private LocalDate fechaIngreso;
	private int tipoId;
	private String tipoNombre;
	private int condicionId;
	private String condicionNombre;
	
	
	public Producto() {
		this.id = 0;
		this.nombre = "";
		this.fechaIngreso = null;
		this.tipoId = 0;
		this.tipoNombre = "";
		this.condicionId = 0;
		this.condicionNombre = "";
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}
	
	public int getTipoId() {
		return tipoId;
	}
	
	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}
	
	public String getTipoNombre() {
		return tipoNombre;
	}
	
	public void setCondicionId(int condicionId) {
		this.condicionId = condicionId;
	}
	
	public int getCondicionId() {
		return condicionId;
	}
	
	public void setCondicionNombre(String condicionNombre) {
		this.condicionNombre = condicionNombre;
	}
	
	public String getCondicionNombre() {
		return condicionNombre;
	}
	
	public void crearProducto() {
		conn = ConnectDB.getConnection();
		try {
    		String sSQL = "insert into producto (nombre, fecha_ingreso, tipo_id, condicion_id) values ('"+nombre+"','"+fechaIngreso+"',"+tipoId+","+condicionId+")";
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
	
	public void modificarProducto() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "update producto set nombre = '"+nombre+"', fecha_ingreso = '"+fechaIngreso+"', tipo_id = "+tipoId+", condicion_id = "+condicionId+" where id = "+id;
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
	 public void eliminarProducto() {
		 conn = ConnectDB.getConnection();
		 try {
 			String sSQL = "delete from producto where id = "+id;
 			Statement st = conn.createStatement(); 
 			st.executeUpdate(sSQL);
 			
 		}catch (Exception e) {
				e.printStackTrace();
			}
 		conn = ConnectDB.getDesconectar();
	 }
	 
	 public int getProductoId() {
			try {
				String sSQL = "select id from producto where nombre = '"+nombre+"'";
				System.out.println("SQL " + sSQL);
				Statement st = conn.createStatement();
	    		ResultSet rs = st.executeQuery(sSQL);
	    		rs.next();
	    		id = rs.getInt("id");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return id;
		}
	 

}
