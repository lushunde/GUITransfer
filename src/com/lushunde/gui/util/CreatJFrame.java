package com.lushunde.gui.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreatJFrame extends JFrame {
	private static final long serialVersionUID = -2085588912441845548L;
//	  private JPanel contentPane;

	public CreatJFrame(String title, int witch, int heigth) {
		JFrame.setDefaultLookAndFeelDecorated(true); //确保一个漂亮的外观风格
		setTitle(title);// 设置窗体标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭方式
		setSize(witch, heigth);// 设置窗体大小
		setLocationRelativeTo(null); // Frame在窗体居中
//		contentPane = new JPanel();
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);// 设置内容面板
	}

	
	
	
}
