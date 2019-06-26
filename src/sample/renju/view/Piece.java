package sample.renju.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import sample.renju.model.PieceType;

import  static sample.renju.view.View.TILE_SIZE;

class Piece extends StackPane {

    Piece(PieceType type, int x, int y) {
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        Ellipse bg = new Ellipse(TILE_SIZE * 0.4125, TILE_SIZE * 0.4125);
        bg.setFill(type == PieceType.BLACK ? Color.BLACK: Color.WHITE);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(TILE_SIZE * 0.03);

        bg.setTranslateX((TILE_SIZE - TILE_SIZE * 0.4125 * 2)/2);
        bg.setTranslateY((TILE_SIZE - TILE_SIZE * 0.4125 * 2)/2);

        getChildren().addAll(bg);
    }
}
