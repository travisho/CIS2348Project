package sample;

import javafx.scene.control.Alert;

public class AlertType {

    public static void helpme(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Help Box.");
        alert.setHeaderText("This is a Badminton tournament creator. You can choose select bracket styles, " +
                "\nadd players, remove players, record matches, inspect players, and generate a report.");
        String contentText = String.format("Note: You can only set the gamemode once through \"Create brackets\"  Round Robin players only play against each other once. Single Elimination players are eliminated after losing a match, and can only play against players with equal match victories.");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void errorname(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Box.");
        alert.setHeaderText("Error type: Name Error.");
        String contentText = String.format("A player already has this name.");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void errorgamemode(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Box.");
        alert.setHeaderText("Error type: Gamemode Error.");
        String contentText = String.format("Please select a gamemode through \"Create brackets\".");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void eliminatedplayer(String playername){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Box.");
        alert.setHeaderText("Error type: Player Eliminated.");
        String contentText = String.format(playername+" has already been eliminated.");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void duplicatematch(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Box.");
        alert.setHeaderText("Error type: Player Match Error.");
        String contentText = String.format("These players have already played a match together.");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void errormatchup(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert Box.");
        alert.setHeaderText("Error type: Unequal Matchup.");
        String contentText = String.format("These players do not have equal match victories.");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
