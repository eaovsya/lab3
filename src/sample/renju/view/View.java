package sample.renju.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.renju.model.PieceType;

public class View {

    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    public Stage stage;
    public Button playAgainButton;
    public Button exitButton;

    public View() {
        this.stage = new Stage();
        this.playAgainButton = new Button();
        this.exitButton = new Button();
    }


    public Piece makePiece(PieceType type, int x, int y) {
        return new Piece(type, x, y);
    }

    public void endWindow(String message) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Game over");
        stage.setMinWidth(200);

        Label label = new Label();
        label.setText(message);

        playAgainButton.setText("Play again!");

        exitButton.setText("Exit");


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, playAgainButton,exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 200, 150);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
