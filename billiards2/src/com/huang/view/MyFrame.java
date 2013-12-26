package com.huang.view;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame
{

	List<MyPanel> mps = new ArrayList<MyPanel>();

	public MyFrame()
	{
		for (int i = 1; i < 13; i++)
		{
			mps.add(new MyPanel(i));
		}

		for (int i = 0; i < 12;)
		{
			this.add(mps.get(i));
			mps.get(i).add(new JLabel("×ÀºÅ" + (++i)));
		}
		setBounds(20, 20, 800, 600);
		setLayout(new GridLayout(4, 3));
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args)
	{
		new MyFrame();
	}
}
