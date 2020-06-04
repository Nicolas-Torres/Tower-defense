package TD.Modele.Tourelle;

import TD.Modele.Environnement;
import TD.Modele.Personnage.Personnage;
import TD.Utilitaire.Position;

public class TourelleSeringue extends TourelleDegatUnique {

    public TourelleSeringue(int x, int y, Environnement env) {
        super(x, y, env, 200, 20);
    }

    @Override
    public void agit() {
        if (delai % 100 == 0) {
            Personnage p = viser();
            if (p != null) {
                Position positionCible = new Position(p.getX() + 8, p.getY() + 8);
                // Tir tir = new TirVitamine(this.getPosition(), positionCible, env, this);
                //   env.ajouterTir(tir);
            }
        }
        delai++;
    }
}