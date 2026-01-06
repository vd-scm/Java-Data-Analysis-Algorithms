//
// Project: Whack a Mole
// Author: Vince David M. Muego
//
// this program displays a whack-a-mole game

package whackamole.demo2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

// individual hole where the mole can pop out
class HolePane extends StackPane {
    private Image empty = new Image("file:04-GUI-Whack-A-Mole/images/empty.png");
    private Image out = new Image("file:04-GUI-Whack-A-Mole/images/out.png");
    private Image in = new Image("file:04-GUI-Whack-A-Mole/images/in.png");
    private ImageView view = new ImageView(empty);
    private Text text = new Text("");

    // default constructor to initialize each hole with image and mouse click event
    public HolePane() {
        getChildren().addAll(view, text);
        setOnMousePressed(e -> {
            if (whack()) {
                System.out.println("Mole Whacked!");
            }
        });
    }

    // hide mole -> empty image
    public void hide() {
        view.setImage(empty);
        text.setText("");
    }

    // show mole -> out image
    public void popOut() {
        view.setImage(out);
        text.setText("");
    }

    // whacked mole -> in image
    public void popIn() {
        view.setImage(in);
    }

    // action when mole is clicked
    public boolean whack() {
        if (view.getImage() == out) { // check if mole is out
            text.setText("Ouch!!!");
            popIn(); // change to in image
            return true;
        }
        return false;
    }
}

// main class
public class WhackAMole extends Application {
    private HolePane[] holes = new HolePane[9];
    private int moleIndex = 0;
    private Timer timer;

    @Override
    public void start(Stage primaryStage) {
        // create two buttons object
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");

        // create hbox pane and put in buttons
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(startButton, stopButton);

        // create GidPane and place 9 HolePane objects in a 3x3 configuration
        GridPane grid = new GridPane();
        for (int i = 0; i < 9; i++) {
            holes[i] = new HolePane();
            grid.add(holes[i], i % 3, i / 3); // positions each hole in 3x3 layout: 3 rows, 3 columns
        }

        // create BorderPane and set HBox pane at bottom and grid pane at center
        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hBox);
        borderPane.setCenter(grid);

        // create scene and set it to the stage
        Scene scene = new Scene(borderPane, 561, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vince David's Whack a Mole");
        primaryStage.show();

        // set button actions
        startButton.setOnAction(e -> {
            if (timer != null) {
                timer.cancel(); // cancel timer if running
            }
            timer = new Timer();
            timer.schedule(new MolePopper(), 0, 1000); // mole popping every second
        });

        stopButton.setOnAction(e -> {
            if (timer != null) {
                timer.cancel(); // stop mole popping when stop button is clicked
            }
        });
    }

    // inner class that pops mole out of hole at intervals
    private class MolePopper extends TimerTask {
        @Override
        public void run() {
            holes[moleIndex].hide(); // hide current mole
            moleIndex = new Random().nextInt(9); // select new random hole
            holes[moleIndex].popOut(); // pop the mole out the new hole
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}