package TD.Modele.Tir;

import TD.Modele.Environnement;
import TD.Modele.Tourelle.TourelleVitamine;
import TD.Utilitaire.Position;

public class TirVitamine extends TirDirection {

    public TirVitamine(Position cible, Environnement env, TourelleVitamine t) {
        super(6, cible, 10, env, 8, t);
    }
}