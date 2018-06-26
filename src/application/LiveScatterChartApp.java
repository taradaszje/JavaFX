package application;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import javafx.util.Duration;
 
 
/**
 * A live scatter chart.
 */
public class LiveScatterChartApp extends Application {
 
    private ScatterChart.Series<Number,Number> series;
    private double nextX = 0;
    private SequentialTransition animation;
 
    public LiveScatterChartApp() {
        // create initial frames
        final KeyFrame initialFrames =
            new KeyFrame(Duration.millis(60),
                         (ActionEvent actionEvent) -> {
                             final Number nextY =
                                Math.sin(Math.toRadians(nextX)) * 100;
                             final Data<Number,Number> data =
                                new Data<Number, Number>(nextX, nextY);
                             series.getData().add(data);
                             nextX += 10;
                         });
        Timeline initial = new Timeline(initialFrames);
        initial.setCycleCount(200);
 
        // create follow-on frames
        final KeyFrame followingFrames =
            new KeyFrame(Duration.millis(60),
                         (ActionEvent actionEvent) -> {
                             final Number nextY =
                                 Math.sin(Math.toRadians(nextX)) * 100;
                             final Data<Number,Number> data =
                             new Data<Number, Number>(nextX, nextY);
                             series.getData().add(data);
                             if (series.getData().size() > 54) {
                                 series.getData().remove(0);
                             }
                             nextX += 10;
                         });
        Timeline following = new Timeline(followingFrames);
        following.setCycleCount(Animation.INDEFINITE);
 
        // create animation
        animation = new SequentialTransition(initial, following);
    }
 
    public Parent createContent() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis(-100, 100, 10);
        xAxis.setForceZeroInRange(false);
        xAxis.setLabel("X Axis");
        xAxis.setAnimated(false);
        yAxis.setLabel("Y Axis");
        yAxis.setAutoRanging(false);
        // add starting data
        series = new ScatterChart.Series<>();
        series.setName("Sine Wave");
        series.getData().add(new ScatterChart.Data<Number, Number>(5d, 5d));
        // setup chart
        final String liveScatterChartCss =
            getClass().getResource("LiveScatterChart.css").toExternalForm();
        final ScatterChart<Number, Number> sc =
            new ScatterChart<>(xAxis, yAxis);
        sc.getData().add(series);
        sc.getStylesheets().add(liveScatterChartCss);
        sc.setTitle("Animated Sine Wave ScatterChart");
 
        return sc;
    }
 
    public void play() {
        animation.play();
    }
 
    @Override public void stop() {
        animation.pause();
    }
 
    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        play();
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}