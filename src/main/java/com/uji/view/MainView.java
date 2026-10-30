package com.uji.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public class MainView {
    private final BorderPane rootLayout;
    private final VBox leftPanel;
    private final MenuBar menuBar;
    private final TabPane centerTabPane;
    private final HBox bottomPanel;

    //Parte del tabs
    private final RecommendationsView recommendationsView;
    private final HistoryView historyView;
    private final ClustersView clustersView;

    //Parte de left panel
    private final SongSelectionView songSelectionView;
    private final ConfigView configView;

    //Parte de bottonPanel
    private final Label cantidadLabel;
    private final Label tiempoLabel;
    private final Button recomendarButton;

    //Parte de menuBar
    private final MenuItem acercaDeInfoMenuItem;
    private final MenuItem contactoMenuItem;

    private final MenuItem fondoGrisClaroMenuItem;
    private final MenuItem fondoAzulClaroMenuItem;
    private final MenuItem fondoPredeterminadoMenuItem;
    private final MenuItem fondoVerdeClaroMenuItem;
    private final MenuItem fondoAmarilloClaroMenuItem;
    private final MenuItem fondoRosaClaroMenuItem;

    public MainView() {
        this.rootLayout = new BorderPane();
        this.leftPanel = new VBox();
        this.menuBar = new MenuBar();
        this.centerTabPane = new TabPane();
        this.bottomPanel = new HBox();
        
        //-- Gestion de leftPanel-- 
        this.songSelectionView = new SongSelectionView();
        leftPanel.setPrefWidth(400);
        leftPanel.getChildren().add(songSelectionView.getRootNode());

        this.configView = new ConfigView();
        leftPanel.getChildren().add(configView.getRootNode());

        // -- Gestión de menus --
        Menu aparienciaMenu = new Menu("Apariencia");
        Menu acercaDeMenu = new Menu("Acerca de");
        Menu contactoMenu = new Menu("Contacto");

        Menu temasMenu = new Menu("Temas de Fondo");
        fondoGrisClaroMenuItem = new MenuItem("Fondo Gris Claro");
        fondoAzulClaroMenuItem = new MenuItem("Fondo Azul Claro");
        fondoVerdeClaroMenuItem = new MenuItem("Fondo Verde Claro");
        fondoAmarilloClaroMenuItem = new MenuItem("Fondo Amarillo Claro");
        fondoRosaClaroMenuItem = new MenuItem("Fondo Rosa Claro");
        
        fondoPredeterminadoMenuItem = new MenuItem("Fondo Blanco");
        
        temasMenu.getItems().addAll(fondoGrisClaroMenuItem, fondoAzulClaroMenuItem, 
                                fondoVerdeClaroMenuItem, fondoAmarilloClaroMenuItem, 
                                fondoRosaClaroMenuItem, fondoPredeterminadoMenuItem);

        aparienciaMenu.getItems().add(temasMenu);

        acercaDeInfoMenuItem = new MenuItem("Información de la Aplicación");
        acercaDeMenu.getItems().add(acercaDeInfoMenuItem);

        contactoMenuItem = new MenuItem("Mostrar Información de Contacto");
        contactoMenu.getItems().add(contactoMenuItem);

        this.menuBar.getMenus().addAll(aparienciaMenu, acercaDeMenu, contactoMenu);

        // -- Gestion de CenterTabPane --
        this.recommendationsView = new RecommendationsView();
        this.historyView = new HistoryView();
        this.clustersView = new ClustersView();

        Tab tabRecomendaciones = new Tab("Recomendaciones");
        tabRecomendaciones.setContent(recommendationsView.getRootNode());
        tabRecomendaciones.setClosable(false);

        Tab tabHistorial = new Tab("Historial de canciones");
        tabHistorial.setContent(historyView.getRootNode());
        tabHistorial.setClosable(false);

        Tab tabClusters = new Tab("Clusters");
        Node originalClustersContent = clustersView.getRootNode();

        Label clustersPlaceholder = new Label("La visualización actual de Clusters no es posible, por favor espere a proximas actualizaciones. ");

        clustersPlaceholder.setAlignment(Pos.CENTER);

        StackPane clustersStackPane = new StackPane();
        clustersStackPane.getChildren().addAll(originalClustersContent, clustersPlaceholder);
        StackPane.setAlignment(clustersPlaceholder, Pos.CENTER);

        tabClusters.setContent(clustersStackPane);
        tabClusters.setClosable(false);
        centerTabPane.getTabs().addAll(tabRecomendaciones,tabHistorial,tabClusters);

        //Gestion footer
        this.bottomPanel.setAlignment(Pos.CENTER_LEFT);

        this.cantidadLabel = new Label("");
        this.tiempoLabel = new Label("");

        this.recomendarButton = new Button("Recomendar");
        recomendarButton.setDisable(false);
        recomendarButton.setPadding(new Insets(10));
        HBox espaciador = new HBox();
        HBox.setMargin(recomendarButton, new Insets(10));

        espaciador.setHgrow(espaciador, Priority.ALWAYS);

        this.bottomPanel.getChildren().addAll(cantidadLabel, tiempoLabel,espaciador, recomendarButton);

        //Gestion final
        this.rootLayout.setTop(menuBar);
        this.rootLayout.setLeft(leftPanel);
        this.rootLayout.setCenter(centerTabPane);
        this.rootLayout.setBottom(bottomPanel);
    }

    //Getters
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public VBox getLeftPanel() {
        return leftPanel;
    }

    public TabPane getCenterTabPane() {
        return centerTabPane;
    }

    public HBox getBottomPanel() {
        return bottomPanel;
    }

    public Button getRecomendarButton(){
        return recomendarButton;
    }

    public SongSelectionView getSongSelectionView(){
        return songSelectionView;
    }

    public ConfigView getFiltersAndConfigView() {
        return configView;
    }

    public RecommendationsView getRecommendationsView() {
        return recommendationsView;
    }

    public HistoryView getHistoryView() {
        return historyView;
    }

    public Label getCantidadLabel() {
        return cantidadLabel;
    }

    public Label getTiempoLabel() {
        return tiempoLabel;
    }

    public MenuItem getAcercaDeInfoMenuItem() {
        return acercaDeInfoMenuItem;
    }

    public MenuItem getContactoMenuItem() {
        return contactoMenuItem;
    }

    public MenuItem getFondoGrisClaroMenuItem() {
        return fondoGrisClaroMenuItem;
    }

    public MenuItem getFondoAzulClaroMenuItem() {
        return fondoAzulClaroMenuItem;
    }

    public MenuItem getFondoVerdeClaroMenuItem() {
        return fondoVerdeClaroMenuItem;
    }

    public MenuItem getFondoAmarilloClaroMenuItem() {
        return fondoAmarilloClaroMenuItem;
    }

    public MenuItem getFondoRosaClaroMenuItem() {
        return fondoRosaClaroMenuItem;
    }

    public MenuItem getFondoPredeterminadoMenuItem() {
        return fondoPredeterminadoMenuItem;
    }
}
