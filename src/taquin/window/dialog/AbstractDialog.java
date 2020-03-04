package taquin.window.dialog;

public class AbstractDialog {

	public AbstractDialog() {
		
	}

	/**
	 * @param title
	 * @return void
	 */
	public void AbstractDialog(String title) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param title
	 * @return void
	 */
	private void initDialogUI(String title) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

}

