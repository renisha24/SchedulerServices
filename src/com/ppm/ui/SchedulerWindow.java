package com.ppm.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import com.ppm.clarity.scheduler.ScheduledDbCsvLoad;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class SchedulerWindow {

	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchedulerWindow window = new SchedulerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SchedulerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblSchedulerServices = new JLabel("Scheduler Services");
		panel.add(lblSchedulerServices);
		
		JButton btnStart = new JButton("Start");
		ScheduledDbCsvLoad sdcl = new ScheduledDbCsvLoad();
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);
				sdcl.startScheduleTask();
			}
		});
		panel.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStop.setEnabled(false);
				sdcl.killScheduler();
			}
		});
		panel.add(btnStop);

	}

}
