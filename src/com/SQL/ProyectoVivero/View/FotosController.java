package com.SQL.ProyectoVivero.View;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Fotografia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class FotosController implements Initializable{
	private Main main;
	Connection conn;
	private String accion;
	private int tipoId;
	private Fotografia selectFoto;
	
	@FXML
    private Button btnAceptar;

    @FXML
    private TextField txFFoto;

    @FXML
    private DatePicker dPFecha;
    
    @FXML
    private ImageView imgPrueba;
    
    @FXML
    private ComboBox<String> cmbTipo;
    
    FileChooser fileChooser = new FileChooser();
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML 
	private void guardarFoto() {
		if (accion.compareTo("NUEVO") == 0) {
			if (verificarCampos()) {
				Fotografia nuevaFoto = new Fotografia();
				try {
					nuevaFoto.setUrl(txFFoto.getText());
					nuevaFoto.setFecha(dPFecha.getValue());
					nuevaFoto.setProductoNombre(cmbTipo.getSelectionModel().getSelectedItem());
					nuevaFoto.setProductoId(getTipoId());
					nuevaFoto.crearFoto();
				}catch(Exception e) {
					
				}
			}
		}
		if (accion.compareTo("MODIFICAR") == 0) {
			if (verificarCampos()) {
				try {
					selectFoto.setUrl(txFFoto.getText());
					selectFoto.setFecha(dPFecha.getValue());
					selectFoto.setProductoNombre(cmbTipo.getSelectionModel().getSelectedItem());
					selectFoto.setProductoId(getTipoId());
					selectFoto.modificarFoto();
				}catch(Exception e) {
					
				}
			}
		}
		main.getFotosStage().close();
	}
	
	@FXML 
	private void buscarImagen() {
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(main.getFotosStage());
		txFFoto.setText("File:" + file.getAbsolutePath());
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    obtenerTiposProducto();
		cmbTipo.getItems().addAll(tipos);
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public void setSelectFoto(Fotografia foto) {
		this.selectFoto = foto;
	}
	
	public void llenarCampos(Fotografia foto) {
		txFFoto.setText(foto.getUrl());
		dPFecha.setValue(foto.getFecha());
		cmbTipo.setValue(foto.getProductoNombre());
		
	}
	
	private boolean verificarCampos() {
		String mensaje = "";
		if (txFFoto.getText().isEmpty()) {
			mensaje += "url\n";
		}
		if (dPFecha.getValue() == null) {
			mensaje += "Fecha de la foto\n";
		}
		if(cmbTipo.getSelectionModel().isEmpty()) {
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
			String sSQL = "select id from tipo where nombre = '"+cmbTipo.getSelectionModel().getSelectedItem()+"'";
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
	
	@FXML
    void clickCancelar() {
		main.getFotosStage().close();
    }
}
