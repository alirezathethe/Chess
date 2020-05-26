package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * JavaFX main.App
 */
public class App extends Application {

    public static Scene mainScene;
    public static Stage currentStage;
    public static ArrayList<AudioClip> clickSounds;
    public static boolean inGame;

    @Override
    public void start(Stage stage) throws IOException {
        initializeSounds ();
        App.currentStage = stage ;
        BorderPane root = getFXMLLoader ("mainScreen").load ();
        mainScene = new Scene ( root, 600, 400 );
        mainScene.addEventFilter( MouseEvent.MOUSE_PRESSED, mouseEvent -> sound ( ) );
        stage.setTitle ( "Weird Chess" );
        stage.setScene( mainScene );
        stage.show();
    }

    static void setRoot(String fxml) {
        try {
            mainScene.setRoot( getFXMLLoader (fxml).load () );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public static FXMLLoader getFXMLLoader ( String fxml) {
        return new FXMLLoader(App.class.getResource("/resources/fxml/" + fxml + ".fxml"));
    }

    public static void error (String message) {
        Alert alert = new Alert ( Alert.AlertType.ERROR );
        alert.setContentText ( message );
        alert.showAndWait ();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void initializeSounds () {
        clickSounds = new ArrayList <> (  );
        clickSounds.add ( new AudioClip ( new File ( "src/resources/click.mp3" ).toURI ().toString () ) );
        clickSounds.add ( new AudioClip ( new File ( "src/resources/actual_click.mp3" ).toURI ().toString () ) );
        clickSounds.add ( new AudioClip ( new File ( "src/resources/taq.mp3" ).toURI ().toString () ) );
        clickSounds.add ( new AudioClip ( new File ( "src/resources/akh.mp3" ).toURI ().toString () ) );
        clickSounds.add ( new AudioClip ( new File ( "src/resources/loq.mp3" ).toURI ().toString () ) );
    }

    public static void sound ( ) {
        Random rand = new Random ();
        clickSounds.get(rand.nextInt(clickSounds.size())).play ();
    }

}