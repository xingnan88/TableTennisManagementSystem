package com.huang.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.huang.model.Billiards;

public class MyPanel extends JPanel implements ActionListener, Runnable
{
	private Billiards b;
	private JButton start, end, time, save, reset;
	private JLabel startLabel, endLabel, timeLabel, saveLabel, timerLabel;
	private Thread th;
	long count;

	public MyPanel(int tableNo)
	{
		b = new Billiards();
		b.setTableNo(tableNo);

		startLabel = new JLabel();
		endLabel = new JLabel();
		timeLabel = new JLabel();
		saveLabel = new JLabel();
		timerLabel = new JLabel("00:00:00.000");

		start = new JButton("开始");
		end = new JButton("结束");
		time = new JButton("用时");
		save = new JButton("保存到数据库");
		reset = new JButton("重置");

		time.setEnabled(false);
		save.setEnabled(false);
		end.setEnabled(false);
		

		start.addActionListener(this);
		end.addActionListener(this);
		time.addActionListener(this);
		save.addActionListener(this);
		reset.addActionListener(this);

		this.add(start);
		this.add(startLabel);
		this.add(end);
		this.add(endLabel);
		this.add(time);
		this.add(timeLabel);
		this.add(save);
		this.add(saveLabel);
		this.add(reset);
		this.add(timerLabel);
		setSize(200, 200);
		this.setBorder(BorderFactory.createLineBorder(Color.pink, 2));
		setLayout(new GridLayout(6, 2));
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton btn = (JButton) e.getSource();
		if (btn.getText().equals("开始"))
		{
			Date date = new Date();
			b.setStart(date);
			String d = new SimpleDateFormat("HH:mm:ss").format(date);
			startLabel.setText(d);
			endLabel.setText(null);
			start.setEnabled(false);
			end.setEnabled(true);
			saveLabel.setText(null);

			th = new Thread(this);
			count = 0;
			th.start();
		} else if (btn.getText().equals("结束"))
		{
			Date date = new Date();
			b.setEnd(date);
			String d = new SimpleDateFormat("HH:mm:ss").format(date);
			endLabel.setText(d);
			start.setEnabled(true);
			time.setEnabled(true);
			end.setEnabled(false);
			th.stop();
		} else if (btn.getText().equals("用时"))
		{
			long min = (b.getEnd().getTime() - b.getStart().getTime()) / 60000;
			// long sec
			// =(b.getEnd().getTime()-b.getStart().getTime())/1000%60;
			b.setTime(min);
			timeLabel.setText(Long.valueOf(b.getTime()).toString());
			save.setEnabled(true);
			
		} else if (btn.getText().equals("保存到数据库"))
		{
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(b);
			session.getTransaction().commit();
			saveLabel.setText("保存到数据库成功");
			save.setEnabled(false);
		} else if (btn.getText().equals("重置"))
		{
			startLabel.setText(null);
			endLabel.setText(null);
			timeLabel.setText(null);
			saveLabel.setText(null);
			timerLabel.setText("00:00:00.000");
			time.setEnabled(false);
			save.setEnabled(false);
			end.setEnabled(false);
			start.setEnabled(true);
			th.stop();
		}

	}

	public void run()
	{
		while (true)
		{
			int ms, seconds, minutes, hours;
			String msg = "";
			hours = (int) (count / 3600000);
			minutes = (int) ((count - hours * 3600000) / 60000);
			seconds = (int) ((count - hours * 3600000 - minutes * 60000) / 1000);
			ms = (int) (count % 1000);
			if (hours < 10)
			{
				msg += "0" + hours + ":";
			} else
			{
				msg += hours + ":";
			}
			if (minutes < 10)
			{
				msg += "0" + minutes + ":";
			} else
			{
				msg += minutes + ":";
			}
			if (seconds < 10)
			{
				msg += "0" + seconds + ":";
			} else
			{
				msg += seconds + ":";
			}
			if (ms < 10)
			{
				msg += "00" + ms;
			} else if (ms < 100)
			{
				msg += "0" + ms;
			} else
			{
				msg += ms;
			}

			timerLabel.setText(msg);
			count++;
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
