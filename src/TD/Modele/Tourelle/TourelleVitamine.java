package TD.Modele.Tourelle;

import TD.Modele.Environnement;
import TD.Modele.Personnage.Personnage;
import TD.Modele.Tir.Tir;
import TD.Modele.Tir.TirVitamine;
import TD.Utilitaire.Position;

import java.util.ArrayList;

public class TourelleVitamine extends Tourelle {
    private int portee;

    public TourelleVitamine(int x, int y, Environnement env) {
        super(x, y, 3, env);
        this.portee = 100;
    }

    @Override
    public void agit() {
	        Personnage p = viser();
	        if(p != null) {
                Position positionCible = new Position(p.getX(), p.getY());
                Tir tir = new TirVitamine(this.getPosition(), positionCible, env, this);
                env.ajouterTir(tir);
            }
    }

    public ArrayList<Personnage> estAPortee() {
        ArrayList<Personnage> persosAPortee = new ArrayList<>();

        for (Personnage p : env.getPersos()) {
            Position positionPersoActuel = new Position(p.getX(), p.getY());
            if (this.getPosition().distance(positionPersoActuel) <= portee) {
                persosAPortee.add(p);
            }
        }
        return persosAPortee;
    }

    public Personnage viser() {
        if (estAPortee().size() == 0) {
            return null;
        }

        Personnage persoPlusProche = estAPortee().get(0);

        for (Personnage p : estAPortee()) {
            Position positionPersoProche = new Position(persoPlusProche.getX(), persoPlusProche.getY());
            Position positionPersoActuel = new Position(p.getX(), p.getY());

            if (this.getPosition().distance(positionPersoProche) > this.getPosition().distance(positionPersoActuel)) {
                persoPlusProche = p;
            }
        }
        return persoPlusProche;
    }

    public int getPortee() {
        return this.portee;
    }

    public void setPortee(int portee) {
        this.portee = portee;
    }
}