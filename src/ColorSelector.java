import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ColorSelector extends JPanel implements ChangeListener, MouseListener, KeyListener {
	private JFrame frame;
	
	private JSlider r;
	private JSlider g;
	private JSlider b;
	private JSlider all;
	
	private JCheckBox autoCopy;
	
	private JTextField copy;
	
	private JLabel rLabel;
	private JLabel gLabel;
	private JLabel bLabel;
	private JLabel copyLabel;
	
	private int rValue = 0;
	private int gValue = 0;
	private int bValue = 0;
	private int x = 10;
	private int rLabelX = 10;
	private int gLabelX = 10;
	private int bLabelX = 10;
	private int y = 20;
	private int copyY;
	private int rectX;
	private int rectY;
	
	private int width;
	
	private boolean checkToggle = true;
	
	private Color color = new Color(rValue, gValue, bValue);

	public ColorSelector() {
		frame = new JFrame("Color Selector");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			setPreferredSize(new Dimension(440, 500));
		}
		else {
			setPreferredSize(new Dimension(490, 520));
		}
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		width = getPreferredSize().width - 20;
		setLayout(null);
		Container canvas = frame.getContentPane();
		
		r = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		r.addChangeListener(this);
		r.setMajorTickSpacing(25);
		r.setMinorTickSpacing(5);
		r.setPaintTicks(true);
		r.setPaintTrack(true);
		r.setPaintLabels(true);
		r.setBounds(x, y, width, r.getPreferredSize().height + 10);
		r.setForeground(Color.RED);
		r.setBackground(new Color(214, 215, 214));
		r.setOpaque(true);
		r.addKeyListener(this);
		
		y += r.getPreferredSize().height + 20;
		
		g = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		g.addChangeListener(this);
		g.setMajorTickSpacing(25);
		g.setMinorTickSpacing(5);
		g.setPaintTicks(true);
		g.setPaintLabels(true);
		g.setPaintTrack(true);
		g.setBounds(x, y, width, g.getPreferredSize().height + 10);
		g.setForeground(new Color(0, 130, 8));
		g.setBackground(new Color(214, 215, 214));
		g.setOpaque(true);
		g.addKeyListener(this);
		
		y += g.getPreferredSize().height + 20;
		
		b = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		b.addChangeListener(this);
		b.setMajorTickSpacing(25);
		b.setPaintTicks(true);
		b.setPaintLabels(true);
		b.setPaintTrack(true);
		b.setBounds(x, y, width, b.getPreferredSize().height + 10);
		b.setForeground(Color.BLUE);
		b.setBackground(new Color(214, 215, 214));
		b.setOpaque(true);
		b.setMinorTickSpacing(5);
		b.addKeyListener(this);
		
		y += b.getPreferredSize().height + 20;
		
		all = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		all.addChangeListener(this);
		all.setMajorTickSpacing(25);
		all.setPaintTicks(true);
		all.setPaintLabels(true);
		all.setPaintTrack(true);
		all.setBounds(x, y, width, all.getPreferredSize().height + 10);
		all.setForeground(Color.BLACK);
		all.setBackground(new Color(214, 215, 214));
		all.setOpaque(true);
		all.setMinorTickSpacing(5);
		all.addKeyListener(this);
		
		y += all.getPreferredSize().height + 20;
		
		rLabel = new JLabel("Red: " + rValue);
		rLabel.setFont(rLabel.getFont().deriveFont(22f));
		rLabel.setBounds(rLabelX, y, rLabel.getPreferredSize().width, rLabel.getPreferredSize().height);
		
		gLabelX = rLabelX + rLabel.getPreferredSize().width + 10;
		
		gLabel = new JLabel("Green: " + gValue);
		gLabel.setFont(gLabel.getFont().deriveFont(22f));
		gLabel.setBounds(gLabelX, y, gLabel.getPreferredSize().width, gLabel.getPreferredSize().height);
		
		bLabelX = gLabelX + gLabel.getPreferredSize().width + 10;
		
		bLabel = new JLabel("Blue: " + bValue);
		bLabel.setFont(bLabel.getFont().deriveFont(22f));
		bLabel.setBounds(bLabelX, y, bLabel.getPreferredSize().width, bLabel.getPreferredSize().height);
		
		rectY = y + bLabel.getPreferredSize().height + 10;
		rectX = x;

		copyY = y + bLabel.getPreferredSize().height + 130;
		
		copyLabel = new JLabel("Click to copy:");
		copyLabel.setFont(copyLabel.getFont().deriveFont(22f));
		copyLabel.setBounds(x, copyY, copyLabel.getPreferredSize().width, copyLabel.getPreferredSize().height);
		copyLabel.addMouseListener(this);
		
		copy = new JTextField("" + rValue + ", " + gValue + ", " + bValue);
		copy.setEditable(false);
		copy.setFont(copy.getFont().deriveFont(22f));
		copy.setBounds(x + copyLabel.getPreferredSize().width + 10, copyY, copy.getPreferredSize().width, copy.getPreferredSize().height);
		copy.addMouseListener(this);
		
		autoCopy = new JCheckBox("Auto Copy");
		autoCopy.setMnemonic(KeyEvent.VK_A);
		autoCopy.setSelected(true);
		autoCopy.setFont(autoCopy.getFont().deriveFont(22f));
		autoCopy.setBounds(getPreferredSize().width - autoCopy.getPreferredSize().width - 10, copyY, autoCopy.getPreferredSize().width,
				autoCopy.getPreferredSize().height);
		autoCopy.addMouseListener(this);
		
		add(r);
		add(g);
		add(b);
		add(all);
		add(rLabel);
		add(gLabel);
		add(bLabel);
		add(copyLabel);
		add(copy);
		add(autoCopy);
		canvas.add(this);
		
		frame.pack();
		frame.setVisible(true);
		frame.requestFocus();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillRect(rectX, rectY, width, 100);
	}
	
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		
		if(source == r) {
			rValue = r.getValue();
			
			rLabel.setText("Red: " + rValue);
			rLabel.setBounds(rLabelX, y, rLabel.getPreferredSize().width, rLabel.getPreferredSize().height);

			gLabelX = rLabelX + rLabel.getPreferredSize().width + 10;
			gLabel.setBounds(gLabelX, y, gLabel.getPreferredSize().width, gLabel.getPreferredSize().height);
			
			bLabelX = gLabelX + gLabel.getPreferredSize().width + 10;
			bLabel.setBounds(bLabelX, y, bLabel.getPreferredSize().width, bLabel.getPreferredSize().height);
			
			color = new Color(rValue, gValue, bValue);
			
			copy.setText("" + rValue + ", " + gValue + ", " + bValue);
			copy.setBounds(x + copyLabel.getPreferredSize().width + 10, copyY, copy.getPreferredSize().width, copy.getPreferredSize().height);
		}
		if(source == g) {
			gValue = g.getValue();
			
			gLabel.setText("Green: " + gValue);
			gLabelX = rLabelX + rLabel.getPreferredSize().width + 10;
			gLabel.setBounds(gLabelX, y, gLabel.getPreferredSize().width, gLabel.getPreferredSize().height);
			
			bLabelX = gLabelX + gLabel.getPreferredSize().width + 10;
			bLabel.setBounds(bLabelX, y, bLabel.getPreferredSize().width, bLabel.getPreferredSize().height);
			
			color = new Color(rValue, gValue, bValue);
			
			copy.setText("" + rValue + ", " + gValue + ", " + bValue);
			copy.setBounds(x + copyLabel.getPreferredSize().width + 10, copyY, copy.getPreferredSize().width, copy.getPreferredSize().height);
		}
		if(source == b) {
			bValue = b.getValue();
			
			bLabel.setText("Blue: " + bValue);
			bLabel.setBounds(bLabelX, y, bLabel.getPreferredSize().width, bLabel.getPreferredSize().height);
			
			color = new Color(rValue, gValue, bValue);
			
			copy.setText("" + rValue + ", " + gValue + ", " + bValue);
			copy.setBounds(x + copyLabel.getPreferredSize().width + 10, copyY, copy.getPreferredSize().width, copy.getPreferredSize().height);
		}
		
		if(source == all) {
			rValue = all.getValue();
			gValue = all.getValue();
			bValue = all.getValue();
			
			r.setValue(all.getValue());
			g.setValue(all.getValue());
			b.setValue(all.getValue());
			
			rLabel.setText("Red: " + rValue);
			gLabel.setText("Green: " + gValue);
			bLabel.setText("Blue: " + bValue);
			rLabel.setBounds(rLabelX, y, rLabel.getPreferredSize().width, rLabel.getPreferredSize().height);

			gLabelX = rLabelX + rLabel.getPreferredSize().width + 10;
			gLabel.setBounds(gLabelX, y, gLabel.getPreferredSize().width, gLabel.getPreferredSize().height);
			
			bLabelX = gLabelX + gLabel.getPreferredSize().width + 10;
			bLabel.setBounds(bLabelX, y, bLabel.getPreferredSize().width, bLabel.getPreferredSize().height);
			
			color = new Color(rValue, gValue, bValue);
			
			copy.setText("" + rValue + ", " + gValue + ", " + bValue);
			copy.setBounds(x + copyLabel.getPreferredSize().width + 10, copyY, copy.getPreferredSize().width, copy.getPreferredSize().height);
		}
		
		repaint();
	}
	
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		
		if(source == copy || source == copyLabel) {
			copy.requestFocus();
			copy.selectAll();
			if(autoCopy.isSelected()) {
				copy.copy();
			}
		}
		else if(source == autoCopy) {
			if(autoCopy.isSelected()) {
				checkToggle = false;
			}
			else {
				checkToggle = true;
			}
			
			frame.requestFocus();
		}
		else {
			frame.requestFocus();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_A) {
			if(checkToggle) {
				checkToggle = false;
				autoCopy.setSelected(false);
			}
			else {
				checkToggle = true;
				autoCopy.setSelected(true);
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) {
		new ColorSelector();
	}
}
