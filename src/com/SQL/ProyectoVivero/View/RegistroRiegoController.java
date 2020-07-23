package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Producto;
import com.SQL.ProyectoVivero.Model.Riego;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegistroRiegoController implements Initializable{
	private Main main;
	Connection conn;
	
	 @FXML
	 private TableView<Riego> tableRiego;

	 @FXML
	 private TableColumn<Riego, Integer> columnId;

	 @FXML
	 private TableColumn<Riego, String> columnProducto;

	 @FXML
	 private TableColumn<Riego, String> columnFecha;

	 @FXML
	 private Button btnVer;

	 @FXML
	 private Button btnAgregar;

	 @FXML
	 private Button btnBorrar;

	 @FXML
	 private Button btnModificar;

	 @FXML
	 private ImageView imgAtras;
	    
	 private ObservableList<Riego> riegos = FXCollections.observableArrayList();

	    
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
			
		 cargarTabla();
	}
	    
	 public void cargarTabla() {
		 conn = ConnectDB.getConnection();
		 Riego riego;
			
		try {
			String sSQL = "select r.id, p.nombre producto, r.fecha from riego r, producto p "
					+ "where p.id = r.producto_id ";
			System.out.println("SQL " + sSQL);		
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery(sSQL);
			
			while(rs.next()) {
				riego = new Riego();
				riego.setId(rs.getInt("id"));
				riego.setProductoNombre(rs.getString("producto"));
				riego.setFecha(rs.getDate("fecha").toLocalDate());
				
				riegos.add(riego);
					
			}
		}catch(Exception e) {}
			
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnProducto.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));
		
			
		tableRiego.setItems(riegos);
		conn = ConnectDB.getDesconectar();
		}

	 	@FXML
	    void clickCrear() {
	    	main.abrirVentanaRiego();
	    	main.getRiegoController2().setAccion("NUEVO");
	    }
	 
	    @FXML
	    void clickBorrar(){
	    	Riego riego = tableRiego.getSelectionModel().getSelectedItem();
	    	if(riego!= null) {
	    		riego.eliminarRiego();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AVISO");
				alert.setHeaderText("SELECCIONE ELEMENTO");
				alert.setContentText("No hay una fecha de riego seleccionado en la tabla");
				alert.showAndWait();
	    	}
	    }

	    @FXML
	    void clickModificar() {
	    	conn = ConnectDB.getConnection();
	    	Riego riego = tableRiego.getSelectionModel().getSelectedItem();
	    	if(riego!= null) {
	    		main.abrirVentanaRiego(); ;
	    		main.getRiegoController2().llenarCampos(riego);
	    		main.getRiegoController2().setAccion("MODIFICAR");
	    		main.getRiegoController2().setSelectRiego(riego);
	    		
	    	} else {
	    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AVISO");
				alert.setHeaderText("SELECCIONE ELEMENTO");
				alert.setContentText("No hay una fecha de riego seleccionado en la tabla");
				alert.showAndWait();
	    	}
	    	conn = ConnectDB.getDesconectar();
	    }

	    @FXML
	    void clickVer() {
	    	riegos.clear();
	    	cargarTabla();
	    }
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	 @FXML
	 void clickAtras(MouseEvent event) {
	    main.getRegistroRiegoStage().close();
	 }
	
	

}
