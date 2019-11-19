package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main extends Application {

    static ArrayList<ArrayList<String>> players = new ArrayList<>();
    /**
     Here's how the ArrayList "players" works:
     It's a two-dimensional ArrayList.
     The first dimension holds the different players.
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

    static int playername = 0;
    static int matcheswon = 1;
    static int matcheslost = 2;
    static int gameswon = 3;
    static int gameslost = 4;
    static int pointswon = 5;
    static int pointslost = 6;
    /**
     These variables are not necessary, but it makes the coding process more intelligible.
     These variables should be used when getting/setting the second dimension of "players".
     For instance, to access player 3's games won, you would use:
     > players.get(3).get(gameswon)

     This is more intelligible than:
     > players.get(3).get(3)
     */

    static ArrayList<ArrayList<String>> matches = new ArrayList<>();
    /**
     Here's how the the ArrayList "matches" works:
     It's also a two-dimensional ArrayList.
     The first dimension holds the different matches played.
     The second dimension holds the players and winner of a match.

     Here's how the second dimension works:
     Index 0 holds player one.
     Index 1 holds player two.
     Index 2 holds the winner.
     */

    static ArrayList<ArrayList<String>> rounds = new ArrayList<>();
    /**
     Here's how the ArrayList "rounds" works:
     It's a two dimensional ArrayList.
     The first dimension hold the matches.
     The second dimension holds the information of a match.

     Here's how the third dimension works:
     Index 0 holds what round the match was played.
     Index 1 holds players one.
     Index 2 holds player two.
     Index 3 holds the winner.
     */


    static int gamemode = 0;
    /**
     The "gamemode" variable refers to round robin or single elimination.
     If gamemode is set to 1, then the tournament is using the round robin format.
     If the gamemode is set to 2, then the tournament is using a single elimination format.
     The user must set this before recording games.
     The user cannot change this after setting it once.
     */

    static Stage allplayers;
    static Stage rrmatches;
    static Stage sematches;
    /**
     These stages are made static so that they can be closed in any method.
     */

    //Start of start()
    @Override
    public void start(Stage primaryStage) throws Exception{
        Label info = new Label("Press a button to continue.");

        Button button1 = new Button("Add a player");
        button1.setOnAction(event->{
            option1();
        });

        Button button2 = new Button("Remove a player");
        button2.setOnAction(event->{
            option2();
        });

        Button button3 = new Button("Create brackets");
        button3.setOnAction(event->{
            option3();
        });

        Button button4 = new Button("Record a match");
        button4.setOnAction(event->{
            option4();
        });


        Button button5 = new Button("View a player");
        button5.setOnAction(event->{
            option5();
        });

        Button button6 = new Button("Exit the game");
        button6.setOnAction(event->{
            primaryStage.close();
        });

        VBox layout = new VBox(info, button1, button2, button3, button4, button5, button6);
        Scene scene = new Scene(layout);
        primaryStage.setTitle("Badminton Game!");
        primaryStage.setScene(scene);
        primaryStage.setWidth(300);
        primaryStage.setX(800);
        primaryStage.setY(300);
        primaryStage.show();
    }//End of start

    public static void main(String[] args) {
        launch(args);
    }

    public static void playernames() {
        allplayers = new Stage();
        allplayers.setTitle("Current Players");
        allplayers.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();
        int f = 2;
        for(int i =0; i< players.size(); i++){
            Label label = new Label(players.get(i).get(playername));
            layout.add(label, 0, i);
            f++;
        }
        Button button = new Button("Close Window");
        button.setOnAction(event-> {
            allplayers.close();
        });
        layout.add(button, 0, f);

        Scene scene = new Scene(layout);
        allplayers.setScene(scene);
        allplayers.setWidth(300);
        allplayers.setX(400);
        allplayers.setY(300);
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
        playernames();

        Stage stage = new Stage();
        stage.setTitle("Adding a player...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label namet = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit");

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

        HBox layout = new HBox(namet, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    }//End of option1()

    //Start of option2()
    public static void option2() {
        playernames();

        Stage stage = new Stage();
        stage.setTitle("Removing a player...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label namet = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit");

        button.setOnAction(event-> {
            for(int i = 0; i < players.size(); i++) {
                if(players.get(i).get(playername).equals(namef.getText())){
                    players.remove(i);
                    stage.close();
                    allplayers.close();
                    playernames();
                    break;
                }
            }
        });

        HBox layout = new HBox(namet, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    }//End of option2()

    //Start of option3()
    public static void option3() {

        switch(gamemode){
            case 0:
                option3g0();
                break;

            case 1:
                option3g1();
                break;

            case 2:
                option3g2();
                break;
        }

    } //End of option3()

    //Start of option3g0()
    public static void option3g0() {
        Stage stage = new Stage();
        stage.setTitle("Creating tournament brackets...");
        GridPane layout = new GridPane();

        Label info = new Label("Which format is this tournament?");
        Label roundrobin = new Label("In a Round Robin format, every " +
                "\nplayer will go against every player.");
        Label singelim = new Label("In a Single Elimination format, " +
                "\nplayers will play until they lose.");

        Button buttonr = new Button("Round Robin");
        buttonr.setOnAction(event->{
            gamemode = 1;
            stage.close();
            option3();
        });

        Button buttons = new Button("Single Elimination");
        buttons.setOnAction(event->{
            gamemode = 2;
            stage.close();
            option3();
        });

        layout.add(info, 1, 0);

        layout.add(roundrobin, 0, 1);
        layout.add(buttonr, 0, 2);

        layout.add(singelim, 2, 1);
        layout.add(buttons, 2, 2);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    } //End of option3g0()

    //Start of option3g()
    public static void option3g1() {
        rrmatches = new Stage();
        rrmatches.setTitle("Creating brackets...");
        rrmatches.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        layout.add(new Label(" Matchups"),1,0);
        layout.add(new Label(" - "), 3, 0);
        layout.add(new Label("Winner"),4,0);

        Button button = new Button("Close Window");
        button.setOnAction(event -> {
            rrmatches.close();
        });

        int position = 1;
        for(int i = 0; i < players.size(); i++){
            for(int p = i+1; p < players.size(); p++){
                String playerone = players.get(i).get(playername);
                Label matchup1 = new Label(playerone);
                layout.add(matchup1, 0, position);

                layout.add(new Label("   versus   "), 1, position);

                String playertwo = players.get(p).get(playername);
                Label matchup2 = new Label(playertwo);
                layout.add(matchup2, 2, position);

                String matchwinner = getwinner(playerone, playertwo);
                Label winner = new Label(matchwinner);
                layout.add(winner, 4, position);

                position++;
            }
        }
        layout.add(button, 0, position);

        Scene scene = new Scene(layout);
        rrmatches.setScene(scene);
        rrmatches.setWidth(300);
        rrmatches.setX(800);
        rrmatches.setY(500);
        rrmatches.show();
    } //End of option3g1()

    //Start of getwinner()
    public static String getwinner(String playerone, String playertwo){
        String give = "TBD";
        for(int d = 0; d < matches.size(); d++) {
            if (playerone.equals(matches.get(d).get(0)) && playertwo.equals(matches.get(d).get(1))) {
                give = matches.get(d).get(2);
                break;
            } else if (playerone.equals(matches.get(d).get(1)) && playertwo.equals(matches.get(d).get(0))) {
                give = matches.get(d).get(2);
                break;
            }
        }
        return give;
    } //End of getwinner()

    public static void option3g2(){
        sematches = new Stage();
        sematches.setTitle("Creating brackets...");
        sematches.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        layout.add(new Label(" Matchups"),1,0);
        layout.add(new Label(" - "), 3, 0);
        layout.add(new Label("Winner"),4,0);

        Button button = new Button("Close Window");
        button.setOnAction(event -> {
            sematches.close();
        });

        int position = 1;
        String original = "0";
        String currentround;
        Collections.sort(rounds, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        for(int r = 0; r < rounds.size(); r++){
            currentround = rounds.get(r).get(0);
            if(!(currentround.equals(original))){
                original = currentround;
                layout.add(new Label("-"),0,position);
                position++;
                layout.add(new Label("Round "+currentround), 0, position);
                position++;
            }

            Label matchup1 = new Label(rounds.get(r).get(1));
            layout.add(matchup1, 0, position);

            layout.add(new Label("   versus   "), 1, position);

            Label matchup2 = new Label(rounds.get(r).get(2));
            layout.add(matchup2, 2, position);

            Label winner = new Label(rounds.get(r).get(3));
            layout.add(winner, 4, position);

            position++;
        }
        layout.add(button, 0, position);

        Scene scene = new Scene(layout);
        sematches.setScene(scene);
        sematches.setWidth(300);
        sematches.setX(800);
        sematches.setY(500);
        sematches.show();
    }

    public static void option4() {
        switch(gamemode){
            case 0:
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert Box.");
                alert.setHeaderText("Error type: Gamemode Error.");
                String contentText = String.format("Please select a gamemode.");
                alert.setContentText(contentText);
                alert.showAndWait();
                break;
            case 1:
                option4g1();
                break;
            case 2:
                option4g2();
                break;
        }
    }


    //Start of option4()
    public static void option4g1() {
        playernames();
        option3g1();

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

            //Searching for player
            int i, u;
            for(i = 0; i < players.size(); i++){
                if(players.get(i).get(playername).equals(player1.getText())){
                    break;
                }
            }
            for(u = 0; u < players.size(); u++){
                if(players.get(u).get(playername).equals(player2.getText())){
                    break;
                }
            }

            //This is testing to see if these players have played together already
            boolean test = true;
            for(int s = 0; s < matches.size(); s++){
                if(players.get(i).get(playername).equals(matches.get(s).get(0)) && players.get(u).get(playername).equals(matches.get(s).get(1))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert Box.");
                    alert.setHeaderText("Error type: Player Match Error.");
                    String contentText = String.format("These players have already played a match together.");
                    alert.setContentText(contentText);
                    alert.showAndWait();
                    test = false;
                } else if(players.get(u).get(playername).equals(matches.get(s).get(0)) && players.get(i).get(playername).equals(matches.get(s).get(1))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alert Box.");
                    alert.setHeaderText("Error type: Player Match Error.");
                    String contentText = String.format("These players have already played a match together.");
                    alert.setContentText(contentText);
                    alert.showAndWait();
                    test = false;
                }
            }

            if(test) {
                match(i, u);
            }

            allplayers.close();
            rrmatches.close();
            stage.close();
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    } //End of option4()

    //Start of option4()
    public static void option4g2() {
        playernames();
        option3g2();

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

            //Searching for player
            int i, u;
            for(i = 0; i < players.size(); i++){
                if(players.get(i).get(playername).equals(player1.getText())){
                    break;
                }
            }
            for(u = 0; u < players.size(); u++){
                if(players.get(u).get(playername).equals(player2.getText())){
                    break;
                }
            }

            boolean test = true;
            if(!(players.get(i).get(matcheslost).equals("0"))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert Box.");
                alert.setHeaderText("Error type: Player Eliminated.");
                String contentText = String.format(players.get(i).get(playername)+" has already been eliminated.");
                alert.setContentText(contentText);
                alert.showAndWait();
                test = false;
            } else if(!(players.get(u).get(matcheslost).equals("0"))){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert Box.");
                alert.setHeaderText("Error type: Player Eliminated.");
                String contentText = String.format(players.get(u).get(playername)+" has already been eliminated.");
                alert.setContentText(contentText);
                alert.showAndWait();
                test = false;
            } else if(!(players.get(i).get(matcheswon).equals(players.get(u).get(matcheswon)))){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert Box.");
                alert.setHeaderText("Error type: Unequal Matchup.");
                String contentText = String.format("These players do not have equal victories.");
                alert.setContentText(contentText);
                alert.showAndWait();
                test = false;
            }

            if(test) {
                match(i, u);
            }

            allplayers.close();
            sematches.close();
            stage.close();
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    } //End of option4()

    //Beginning of match()
    public static void match(int player1, int player2){
        Stage stage = new Stage();
        stage.setTitle("Recording a game...");
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        //Labels for player names
        layout.add(new Label(players.get(player1).get(playername)), 1, 0);
        layout.add(new Label(players.get(player2).get(playername)), 3, 0);
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
            changeValues(player1, gameswon, Integer.parseInt(p1gw.getText()));
            changeValues(player2, gameslost, Integer.parseInt(p1gw.getText()));

            //Player2's games won = Player1's games lost
            changeValues(player2, gameswon, Integer.parseInt(p2gw.getText()));
            changeValues(player1, gameslost, Integer.parseInt(p2gw.getText()));

            //Player1's points won = Player2's points lost
            changeValues(player1, pointswon, Integer.parseInt(p1pw.getText()));
            changeValues(player2, pointslost, Integer.parseInt(p1pw.getText()));

            //Player2's points won = Player1's points lost
            changeValues(player2, pointswon, Integer.parseInt(p2pw.getText()));
            changeValues(player1, pointslost, Integer.parseInt(p2pw.getText()));

            //Now we check to see who won the match
            int playeronewins = Integer.parseInt(p1gw.getText());
            int playertwowins = Integer.parseInt(p2gw.getText());
            ArrayList<String> newwinner = new ArrayList<String>(3);
            if(playeronewins>playertwowins){
                changeValues(player1, matcheswon, 1);
                changeValues(player2, matcheslost, 1);

                //Record this match into matches
                if(gamemode==1) {
                    newwinner.add(players.get(player1).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    newwinner.add(players.get(player1).get(playername));
                    matches.add(newwinner);
                } else if (gamemode==2) {
                    newwinner.add(players.get(player1).get(matcheswon));
                    newwinner.add(players.get(player1).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    newwinner.add(players.get(player1).get(playername));
                    rounds.add(newwinner);
                }
            } else{
                changeValues(player2, matcheswon, 1);
                changeValues(player1, matcheslost, 1);

                //Record this match into matches
                if(gamemode==1) {
                    newwinner.add(players.get(player1).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    matches.add(newwinner);
                } else if (gamemode==2) {
                    newwinner.add(players.get(player2).get(matcheswon));
                    newwinner.add(players.get(player1).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    newwinner.add(players.get(player2).get(playername));
                    rounds.add(newwinner);
                }
            }

            stage.close();
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    }//Ending of match()

    //Beginning of changeValues()
    public static void changeValues(int playerID, int changethis, int amount){
        int oldnum = Integer.parseInt(players.get(playerID).get(changethis));
        int newnum = oldnum + amount;
        players.get(playerID).set(changethis, String.valueOf(newnum));
    }//Ending of changeValues()

    //Start of option5()
    public static void option5(){
        playernames();

        Stage stage = new Stage();
        stage.setTitle("Getting a player summary...");
        stage.initModality(Modality.APPLICATION_MODAL);

        Label namet = new Label("Name: ");
        TextField namef = new TextField();
        Button button = new Button("Submit");

        button.setOnAction(event-> {
            for(int i = 0; i < players.size(); i++) {
                if(players.get(i).get(playername).equals(namef.getText())){
                    option5player(i);
                    allplayers.close();
                    stage.close();
                    break;
                }
            }
        });

        HBox layout = new HBox(namet, namef, button);
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    }//End of option5()

    //Start of option5player()
    public static void option5player(int num){
        Stage stage = new Stage();
        stage.setTitle("Summary of "+players.get(num).get(playername));
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
        stage.setX(1200);
        stage.setY(300);
        stage.show();
    }//End of option5player()

}
