package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    static ArrayList<ArrayList<String>> players = new ArrayList<>(7);
    /**
     Here's how the ArrayList works:
     It's a two dimensional ArrayList.
     The first dimension holds all the different players.
     The second dimension holds the information of a single player.

     Here's how the second dimension's index works:
     Index 0 holds the name of the player.
     Index 1 holds matches won.
     Index 2 holds matches lost.
     Index 3 holds games won.
     Index 4 hods games lost.
     Index 5 holds points won.
     Index 6 holds points lost.

     To access information, you have to call a specific player, then what information you want.
     For instance, if you want player seven's games lost, you would use:
     > players.get(7).get(4)

     If you wanted to change the player six's points won to 8, you would use:
     > players.get(6).set(5, "8")
     There is also have a method to change values named "ChangeValues()"

     Because this is a string ArrayList, you have to store information as strings.
     However, we can use Integer.parseInt to operate on these values.
     */

    static int matcheswon = 1;
    static int matcheslost = 2;
    static int gameswon = 3;
    static int gameslost = 4;
    static int pointswon = 5;
    static int pointslost = 6;

    //I have to make this a static variable so that it can be reached in any method
    static Stage allplayers;

    //Start of start()
    @Override
    public void start(Stage primaryStage) throws Exception{
        Label info = new Label("Press a button to continue.");

        Button button1 = new Button("Add a player.");
        button1.setOnAction(event->{
            playernames();
            option1();
        });

        Button button2 = new Button("Remove a player.");
        button2.setOnAction(event->{
            playernames();
            option2();
        });

        Button button3 = new Button("Create brackets.");

        Button button4 = new Button("Record a match.");
        button4.setOnAction(event->{
           playernames();
           option4();
        });


        Button button5 = new Button("View a player.");
        button5.setOnAction(event->{
            playernames();
            option5();
        });
        
        Button button6 = new Button("Exit the game.");

        VBox layout = new VBox(info, button1, button2, button4, button5);
        Scene scene = new Scene(layout);
        primaryStage.setTitle("Badminton Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }//End of start

    public static void main(String[] args) {
        launch(args);
    }

    public static void playernames() {
        allplayers = new Stage();
        allplayers.setTitle("Current Players");

        GridPane layout = new GridPane();
        layout.add(new Label("Player Name"), 0, 0);
        int f = 2;
        for(int i =0; i< players.size(); i++){
            Label label = new Label(players.get(i).get(0));
            layout.add(label, 0, i+1);
            f++;
        }
        Button button = new Button("Close window.");
        button.setOnAction(event-> {
            allplayers.close();
        });
        layout.add(button, 0, f);

        Scene scene = new Scene(layout);
        allplayers.setScene(scene);
        allplayers.show();
    }

    /*//Start of getplayers()
    public static void getplayers() {
        allplayers = new Stage();
        allplayers.setTitle("Current Players");
        allplayers.initModality(Modality.APPLICATION_MODAL);
        GridPane gridpane = new GridPane();
        gridpane.setVgap(10);
        gridpane.setHgap(20);

        gridpane.add(new Label("Name"), 0, 0);
        gridpane.add(new Label("Matches Won"), 1, 0);
        gridpane.add(new Label("Matches Lost"), 2, 0);
        gridpane.add(new Label("Games Won"), 3, 0);
        gridpane.add(new Label("Games Lost"), 4, 0);
        gridpane.add(new Label("Points Won"), 5, 0);
        gridpane.add(new Label("Points Lost"), 6, 0);

        for(int i = 0; i < players.size(); i++){
            for(int y = 0; y < 7; y++) {
                Label label = new Label(players.get(i).get(y));

                //Have to use i+1 because i=0 is where the labels are
                gridpane.add(label, y, i+1);
            }
        }

        Scene scene = new Scene(gridpane);
        allplayers.setScene(scene);
        allplayers.show();
    }//End of getplayers()*/

    //Start of option1()
    public static void option1() {
        //New window that must be taken care of by user
        Stage stage = new Stage();
        stage.setTitle("Adding a player...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label name = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit.");

        HBox layout = new HBox(name, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        button.setOnAction(event -> {
                    ArrayList<String> newplayer = new ArrayList<String>(7);
                    newplayer.add(namef.getText());
                    for (int i = 1; i < 7; i++) {
                        newplayer.add("0");
                    }
                    players.add(newplayer);
                    stage.close();
                    allplayers.close();
                    playernames();
                });

    }//End of option1()

    //Start of option2()
    public static void option2() {
        Stage stage = new Stage();
        stage.setTitle("Removing a player...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label name = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit");

        HBox layout = new HBox(name, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        button.setOnAction(event-> {
            for(int i = 0; i < players.size(); i++) {
                if(players.get(i).get(0).equals(namef.getText())){
                    players.remove(players.get(i));
                    stage.close();
                    allplayers.close();
                    playernames();
                    break;
                }
            }
        });

    }//End of option2()

    //public static void option3() {}

    public static void option4() {
        Stage stage = new Stage();
        stage.setTitle("Choose players.");
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        layout.add(new Label("Player One"), 0, 0);
        layout.add(new Label("Player Two"), 1, 0);

        TextField player1 = new TextField();
        TextField player2 = new TextField();
        layout.add(player1, 0, 1);
        layout.add(player2, 1, 1);

        Button button = new Button("Start the Match!");
        layout.add(button, 2, 1);
        button.setOnAction(event ->{
            int i;
            int u;
            for(i = 0; i < players.size(); i++){
                if(players.get(i).get(0).equals(player1.getText())){
                    break;
                }
            }
            for(u = 0; u < players.size(); u++){
                if(players.get(u).get(0).equals(player2.getText())){
                    break;
                }
            }
            int x = i;
            int y = u;
            allplayers.close();
            stage.close();
            match(x, y);
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    //Beginning of match()
    public static void match(int player1, int player2){
        Stage stage = new Stage();
        stage.setTitle("Record a game.");
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        //Labels for player names
        layout.add(new Label(players.get(player1).get(0)), 1, 0);
        layout.add(new Label(players.get(player2).get(0)), 3, 0);
        //Labels for games won/lost
        layout.add(new Label("Games won: "), 0, 1);
        layout.add(new Label("Games won: "), 2, 1);
        //Labels for points won/lost
        layout.add(new Label("Points won: "), 0, 2);
        layout.add(new Label("Points won: "), 2, 2);

        //Textfields
        TextField p1gw = new TextField();
        layout.add(p1gw, 1, 1);
        TextField p2gw = new TextField();
        layout.add(p2gw, 3, 1);
        TextField p1pw = new TextField();
        layout.add(p1pw, 1, 2);
        TextField p2pw = new TextField();
        layout.add(p2pw, 3, 2);

        Button button = new Button("Submit!");
        layout.add(button, 3, 3);
        button.setOnAction(event-> {
            int oldnum;
            int newnum;

            //Player1's games won = Player2's games lost
            oldnum = Integer.parseInt(players.get(player1).get(gameswon));
            newnum = oldnum + Integer.parseInt(p1gw.getText());
            players.get(player1).set(gameswon, String.valueOf(newnum));
            oldnum = Integer.parseInt(players.get(player2).get(gameslost));
            newnum = oldnum + Integer.parseInt(p1gw.getText());
            players.get(player2).set(gameslost, String.valueOf(newnum));

            //Player2's games won = Player1's games lost
            oldnum = Integer.parseInt(players.get(player2).get(gameswon));
            newnum = oldnum + Integer.parseInt(p2gw.getText());
            players.get(player2).set(gameswon, String.valueOf(newnum));
            oldnum = Integer.parseInt(players.get(player1).get(gameslost));
            newnum = oldnum + Integer.parseInt(p2gw.getText());
            players.get(player1).set(gameslost, String.valueOf(newnum));

            //Player1's points won = Player2's points lost
            oldnum = Integer.parseInt(players.get(player1).get(pointswon));
            newnum = oldnum + Integer.parseInt(p1pw.getText());
            players.get(player1).set(pointswon, String.valueOf(newnum));
            oldnum = Integer.parseInt(players.get(player2).get(pointslost));
            newnum = oldnum + Integer.parseInt(p1pw.getText());
            players.get(player2).set(pointslost, String.valueOf(newnum));

            //Player2's points won = Player1's points lost
            oldnum = Integer.parseInt(players.get(player2).get(pointswon));
            newnum = oldnum + Integer.parseInt(p2pw.getText());
            players.get(player2).set(pointswon, String.valueOf(newnum));
            oldnum = Integer.parseInt(players.get(player1).get(pointslost));
            newnum = oldnum + Integer.parseInt(p2pw.getText());
            players.get(player1).set(pointslost, String.valueOf(newnum));

            //Now we check to see who won the match
            int playeronewins = Integer.parseInt(p1gw.getText());
            int playertwowins = Integer.parseInt(p2gw.getText());
            if(playeronewins>playertwowins){
                oldnum = Integer.parseInt(players.get(player1).get(matcheswon));
                newnum = oldnum + 1;
                players.get(player1).set(matcheswon, String.valueOf(newnum));
                oldnum = Integer.parseInt(players.get(player2).get(matcheslost));
                newnum = oldnum + 1;
                players.get(player2).set(matcheslost, String.valueOf(newnum));
            } else{
                oldnum = Integer.parseInt(players.get(player2).get(matcheswon));
                newnum = oldnum + 1;
                players.get(player2).set(matcheswon, String.valueOf(newnum));
                oldnum = Integer.parseInt(players.get(player1).get(matcheslost));
                newnum = oldnum + 1;
                players.get(player1).set(matcheslost, String.valueOf(newnum));
            }

            stage.close();
                });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }//Ending of match()

/*    //Beginning of changeValues()
    public static void changeValues(int playerID, int changethis, int amount){
        int oldnum = Integer.parseInt(players.get(playerID).get(changethis));
        int newnum = oldnum + amount;
        players.get(playerID).set(changethis, String.valueOf(newnum));
    }//Ending of changeValues()*/

    //Start of option5()
    public static void option5(){
        Stage stage = new Stage();
        stage.setTitle("Getting a player summary...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label name = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit.");

        HBox layout = new HBox(name, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        button.setOnAction(event-> {
            for(int i = 0; i < players.size(); i++) {
                if(players.get(i).get(0).equals(namef.getText())){
                    option5player(i);
                    allplayers.close();
                    stage.close();
                    break;
                }
            }
    });

    }//End of option5()

    //Start of option5player()
    public static void option5player(int num){
        Stage stage = new Stage();
        stage.setTitle("Summary of "+players.get(num).get(0));
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane gridpane = new GridPane();
        gridpane.setVgap(10);
        gridpane.setHgap(20);

        gridpane.add(new Label("Name"), 0, 0);
        gridpane.add(new Label("Matches Won"), 1, 0);
        gridpane.add(new Label("Matches Lost"), 2, 0);
        gridpane.add(new Label("Games Won"), 3, 0);
        gridpane.add(new Label("Games Lost"), 4, 0);
        gridpane.add(new Label("Points Won"), 5, 0);
        gridpane.add(new Label("Points Lost"), 6, 0);

        for(int y = 0; y < 7; y++){
            Label label = new Label(players.get(num).get(y));
            gridpane.add(label, y, 1);
        }

        Scene scene = new Scene(gridpane);
        stage.setScene(scene);
        stage.show();
    }//End of option4player()

}
