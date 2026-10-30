package com.uji.controller;

import com.uji.model.models.SongModel;
import com.uji.view.MainView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 * Maneja las acciones de la barra de menu
 */
public class MenuActionController {

    private final MainView mainView;

    public MenuActionController(MainView mainView, SongModel songModel) {
        this.mainView = mainView;

    }

    public void setupMenuListeners() {

        // Configura acerca de
        MenuItem acercaDeItem = mainView.getAcercaDeInfoMenuItem();
        acercaDeItem.setOnAction(event -> PonerAcercaDeInfo());
            
        

        // Configurar contacto
        MenuItem contactoItem = mainView.getContactoMenuItem();
        contactoItem.setOnAction(event -> ponerContactoInfo());
        
        // Configurar Temas de Fondo
        setupTemasDeFondoListeners();

    }
    
    private void setupTemasDeFondoListeners(){
        MenuItem fondoGrisItem = mainView.getFondoGrisClaroMenuItem();
        if (fondoGrisItem != null) {
            fondoGrisItem.setOnAction(event -> aplicarFondoEscena(Color.LIGHTGRAY));
        }
        MenuItem fondoAzulItem = mainView.getFondoAzulClaroMenuItem();
        if (fondoAzulItem != null) {
            fondoAzulItem.setOnAction(event -> aplicarFondoEscena(Color.LIGHTBLUE));
        }
        MenuItem fondoVerdeItem = mainView.getFondoVerdeClaroMenuItem();
        if (fondoVerdeItem != null) {
            fondoVerdeItem.setOnAction(event -> aplicarFondoEscena(Color.LIGHTGREEN));
        }
        MenuItem fondoAmarilloItem = mainView.getFondoAmarilloClaroMenuItem();
        if (fondoAmarilloItem != null) {
            fondoAmarilloItem.setOnAction(event -> aplicarFondoEscena(Color.LIGHTYELLOW));
        }
        MenuItem fondoRosaItem = mainView.getFondoRosaClaroMenuItem();
        if (fondoRosaItem != null) {
            fondoRosaItem.setOnAction(event -> aplicarFondoEscena(Color.LIGHTPINK));
        }
        MenuItem fondoBlancoItem = mainView.getFondoPredeterminadoMenuItem();
        if (fondoBlancoItem != null) {
            fondoBlancoItem.setOnAction(event -> aplicarFondoEscena(Color.WHITE));
        }
    }

    private void PonerAcercaDeInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca del recomendador");
        alert.setHeaderText("Información y Próximas Mejoras");

        String content = "Desarrollado por Robert Valentín Moldovanu y Daniel Salafranca López \n\n" +
                         "Funcionalidades Actuales:\n" +
                         "- Recomendación de canciones (KMeans, KNN)\n" +
                         "- Selección de métrica de distancia\n" +
                         "- Historial de canciones base\n" +
                         "- Implementación de Temas\n\n" +
                         "Próximas Actualizaciones Planeadas:\n" +
                         "- Visualización detallada de Clusters\n" +
                         "- Visualización de Estadísticas de canciones \n";

        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        alert.getDialogPane().setContent(textArea);
        alert.setResizable(true);
        alert.showAndWait();
    }

    private void ponerContactoInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Autores");
        alert.setContentText(
            "Robert Valentín Moldovanu\n" +
            "Daniel Salafranca López\n\n" +
            "Proyecto de Programación Avanzada (Universitat Jaume I).");
        alert.showAndWait();
    }

    private void aplicarFondoEscena(Color color) {
        Scene scene = mainView.getRootLayout().getScene();
        scene.setFill(color);

        mainView.getRootLayout().setBackground(null);
        mainView.getLeftPanel().setBackground(null);
        mainView.getCenterTabPane().setBackground(null);
        mainView.getBottomPanel().setBackground(null);
    }

} 