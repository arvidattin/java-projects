package battleShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/** class PlaceButton: "listener class" which checks which button is pressed in the grid in the gamebooard class.
 * Extends from swing JButton class*/
public class PlaceButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int coordinate;
	private int buttonValue;

	public PlaceButton(int coordinate, int buttonValue, Gamebooard gamebooard) {

		this.coordinate = coordinate;
		this.buttonValue = buttonValue;

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				gamebooard.notify(getCoordinates(), getButtonValue());

			}

		};

		addActionListener(listener);
	}

	public int getCoordinates() {

		return this.coordinate;
	}

	public int getButtonValue() {

		return this.buttonValue;
	}

	public int setButtonValue(int x) {
		this.buttonValue = x;
		return buttonValue;
	}

}
