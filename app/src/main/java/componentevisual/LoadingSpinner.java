/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentevisual;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;

/**
 *
 * @author plope
 */
public class LoadingSpinner extends StackPane {
    private final ProgressIndicator spinner;
    public final BooleanProperty isLoadingProperty = new SimpleBooleanProperty(false);

    public LoadingSpinner() {
        spinner = new ProgressIndicator();
        spinner.setPrefSize(50, 50);
        getChildren().add(spinner);
        visibleProperty().bind(isLoadingProperty);
    }

    public void setLoading(boolean loading) {
        isLoadingProperty.set(loading);
    }
}
