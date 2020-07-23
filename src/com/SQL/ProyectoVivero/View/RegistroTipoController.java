package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Producto;
import com.SQL.ProyectoVivero.Model.TipoProducto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegistroTipoController implements Initializable{
	private Main main;
	
	Connection conn;
	
	@FXML
    private TableView<TipoProducto> tableTipo;
	
	@FXML
	private TableColumn<TipoProducto, Integer> columnId;

	@FXML
	private TableColumn<TipoProducto, String> columnTipo;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnVer;
    
    @FXML
    private ImageView imgAtras;

    private ObservableList<TipoProducto> tipos = FXCollections.observableArrayList();
    
    
    @FXML
    public void clickVer() {
    	tipos.clear();
    	cargarTabla();
    		
    }
    
    @FXML
    public void clickCrear() {
    	main.abrirVentanaTipo();
    	main.getTipoController2().setAccion("NUEVO");
    }
    
    @FXML
    public void clickModificar() {
    	TipoProducto tipoProducto = tableTipo.getSelectionModel().getSelectedItem();
    	if(tipoProducto!=null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("AVISO");
    		alert.setHeaderText("MODIFICAR TIPO DE PRODUCTO");
    		alert.setContentText("Si continua, cambiara todos los productos del tipo de producto seleccionado");
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			main.abrirVentanaTipo();
        		main.getTipoController2().llenarCampos(tipoProducto);
        		main.getTipoController2().setAccion("MODIFICAR");
        		main.getTipoController2().setTipoSelected(tipoProducto);
    		}
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("AVISO");
			alert.setHeaderText("SELECCIONE UN TIPO DE PRODUCTO");
			alert.setContentText("No hay un tipo de producto seleccionado en la tabla");
			alert.showAndWait();
    	}
    }
    
    @FXML
    public void clickBorrar() {
	    	TipoProducto tipoProducto = tableTipo.getSelectionModel().getSelectedItem();
	    	
	    	if (tipoProducto!= null) {
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("AVISO");
	    		alert.setHeaderText("ELIMINAR TIPO DE PRODUCTO");
	    		alert.setContentText("Si continua, eliminara todos los productos del tipo de producto seleccionado");
	    		Optional<ButtonType> result = alert.showAndWait();
	    		if (result.get() == ButtonType.OK){
	    			tipoProducto.eliminarTipoProducto();
	    		}
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AVISO");
				alert.setHeaderText("SELECCIONE UN TIPO DE PRODUCTO");
				alert.setContentText("No hay un tipo de producto seleccionado en la tabla");
				alert.showAndWait();
	    	}
    	
    }
	
	public void setMain(Main main) {
		this.main = main;
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarTabla();
		
	}
	
	public void cargarTabla() {
		conn = ConnectDB.getConnection();
		TipoProducto tipoProducto;
		try {
			String sSQL = "select * from tipo Order by id";
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery(sSQL);
			
			while(rs.next()) {
				tipoProducto = new TipoProducto();
				tipoProducto.setId(rs.getInt("id"));
				tipoProducto.setNombre(rs.getString("nombre"));
				tipos.add(tipoProducto);
				
			}
		}catch(Exception e) {}
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnTipo.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		tableTipo.setItems(tipos);
		conn = ConnectDB.getDesconectar();
	}

    @FXML
    void clickAtras(MouseEvent event) {
    	main.getRegistroTipoStage().close();
    }
	
	
}
