package com.example.dobozok;

import javafx.animation.AnimationTimer;
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
    public int feldolgDb = 0;
    public int letettDb = 0;


    public ImageView arrow;
    public Image boxopen = new Image(getClass().getResourceAsStream("boxopen.png"));
    public Image box = new Image(getClass().getResourceAsStream("box.png"));
    public Image semmi = new Image(getClass().getResourceAsStream("null.png"));

    public Label[][] lbT = new Label[10][15];
    public int[][] intT = new int[10][15];
    public long[][] eletT = new long[10][15];


    public final int NULL = 0;
    public final int OPEN = 1;
    public final int CLOSE = 2;

    public AnimationTimer timer = null;
    public long ido = 0;
    public long most = 0;
    public int angle = 0;

    public void initialize() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 15; x++) {
                int yy = y, xx = x;

                lbT[y][x] = new Label("");
                intT[y][x] = NULL;
                eletT[y][x] = 0;
                lbT[y][x].setGraphic(new ImageView(semmi));

                lbT[y][x].setLayoutX(x*64+10);
                lbT[y][x].setLayoutY(y*64+10);

                lbT[y][x].setOnMouseEntered(e -> lbT[yy][xx].setStyle("-fx-background-color: lightgreen;"));
                lbT[y][x].setOnMouseExited(e -> lbT[yy][xx].setStyle("-fx-background-color: white;"));
                lbT[y][x].setOnMousePressed(e -> onPalyaKatt(yy, xx));

                pane.getChildren().add(lbT[y][x]);
            }
        }

        onClickRand();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (l > ido) {
                    ido = l + 100_000_000;
                    most = l;
                    angle += 36;
                    arrow.setRotate(angle);

                    for (int y = 0; y < 10; y++) for (int x = 0; x < 15; x++) {
                        if (intT[y][x] == OPEN && eletT[y][x] < ido) {
                            eletT[y][x] = 0;
                            lbT[y][x].setGraphic(new ImageView(semmi));
                            intT[y][x] = NULL;

                            feldolgDb++;
                            feldolgLb.setText(feldolgDb + " db");
                        }
                    }
                }

            }
        };
        timer.start();
    }

    public void onClickRand() {
        for (int x = 0; x < 15; x++) {
            int randDoboz = (int)(Math.random()*10);
            for (int y = 9; y >= 0; y--) {
                if (y >= randDoboz) {
                    lbT[y][x].setGraphic(new ImageView(box));
                    intT[y][x] = CLOSE;
                } else {
                    lbT[y][x].setGraphic(new ImageView(semmi));
                    intT[y][x] = NULL;
                }
                eletT[y][x] = 0;
                feldolgDb = 0;
                letettDb = 0;
                feldolgLb.setText("0 db");
                letettLb.setText("0 db");
            }
        }
    }

    private void onPalyaKatt(int y, int x) {
        if (intT[y][x] == CLOSE && (y == 0 || intT[y - 1][x] == NULL)) {
            intT[y][x] = OPEN;
            lbT[y][x].setGraphic(new ImageView(boxopen));
            eletT[y][x] = most + 1_000_000_000;
        } else if (intT[y][x] == NULL && (y < 9 && intT[y + 1][x] == CLOSE) || y == 9) {
            intT[y][x] = CLOSE;
            lbT[y][x].setGraphic(new ImageView(box));

            letettDb++;
            letettLb.setText(letettDb + " db");
        }
    }
}