package at.bestsolution.fx.test.rcontrol.sample;

import java.time.Duration;

import org.eclipse.fx.core.ServiceUtils;

import at.bestsolution.fx.test.rcontrol.RController;
import at.bestsolution.fx.test.rcontrol.RControllerFactory;
import at.bestsolution.fx.test.rcontrol.RNode;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleDrag extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Slider slider = new Slider();

		BorderPane root = new BorderPane(slider);
		Button b = new Button("Start");
		root.setBottom(b);

		Scene s = new Scene(root, 800, 600);
		primaryStage.setScene(s);
		primaryStage.show();

		RController rController = ServiceUtils.getService(RControllerFactory.class).get().create(s);
		b.setOnAction(e -> {
			RNode<Node> rNode = rController.cssFirst(".slider").get();
			rNode.click();
			rNode.dragBy(Duration.ofMillis(1000), 200, 0);
		});
	}

}
