package com.example.javafxgame;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Game extends App{
    private static Scene rootScene;
    private static ProgressBar healthBar;
    private static String difficulty = "normal";
    private static Entity player;
    //THIS ALL BELOW IS A BUNCH OF TESTING ON HOW TO RETRIEVE AND SEND DATA FROM GUI TO OUR CLASSES
    public Game(Entity player) { //-sam
        this.player = player;
        runGame();
        rootScene.getStylesheets().add(getClass().getResource("gameStyles.css").toExternalForm());
    }
    public void runGame() { //-sam
        rootScene = ccCreation();
        //rootPane.setStyle("-fx-Background-color:#F4EBD0;");

    }
    public Scene getRootScene() { //sam
        return rootScene;
    }
    //LEVEL UP SCREEN

    public static void levelUpScreen() {
        GridPane grid = new GridPane();
        Stage stage = new Stage();
        stage.setTitle("Level Up");
        stage.setScene(new Scene(grid,300, 200));
        grid.setPadding(new Insets(0,0,0,10));

        Label entryLabel = new Label("LEVEL UP");
        entryLabel.setStyle("-fx-font: 20 Verdana");
        entryLabel.setTextFill(Color.BLACK);
        entryLabel.setPadding(new Insets(10, 10, 0, 0));
        Text level = new Text(player.getName() + ": " + player.getLevel() + " level");
        grid.add(level,0,1);
        grid.add(entryLabel,0,0);
        stage.show();
    }

    //testing item generation
    public static void generateItem() { //sam
        Item item = new Item("");
        GridPane grid = new GridPane();
        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(grid,300, 200));
        grid.setPadding(new Insets(0,0,0,10));

        Text level = new Text("Name: "+item.getName() + " Rarity: " + item.getRarity() + " Type: " + item.getType());
        grid.add(level,0,1);
        stage.show();
    }

    //CHARACTER CREATION SCREEN --TESTING
    public static Scene characterCreation() { //sam
        Stage stage = new Stage();
        GridPane grid = new GridPane();
        final HBox hboxCharacterCreation = new HBox();
        hboxCharacterCreation.setPadding(new Insets(20,20,20,20));
        hboxCharacterCreation.setStyle("-fx-background-color: BLACK;");
        Label title = new Label("Character Creation");
        title.setId("characterCreationTitle");
        hboxCharacterCreation.getChildren().add(title);
        hboxCharacterCreation.setAlignment(Pos.CENTER);
        grid.add(hboxCharacterCreation, 0, 0);
        grid.setColumnSpan(hboxCharacterCreation, 3);
        Button backButton = new Button("Back");
        backButton.setId("optionBack");
        backButton.setOnAction(e -> {
            App.mainStage.setScene((Scene)sceneStack.poptop());
        });
        grid.add(backButton,0,0);



        //COLUMN CONSTRAINT
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        grid.getColumnConstraints().addAll(column1, column2, column3); // each get 50% of width
        RowConstraints rc1 = new RowConstraints();
        rc1.setMinHeight(20);
        RowConstraints rc2 = new RowConstraints();
        rc2.setMinHeight(10);
        RowConstraints rc3 = new RowConstraints();
        rc3.setMinHeight(10);


        grid.getRowConstraints().addAll(rc1,rc2,rc3);

        Text t = new Text();
        t.setText("NAME: " + player.getName());
        t.setFont(Font.font ("Verdana", 20));
        t.setFill(Color.BLACK);
        grid.add(t, 0, 1);
        //COLUMN STUFF


        grid.setPadding(new Insets(0, 0, 30, 0));
        grid.setGridLinesVisible(true);

        TextField enterName = new TextField();
        final VBox nameBox = new VBox();
        nameBox.getChildren().add(enterName);
        //nameBox.setPadding(new Insets(0, 10, 10, 0));
        grid.add(nameBox,0,2);

        final VBox ccBtns = new VBox();
        nameBox.setPadding(new Insets(0, 10, 10, 0));
        Button submitEntry = new Button("Submit");
        submitEntry.setMaxWidth(1000);
        submitEntry.setId("ccButton");

        submitEntry.setOnAction(e -> {
            //Retrieving data
            player.setName(enterName.getText());
            t.setText("NAME: " + player.getName());
        });
        //grid.add(submitEntry,0,3);
        Button generateItemBtn = new Button("generate item");
        generateItemBtn.setMaxWidth(1000);
        generateItemBtn.setId("ccButton");
        generateItemBtn.setOnAction(e -> {
            //Retrieving data
            generateItem();
        });
        //grid.add(generateItemBtn,0,4);
        Button addXP = new Button("ADD 10 XP");
        addXP.setPadding(new Insets(5,10,5,5));
        addXP.setMaxWidth(1000);
        addXP.setId("ccButton");
        addXP.setOnAction(e -> {
            //Retrieving data
            player.setXP(player.getXP() + 10);

        });
        ccBtns.getChildren().addAll(submitEntry,generateItemBtn,addXP);
        grid.add(ccBtns,0,5);
        ccBtns.setMargin(generateItemBtn, new Insets(10,0,10,0));

        //testing healthbar gui
        //one issue with this health bar is that it is 0-1.0,
        //wont take higher I think. so if a player adds addtributes to their health and make it 1.2, health bar wont reflect that properly
        //also, the progress bar goes into the negatives but again wont show it, so we need a check that if it reaches 0 or less,
        //player dies or etc
        healthBar = new ProgressBar(player.getHealth());
        healthBar.setId("healthBar");
        Button changeHealth = new Button("Take Damage");
        changeHealth.setId("ccButton");
        changeHealth.setOnAction(e -> {
            player.setHealth(player.getHealth() - .1);
        });

        Button addHealth = new Button("add health");
        addHealth.setId("ccButton");
        addHealth.setOnAction(e -> {
            player.setHealth(player.getHealth() + .1);
        });

        //for the health bar auto updating----------
        //For now, this seems like we will use this to updata player attributes in real time
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(0);
        //corePoolSize was at 1, but it would keep a live thread even when they are idle, setting to 0 should terminate any idle threads
        executorService.scheduleAtFixedRate(() -> {

            javafx.application.Platform.runLater(() -> healthBar.setProgress(Double.valueOf(player.getHealth())));
            //testing the auto update
            javafx.application.Platform.runLater(() -> player.setName(enterName.getText()));
            javafx.application.Platform.runLater(() -> t.setText("NAME: " + player.getName()));
            //System.out.println("update");

        }, 0, 100, TimeUnit.MILLISECONDS);
        //---------------------------------------------------------
        changeHealth.setMaxWidth(1000);
        //testing an exit button that will properly exit the program
        //using the window close button in the corner will keep the thread live and unresponsive and will have to manually close it
        Button exitButton = new Button("Exit");
        exitButton.setId("ccButton");
        exitButton.setOnAction(e -> {
            executorService.shutdown(); //this offcially closes the thread
            Platform.exit(); //closes application
        });
        grid.add(changeHealth,0,6);
        grid.add(healthBar,0,7);
        grid.add(addHealth,0,8);
        grid.add(exitButton,0,9);
        grid.setColumnSpan(healthBar,2);
        Scene scene = new Scene(grid, 800, 620);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case ESCAPE:
                        mainStage.setScene((Scene)sceneStack.poptop());
                        break;
                }
            }

        });
        return scene;

    }
    public void setDifficulty(String diff) {
        this.difficulty = diff;
    }
    public String getDifficulty() {
        return this.difficulty;
    }


}
