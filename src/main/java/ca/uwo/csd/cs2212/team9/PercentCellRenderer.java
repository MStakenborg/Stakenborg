package ca.uwo.csd.cs2212.team9;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.Format;
import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * 
 * @author Tyler
 */
public class PercentCellRenderer extends DefaultTableCellRenderer {

	private final Format formatter;

	public PercentCellRenderer() {
		this.formatter = NumberFormat.getPercentInstance();
		this.setHorizontalAlignment(RIGHT);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (value != null) {
			double amount = (double) value;
			if (amount <= 50)
				this.setForeground(Color.RED);
			else if (amount < 70)
				this.setForeground(Color.ORANGE.darker());
			else if (amount >= 80)
				this.setForeground(Color.GREEN.darker().darker());
			else
				this.setForeground(Color.BLACK);

			// Format the value with a percent symbol
			String formattedValue = formatter.format(amount / 100);
			this.setValue(formattedValue);

		} else
			this.setValue("");

		if (isSelected) {
			setForeground((Color) UIManager.get("Table.selectionForeground"));
			setBackground((Color) UIManager.get("Table.selectionBackground"));
		} else {
			setBackground((Color) UIManager.get("Table.background"));
		}

		return this;
	}
}
