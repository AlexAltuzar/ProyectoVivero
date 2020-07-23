package com.SQL.ProyectoVivero.View;

import java.sql.Connection;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class MenuController {
	private Main main;

	@FXML private RadioButton rBTipo;
    @FXML private RadioButton rBHistorial;
    @FXML private RadioButton rBRiego;
    @FXML private RadioButton rBProducto;
    @FXML private RadioButton rBReportes;
    @FXML private ToggleGroup group;
    @FXML private Button btnClose;
    @FXML private Button btnAceptar;
    @FXML private ImageView imgView;
    
    @FXML private void clickAceptar() {
    	if(rBTipo.isSelected() == true) {
			main.abrirVentanaRegistroTipo();
		}
		if(rBProducto.isSelected() == true) {
			main.abriVentanaRegistroProducto();
		}
		if(rBRiego.isSelected() == true) {
			main.abriVentanaRegistroRiego();
		}
		if(rBHistorial.isSelected() == true) {
			main.abrirVentanaRegistroFotos();
		}
		if(rBReportes.isSelected() == true) {
			main.abrirVentanaReportes();
		}
    }
    
    Connection conn;
    @FXML private void clickCerrar() {
    	main.getMenuStage().close();
    	main.start(main.getPrimaryStage());
    	conn = ConnectDB.getDesconectar();
    }
    
	public void setMain(Main main) {
		this.main = main;
	}

}
