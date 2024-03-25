import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

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
        vbox.setSpacing(500/16);
        HBox hBox1 = new HBox();
        hBox1.setSpacing(500/16);
        HBox hBox2 = new HBox();
        hBox2.setSpacing(500/16);
        vbox.getChildren().addAll(new Label());
        vm = new VendingMachine(0);
        vm.addProduct(new Product("Coffe", 2.50));
        vm.addProduct(new Product("Nigeria", 1));
        vm.addProduct(new Product("Palle", 3));
        vm.addProduct(new Product("Manual", 1.50));
        Label label = new Label("Credit : " + vm.getCredit());
        label.setAlignment(Pos.CENTER);
        Label alta = new Label();
        alta.setMinWidth(400);
        alta.setAlignment(Pos.CENTER);
        alta.setText("nigga");

        for (int i = 0; i < vm.productsLength(); i++) {
            VBox prod = new VBox() ;
            Button b = new Button(vm.getProduct(i).getName());
            arr[i]=b;
            b.setMinSize(500/8, 500/16);

            prod.getChildren().addAll(b);
            arrL[i] = new Label("" + vm.getProduct(i).getCost());
            arrL[i].setMinSize(500/8, 500/16);
            prod.getChildren().addAll(arrL[i]);
            hBox1.getChildren().addAll(prod);
            int finalI = i;
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try{
                        vm.buyProduct(finalI);
                        alta.setText("hai comprato: " + vm.getProduct(finalI).getName());
                        label.setText(""+vm.getCredit());
                    } catch (Exception e) {
                        alta.setText(e.getMessage());
                    }
                }
            });
        }
        Button[] b1 = new Button[]{new Button("0.5"), new Button("1"), new Button("2")};
        for (Button b : b1) {
            b.setMinSize(500/8,500/16);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        vm.editCredit(Double.parseDouble(b.getText()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    label.setText("Credit : " + vm.getCredit());
                }
            });
        }

        hBox2.getChildren().addAll(label, b1[0], b1[1], b1[2]);

        Scene scene = new Scene(vbox, 400, 250);

        vbox.getChildren().addAll(alta, hBox1, hBox2);

        stage.setScene(scene);
        stage.show();
    }
}

