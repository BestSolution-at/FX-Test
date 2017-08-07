package sample.osgi.app.app.parts;

import javax.annotation.PostConstruct;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SamplePart {
	@PostConstruct
	void init(BorderPane p) {
		{
			Button button = new Button("Center");
			button.getStyleClass().add("my-button");
			button.setOnAction( e -> {
				System.err.println("Button center");
			});
			p.setCenter(button);			
		}
		
		{
			Button button = new Button("Bottom");
			button.getStyleClass().add("my-button2");
			button.setOnAction( e -> {
				System.err.println("Button bottom");
			});
			p.setBottom(button);			
		}
	}
}
