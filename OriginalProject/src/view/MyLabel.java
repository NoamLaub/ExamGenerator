package view;


import java.util.Objects;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import module.MyArrayList;
import module.Observer;

public class MyLabel  extends Label implements Observer{
	private static int SERIALCOUNT=0;
	private int serialNum;
	public MyLabel()
	{
		this.serialNum=SERIALCOUNT++;
	}
	@Override
	public void update() {
		this.setVisible(true);
		this.setStyle("-fx-text-fill: green");
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
		MyLabel other = (MyLabel) obj;
		return serialNum == other.serialNum;
	}
}
