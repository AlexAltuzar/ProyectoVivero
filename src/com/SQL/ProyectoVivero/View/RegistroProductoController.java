package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Producto;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class RegistroProductoController implements Initializable {
	private Main main;
	Connection conn;
	
	@FXML private Button btnVer;
	@FXML private Button btnAgregar;
	@FXML private Button btnModificar;
	@FXML private Button btnBorrar;
	@FXML private TableView<Producto> tableProducto;
	@FXML private TableColumn<Producto, Integer> columnId;
	@FXML private TableColumn<Producto, String> columnNombre;
	@FXML private TableColumn<Producto, String> columnTipo;
	@FXML private TableColumn<Producto, String> columnCondicion;
	@FXML private TableColumn<Producto, Data> columnFecha;
	@FXML private ImageView imgRefresh;
	@FXML
    private ImageView imgAtras;
	
	private ObservableList<Producto> productos = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cargarTabla();
	}
	
	public void cargarTabla() {
		conn = ConnectDB.getConnection();
		Producto producto;
		
		try {
			String sSQL = "select p.id, p.nombre, p.fecha_Ingreso, c.nombre condicion, t.nombre tipo from producto p, tipo t, condicion c "
					+ "where t.id = p.tipo_id and c.id = p.condicion_id";
			
			System.out.println("SQL " + sSQL);		
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery(sSQL);
			
			while(rs.next()) {
				
				producto = new Producto();
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
				producto.setCondicionNombre(rs.getString("condicion"));
				producto.setTipoNombre(rs.getString("tipo"));
				productos.add(producto);
				
				
			}
		}catch(Exception e) {}
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnFecha.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
		columnCondicion.setCellValueFactory(new PropertyValueFactory<>("condicionNombre"));
		columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipoNombre"));
		
		tableProducto.setItems(productos);
		conn = ConnectDB.getDesconectar();
	}
    	

    @FXML
    public void clickAgregar() {
    	main.abrirVentanaProducto();
    	main.getProductoController2().setAccion("NUEVO");
    }

    @FXML
    public void clickBorrar() {
    	
    	Producto producto = tableProducto.getSelectionModel().getSelectedItem();
    	
    	if (producto!= null) {
    		producto.eliminarProducto();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("AVISO");
			alert.setHeaderText("SELECCIONE PRODUCTO");
			alert.setContentText("No hay un producto seleccionado en la tabla");
			alert.showAndWait();
    	}
    }

    @FXML
    public void clickModificar() {
    	Producto producto = tableProducto.getSelectionModel().getSelectedItem();
    	
    	if(producto!= null) {
    		main.abrirVentanaProducto(); ;
    		main.getProductoController2().llenarCampos(producto);
    		main.getProductoController2().setAccion("MODIFICAR");
    		main.getProductoController2().setSelectProducto(producto);
    		
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("AVISO");
			alert.setHeaderText("SELECCIONE PRODUCTO");
			alert.setContentText("No hay un producto seleccionado en la tabla");
			alert.showAndWait();
    	}
    }
    
    @FXML
    public void clickVer() {
    	productos.clear();
    	cargarTabla();
    }

	public void setMain(Main main) {
		this.main = main;
		
	}
	
	@FXML
    void clickAtras(MouseEvent event) {
		main.getRegistroProductoStage().close();
    }

	/*@FXML
    void clickVer(MouseEvent event) {
		
    }*/
}
