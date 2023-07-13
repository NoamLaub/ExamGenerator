package view;

import java.util.Objects;

import javafx.scene.control.Button;
import module.MyArrayList;
import module.Observer;

public class MyButton extends Button implements Observer {



	int serialNum;
	private static int counter=0;

	public MyButton() {
		super();
		this.serialNum = counter++;
	}


	@Override
	public void update() {
		this.setDisable(false);
		this.setVisible(true);
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(serialNum);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyButton other = (MyButton) obj;
		return serialNum == other.serialNum;
	}
	
}
