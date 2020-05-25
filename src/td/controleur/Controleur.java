package td.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import td.modele.*;
import td.vue.VueMap;
import td.vue.VuePers;
import td.vue.vueTourelle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controleur implements Initializable {

    @FXML
    private TilePane tilePaneMap;
    @FXML
    private Pane panePers;

    private Partie partie;
    private VueMap vM;
    private VuePers vP;
    private vueTourelle vT;
    private Timeline gameLoop;
    
    IntegerProperty nbTour;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.partie = new Partie();
        vM = new VueMap(partie.getEnv().getMap(), tilePaneMap);
        this.partie.getEnv().getTirs().addListener(new TirsListener(panePers));
        initGame();
        this.partie.getEnv().creerArbre();
        this.nbTour = new SimpleIntegerProperty();
        this.nbTour.set(0);
    }
    
    private void initGame() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.05),(ev ->{
			if(this.partie.estPerdu()){
				System.out.println("perdu");
				System.out.println("game over");
				gameLoop.stop();
			}
			else if(this.partie.niveauFini()) {
				System.out.println("Niveau fini");
				gameLoop.stop();
			}
			else {
				this.partie.unTour();
				this.nbTour.set(this.nbTour.getValue() + 1);
			}
		}));
		gameLoop.getKeyFrames().add(kf);
	}
    
	@FXML
    void CreePers(ActionEvent event) {
		this.partie.getEnv().ajouterPers(new InfecteSansSymp(6, 18, this.partie.getEnv()));
    	vP = new VuePers();
    	vP.translateXProperty().bind(this.partie.getEnv().getPersos().get(0).getXProperty());
    	vP.translateYProperty().bind(this.partie.getEnv().getPersos().get(0).getYProperty());
    	this.nbTour.addListener(e -> vP.changerSprite(nbTour.getValue()));
    	this.panePers.getChildren().add(vP);
    }


	@FXML
	void creerTir(ActionEvent event) {

	}

	@FXML
	void creerTourelle(ActionEvent event) {
		Tourelle t = new TourelleVitamine(30, 30, partie.getEnv());
		panePers.getChildren().add(new vueTourelle(t));
		new TirVitamine(t.getX(), t.getY(), 120,  20, partie.getEnv());
	}

    @FXML
    void action(ActionEvent event) {
    	gameLoop.play();
    }
}