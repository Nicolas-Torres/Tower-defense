package TD.Utilitaire;

import javafx.beans.property.SimpleDoubleProperty;

public interface CoordonneProperty {
    SimpleDoubleProperty getXProperty();
    SimpleDoubleProperty getYProperty();

    // DELETE
    double getX();
    double getY();
}