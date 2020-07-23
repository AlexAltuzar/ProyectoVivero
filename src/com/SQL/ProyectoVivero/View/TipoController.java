package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.TipoProducto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TipoController {
	private Main main;
	private String accion;
	private TipoProducto tipoSelected;
	@FXML private Label lbTittle;
	@FXML private TextField txFTipo;
	@FXML private Button btnAceptar;
	
	
	
	@FXML
	public void clickAceptar() {
		if(accion.compareTo("NUEVO") == 0) {
			
			if(evaluarCampos()) {
				TipoProducto newTipoProducto = new TipoProducto();
				try {
					newTipoProducto.setNombre(txFTipo.getText());
					newTipoProducto.crearTipoProducto();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		if(accion.compareTo("MODIFICAR") == 0) {
			if(evaluarCampos()) {
				try {
					tipoSelected.setNombre(txFTipo.getText());
					tipoSelected.editarTipoProducto();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		main.getTipoStage().close();
	}
	
	private boolean evaluarCampos() {
		String mensaje = "";
		if (txFTipo.getText().isEmpty()) {

			mensaje += "Tipo\n";
		}
		if(!mensaje.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Aviso");
			alert.setHeaderText("Campos Incompletos:");
			alert.setContentText(mensaje);
			alert.show();
			return false;
		}
		return true;
	}
	
	public void setAccion(String accion) {
		this.accion = accion;
		lbTittle.setText(accion);
	}
	
	public void setTipoSelected(TipoProducto tipoProducto) {
		this.tipoSelected = tipoProducto;
	}
	
	public void llenarCampos(TipoProducto tipoProducto) {
		txFTipo.setText(tipoProducto.getNombre());
	}
	
	@FXML
	public void clickCancelar(ActionEvent event) {
		main.getTipoStage().close();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
