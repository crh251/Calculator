package effects;

import javafx.scene.control.Button;

public class SetEffects {
	
	public static void setNumMouseEnter(Button button) {
		button.setOnMouseEntered(e->{
			button.setStyle("-fx-background-color: C5C1AA;");
		});
	}
	
	public static void setNumMouseExited(Button button) {
		button.setOnMouseExited(e->{
			button.setStyle("-fx-background-color: FAFAFA;");
		});
	}
	
	public static void setOpMouseEnter(Button button) {
		button.setOnMouseEntered(e->{
			button.setStyle("-fx-background-color: EED5B7;");
		});
	}
	
	public static void setOpMouseExited(Button button) {
		button.setOnMouseExited(e->{
			button.setStyle("-fx-background-color: E3E3E3");
		});
	}
	
	public static void setBaseOpMouseEnter(Button button) {
		button.setOnMouseEntered(e->{
			button.setStyle("-fx-background-color: A4D3EE");
		});
	}
	
	public static void setBaseOpMouseExited(Button button) {
		button.setOnMouseExited(e->{
			button.setStyle("-fx-background-color: E3E3E3");
		});
	}

}
