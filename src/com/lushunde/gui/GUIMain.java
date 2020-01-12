package com.lushunde.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lushunde.gui.transfer.TransferUtil;
import com.lushunde.gui.util.CreatJFrame;
import com.lushunde.gui.util.DirectoryFileFilter;
import com.lushunde.gui.util.Menu;
import com.lushunde.gui.util.MenuItem;
import com.lushunde.gui.util.TxtFileFilter;
import com.lushunde.gui.util.ZipFileFilter;

public class GUIMain {

	/**
	 * { ��������ʾGUI�������̰߳�ȫ�Ŀ��ǣ� ����������¼������߳��е��á�
	 */
	final static int width = 800;
	final static int height = 600;

	private static void createAndShowGUI() throws Exception {
		// ����jframe
		CreatJFrame frame = new CreatJFrame("ZIPTransfer", width, height);
		// Container rootPane = frame.getContentPane();
		JPanel rootPane = new JPanel();
		rootPane.setLayout(null);
		frame.add(rootPane);
		// ���ò˵�
		int menuwidth = width;
		int menuheight = 25;

		JPanel menu = getMenu(menuwidth, menuheight);
		menu.setLayout(null);
		rootPane.add(menu, BorderLayout.NORTH);

		// ����tab
		String[] tabnames = { "zip�����txt", "txt�����zip" };
		JPanel tabPanel = new JPanel();
		JTabbedPane tabs = getTabs(tabnames, width, height - menuheight);
		JPanel ZTTabPanel = getZTTabPanel();
		tabs.addTab(tabnames[0], null, ZTTabPanel, null);
		
		JPanel TZTabPanel = getTZTabPanel();
		tabs.addTab(tabnames[1], null, TZTabPanel, null);
		tabPanel.add(tabs);
		tabPanel.setBounds(0, menuheight, width, height - menuheight);
		tabPanel.setLayout(null);
		rootPane.add(tabPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private static JPanel getZTTabPanel() {
		JPanel c = new JPanel();
		c.setLayout(null);
		
		// չʾ��Ϣ
		JLabel title = new JLabel("����:");
		title.setBounds(100, 200, 50, 25);
		c.add(title);
		JTextArea context = new JTextArea();
		context.setLineWrap(true);// ������ݹ������Զ�����
		JScrollPane scr = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(125, 225, 550, 200);
		// JLabel context = new JLabel("���������ı�:");
		c.add(scr);
		
		
		// ��ʼ��--��������������ؼ�
		JTextField srctext = new JTextField();
		srctext.setBounds(180, 20, 580, 20);
		// ѡ��Ŀ�� �ļ�����Ŀ¼
		JTextField tagtext = new JTextField();
		tagtext.setBounds(180, 50, 580, 20);
		JButton srcbutton = new JButton("��ѡ��Դ�ļ�..."); // ��ť��������Ӧ�¼������ļ�ѡ����
		JFileChooser srcfilechooser = new JFileChooser();
		srcfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// ����������������
		srcbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZipFileFilter zipFileFilter = new ZipFileFilter();
				srcfilechooser.setFileFilter(zipFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // ѡ��������
				int result = srcfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION�Ǹ����ͳ���������0������˵������0��ֵ���ǲ�ִ����ز���������ʲôҲ������
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = srcfilechooser.getSelectedFile();
					if (selectedFile != null) {
						boolean isfile = selectedFile.isFile();
						if (isfile) {
							String absolutePath = selectedFile.getPath();
							srctext.setText(absolutePath);
							String defultsavepath = absolutePath.substring(0, absolutePath.length() - 3) + "txt";
							tagtext.setText(defultsavepath);
							context.append("ѡ��Դ�ļ���"+absolutePath+ "\r\n");
							context.append("Ĭ��Ŀ���ļ���"+defultsavepath+ "\r\n");
						} else {
							JOptionPane.showMessageDialog(null, "����ѡ��ZIP�ļ�", "��������", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
			}
		});
		srcbutton.setBounds(40, 20, 130, 20);
		c.add(srcbutton); // �����Ӱ�ť
		c.add(srctext); // �����Ӱ�ť
		JButton tagbutton = new JButton("��ѡ�񱣴�Ŀ¼..."); // ��ť��������Ӧ�¼������ļ�ѡ����
		JFileChooser tagfilechooser = new JFileChooser();
		tagfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// ����������������
		tagbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
				tagfilechooser.setFileFilter(directoryFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // ѡ��������
				int result = tagfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION�Ǹ����ͳ���������0������˵������0��ֵ���ǲ�ִ����ز���������ʲôҲ������
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = tagfilechooser.getSelectedFile();
					if (selectedFile != null) {
						String absolutePath = selectedFile.getPath();
						tagtext.setText(absolutePath);
						context.append("ѡ��Ŀ���ļ�Ŀ¼��"+absolutePath + "\r\n");
					}
				}
			}
		});
		tagbutton.setBounds(40, 50, 130, 20);
		c.add(tagbutton); // �����Ӱ�ť
		c.add(tagtext); // �����Ӱ�ť

		

		JButton transferbutton = new JButton("����");
		transferbutton.setSize(120, 20);
		transferbutton.setLocation(600, 450);
		// ����������������
		transferbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				context.append("\r\n");
				String src = srctext.getText();
				String tag = tagtext.getText();
				if (src == null || !src.endsWith(".zip")) {
//					JOptionPane.showMessageDialog(null, "û���ҵ�Դ�ļ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
					context.append("û���ҵ�Դ�ļ�\r\n");
					return;
				}

				File srcfile = new File(src);
				boolean isfile = srcfile.isFile();
				boolean canRead = srcfile.canRead();
				if (!isfile || !canRead) {
//					JOptionPane.showMessageDialog(null, "û���ҵ�Դ�ļ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
					context.append("û���ҵ�Դ�ļ�\r\n");
					return;
				}

				File tagfile = new File(tag);
				if (tagfile.isDirectory()) {
					String name = srcfile.getName();
					String defultsaveName = tag + "/" + name.substring(0, name.length() - 3) + "txt";
					tag = defultsaveName;
					return;
				}

				// ִ��
				List<String> list = TransferUtil.srcFiletoBase64txt(src, tag, null);

				for (String message : list) {
					context.append(message+"\r\n");
				}
				
//				JOptionPane.showMessageDialog(null, "ת�����", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
				context.append("������� \r\n");
				
				context.append("\r\n");
			}
		});

		c.add(transferbutton);

		return c;
	}
	
	
	private static JPanel getTZTabPanel() {
		JPanel c = new JPanel();
		c.setLayout(null);
		
		// չʾ��Ϣ
		JLabel title = new JLabel("����:");
		title.setBounds(100, 200, 50, 25);
		c.add(title);
		JTextArea context = new JTextArea();
		context.setLineWrap(true);// ������ݹ������Զ�����
		JScrollPane scr = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(125, 225, 550, 200);
		// JLabel context = new JLabel("���������ı�:");
		c.add(scr);
		
		
		// ��ʼ��--��������������ؼ�
		JTextField srctext = new JTextField();
		srctext.setBounds(180, 20, 580, 20);
		// ѡ��Ŀ�� �ļ�����Ŀ¼
		JTextField tagtext = new JTextField();
		tagtext.setBounds(180, 50, 580, 20);
		JButton srcbutton = new JButton("��ѡ������ļ�..."); // ��ť��������Ӧ�¼������ļ�ѡ����
		JFileChooser srcfilechooser = new JFileChooser();
		srcfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// ����������������
		srcbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TxtFileFilter txtFileFilter = new TxtFileFilter();
				srcfilechooser.setFileFilter(txtFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // ѡ��������
				int result = srcfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION�Ǹ����ͳ���������0������˵������0��ֵ���ǲ�ִ����ز���������ʲôҲ������
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = srcfilechooser.getSelectedFile();
					if (selectedFile != null) {
						boolean isfile = selectedFile.isFile();
						if (isfile) {
							String absolutePath = selectedFile.getPath();
							srctext.setText(absolutePath);
							String defultsavepath = absolutePath.substring(0, absolutePath.length() - 3) + "zip";
							tagtext.setText(defultsavepath);
							context.append("ѡ������ļ���"+absolutePath+ "\r\n");
							context.append("Ĭ�Ͻ����ļ���"+defultsavepath+ "\r\n");
						} else {
							JOptionPane.showMessageDialog(null, "����ѡ��txt�ļ�", "��������", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
			}
		});
		srcbutton.setBounds(40, 20, 130, 20);
		c.add(srcbutton); // �����Ӱ�ť
		c.add(srctext); // �����Ӱ�ť
		JButton tagbutton = new JButton("��ѡ�񱣴�Ŀ¼..."); // ��ť��������Ӧ�¼������ļ�ѡ����
		JFileChooser tagfilechooser = new JFileChooser();
		tagfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// ����������������
		tagbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
				tagfilechooser.setFileFilter(directoryFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // ѡ��������
				int result = tagfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION�Ǹ����ͳ���������0������˵������0��ֵ���ǲ�ִ����ز���������ʲôҲ������
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = tagfilechooser.getSelectedFile();
					if (selectedFile != null) {
						String absolutePath = selectedFile.getPath();
						tagtext.setText(absolutePath);
						context.append("ѡ�񱣴�Ŀ¼��"+absolutePath + "\r\n");
					}
				}
			}
		});
		tagbutton.setBounds(40, 50, 130, 20);
		c.add(tagbutton); // �����Ӱ�ť
		c.add(tagtext); // �����Ӱ�ť

		

		JButton transferbutton = new JButton("����");
		transferbutton.setSize(120, 20);
		transferbutton.setLocation(600, 450);
		// ����������������
		transferbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				context.append("\r\n");
				String src = srctext.getText();
				String tag = tagtext.getText();
				if (src == null || !src.endsWith(".txt")) {
//					JOptionPane.showMessageDialog(null, "û���ҵ�Դ�ļ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
					context.append("û���ҵ������ļ�\r\n");
					return;
				}

				File srcfile = new File(src);
				boolean isfile = srcfile.isFile();
				boolean canRead = srcfile.canRead();
				if (!isfile || !canRead) {
//					JOptionPane.showMessageDialog(null, "û���ҵ�Դ�ļ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
					context.append("û���ҵ������ļ�\r\n");
					return;
				}

				File tagfile = new File(tag);
				if (tagfile.isDirectory()) {
					String name = srcfile.getName();
					String defultsaveName = tag + "/" + name.substring(0, name.length() - 3) + "zip";
					tag = defultsaveName;
					return;
				}

				// ִ��
				List<String> list = TransferUtil.base64txttoTagFile(src, tag);

				for (String message : list) {
					context.append(message+"\r\n");
				}
				
