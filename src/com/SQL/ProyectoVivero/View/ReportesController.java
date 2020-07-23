package com.SQL.ProyectoVivero.View;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.Main;
import com.SQL.ProyectoVivero.Model.ConnectDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ReportesController implements Initializable {
	Main main;
	
	Connection conn;
	
	@FXML
    private BarChart<String, Integer> reportes;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Button btnR1;

    @FXML
    private Button btnR2;

    @FXML
    private Button btnR3;

    @FXML
    private Button btnR4;

    @FXML
    private Label lb1;
    
    @FXML
    void clickBtnR() throws Exception{
    	conn = ConnectDB.getConnection();
    	btnR1.setOnAction(event-> {
    		lb1.setText("CANTIDAD DE PRODUCTOS POR TIPO DE PRODUCTO");
    		try {
    			Statement st = conn.createStatement();
    			String sSQL = "select t.nombre, count(*) cantidadProductos from tipo t, producto p\r\n" + 
    					"where t.id = p.tipo_id\r\n" + 
    					"group by t.nombre";
    			System.out.println("SQL " + sSQL);
    			ResultSet rs = st.executeQuery(sSQL);
    			XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    			reportes.getData().clear();
        		xAxis.getCategories().clear();
    			dataSeries1.getData().clear();
    			ObservableList<String> leyenda =  FXCollections.observableArrayList();
    			while (rs.next()){
    				leyenda.add(""+rs.getString(1)); // 
    				dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));      
    			}
    			xAxis.setCategories(leyenda); 
    			reportes.getData().addAll(dataSeries1);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	});
    	btnR2.setOnAction(event -> {
    		lb1.setText("CANTIDAD DE RIEGOS POR DIA");
    		try {
    			Statement st = conn.createStatement();
    			String sSQL = "select dia, count(*) cantidadDia from calendario c, riego r\r\n" + 
    					"where c.dia = DATEPART(DW, fecha)\r\n" + 
    					"group by dia";
    			System.out.println("SQL " + sSQL);
    			ResultSet rs = st.executeQuery(sSQL);
    			XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    			ObservableList<String> leyenda =  FXCollections.observableArrayList();
    			reportes.getData().clear();
        		xAxis.getCategories().clear();
    			dataSeries1.getData().clear();
    			while (rs.next()){
    				leyenda.add(""+rs.getString(1)); // 
    				dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));      
    			}
    			xAxis.setCategories(leyenda); 
    			reportes.getData().addAll(dataSeries1);
    		}catch (Exception e) {}
    		
    		try {
    			Statement st = conn.createStatement();
    			String sSQL = "SELECT dia, COUNT(*) from calendario c, riego r\r\n" + 
    					"where dia = DAYOFWEEK(fecha)\r\n" + 
    					"group by dia";
    			System.out.println("SQL " + sSQL);
    			ResultSet rs = st.executeQuery(sSQL);
    			XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    			ObservableList<String> leyenda =  FXCollections.observableArrayList();
    			reportes.getData().clear();
    			xAxis.getCategories().clear();
    			dataSeries1.getData().clear();
    			while (rs.next()){
    				leyenda.add(""+rs.getString(1)); // 
    				dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));      
    			}
    			xAxis.setCategories(leyenda); 
    			reportes.getData().addAll(dataSeries1);
    		}catch (Exception e) {}
    	});
    	btnR3.setOnAction(event -> {
    		lb1.setText("CANTIDAD DE PRODUCTOS POR CONDICION ACTUAL");
    		try {
    			Statement st = conn.createStatement();
    			String sSQL = "select c.nombre, count(*) cantidadProductos from condicion c, producto p "
    					+" where c.id = p.condicion_id" 
    					+" group by c.nombre";
    			System.out.println("SQL " + sSQL);
    			ResultSet rs = st.executeQuery(sSQL);
    			XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    			ObservableList<String> leyenda =  FXCollections.observableArrayList();
    			reportes.getData().clear();
        		xAxis.getCategories().clear();
    			dataSeries1.getData().clear();
    			while (rs.next()){
    				leyenda.add(""+rs.getString(1)); // 
    				dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));      
    			}
    			xAxis.setCategories(leyenda); 
    			reportes.getData().addAll(dataSeries1);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	});
    	btnR4.setOnAction(event -> {
    		
    		lb1.setText("CANTIDAD DE FOTOGRAFIAS POR TIPO DE PRODUCTO");
    		try {
    			Statement st = conn.createStatement();
    			String sSQL = "select t.nombre, count(foto) cantidadFotos from fotografia f, tipo t \r\n" + 
    					"where t.id = f.tipo_id\r\n" + 
    					"group by t.nombre";
    			System.out.println("SQL " + sSQL);
    			ResultSet rs = st.executeQuery(sSQL);
    			XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    			ObservableList<String> leyenda =  FXCollections.observableArrayList();
    			reportes.getData().clear();
        		xAxis.getCategories().clear();
    			dataSeries1.getData().clear();
    			while (rs.next()){
    				leyenda.add(""+rs.getString(1)); // 
    				dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2)));      
    			}
    			xAxis.setCategories(leyenda); 
    			reportes.getData().addAll(dataSeries1);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	});
    }	
    
    
	public void setMain(Main main) {
		this.main = main;
		
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		try {
			clickBtnR();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
	
	@FXML
    void clickAtras(MouseEvent event) {
    	main.getReportesStage().close();
    }

}
