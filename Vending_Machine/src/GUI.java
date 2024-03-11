import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage stage;
    Button[] arr = new Button[4];

    Label alta = new Label();
    Label bassa = new Label();

    Label[] arrL = new Label[4];
    private VendingMachine vm;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage=primaryStage;
        VBox vbox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        vbox.getChildren().addAll(new Label());
        vm = new VendingMachine(0);
        vm.addProduct(new Product("Coffe", 2.50));
        vm.addProduct(new Product("Nigeria", 1));
        vm.addProduct(new Product("Palle", 3));
        vm.addProduct(new Product("Manual", 1.50));

        for (int i = 0; i < vm.productsLength(); i++) {
            VBox prod = new VBox();
            Button b = new Button(vm.getProduct(i).getName());
            arr[i]=b;
            prod.getChildren().addAll( b, arrL[i] = new Label("" + vm.getProduct(i).getCost()));
            hBox1.getChildren().addAll(prod);
            int finalI = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try{
                        vm.buyProduct(finalI);
                        alta.setText("hai comprato: " + vm.getProduct(finalI).getName());
                        bassa.setText(""+vm.getCredit());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        hBox2.getChildren().addAll(new Label("" + vm.getCredit()), new Button("0.5"),new Button("1"), new Button("2"));

        Scene scene = new Scene(vbox, 500, 500);

        vbox.getChildren().addAll(new Label(""), hBox1, hBox2);

        stage.setScene(scene);
        stage.show();
    }
}

