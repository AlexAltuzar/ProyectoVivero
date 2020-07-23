package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;
import com.SQL.ProyectoVivero.Model.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable{
	private Main main;
    @FXML private Button iniciar;
    @FXML private ChoiceBox<String> chBox_BD;
    @FXML private TextField user;
    @FXML private PasswordField password;
    @FXML private Button btnVideo;
    
    Connection conn;
    
    
    ObservableList <String> basesDatos = FXCollections.observableArrayList("SQLSERVER", "MYSQL");

    @FXML
    void iniciar_Click() {
    	if(user.getText().isEmpty() || password.getText().isEmpty() || chBox_BD.getSelectionModel().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("CAMPOS VACIOS");
			alert.setContentText("Verifique que el formulario no tenga ningun campo vacio");
			alert.showAndWait();
		}else {
			Usuario usuario = new Usuario();
			usuario.setUser(user.getText());
			usuario.setPassword(password.getText());
			if(usuario.verificarDatos() == true) {
				
				main.abrirVentanaMenu();
			}
    	}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chBox_BD.setItems(basesDatos);
        chBox_BD.setOnAction(new ClickHandler());
    	
    }

	public void setMain(Main main) {
		this.main = main;
		
	}
	
	
	private class ClickHandler implements EventHandler <ActionEvent>{

		@Override
			public void handle(ActionEvent e) {
				if(e.getSource() == chBox_BD ) {
					if(chBox_BD.getSelectionModel().getSelectedItem().equals("SQLSERVER")==true) {
						String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
						String url = "jdbc:sqlserver://localhost:1433;databaseName=Vivero;";
						String user = "JAAG";
						String pass = "jaag2610";
						new ConnectDB(driver,url,user,pass);
						
					}else if(chBox_BD.getSelectionModel().getSelectedItem().equals("MYSQL")==true){
						String driver="com.mysql.jdbc.Driver";
						String url = "jdbc:mysql://192.168.43.144:3306/vivero";
						String user = "erikToala";
						String pass = "1234";
						new ConnectDB(driver,url,user,pass);
					}
				}
			}
		}

    @FXML
    void clickVideo(ActionEvent event) {
    	main.abrirVentanaVideo();
    }
	
}
