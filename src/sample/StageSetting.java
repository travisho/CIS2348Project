package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StageSetting {

    public static void showtime(Stage stage, GridPane layout){
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void setleft(Stage stage){
        stage.setX(400);
        stage.setY(300);
    }

    public static GridPane basicinput(Label namet, TextField namef, Button button){
        GridPane layout = new GridPane();

        layout.add(namet, 0, 0);
        layout.add(namef, 1, 0);
        layout.add(button, 2, 0);

        return layout;
    }

    public static void position(Stage stage, GridPane layout, String pos){
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        int x = 0;
        int y = 0;
        switch(pos){
            case "center":
                x = 800;
                y = 300;
                break;
            case "down":
                x = 800;
                y = 500;
                break;
            case "left":
                x = 400;
                y = 300;
                break;
            case "right":
                x = 1200;
                y = 300;
                break;
        }
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    public static GridPane matchuplabels(){
        GridPane layout = new GridPane();

        layout.setVgap(10);
        layout.setHgap(20);
        layout.add(new Label("Matchups"),1,0);
        layout.add(new Label("-"), 3, 0);
        layout.add(new Label("Winner"),4,0);

        return layout;
    }

    public static void playermatchup(GridPane layout, String playerone, String playertwo, String matchwinner, int position){
        layout.add(new Label(playerone), 0, position);
        layout.add(new Label("versus"), 1, position);
        layout.add(new Label(playertwo), 2, position);
        layout.add(new Label(matchwinner), 4, position);
    }

    public static void deflabels(GridPane layout){
        layout.setVgap(10);
        layout.setHgap(20);
        layout.add(new Label("Name"), 0, 0);
        layout.add(new Label("Matches Won"), 1, 0);
        layout.add(new Label("Matches Lost"), 2, 0);
        layout.add(new Label("Games Won"), 3, 0);
        layout.add(new Label("Games Lost"), 4, 0);
        layout.add(new Label("Points Won"), 5, 0);
        layout.add(new Label("Points Lost"), 6, 0);
    }

}
