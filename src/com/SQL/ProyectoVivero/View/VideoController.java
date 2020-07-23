package com.SQL.ProyectoVivero.View;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.SQL.ProyectoVivero.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoController implements Initializable{
	private Main main;
		
	 @FXML private MediaView media;
	 @FXML private ImageView imgAtras;
	 @FXML private ImageView play;
	 @FXML private ImageView pause;

	 
	 private MediaPlayer mediaPlayer;
	 
	 private File link = new File( "src/video/Proyecto.mp4");
	 
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		
		Media video = new Media(link.toURI().toString());
		mediaPlayer = new MediaPlayer(video);
		media.setMediaPlayer(mediaPlayer);
		play.setOnMouseClicked((MouseEvent) -> mediaPlayer.play());
		pause.setOnMouseClicked((MouseEvent) -> mediaPlayer.pause()); 
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

    @FXML
    void clickAtras(MouseEvent event) {
    	main.getVideoStage().close();
    	mediaPlayer.stop();
    }

}
