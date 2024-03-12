package com.example.dobozok;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {

    public Pane pane;
    public Label feldolgLb;
    public Label letettLb;

    public Image boxopen = new Image(getClass().getResourceAsStream("boxopen.png"));
    public Image box = new Image(getClass().getResourceAsStream("box.png"));
    public Image semmi = new Image(getClass().getResourceAsStream("null.png"));

    public Label[][] lbT = new Label[10][15];
    public int[][] intT = new int[10][15];


    public final int NULL = 0;
    public final int OPEN = 1;
    public final int CLOSE = 2;


    public void initialize() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 15; x++) {
                int yy = y, xx = x;

                lbT[y][x] = new Label("");
                intT[y][x] = NULL;
                lbT[y][x].setGraphic(new ImageView(semmi));

                lbT[y][x].setLayoutX(x*64+10);
                lbT[y][x].setLayoutY(y*64+10);

                lbT[y][x].setOnMouseEntered(e -> lbT[yy][xx].setStyle("-fx-background-color: lightgreen;"));
                lbT[y][x].setOnMouseExited(e -> lbT[yy][xx].setStyle("-fx-background-color: white;"));
                lbT[y][x].setOnMousePressed(e -> onPalyaKatt(yy, xx));

                pane.getChildren().add(lbT[y][x]);
            }
        }
    }

    private void onPalyaKatt(int y, int x) {
    }

    public void onClickRand() {
    }
}