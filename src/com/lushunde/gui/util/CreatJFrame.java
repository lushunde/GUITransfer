package com.lushunde.gui.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreatJFrame extends JFrame {
	private static final long serialVersionUID = -2085588912441845548L;
//	  private JPanel contentPane;

	public CreatJFrame(String title, int witch, int heigth) {
		JFrame.setDefaultLookAndFeelDecorated(true); //ȷ��һ��Ư������۷��
		setTitle(title);// ���ô������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Ĭ�Ϲرշ�ʽ
		setSize(witch, heigth);// ���ô����С
		setLocationRelativeTo(null); // Frame�ڴ������
//		contentPane = new JPanel();
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);// �����������
	}

	
	
	
}