//				JOptionPane.showMessageDialog(null, "ת�����", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
				context.append("������� \r\n");
				
				context.append("\r\n");
			}
		});

		c.add(transferbutton);

		return c;
	}
	

	/**
	 * ���� table
	 * 
	 * @param height
	 * @param width
	 * @param object
	 * @throws Exception
	 */
	private static JTabbedPane getTabs(String[] tabNames, int width, int height) throws Exception {
		int heightmenu = 50;
		int widthmenu = 18;
		// JPanel tab = new JPanel();
		JTabbedPane tabs = new JTabbedPane();// ���ѡ������
		tabs.setBounds(0, 0, width - widthmenu, height - heightmenu);
		// tabs.setLayout(null);

		if (tabNames == null) {
			throw new Exception("����");
		}
		// JPanel c = new JPanel();
		// c.setLayout(null);
		// tabs.addTab(tabNames[0], null, c, null);

		// tab.add(tabs);
		return tabs;
	}

	private static JPanel getMenu(int menuwidth, int menuheight) {
		// �����˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(menuwidth, menuheight);
		// menuBar.setLayout(null);
		// ������һ�� �˵�
		Menu menu = new Menu("�ļ�", 'F');
		// ������һ�� �˵�
		MenuItem menuItemNew = new MenuItem("�½�", KeyEvent.VK_N);
		MenuItem menuItemSave = new MenuItem("����", KeyEvent.VK_S);
		menu.add(menuItemNew);
		menu.add(menuItemSave);
		menuBar.add(menu);
		// �����ڶ��� �˵�
		Menu menuhelp = new Menu("����", 'H');
		// ������һ�� �˵�
		MenuItem menuItemWelcome = new MenuItem("�½�");
		MenuItem menuItemSaveAbout = new MenuItem("����");
		menuhelp.add(menuItemWelcome);
		menuhelp.add(menuItemSaveAbout);
		menuBar.add(menuhelp);

		// ����λ��
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // �����Ű�
		// ��������ͳߴ�
		panel.setBounds(0, 0, menuwidth, menuheight); // ��������ͳߴ� x���꣬y���꣬��ȣ��߶�
		panel.add(menuBar);
		return panel;
	}

	public static void main(String[] args) {
		// ��ʾӦ�� GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}
