package sample;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {

    static ArrayList<Player> players = new ArrayList<>();

    static ArrayList<Match> matches = new ArrayList<>();

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
    static AlertType alerttype = new AlertType();
    static StageSetting stagesetting = new StageSetting();

    //Start of start()
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Badminton Game!");

        Label info = new Label("Press a button to continue.     ");

        Button button1 = new Button("Add a player");
        button1.setOnAction(event->{option1(); });

        Button button2 = new Button("Remove a player");
        button2.setOnAction(event->{ option2(); });

        Button button3 = new Button("Create brackets");
        button3.setOnAction(event->{ option3(); });

        Button button4 = new Button("Record a match");
        button4.setOnAction(event->{ option4(); });

        Button button5 = new Button("View a player");
        button5.setOnAction(event->{ option5(); });

        Button button6 = new Button("Exit the game");
        button6.setOnAction(event->{
            option6();
            primaryStage.close();
        });

        Button help = new Button("Help");
        help.setOnAction(event->{ alerttype.helpme(); });

        GridPane layout = new GridPane();
        layout.add(info, 0, 0);
        layout.add(help, 1, 0);
        layout.add(button1, 0, 1);
        layout.add(button2, 1, 1);
        layout.add(button3, 0, 2);
        layout.add(button4, 1, 2);
        layout.add(button5, 0, 3);
        layout.add(button6, 1, 3);

        primaryStage.setWidth(300);
        stagesetting.position(primaryStage, layout, "center");
    }//End of start()

    public static void main(String[] args) {
        launch(args);
    }


    //Start of playernames()
    public static void playernames() {
        allplayers = new Stage();
        allplayers.initModality(Modality.APPLICATION_MODAL);
        allplayers.setTitle("Current Players");

        GridPane layout = new GridPane();
        int f = 0;
        for(int i = 0; i< players.size(); i++){
            Label label = new Label(players.get(i).name);
            layout.add(label, 0, f);
            f++;
        }
        Button button = new Button("Exit");
        button.setOnAction(event-> { allplayers.close(); });
        layout.add(button, 0, f);

        allplayers.setWidth(300);
        stagesetting.setleft(allplayers);
        stagesetting.showtime(allplayers, layout);
    } //End of playernames()

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
            String uinput = namef.getText();
            boolean check = true;
            for(int c = 0; c < players.size(); c++){
                if(compare(players.get(c).name, uinput)){
                    check = false;
                }
            }

            if(check) {
                Player newplayer = new Player(uinput);
                players.add(newplayer);
            } else{
                alerttype.errorname();
            }

            stage.close();
            allplayers.close();
            playernames();
        });

        GridPane layout = stagesetting.basicinput(namet, namef, button);
        stage.setWidth(300);
        stagesetting.position(stage, layout, "right");
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
            int removeid = findplayer(namef.getText());
            players.remove(removeid);
            stage.close();
            allplayers.close();
            playernames();
        });

        GridPane layout = stagesetting.basicinput(namet, namef, button);
        stage.setWidth(300);
        stagesetting.position(stage, layout, "right");
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

        stagesetting.position(stage, layout, "right");
    } //End of option3g0()

    //Start of option3g()
    public static void option3g1() {
        rrmatches = new Stage();
        rrmatches.setTitle("Creating brackets...");
        rrmatches.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = stagesetting.matchuplabels();

        Button button = new Button("Exit");
        button.setOnAction(event -> {
            rrmatches.close();
        });

        int position = 1;
        for(int i = 0; i < players.size(); i++){
            for(int p = i+1; p < players.size(); p++){
                String playerone = players.get(i).name;
                String playertwo = players.get(p).name;
                String matchwinner = getwinner(playerone, playertwo);
                stagesetting.playermatchup(layout, playerone, playertwo, matchwinner, position);
                position++;
            }
        }
        layout.add(button, 0, position);
        stagesetting.position(rrmatches, layout, "down");
    } //End of option3g1()

    //Start of getwinner()
    public static String getwinner(String playerone, String playertwo){
        String give = "TBD";
        for(int d = 0; d < matches.size(); d++) {
            if(matches.get(d).player1.equals(playerone) && matches.get(d).player2.equals(playertwo)){
                give = matches.get(d).winner;
            } else if(matches.get(d).player2.equals(playerone) && matches.get(d).player1.equals(playertwo)){
                give = matches.get(d).winner;
            }
        }
        return give;
    } //End of getwinner()

    //Start of option3g2()
    public static void option3g2(){
        sematches = new Stage();
        sematches.setTitle("Creating brackets...");
        sematches.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = stagesetting.matchuplabels();

        Button button = new Button("Exit");
        button.setOnAction(event -> {
            sematches.close();
        });

        int position = 1;
        int highestround = 0;
        for(int h = 0; h < matches.size(); h++){
            if(matches.get(h).round > highestround){
                highestround = matches.get(h).round;
            }
        }
        for(int hr = 1; hr <= highestround;  hr++) {
            layout.add(new Label("Round "+hr), 0, position);
            position++;
            for(int i = 0; i < matches.size(); i++){
                if(matches.get(i).round == hr){
                    stagesetting.playermatchup(layout, matches.get(i).player1, matches.get(i).player2, matches.get(i).winner, position);
                    position++;
                }
            }
        }
        layout.add(button, 0, position);
        stagesetting.position(sematches, layout, "down");
    }//End of option3g2()

    //Start of option4()
    public static void option4() {
        switch(gamemode){
            case 0:
                alerttype.errorgamemode();
                break;
            case 1:
                option4g1();
                break;
            case 2:
                option4g2();
                break;
        }
    } //End of option4()

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
            int i = findplayer(player1.getText());
            int u = findplayer(player2.getText());
            //This is testing to see if these players have played together already
            boolean test = true;
            for(int s = 0; s < matches.size(); s++){
                if(compare(players.get(i).name, matches.get(s).player1) && compare(players.get(u).name, matches.get(s).player2)){
                    alerttype.duplicatematch();
                    test = false;
                } else if(compare(players.get(u).name, matches.get(s).player1) && compare(players.get(i).name, matches.get(s).player2)) {
                    alerttype.duplicatematch();
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

        stagesetting.position(stage, layout, "right");
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
            int player1index = findplayer(player1.getText());
            int player2index = findplayer(player2.getText());

            boolean test = true;
            if(players.get(player1index).matcheslost != 0) {
                alerttype.eliminatedplayer(player1.getText());
                test = false;
            } else if(players.get(player2index).matcheslost != 0){
                alerttype.eliminatedplayer(player2.getText());
                test = false;
            } else if(players.get(player1index).matcheswon != players.get(player2index).matcheswon){
                alerttype.errormatchup();
                test = false;
            }

            if(test) {
                match(player1index, player2index);
            }

            allplayers.close();
            sematches.close();
            stage.close();
        });

        stagesetting.position(stage, layout, "right");
    } //End of option4()

    //Beginning of match()
    public static void match(int player1, int player2){
        Stage stage = new Stage();
        stage.setTitle("Recording a game...");
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        //Labels for player names
        layout.add(new Label(players.get(player1).name), 1, 0);
        layout.add(new Label(players.get(player2).name), 3, 0);
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

            //Player1's games won = Player2's games lost
            players.get(player1).gameswon += Integer.parseInt(p1gw.getText());
            players.get(player2).gameslost += Integer.parseInt(p1gw.getText());

            //Player2's games won = Player1's games lost
            players.get(player2).gameswon += Integer.parseInt(p2gw.getText());
            players.get(player1).gameslost += Integer.parseInt(p2gw.getText());

            //Player1's points won = Player2's points lost
            players.get(player1).pointswon += Integer.parseInt(p1pw.getText());
            players.get(player2).pointslost += Integer.parseInt(p1pw.getText());

            //Player2's points won = Player1's points lost
            players.get(player2).pointswon += Integer.parseInt(p2pw.getText());
            players.get(player1).pointslost += Integer.parseInt(p2pw.getText());

            //Now we check to see who won the match
            int playeronewins = Integer.parseInt(p1gw.getText());
            int playertwowins = Integer.parseInt(p2gw.getText());
            String playeronename = players.get(player1).name;
            String playertwoname = players.get(player2).name;
            if(playeronewins>playertwowins){
                players.get(player1).matcheswon += 1;
                players.get(player2).matcheslost += 1;

                //Record this match into matches
                Match newmatch = new Match(playeronename, playertwoname, playeronename);
                if (gamemode==2) {
                    newmatch.round = players.get(player1).matcheswon;
                }
                matches.add(newmatch);
            } else{
                players.get(player2).matcheswon += 1;
                players.get(player1).matcheslost += 1;

                //Record this match into matches
                Match newmatch = new Match(playeronename, playertwoname, playertwoname);
                if (gamemode==2) {
                    newmatch.round = players.get(player2).matcheswon;
                }
                matches.add(newmatch);
            }
            stage.close();
        });
        stagesetting.position(stage, layout, "right");
    }//Ending of match()

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
            int playerindex = findplayer(namef.getText());
            option5player(playerindex);
            allplayers.close();
            stage.close();
        });

        GridPane layout = stagesetting.basicinput(namet, namef, button);
        stagesetting.position(stage, layout, "right");
    }//End of option5()

    //Start of option5player()
    public static void option5player(int num){
        Stage stage = new Stage();
        stage.setTitle("Summary of "+players.get(num).name);
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane gridpane = new GridPane();
        stagesetting.deflabels(gridpane);
        playerinfo(gridpane, num, 1);

        Button button = new Button("Exit");
        button.setOnAction(event ->{
            stage.close();
        });
        gridpane.add(button, 0, 2);

        stagesetting.position(stage, gridpane, "right");
    }//End of option5player()

    //Start of option6()
    public static void option6(){
        switch(gamemode){
            case 0:
                option6g0();
                break;
            case 1:
                option6g1();
                break;
            case 2:
                option6g2();
                break;
        }
    }//End of option6()

    //Start of option6g0()
    public static void option6g0(){
        Stage stage = new Stage();
        stage.setTitle("Game is finished!");
        stage.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();

        layout.add(new Label("A gamemode was not chosen."), 0, 0);
        Button button = new Button("Exit");
        button.setOnAction(event ->{ stage.close(); });
        layout.add(button, 1, 1);

        stagesetting.position(stage, layout, "center");
    }//End of option6g0()

    //Start of option6g1()
    public static void option6g1(){
        Stage stage = new Stage();
        stage.setTitle("Game is finished!");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();
        stagesetting.deflabels(layout);
        layout.add(new Label("Place"), 7, 0);

        int position = 1;
        int highestpoints = 0;
        //Only determines by points :(
        int size = players.size();
        for(int place = 0; place < size; place++) {
            highestpoints = 0;
            for (int hp = 0; hp < players.size(); hp++) {
                if(players.get(hp).pointswon > highestpoints){
                    highestpoints = players.get(hp).pointswon;
                }
            }

            for(int p = 0; p < players.size(); p++){
                if(players.get(p).pointswon == highestpoints){
                    playerinfo(layout, p, position);
                    layout.add(new Label(String.valueOf(place+1)), 7, position);
                    position++;
                    players.remove(players.get(p));
                    p=players.size()+1;
                }
            }
        }
        Button exit = new Button("Exit");
        exit.setOnAction(event->{
           stage.close();
        });
        layout.add(exit, 0, position);

        stagesetting.position(stage, layout, "center");
    }//End of option6g1()

    //Start of option62g()
    public static void option6g2(){
        Stage stage = new Stage();
        stage.setTitle("Game is finished!");
        stage.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();
        stagesetting.deflabels(layout);
        layout.add(new Label("Place"), 7, 0);

        int position = 1;
        int highestwins = 0;

        //Determines by wins
        int size = players.size();
        for(int place = 0; place < size; place++) {
            highestwins = 0;
            for (int hp = 0; hp < players.size(); hp++) {
                if(players.get(hp).matcheswon > highestwins){
                    highestwins = players.get(hp).matcheswon;
                }
            }

            for(int p = 0; p < players.size(); p++){
                if(players.get(p).matcheswon == highestwins){
                    playerinfo(layout, p, position);
                    layout.add(new Label(String.valueOf(place+1)), 7, position);
                    position++;
                    players.remove(players.get(p));
                    p = players.size()+1;
                }
            }
        }
        Button exit = new Button("Exit");
        exit.setOnAction(event->{
            stage.close();
        });
        layout.add(exit, 0, position);

        stagesetting.position(stage, layout, "center");
    }//End of option6g2()

    //Start of playerinfo()
    public static void playerinfo(GridPane layout, int index, int position){
        layout.add(new Label(players.get(index).name), 0, position);
        layout.add(new Label(String.valueOf(players.get(index).matcheswon)), 1, position);
        layout.add(new Label(String.valueOf(players.get(index).matcheslost)), 2, position);
        layout.add(new Label(String.valueOf(players.get(index).gameswon)), 3, position);
        layout.add(new Label(String.valueOf(players.get(index).gameslost)), 4, position);
        layout.add(new Label(String.valueOf(players.get(index).pointswon)), 5, position);
        layout.add(new Label(String.valueOf(players.get(index).pointslost)), 6, position);
    }//End of playerinfo()

    //Start of findplayer()
    public static int findplayer(String playername){
        int i;
        for(i = 0; i< players.size(); i++){
            if(players.get(i).name.equals(playername)){
                break;
            }
        }
        return i;
    }//End of findplayer()

    //Start of compare()
    public static boolean compare(String s1, String s2){
        if(s1.equals(s2)){
            return true;
        } else{
            return false;
        }
    }//End of compare()

}
