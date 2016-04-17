/*
 *  =============================================================
 *  A2.java : Extends JFrame and contains a panel where
 *  shapes move around on the screen. Also contains start and stop
 *  buttons that starts animation and stops animation respectively.
 *  ==============================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;
import javax.swing.event.*;


public class A2 extends JFrame {
	AnimationPanel panel;  // panel for bouncing area
	JButton startButton, stopButton;  //buttons to start and stop the animation

	/** main method for A2
	 */
	public static void main(String[] args) {
		A2 a2 = new A2();
	}

	/** constructor to initialise components
	 */
	public A2() {
		super("Bouncing Application");
		panel = new AnimationPanel();
		add(panel, BorderLayout.CENTER);
		add(setUpToolsPanel(), BorderLayout.NORTH);
		add(setUpButtons(), BorderLayout.SOUTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					panel.resetMarginSize();
			 }
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
		setVisible(true);
	}

	/** Set up the tools panel
	* @return toolsPanel		the Panel
	 */
	public JPanel setUpToolsPanel() {
		//Set up the shape combo box
		//ArrayList<ImageIcon> shapeList = new ArrayList<ImageIcon>();
		
		ImageIcon rectangleButtonIcon = createImageIcon("Rectangle2.gif");
		ImageIcon squareButtonIcon = createImageIcon("Square2.gif");
		
		
		JComboBox<ImageIcon> shapesComboBox = new JComboBox<ImageIcon>(new ImageIcon[] {rectangleButtonIcon, squareButtonIcon} );
		shapesComboBox.setToolTipText("Set shape");
		shapesComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				//set the Current shape type based on the selection: 0 for Circle, 1 for Rectangle etc
				panel.setCurrentShapeType(cb.getSelectedIndex());
			}
		});
		//Set up the path combo box
		ImageIcon fallingButtonIcon = createImageIcon("falling.gif");
		JComboBox<ImageIcon> pathComboBox = new JComboBox<ImageIcon>(new ImageIcon[] {fallingButtonIcon });
		pathComboBox.setToolTipText("Set Path");
		pathComboBox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				//set the Current path type based on the selection from combo box: 0 for Boundary Path, 1 for bouncing Path
				panel.setCurrentPathType(cb.getSelectedIndex());
			}
		});
		//Set up the height TextField
		JTextField heightTxt = new JTextField("20");
		heightTxt.setToolTipText("Set Height");
		heightTxt.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0) // if the value is valid, then change the current height
					 	panel.setCurrentHeight(newValue);
					else
						tf.setText(panel.getCurrentHeight()+"");
				} catch (Exception ex) {
					tf.setText(panel.getCurrentHeight()+""); //if the number entered is invalid, reset it
				}
			}
		});
		//Set up the width TextField
		JTextField widthTxt = new JTextField("50");
		widthTxt.setToolTipText("Set Width");
		widthTxt.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0)
					 	panel.setCurrentWidth(newValue);
					else
						tf.setText(panel.getCurrentWidth()+"");
				} catch (Exception ex) {
					tf.setText(panel.getCurrentWidth()+"");
				}
			}
		});
		
		//Set up the pen width TextField
		JTextField penWidthTxt = new JTextField("1");
		penWidthTxt.setToolTipText("Set Pen Width");
		penWidthTxt.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField)e.getSource();
				try {
					int newValue = Integer.parseInt(tf.getText());
					if (newValue > 0 && newValue < 21)
					 	panel.setCurrentPenWidth(newValue);
					else
						tf.setText(panel.getCurrentPenWidth()+""); //Change this to: panel.getCurrentPenWidth()+"" once getCurrentPenWidth is implemented
				} catch (Exception ex) {
					tf.setText(panel.getCurrentPenWidth()+""); //Change this to: panel.getCurrentPenWidth()+"" once getCurrentPenWidth is implemented
				}
			}
		});
		
		//Set up the border colour button
		JButton borderButton = new JButton("Border");
		borderButton.setToolTipText("Set Border Color");
		borderButton.setForeground(Color.black);
		borderButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				Color newColor = JColorChooser.showDialog(panel, "Border Color", null);
				if ( newColor != null) {
					JButton btn = (JButton) e.getSource();
					btn.setForeground(newColor);
					panel.setCurrentBorderColor(newColor);
				}
			}
		});
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add( new JLabel(" Height: ", JLabel.RIGHT));
		toolsPanel.add(heightTxt);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthTxt);
		toolsPanel.add(new JLabel("Pen Width: ", JLabel.RIGHT));
		toolsPanel.add(penWidthTxt);
		toolsPanel.add(borderButton);
		return toolsPanel;
	}

	/** Set up the buttons panel
		 * @return buttonPanel		the Panel
	 */
	public JPanel setUpButtons() {
		JPanel buttonPanel= new JPanel(new FlowLayout());
		//Set up the start button
		startButton = new JButton("Start");
		startButton.setToolTipText("Start Animation");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				panel.start();  //start the animation
			}
		});
		//Set up the stop button
		stopButton = new JButton("Stop");
		stopButton.setToolTipText("Stop Animation");
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				stopButton.setEnabled(false);
				startButton.setEnabled(true); //stop the animation
				panel.stop();
			 }
		});
		// Slider to adjust the speed of the animation
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
		slider.setToolTipText("Adjust Speed");
		slider.addChangeListener(new ChangeListener() {
		 public void stateChanged(ChangeEvent e) {
			 JSlider source = (JSlider)e.getSource();
			 if (!source.getValueIsAdjusting()) {
				 int value = (int) (source.getValue());  // get the value from slider
				 TitledBorder tb = (TitledBorder) source.getBorder();
				 tb.setTitle("Anim delay = " + String.valueOf(value) + " ms"); //adjust the tilted border to indicate the speed of the animation
				 panel.adjustSpeed(value); //set the speed
				 source.repaint();
			 }
			}
		});
		TitledBorder title = BorderFactory.createTitledBorder("Anim delay = 30 ms");
		slider.setBorder(title);
		// Add buttons and slider control
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(slider);
		return buttonPanel;
	}

	/** create the imageIcon
	 * @param  filename		the filename of the image
	 * @return ImageIcon		the imageIcon
	 */
	protected static ImageIcon createImageIcon(String filename) {
		java.net.URL imgURL = A2.class.getResource(filename);
		return new ImageIcon(imgURL);
	}
}

