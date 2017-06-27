import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;

public class Challenge7
{
    private static TextField enterRANKING;
    private static TextField enterNAME;
    private static TextField enterCOUNTRY;
    private static ListView <University> uniListView; 
    private static ArrayList <University> uniArrList = new ArrayList <University>(); 
    private static University currentlySelectedUniversity = null;
    public static void main (String args[])
    {
        launchFX();
    }

    private static void launchFX()
    {
        new JFXPanel();                             // iniatialise JavaFX
        Platform.runLater(() ->  initialiseGUI());  // runs initialiseation on the JavaFX thread
    }

    private static void initialiseGUI()
    {
        Stage stage = new Stage();
        stage.setTitle("Top Universities");
        stage.setResizable(false);
        Pane rootPane = new Pane();
        stage.setScene(new Scene (rootPane));
        stage.setWidth(950);
        stage.setHeight(650);
        stage.setOnCloseRequest((WindowEvent we) -> terminate());
        stage.show();

        uniListView = new ListView <University>();
        uniListView.setLayoutX(400);
        uniListView.setLayoutY(75);
        rootPane.getChildren().add(uniListView);

        uniListView = new ListView <University>();
        uniListView.setLayoutX(50);
        uniListView.setLayoutY(50);
        uniListView.setOnMouseClicked((MouseEvent me) -> 
            { currentlySelectedUniversity = uniListView.getSelectionModel().getSelectedItem(); });
        rootPane.getChildren().add(uniListView);
        updateListView();

        Label label1 = new Label("Enter details of University");
        label1.setLayoutX(200);
        label1.setLayoutY(75);
        rootPane.getChildren().add(label1);

        enterRANKING = new TextField();
        enterRANKING.setLayoutX(200);
        enterRANKING.setLayoutY(100);
        enterRANKING.setPromptText("Enter ranking");
        rootPane.getChildren().add(enterRANKING);

        enterNAME = new TextField();
        enterNAME.setLayoutX(200);
        enterNAME.setLayoutY(150);
        enterNAME.setPromptText("Enter name");
        rootPane.getChildren().add(enterNAME);

        enterCOUNTRY = new TextField();
        enterCOUNTRY.setLayoutX(200);
        enterCOUNTRY.setLayoutY(200);
        enterCOUNTRY.setPromptText("Enter country");
        rootPane.getChildren().add(enterCOUNTRY);

        Button btn = new Button();
        btn.setText("Enter");
        btn.setLayoutX(stage.getWidth() / 2);
        btn.setLayoutY(stage.getHeight() / 2);
        btn.setOnAction((ActionEvent ae) -> addNewItem());
        rootPane.getChildren().add(btn);

        Button actionEvent = new Button();
        actionEvent.setText("Action Event");
        actionEvent.setLayoutX(stage.getWidth() / 2);
        actionEvent.setLayoutY(stage.getHeight() / 2);
        actionEvent.setOnAction((ActionEvent ae) -> deleteItem());
        rootPane.getChildren().add(actionEvent);
    }

    private static void addNewItem()
    {
        int ranking = Integer.parseInt(enterRANKING.getText());
        String name = enterNAME.getText();
        String country = enterCOUNTRY.getText();
        uniArrList.add(new University(ranking, name, country));

        uniListView.getItems().clear();

        for (University uni : uniArrList)
        {
            uniListView.getItems().add(uni);
        }
    }
    
    private static void updateListView()
    {
    }

    private static void deleteItem()
    {
        uniArrList.remove(currentlySelectedUniversity);
        updateListView();
    }

    private static void terminate()
    {
        System.out.println("bye bye!");
        System.exit(0);
    }
}
