package taquin.window.dialog;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public abstract class AbstractDialog extends JDialog {

	/**
	 * @param title
	 * @return void
	 */
	public AbstractDialog(JFrame parent, String title) {
		super(parent, title, true);
		initDialogUI(parent, title);
	}

	/**
	 * @param title
	 * @return void
	 */
	private void initDialogUI(JFrame parent, String title) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(getMainUI(), BorderLayout.CENTER);
		panel.add(getControlButtons(), BorderLayout.PAGE_END);

		this.setContentPane(panel);

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * @return JComponent
	 */
	protected abstract JComponent getMainUI();

	/**
	 * @return ActionListener
	 */
	protected abstract ActionListener getValidButtonEventHandler();

	/**
	 * @return JComponent
	 */
	private JComponent getControlButtons() {
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractDialog.this.dispose();
			}
		});

		JButton boutonValider = new JButton("Valider");
		boutonValider.addActionListener(getValidButtonEventHandler());

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));

		controlPanel.add(Box.createHorizontalGlue());
		controlPanel.add(boutonAnnuler);
		controlPanel.add(boutonValider);

		return controlPanel;
	}

}
