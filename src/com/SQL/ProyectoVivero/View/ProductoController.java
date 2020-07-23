package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Producto;
import com.SQL.ProyectoVivero.Model.TipoProducto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class ProductoController implements Initializable {
	
	private Main main;
	private String accion;
	private Producto selectProducto;
	private int tipoId;
	private int condicionId;

	@FXML private TextField txfNombre;
	@FXML private ComboBox<String> combTipo;
	@FXML private ComboBox<String> combCondicion;
	@FXML private DatePicker dataFechaIngreso;
	@FXML private Button btnCancelar;
	@FXML private Button btnAceptar;
    @FXML private Label lbTittle;
	
	
	private boolean verificarCampos() {
		
		String mensaje = "";
		 if (txfNombre.getText().isEmpty()) {
			 mensaje += "Nombre\n";
		 }
		 if (combCondicion.getSelectionModel().isEmpty()) {
			 mensaje += "Condicion\n";
		 }
		 if (dataFechaIngreso.getValue() == null) {
			 mensaje += "Fecha de ingreso\n";
		 }
		 if(combTipo.getSelectionModel().isEmpty()) {
			 mensaje += "Tipo de producto\n";
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
    					Producto nuevoProducto = new Producto();
    		
    				try {
    					nuevoProducto.setNombre(txfNombre.getText());
    					nuevoProducto.setFechaIngreso(dataFechaIngreso.getValue());
    					nuevoProducto.setTipoNombre(combTipo.getSelectionModel().getSelectedItem());
    					nuevoProducto.setCondicionNombre(combCondicion.getSelectionModel().getSelectedItem());
    					nuevoProducto.setTipoId(getTipoId());
    					nuevoProducto.setCondicionId(getCondicionId());
    					nuevoProducto.crearProducto();
    			
    				}catch(Exception e) {
    					e.printStackTrace();
    				}
    			}
    	}
    	if(accion.compareTo("MODIFICAR") == 0) {
    		if (verificarCampos()) {
    			try {
    				selectProducto.setNombre(txfNombre.getText());
    				selectProducto.setFechaIngreso(dataFechaIngreso.getValue());
    				selectProducto.setTipoNombre(combTipo.getSelectionModel().getSelectedItem());
    				selectProducto.setCondicionNombre(combCondicion.getSelectionModel().getSelectedItem());
					selectProducto.setTipoId(getTipoId());
					selectProducto.setCondicionId(getCondicionId());
    				selectProducto.modificarProducto();
    			}catch(Exception e) {
	    			e.printStackTrace();
	    		}	
    		}
    	}
    	main.getProductoStage().close();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	obtenerTiposProducto();
    	obtenerCondiciones();
		combTipo.getItems().addAll(tipos);
		combCondicion.getItems().addAll(condiciones);
	}

	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setAccion(String accion) {
		this.accion = accion;
		lbTittle.setText(accion);
	}
	
	public void setSelectProducto(Producto producto) {
		this.selectProducto = producto;
	}

	
	public void llenarCampos(Producto producto) {
		txfNombre.setText(producto.getNombre());
		dataFechaIngreso.setValue(producto.getFechaIngreso());
		combCondicion.setValue(producto.getCondicionNombre());
		combTipo.setValue(producto.getTipoNombre());
		
	}
	
	Connection conn;
	private ObservableList<String> tipos = FXCollections.observableArrayList();
	
	private void obtenerTiposProducto() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "select * from tipo";
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		while(rs.next()) {
				String tipoNombre = rs.getString("nombre");
    			tipos.add(tipoNombre);
    		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
	}

	public int getTipoId() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "select id from tipo where nombre = '"+combTipo.getSelectionModel().getSelectedItem()+"'";
			Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		rs.next();
    		tipoId = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
		return tipoId;
	}
	
	
	
	
	private ObservableList<String> condiciones = FXCollections.observableArrayList();
	private void obtenerCondiciones() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "select * from condicion";
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		while(rs.next()) {
				String condicionNombre = rs.getString("nombre");
    			condiciones.add(condicionNombre);
    		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
	}

	public int getCondicionId() {
		conn = ConnectDB.getConnection();
		try {
			String sSQL = "select id from condicion where nombre = '"+combCondicion.getSelectionModel().getSelectedItem()+"'";
			Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		rs.next();
    		condicionId = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
		return condicionId;
	}
	
	
	@FXML
    void clickCancelar(ActionEvent event) {
		main.getProductoStage().close();
    }
}
