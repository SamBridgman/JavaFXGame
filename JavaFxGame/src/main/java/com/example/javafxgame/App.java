package com.example.javafxgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    protected static Scene mainScene;
    protected static Stage mainStage;
    protected static Controller controller = new Controller();
    protected static ArrayBoundedStack sceneStack = new ArrayBoundedStack<>(100);
    @Override
    public void start(Stage stage) throws IOException {
        //-----Sam------
        //title of window
        //System.out.println(Font.getFontNames());
        stage.setTitle("Temp Name");
        StackPane stackP = new StackPane();
        mainScene = new Scene(stackP, 800, 620);
        mainStage = stage;
        //button stuff
        Label textTitle = new Label("Temp Name");
        textTitle.setId("textTitle");
        //textTitle.setFont(new Font("Lao MN",40));
        //test
        stackP.getChildren().add(textTitle);
        //stackP.setStyle("-fx-background-color:#F4EBD0;"); //color styling
        //start btn
        Button startBtn = new Button("Start");
        startBtn.setId("homeButton");
        stackP.getChildren().add(startBtn);
        startBtn.setOnAction(e -> {
            sceneStack.push((Scene)mainScene);
            stage.setScene(ccCreation());
        });
        //temp background for now
        stackP.setId("stackPane");
        //load btn
        Button loadBtn = new Button("Load");
        loadBtn.setId("homeButton");
        stackP.getChildren().add(loadBtn);
        loadBtn.setTranslateY(40);
        Button optionsButton = new Button("Options");
        optionsButton.setId("homeButton");
        optionsButton.setOnAction(e -> {
            sceneStack.push((Scene)mainScene);
            stage.setScene(optionsScreen());
        });
        stackP.getChildren().add(optionsButton);
        optionsButton.setTranslateY(80);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            Platform.exit(); //closes application
        });

        Button testButton = new Button("Test");
        testButton.setTranslateY(160);
        testButton.setId("homeButton");
        testButton.setOnAction(e -> {
            sceneStack.push((Scene)mainScene);
            stage.setScene(testWindow());
        });

        stackP.getChildren().add(exitButton);
        stackP.getChildren().add(testButton);
        exitButton.setId("homeButton");
        exitButton.setTranslateY(120);
        //sets style sheet to the scene

        mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        mainScene.setFill(Color.rgb(244, 235, 208));

        stage.setScene(mainScene);
        stage.show();
    }
    public Scene ccCreation() { //this will be the real one
        Entity player = new Entity("Sam");
        Stage stage = new Stage();
        stage.setTitle("Character Creation");
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 800, 620);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        HBox ccTitleHBox = new HBox();
        Text ccTitle = new Text("Character Creation");
        ccTitleHBox.setId("ccTitleHBox");
        ccTitleHBox.setPrefWidth(scene.getWidth());
        ccTitleHBox.setMaxWidth(1000);
        ccTitleHBox.setAlignment(Pos.CENTER);
        ccTitle.setId("ccTitle");
        ccTitleHBox.getChildren().add(ccTitle);
        grid.add(ccTitleHBox, 0, 0);
        RowConstraints rc1 = new RowConstraints();
        rc1.setPercentHeight(15);

        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setId("optionBack");
        backButton.setOnAction(e -> {
            mainStage.setScene((Scene)sceneStack.poptop());
        });
        grid.add(backButton,0,0);

        HBox nameHBox = new HBox();
        Label nameLabel = new Label("Name: ");
        nameLabel.setId("ccLabel");
        TextField enterName = new TextField();
        enterName.setPrefWidth(90);
        nameHBox.getChildren().addAll(nameLabel,enterName);
        nameHBox.setMargin(nameLabel,new Insets(0, 15, 0, 10));
        grid.add(nameHBox, 0, 1);

        String sex[] = {"Male","Female","Other"};
        HBox ccSexHBox = new HBox();
        ComboBox sexComboBox = new ComboBox<>(FXCollections.observableArrayList(sex));
        Label ccSexLabel = new Label("Sex:");
        ccSexLabel.setId("ccLabel");
        ccSexHBox.getChildren().add(0,ccSexLabel);
        ccSexHBox.getChildren().add(1,sexComboBox);
        ccSexHBox.setMargin(ccSexLabel,new Insets(0, 40, 0, 10));
        grid.add(ccSexHBox, 0, 2);
        sexComboBox.setPrefWidth(90);
        /*
        ccClassHBox.setBorder(new Border(new BorderStroke(Color.BLACK,
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        */
        //CLASS SELECTION
        String classChoice[] = {"Knight","Mage","Archer"};
        HBox ccClassHBox = new HBox();
        ComboBox classComboBox = new ComboBox<>(FXCollections.observableArrayList(classChoice));
        classComboBox.setPrefWidth(90);
        Label ccClassLabel = new Label("Class:");
        ccClassLabel.setId("ccLabel");
        ccClassHBox.getChildren().add(0,ccClassLabel);
        ccClassHBox.getChildren().add(1,classComboBox);
        ccClassHBox.setMargin(ccClassLabel,new Insets(0, 24, 0, 10));
        grid.add(ccClassHBox, 0, 3);
        grid.getRowConstraints().add(0, rc1);

        //ATTRIBUTE LABEL
        Label attributeLabel = new Label("Attributes");
        attributeLabel.setTranslateX(10);
        grid.add(attributeLabel, 0, 4);
        attributeLabel.setId("apLabel");

        Label attributePoints = new Label("Attribute Points: " + Integer.toString(player.getAttributePoints()));
        attributePoints.setId("ccLabel");
        attributePoints.setTranslateX(10);
        grid.add(attributePoints,0,5);

        //HEALTH---
        HBox healthSelectHBox = new HBox();
        grid.add(healthSelectHBox,0,6);
        healthSelectHBox.setId("healthHBox");
        Label ccHealthLabel = new Label("Health: " + Integer.toString( (int)player.getHealth() * 10));
        ccHealthLabel.setId("ccLabel");
        healthSelectHBox.setMargin(ccHealthLabel,new Insets(0, 15, 0, 10));
        healthSelectHBox.getChildren().add(ccHealthLabel);

        Button increaseHealth = new Button("+");
        increaseHealth.setId("increaseButton");
        increaseHealth.setPrefWidth(50);
        increaseHealth.setOnAction(e -> {
            if(player.getAttributePoints() > 1) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setHealth(player.getHealth() + 1);
            }
        });
        Button decreaseHealth = new Button("-");
        decreaseHealth.setOnAction(e -> {
            if((player.getHealth() * 10) > 2) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setHealth(player.getHealth() - 1);
            }
        });
        decreaseHealth.setPrefWidth(50);
        decreaseHealth.setId("decreaseButton");

        healthSelectHBox.getChildren().addAll(increaseHealth,decreaseHealth);

        //ATTACK

        HBox attackSelectHBox = new HBox();
        grid.add(attackSelectHBox,0,7);
        attackSelectHBox.setId("attackHBox");
        Label ccAttackLabel = new Label("Attack: " + Integer.toString( (int)player.getAttack()));
        ccAttackLabel.setId("ccLabel");
        attackSelectHBox.setMargin(ccAttackLabel,new Insets(0, 15, 0, 10));
        attackSelectHBox.getChildren().add(ccAttackLabel);

        Button increaseAttack = new Button("+");
        increaseAttack.setId("increaseButton");
        increaseAttack.setPrefWidth(50);
        increaseAttack.setOnAction(e -> {
            if(player.getAttributePoints() > 0) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setAttack(player.getAttack() + 1);
            }
        });
        Button decreaseAttack = new Button("-");
        decreaseAttack.setOnAction(e -> {
            if(player.getAttack() > 1) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setAttack(player.getAttack() - 1);
            }
        });
        decreaseAttack.setPrefWidth(50);
        decreaseAttack.setId("decreaseButton");

        attackSelectHBox.getChildren().addAll(increaseAttack,decreaseAttack);

        //DEFENSE

        HBox defenseSelectHBox = new HBox();
        grid.add(defenseSelectHBox,0,8);
        defenseSelectHBox.setId("defenseHBox");
        Label ccDefenseLabel = new Label("Defense: " + Integer.toString( (int)player.getDefense()));
        ccDefenseLabel.setId("ccLabel");
        defenseSelectHBox.setMargin(ccDefenseLabel,new Insets(0, 15, 0, 10));
        defenseSelectHBox.getChildren().add(ccDefenseLabel);

        Button increaseDefense = new Button("+");
        increaseDefense.setId("increaseButton");
        increaseDefense.setPrefWidth(50);
        increaseDefense.setOnAction(e -> {
            if(player.getAttributePoints() > 0) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setDefense(player.getDefense() + 1);
            }
        });
        Button decreaseDefense = new Button("-");
        decreaseDefense.setOnAction(e -> {
            if(player.getDefense() > 1) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setDefense(player.getDefense() - 1);
            }
        });
        decreaseDefense.setPrefWidth(50);
        decreaseDefense.setId("decreaseButton");

        defenseSelectHBox.getChildren().addAll(increaseDefense,decreaseDefense);

        //MANA

        HBox manaSelectHBox = new HBox();
        grid.add(manaSelectHBox,0,9);
        manaSelectHBox.setId("manaHBox");
        Label ccManaLabel = new Label("Mana: " + Integer.toString( (int)player.getMana()));
        ccManaLabel.setId("ccLabel");
        manaSelectHBox.setMargin(ccManaLabel,new Insets(0, 15, 0, 10));
        manaSelectHBox.getChildren().add(ccManaLabel);

        Button increaseMana = new Button("+");
        increaseMana.setId("increaseButton");
        increaseMana.setPrefWidth(50);
        increaseMana.setOnAction(e -> {
            if(player.getAttributePoints() > 0) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setMana(player.getMana() + 1);
            }
        });
        Button decreaseMana = new Button("-");
        decreaseMana.setOnAction(e -> {
            if(player.getMana() > 1) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setMana(player.getMana() - 1);
            }
        });
        decreaseMana.setPrefWidth(50);
        decreaseMana.setId("decreaseButton");


        manaSelectHBox.getChildren().addAll(increaseMana,decreaseMana);

        //STAMINA
        HBox staminaSelectHBox = new HBox();
        grid.add(staminaSelectHBox,0,10);
        staminaSelectHBox.setId("staminaHBox");
        Label ccStaminaLabel = new Label("Stamina: " + Integer.toString( (int)player.getStamina()));
        ccStaminaLabel.setId("ccLabel");
        staminaSelectHBox.setMargin(ccStaminaLabel,new Insets(0, 15, 0, 10));
        staminaSelectHBox.getChildren().add(ccStaminaLabel);

        Button increaseStamina = new Button("+");
        increaseStamina.setId("increaseButton");
        increaseStamina.setPrefWidth(50);
        increaseStamina.setOnAction(e -> {
            if(player.getAttributePoints() > 0) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setStamina(player.getStamina() + 1);
            }
        });
        Button decreaseStamina = new Button("-");
        decreaseStamina.setOnAction(e -> {
            if(player.getStamina() > 1) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setStamina(player.getStamina() - 1);
            }
        });
        decreaseStamina.setPrefWidth(50);
        decreaseStamina.setId("decreaseButton");


        staminaSelectHBox.getChildren().addAll(increaseStamina,decreaseStamina);

        //DEXERITY
        HBox dexSelectHBox = new HBox();
        grid.add(dexSelectHBox,0,11);
        dexSelectHBox.setId("dexHBox");
        Label ccDexLabel = new Label("Dex: " + Integer.toString( (int)player.getDex()));
        ccDexLabel.setId("ccLabel");
        dexSelectHBox.setMargin(ccDexLabel,new Insets(0, 15, 0, 10));
        dexSelectHBox.getChildren().add(ccDexLabel);

        Button increaseDex = new Button("+");
        increaseDex.setId("increaseButton");
        increaseDex.setPrefWidth(50);
        increaseDex.setOnAction(e -> {
            if(player.getAttributePoints() > 0) {
                player.setAttributePoints(player.getAttributePoints() - 1);
                player.setDex(player.getDex() + 1);
            }
        });
        Button decreaseDex = new Button("-");
        decreaseDex.setOnAction(e -> {
            if(player.getDex() > 1) {
                player.setAttributePoints(player.getAttributePoints() + 1);
                player.setDex(player.getDex() - 1);
            }
        });
        decreaseDex.setPrefWidth(50);
        decreaseDex.setId("decreaseButton");
        dexSelectHBox.getChildren().addAll(increaseDex,decreaseDex);

        //submit button
        Button submitAttributes = new Button("Submit");
        submitAttributes.setOnAction(e -> {
            submitAffirm(player);
        });
        submitAttributes.setPrefWidth(50);
        grid.add(submitAttributes,0,12);

        //ATTRIBUTE TIME CLOCK
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(0);
        //corePoolSize was at 1, but it would keep a live thread even when they are idle, setting to 0 should terminate any idle threads
        executorService.scheduleAtFixedRate(() -> {

            javafx.application.Platform.runLater(() -> attributePoints.setText("Attribute Points: " + Integer.toString(player.getAttributePoints())));
            javafx.application.Platform.runLater(() -> ccHealthLabel.setText("Health: " + Integer.toString( (int)(player.getHealth()))));
            javafx.application.Platform.runLater(() -> ccAttackLabel.setText("Attack: " + Integer.toString( (int)player.getAttack())));
            javafx.application.Platform.runLater(() -> ccDefenseLabel.setText("Defense: " + Integer.toString( (int)player.getDefense())));
            javafx.application.Platform.runLater(() -> ccManaLabel.setText("Mana: " + Integer.toString( (int)player.getMana())));
            javafx.application.Platform.runLater(() -> ccStaminaLabel.setText("Stamina: " + Integer.toString( (int)player.getStamina())));
            javafx.application.Platform.runLater(() -> ccDexLabel.setText("Dex: " + Integer.toString( (int)player.getDex())));
        }, 0, 100, TimeUnit.MILLISECONDS);

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
    public void submitAffirm(Entity player) {
        GridPane grid = new GridPane();
        Stage stage = new Stage();
        stage.setTitle("Confirm");
        stage.setScene(new Scene(grid,300, 200));
        grid.setPadding(new Insets(0,0,0,10));
        grid.setStyle("-fx-background-color: black;");
        grid.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        Text name = new Text("Name: " + player.getName());
        name.setId("submitAffirmText");
        grid.add(name,0,1);
        Text health = new Text("Health: " + player.getHealth());
        health.setId("submitAffirmText");
        grid.add(health,0,2);
        Text def = new Text("Name: " + player.getDefense());
        def.setId("submitAffirmText");
        grid.add(def,0,3);
        Text attack = new Text("Attack: " + player.getAttack());
        attack.setId("submitAffirmText");
        grid.add(attack,0,4);
        Text mana = new Text("Mana: " + player.getMana());
        mana.setId("submitAffirmText");
        grid.add(mana,0,5);
        Text stamina = new Text("Stamina: " + player.getStamina());
        stamina.setId("submitAffirmText");
        grid.add(stamina,0,6);
        Text dex = new Text("Dex: " + player.getDex());
        dex.setId("submitAffirmText");
        grid.add(dex,0,7);

        HBox confirmHBox = new HBox();
        Button confirmYes = new Button("Yes");
        confirmYes.setOnAction(e -> {
            stage.close();
            mainStage.close();
            Game game = new Game(player);
        });
        confirmHBox.getChildren().add(confirmYes);
        Button confirmNo  = new Button("not yet");
        confirmNo.setOnAction(e -> {
            stage.close();
        });
        confirmHBox.getChildren().add(confirmNo);
        grid.add(confirmHBox,0,8);
        stage.show();
    }
    public Scene testWindow() {
        //BASIC DESIGN----
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 800, 620);
        scene.getStylesheets().add(getClass().getResource("optionStyles.css").toExternalForm());
        //grid.setStyle("-fx-background-image:url('');");
        final HBox hboxCharacterCreation = new HBox();
        hboxCharacterCreation.setPadding(new Insets(20,20,20,20));
        hboxCharacterCreation.setStyle("-fx-background-color: BLACK;");
        Label title = new Label("TEST WINDOW");
        title.setId("optionsTitle");
        hboxCharacterCreation.getChildren().add(title);
        hboxCharacterCreation.setAlignment(Pos.CENTER);
        grid.add(hboxCharacterCreation, 0, 0);
        grid.setColumnSpan(hboxCharacterCreation, 3);

        //COLUMN CONSTRAINT
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(50);
        grid.getColumnConstraints().addAll(column1, column2, column3); // each get 50% of width
        //back btn

        //back btn
        Button backButton = new Button("Back");
        backButton.setId("optionBack");
        backButton.setOnAction(e -> {
            mainStage.setScene((Scene)sceneStack.poptop());

        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case W:
                        controller.moveUp();
                        break;
                    case S:
                        controller.moveDown();
                        break;
                    case D:
                        controller.moveRight();
                        break;
                    case A:
                        controller.moveLeft();
                        break;
                    case ESCAPE:
                        mainStage.setScene((Scene)sceneStack.poptop());
                }
            }

        });


        Button testButton = new Button();
        testButton.setId("testButton");
        testButton.setMinWidth(100);
        testButton.setMinHeight(60);


        grid.setColumnSpan(testButton, 3);
        grid.add(testButton, 0,3);
        grid.add(backButton, 0,0);

        return scene;
    }
    public Scene optionsScreen(){
        //BASIC DESIGN----
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 800, 620);
        scene.getStylesheets().add(getClass().getResource("optionStyles.css").toExternalForm());
        //grid.setStyle("-fx-background-image:url('');");
        final HBox hboxCharacterCreation = new HBox();
        hboxCharacterCreation.setPadding(new Insets(20,20,20,20));
        hboxCharacterCreation.setStyle("-fx-background-color: BLACK;");
        Label title = new Label("Options");
        title.setId("optionsTitle");
        hboxCharacterCreation.getChildren().add(title);
        hboxCharacterCreation.setAlignment(Pos.CENTER);
        grid.add(hboxCharacterCreation, 0, 0);
        grid.setColumnSpan(hboxCharacterCreation, 3);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case ESCAPE:
                        mainStage.setScene((Scene)sceneStack.poptop());
                }
            }

        });

        //BASIC DESIGN----
        /* --image stuff
        ImageView imageView;
        FileInputStream inputstream;
        try {

            inputstream = new FileInputStream("pixelPic.jpeg");
            Image image = new Image(inputstream);
            imageView = new ImageView(image);
             grid.add(imageView, 0,4);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       */


        //COLUMN CONSTRAINT
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(50);
        grid.getColumnConstraints().addAll(column1, column2, column3); // each get 50% of width
        //back btn
        Button backButton = new Button("Back");
        backButton.setId("optionBack");
        backButton.setOnAction(e -> {
            mainStage.setScene((Scene)sceneStack.poptop());

        });
        grid.add(backButton, 0,0);
        //difficulty setting
        Button difficultyButton = new Button("Difficulty");
        difficultyButton.setId("optionDifficulty");
        //blah blah blah
        difficultyButton.setOnAction(e -> {
            //TODO
        });

        grid.add(difficultyButton, 0,2);

        return scene;
    }
    public static void main(String[] args) {
        launch();
    }
}