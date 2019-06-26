package sample.renju.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.renju.view.View;
import sample.renju.model.WinType;
import sample.renju.model.Model;

public class Controller {
    private View view;
    private Model model;

    @FXML
    private Pane mainPane;

    @FXML
    private Group piecesGroup;

    public Controller() {
        view = new View();
        model = new Model();
        view.exitButton.setOnAction(e -> {
            view.stage.close();
            onExit();
        });
        view.playAgainButton.setOnAction(e -> {
            view.stage.close();
            onPlayAgain();
        });
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        Double mouseX = event.getSceneX() / View.TILE_SIZE;
        Double mouseY = event.getSceneY() / View.TILE_SIZE;
        int x = mouseX.intValue();
        int y = mouseY.intValue();
        if(model.checkOccupied(model.getCurrentPlayer(), x, y)) {
            piecesGroup.getChildren().add(view.makePiece(model.getCurrentPlayer(), x, y));
            WinType winType = model.winCheck(model.getCurrentPlayer());
            if(winType == WinType.NONE) model.switchCurrentPlayer();
            String endMessage;
            if(winType == WinType.THREEFORK) {
                endMessage = "Whites won!\nBlacks used 3x3 fork";
                view.endWindow(endMessage);
            }
            if(winType == WinType.FOURFORK) {
                endMessage = "Whites won!\nBlacks used 4x4 fork";
                view.endWindow(endMessage);
            }
            if(winType == WinType.OVERLINE) {
                endMessage = "Whites won!\nBlacks used an overline";
                view.endWindow(endMessage);
            }
            if(winType == WinType.BLACKWIN) {
                endMessage = "Blacks won!";
                view.endWindow(endMessage);
            }
            if(winType == WinType.WHITEWIN) {
                endMessage = "Whites won!";
                view.endWindow(endMessage);
            }
        }
    }

    private void onExit() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }

    private void onPlayAgain() {
        view = new View();
        view.exitButton.setOnAction(e -> {
            view.stage.close();
            onExit();
        });
        view.playAgainButton.setOnAction(e -> {
            view.stage.close();
            onPlayAgain();
        });
        model = new Model();
        piecesGroup.getChildren().clear();
    }
}
