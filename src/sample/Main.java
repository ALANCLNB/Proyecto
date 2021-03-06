package sample;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.controllers.Splash;

public class Main extends Application {
    public static int duracion=100000;
    public static int steps=1;

    @Override
    public void init() throws Exception {
        for (int i=0;i<duracion;i++){
            double progress=(100*i)/duracion;
            LauncherImpl.notifyPreloader(this, new Splash.ProgressNotification(progress)           );

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/principal.fxml"));
        Image Icono = new Image(getClass().getResourceAsStream("../ING/logo.png"));
        primaryStage.getIcons().add(Icono);
        primaryStage.setTitle("Learn-Easy");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
    LauncherImpl.launchApplication(Main.class,Splash.class,args);

        //launch(args);
    }
}
