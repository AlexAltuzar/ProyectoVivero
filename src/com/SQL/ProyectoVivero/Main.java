package com.SQL.ProyectoVivero;
	
import com.SQL.ProyectoVivero.View.*;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage primaryStage;
	private Stage menuStage;
	private Stage registroTipoStage;
	private Stage fotosStage;
	private Stage reportesStage;
	private Stage tipoStage;
	private Stage registroFotosStage;
	private Stage registroProductoStage;
	private Stage productoStage;
	private Stage registroRiegoStage;
	private Stage riegoStage;
	private Stage videoStage;

	private TipoController tipoController2;
	private ProductoController productoController2;
	private RiegoController riegoController2;
	private FotosController fotosController2;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/Login.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			LoginController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("INICIO DE SESIÓN");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowMenu.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            MenuController menuController = loader.getController();
			menuController.setMain(this);
			Stage menuStage = new Stage();
			Scene scene = new Scene(root);
			this.menuStage = menuStage;
			menuStage.setScene(scene);
			menuStage.setTitle("MENU DEL SISTEMA");
			menuStage.initModality(Modality.WINDOW_MODAL);
			menuStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaRegistroTipo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowRegistroTipo.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            RegistroTipoController registroTipo = loader.getController();
			registroTipo.setMain(this);
            Stage registroTipoStage = new Stage();
			Scene scene = new Scene(root);
			this.registroTipoStage = registroTipoStage;
			registroTipoStage.setTitle("REGISTRO DE TIPO DE PRODUCTOS");
			registroTipoStage.setScene(scene);
			registroTipoStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abriVentanaRegistroProducto() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowRegistroProducto.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			RegistroProductoController registroProductoController = loader.getController();
			registroProductoController.setMain(this);
			Scene scene = new Scene(root);
			Stage registroProductoStage = new Stage();
			registroProductoStage.setTitle("REGISTRO DE PRODUCTOS");
			registroProductoStage.setScene(scene);
			this.registroProductoStage = registroProductoStage;
			registroProductoStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abriVentanaRegistroRiego() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowRegistroRiego.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			RegistroRiegoController registroRiegoController = loader.getController();
			registroRiegoController.setMain(this);
			Scene scene = new Scene(root);
			Stage registroRiegoStage = new Stage();
			registroRiegoStage.setTitle("REGISTRO DE RIEGO");
			registroRiegoStage.setScene(scene);
			this.registroRiegoStage = registroRiegoStage;
			registroRiegoStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaRegistroFotos() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowRegistroFotos.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            RegistroFotosController historialController = loader.getController();
			historialController.setMain(this);
            Stage registroFotosStage = new Stage();
			Scene scene = new Scene(root);
			this.registroFotosStage = registroFotosStage;
			registroFotosStage.setScene(scene);
			registroFotosStage.setTitle("HISTORIAL DE FOTOGRAFIAS");
			registroFotosStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaReportes() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowReportes.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ReportesController reportesController = loader.getController();
			reportesController.setMain(this);
            Stage reportesStage = new Stage();
            this.reportesStage = reportesStage;
			Scene scene = new Scene(root);
			reportesStage.setScene(scene);
			reportesStage.setTitle("REPORTES GRAFICOS");
			reportesStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaTipo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowTipo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			TipoController tipoController = loader.getController();
			this.tipoController2 = tipoController;
			tipoController.setMain(this);
			Stage tipoStage = new Stage();
			this.tipoStage = tipoStage;
			Scene scene = new Scene(root);
			tipoStage.setScene(scene);
			tipoStage.setTitle("TIPO DE PRODUCTO");
			tipoStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void abrirVentanaProducto() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowProducto.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			ProductoController productoController = loader.getController();
			this.productoController2 = productoController;
			productoController.setMain(this);
			Stage productoStage = new Stage();
			this.productoStage = productoStage;
			productoStage.setTitle("PRODUCTO");
			Scene scene = new Scene(root);
			productoStage.setScene(scene);
			productoStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaRiego() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowRiego.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			RiegoController riegoController = loader.getController();
			this.riegoController2 = riegoController;
			riegoController.setMain(this);
			Stage riegoStage = new Stage();
			this.riegoStage = riegoStage;
			riegoStage.setTitle("RIEGO");
			Scene scene = new Scene(root);
			riegoStage.setScene(scene);
			riegoStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaFotos() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowFotos.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FotosController fotosController = loader.getController();
            this.fotosController2 = fotosController;
			fotosController.setMain(this);
            Stage fotosStage = new Stage();
			Scene scene = new Scene(root);
			this.fotosStage = fotosStage;
			fotosStage.setScene(scene);
			fotosStage.setTitle("Foto");
			fotosStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirVentanaVideo() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/WindowVideo.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            VideoController videoController = loader.getController();
			videoController.setMain(this);
            Stage videoStage = new Stage();
			Scene scene = new Scene(root);
			this.videoStage = videoStage;
			videoStage.setScene(scene);
			videoStage.setTitle("PRESENTACIÓN");
			videoStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public TipoController getTipoController2() {
		return tipoController2;
	}
	
	public ProductoController getProductoController2() {
		return productoController2;
	}
	
	public RiegoController getRiegoController2() {
		return riegoController2;
	}
	
	public FotosController getFotosController2() {
		return fotosController2;
	}

	public Stage getMenuStage() {
		return menuStage;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public Stage getTipoStage() {
		return tipoStage;
	}
	
	public Stage getFotosStage() {
		return fotosStage;
	}
	
	public Stage getRegistroTipoStage() {
		return registroTipoStage;
	}
	
	public Stage getReportesStage() {
		return reportesStage;
	}
	
	public Stage getRegistroFotosStage() {
		return registroFotosStage;
	}
	
	public Stage getProductoStage() {
		return productoStage;
	}
	
	public Stage getRegistroProductoStage() {
		return registroProductoStage;
	}
	
	public Stage getRegistroRiegoStage() {
		return registroRiegoStage;
	}
	
	public Stage getRiegoStage() {
		return riegoStage;
	}
	
	public Stage getVideoStage() {
		return videoStage;
	}
	

}
