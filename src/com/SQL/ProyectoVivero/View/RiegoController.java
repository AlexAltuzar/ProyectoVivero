package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Riego;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class RiegoController implements Initializable{

	private Main main;
	
	@FXML private Label lbTittle;

    @FXML private DatePicker dPFecha;
    
    @FXML private ComboBox<String> combProducto;

    @FXML private Button btnCancelar;

    @FXML private Button btnAceptar;
    
    private int productoId;
    private Riego selectRiego;
    private String accion;
	
	
    private boolean verificarCampos() {
		
		String mensaje = "";
		if (dPFecha.getValue() == null) {
			 	mensaje += "Fecha\n";
		}
		if(combProducto.getSelectionModel().isEmpty()) {
				mensaje += "Producto\n";
		}
		if (!mensaje.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText("Campos Incompletos:");
			alert.setContentText(mensaje);
			alert.show();
			return false;
		}
		return true;		 
	}
	
	@FXML
	public void clickAceptar(ActionEvent event) {
		if (accion.compareTo("NUEVO") == 0) {
			if (verificarCampos()) {
				Riego nuevoRiego = new Riego();
				try {
					nuevoRiego.setFecha(dPFecha.getValue());
					nuevoRiego.setProductoNombre(combProducto.getSelectionModel().getSelectedItem());
					nuevoRiego.setProductoId(getProductoId());
					nuevoRiego.crearRiego();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(accion.compareTo("MODIFICAR") == 0) {
    		if (verificarCampos()) {
    			try {
    				selectRiego.setFecha(dPFecha.getValue());
    				selectRiego.setProductoNombre(combProducto.getSelectionModel().getSelectedItem());
    				selectRiego.setProductoId(getProductoId());
    				selectRiego.modificarRiego();
    			}catch(Exception e) {
	    			e.printStackTrace();
	    		}	
    		}
    	}
		main.getRiegoStage().close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	obtenerProductos();
		combProducto.getItems().addAll(productos);
		
	}
	
	public void setAccion(String accion) {
		this.accion = accion;
		lbTittle.setText(accion);
	}
	
	public void setSelectRiego(Riego riego) {
		this.selectRiego = riego;
	}

	
	public void llenarCampos(Riego riego) {
		dPFecha.setValue(riego.getFecha());
		combProducto.setValue(riego.getProductoNombre());
		
	}
	
	Connection conn;
	private ObservableList<String> productos = FXCollections.observableArrayList();
	private void obtenerProductos() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "select * from producto";
			System.out.println("SQL " + sSQL);
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		while(rs.next()) {
				String productoNombre = rs.getString("nombre");
    			productos.add(productoNombre);
    		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getProductoId() {
		try {
			String sSQL = "select id from producto where nombre = '"+combProducto.getSelectionModel().getSelectedItem()+"'";
			System.out.println("SQL " + sSQL);
			Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		rs.next();
    		productoId = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoId;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	

    @FXML
    void clickCancelar(ActionEvent event) {
    	main.getRiegoStage().close();
    }
	
}
