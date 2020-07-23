package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Fotografia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class RegistroFotosController implements Initializable{
	private Main main;
	Connection conn;
	
	@FXML
	private BorderPane root;
	
	@FXML
    private ChoiceBox<String> chBox_Tipo;
	@FXML
    private Button btnBorrar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnAgregar;

    
    @FXML
    private ImageView imgAtras;

    @FXML
    void clickAtras(MouseEvent event) {
    	main.getRegistroFotosStage().close();
    }
    
    @FXML
    void clickAgregar(ActionEvent event) {
    	main.abrirVentanaFotos();
    	main.getFotosController2().setAccion("NUEVO");
    }
    
    @FXML
    void clickBorrar(ActionEvent event) {

    }

    @FXML
    void clickModificar(ActionEvent event) {
    	
    }
    
    private class ClickHandler implements EventHandler <ActionEvent>{

		@Override
			public void handle(ActionEvent e) {
			
				if(e.getSource() == chBox_Tipo ) {
					String tipoNombre = chBox_Tipo.getSelectionModel().getSelectedItem();
					int tipoId = getTipoId();
					int cantidad = contarFotografias(tipoId);
					cargarFotografias(tipoNombre, tipoId, cantidad);
				}
			}
		}
    
    public void cargarFotografias(String tipoNombre, int tipoId, int cantidad) {
    	conn = ConnectDB.getConnection();
    	Fotografia foto;
    	try {
    		String sSQL = "select foto from fotografia, tipo t\r\n" + 
    				"where t.id = tipo_id\r\n" + 
    				"and t.nombre = '"+tipoNombre+"'";
    		System.out.println("SQL " + sSQL);		
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery(sSQL);
			while(rs.next()) {
				String url = rs.getString("foto");
				System.out.println(url);
				cargarImagen(url);
			}
    	}catch(Exception e){
    		
    	}
    	//conn = ConnectDB.getDesconectar();
    }
    @FXML
    ImageView img;
    
    public void cargarImagen(String url) {
    	GridPane gridPane = new GridPane();
    	Image image = new Image("C:/Users/JAAG/Pictures/ICONS/arbolLimon.jpg");
    	img = new ImageView();
    	img.setImage(image);
		GridPane.setVgrow(img, Priority.ALWAYS);
		GridPane.setHgrow(img, Priority.ALWAYS);
		gridPane.add(img, 0, 0);
		root.setCenter(gridPane);
		root.setBottom(img);
		/*Scene scene = new Scene(root);
		main.getRegistroFotosStage().setScene(scene);*/
    }
    
    public int contarFotografias(int tipoId) {
    	conn = ConnectDB.getConnection();
    	try {
    		String sSQL = "select count(foto) cantidadFotos from fotografia \r\n" + 
        			"where tipo_id = "+getTipoId()+"\r\n" + 
        			"group by tipo_id";
        	System.out.println("SQL " + sSQL);		
    		Statement st = conn.createStatement(); 
    		ResultSet rs = st.executeQuery(sSQL);
    		rs.next();
    		int cantidad = rs.getInt("cantidadFotos");
    		conn = ConnectDB.getDesconectar();
    		return cantidad;
    	}catch(Exception e) {
    		
    	}
    	conn = ConnectDB.getDesconectar();
    	return 0;
    	
    }
    
    private int tipoId;
    public int getTipoId() {
    	conn = ConnectDB.getConnection();
		try {
			String sSQL = "select id from tipo where nombre = '"+chBox_Tipo.getSelectionModel().getSelectedItem()+"'";
			Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(sSQL);
    		rs.next();
    		int tipoId = rs.getInt("id");
    		return tipoId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = ConnectDB.getDesconectar();
		return 0;
		
	}
    
    
	public void setMain(Main main) {
		this.main = main;
	}
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    obtenerTiposProducto();
		chBox_Tipo.getItems().addAll(tipos);
		chBox_Tipo.setOnAction(new ClickHandler());
	}
}
