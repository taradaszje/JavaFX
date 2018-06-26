package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
 
/**
 * A sample that demonstrates the basics of timeline creation.
 */
public class TimelineApp extends Application {
 
    private Timeline timeline;
 
    public Parent createContent() {
        final Pane root = new Pane();
        BorderPane border = new BorderPane();
            // Add stack to HBox in top region
        HBox hbox = addHBox();
        		border.setTop(hbox);
        		border.setLeft(addVBox());
        		addStackPane(hbox);         // Add stack to HBox in top region

        		border.setCenter(addGridPane());
        		border.setRight(addFlowPane());
        
        root.setPrefSize(800, 600);
        root.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        root.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
 
        // create a circle
        final Circle circle = new Circle(25, 25, 20, Color.web("1c89f4"));
        circle.setEffect(new Lighting());
        Button button = new Button("Graph");
        button.setLayoutX(250);
        button.setLayoutY(50);
        button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        DropShadow shadow = new DropShadow();
        //Adding the shadow when the mouse cursor is on
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, 
          new EventHandler<MouseEvent>() {
              @Override public void handle(MouseEvent e) {
                  button.setEffect(shadow);
              }
        });
        //Removing the shadow when the mouse cursor is off
        button.addEventHandler(MouseEvent.MOUSE_EXITED, 
          new EventHandler<MouseEvent>() {
              @Override public void handle(MouseEvent e) {
                  button.setEffect(null);
              }
        });
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               
                
            }
        });
        
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Home");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Calendar");
        rb2.setToggleGroup(group);
         
        RadioButton rb3 = new RadioButton("Contacts");
        rb3.setToggleGroup(group);
 
        // create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
 
        // add the following keyframes to the timeline
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,
                new KeyValue(circle.translateXProperty(), 0)),
                new KeyFrame(new Duration(4000),
                new KeyValue(circle.translateXProperty(), 205)));
        root.getChildren().addAll(createNavigation(), circle,button,rb1,rb2,rb3);
 
        return root;
    }
    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        // Category in column 2, row 1
        Text category = new Text("Sales:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0); 

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Year");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 2, 0);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Goods and Services");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        // House icon in column 1, rows 1-2
        ImageView imageHouse = new ImageView(
          new Image(TimelineApp.class.getResourceAsStream("graphics/house.png")));
        grid.add(imageHouse, 0, 0, 1, 2); 

        // Left label in column 1 (bottom), row 3
        Text goodsPercent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPercent, VPos.BOTTOM);
        grid.add(goodsPercent, 0, 2); 

        // Chart in columns 2-3, row 3
        ImageView imageChart = new ImageView(
         new Image(TimelineApp.class.getResourceAsStream("graphics/piechart.png")));
        grid.add(imageChart, 1, 2, 2, 1); 

        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);

        return grid;
    }
    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[] {
            new Hyperlink("Sales"),
            new Hyperlink("Marketing"),
            new Hyperlink("Distribution"),
            new Hyperlink("Costs")};

        for (int i=0; i<4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }
    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }
 
    /*
     * one can start/pause/stop/play animation by
     * timeline.play();
     * timeline.pause();
     * timeline.stop();
     * timeline.playFromStart();
     */
    public FlowPane addFlowPane() {
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");

        ImageView pages[] = new ImageView[8];
        for (int i=0; i<8; i++) {
            pages[i] = new ImageView(
                new Image(TimelineApp.class.getResourceAsStream(
                "graphics/chart_"+(i+1)+".png")));
            flow.getChildren().add(pages[i]);
        }

        return flow;
    }
    private VBox createNavigation() {
        // method for creating navigation panel
        // start/stop/pause/play from start buttons
        Button buttonStart = new Button("Start");
        buttonStart.setOnAction((ActionEvent t) -> {
            // start timeline
            timeline.play();
        });
        Button buttonStop = new Button("Stop");
        buttonStop.setOnAction((ActionEvent t) -> {
            // stop timeline
            timeline.stop();
        });
        Button buttonPlayFromStart = new Button("Restart");
        buttonPlayFromStart.setOnAction((ActionEvent t) -> {
            // play from start
            timeline.playFromStart();
        });
        Button buttonPause = new Button("Pause");
        buttonPause.setOnAction((ActionEvent t) -> {
            // pause from start
            timeline.pause();
        });
        // text showing current time
        final TextFlow flow = new TextFlow();
        final Text current = new Text("Current time: ");
        final Text rate = new Text();
        final Text ms = new Text(" ms");
        current.setBoundsType(TextBoundsType.VISUAL);
        ms.setBoundsType(TextBoundsType.VISUAL);
        rate.setFont(Font.font("Courier", FontWeight.BOLD, 14));
        rate.setText(String.format("%4d", 0));
        timeline.currentTimeProperty().addListener((Observable ov) -> {
            rate.setText(String.format("%4.0f",
                                       timeline.getCurrentTime().toMillis()));
            flow.requestLayout();
        });
        flow.getChildren().addAll(current, rate, ms);
        // Autoreverse checkbox
        final CheckBox checkBoxAutoReverse = new CheckBox("Auto Reverse");
        checkBoxAutoReverse.setSelected(true);
        checkBoxAutoReverse.selectedProperty().addListener((Observable ov) -> {
            timeline.setAutoReverse(checkBoxAutoReverse.isSelected());
        });
        // add all navigation to layout
        HBox hBox1 = new HBox(10);
        hBox1.setPadding(new Insets(5, 10, 0, 5));
        hBox1.getChildren().addAll(buttonStart, buttonPause,
                                   buttonStop, buttonPlayFromStart);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        VBox controls = new VBox(10);
        controls.setPadding(new Insets(0, 0, 0, 5));
        controls.getChildren().addAll(checkBoxAutoReverse, flow);
        controls.setAlignment(Pos.CENTER_LEFT);
        VBox vBox = new VBox(20);
        vBox.setLayoutY(60);
        vBox.getChildren().addAll(hBox1, controls);
        return vBox;
    	}
	    public void addStackPane(HBox hb) {
	        StackPane stack = new StackPane();
	        Rectangle helpIcon = new Rectangle(30.0, 25.0);
	        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
	            new Stop[]{
	            new Stop(0,Color.web("#4977A3")),
	            new Stop(0.5, Color.web("#B0C6DA")),
	            new Stop(1,Color.web("#9CB6CF")),}));
	        helpIcon.setStroke(Color.web("#D0E6FA"));
	        helpIcon.setArcHeight(3.5);
	        helpIcon.setArcWidth(3.5);
	
	        Text helpText = new Text("?");
	        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
	        helpText.setFill(Color.WHITE);
	        helpText.setStroke(Color.web("#7080A0")); 
	
	        stack.getChildren().addAll(helpIcon, helpText);
	        stack.setAlignment(Pos.CENTER_RIGHT);     // Right-justify nodes in stack
	        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); // Center "?"
	
	        hb.getChildren().add(stack);            // Add to HBox from Example 1-2
	        HBox.setHgrow(stack, Priority.ALWAYS);    // Give stack any extra space
	    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}