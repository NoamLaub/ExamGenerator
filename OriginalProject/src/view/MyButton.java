package view;

import java.util.Objects;

import javafx.scene.control.Button;
import module.MyArrayList;
import module.Observer;

public class MyButton extends Button implements Observer {
	private static int SERIALCOUNT=0;
	private int serialNum;
	
	public MyButton ()
	{
		this.serialNum=SERIALCOUNT;
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
