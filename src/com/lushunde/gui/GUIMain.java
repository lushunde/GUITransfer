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
	 * { 创建并显示GUI。出于线程安全的考虑， 这个方法在事件调用线程中调用。
	 */
	final static int width = 800;
	final static int height = 600;

	private static void createAndShowGUI() throws Exception {
		// 创建jframe
		CreatJFrame frame = new CreatJFrame("ZIPTransfer", width, height);
		// Container rootPane = frame.getContentPane();
		JPanel rootPane = new JPanel();
		rootPane.setLayout(null);
		frame.add(rootPane);
		// 设置菜单
		int menuwidth = width;
		int menuheight = 25;

		JPanel menu = getMenu(menuwidth, menuheight);
		menu.setLayout(null);
		rootPane.add(menu, BorderLayout.NORTH);

		// 设置tab
		String[] tabnames = { "zip编码成txt", "txt解码成zip" };
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
		
		// 展示信息
		JLabel title = new JLabel("过程:");
		title.setBounds(100, 200, 50, 25);
		c.add(title);
		JTextArea context = new JTextArea();
		context.setLineWrap(true);// 如果内容过长。自动换行
		JScrollPane scr = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(125, 225, 550, 200);
		// JLabel context = new JLabel("多行输入文本:");
		c.add(scr);
		
		
		// 初始化--往窗体里放其他控件
		JTextField srctext = new JTextField();
		srctext.setBounds(180, 20, 580, 20);
		// 选择目标 文件或者目录
		JTextField tagtext = new JTextField();
		tagtext.setBounds(180, 50, 580, 20);
		JButton srcbutton = new JButton("请选择源文件..."); // 按钮，单击响应事件，打开文件选择器
		JFileChooser srcfilechooser = new JFileChooser();
		srcfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// 创建动作监听者者
		srcbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZipFileFilter zipFileFilter = new ZipFileFilter();
				srcfilechooser.setFileFilter(zipFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // 选择中增加
				int result = srcfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = srcfilechooser.getSelectedFile();
					if (selectedFile != null) {
						boolean isfile = selectedFile.isFile();
						if (isfile) {
							String absolutePath = selectedFile.getPath();
							srctext.setText(absolutePath);
							String defultsavepath = absolutePath.substring(0, absolutePath.length() - 3) + "txt";
							tagtext.setText(defultsavepath);
							context.append("选择源文件："+absolutePath+ "\r\n");
							context.append("默认目标文件："+defultsavepath+ "\r\n");
						} else {
							JOptionPane.showMessageDialog(null, "必须选择ZIP文件", "操作错误", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
			}
		});
		srcbutton.setBounds(40, 20, 130, 20);
		c.add(srcbutton); // 面板添加按钮
		c.add(srctext); // 面板添加按钮
		JButton tagbutton = new JButton("请选择保存目录..."); // 按钮，单击响应事件，打开文件选择器
		JFileChooser tagfilechooser = new JFileChooser();
		tagfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// 创建动作监听者者
		tagbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
				tagfilechooser.setFileFilter(directoryFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // 选择中增加
				int result = tagfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = tagfilechooser.getSelectedFile();
					if (selectedFile != null) {
						String absolutePath = selectedFile.getPath();
						tagtext.setText(absolutePath);
						context.append("选择目标文件目录："+absolutePath + "\r\n");
					}
				}
			}
		});
		tagbutton.setBounds(40, 50, 130, 20);
		c.add(tagbutton); // 面板添加按钮
		c.add(tagtext); // 面板添加按钮

		

		JButton transferbutton = new JButton("编码");
		transferbutton.setSize(120, 20);
		transferbutton.setLocation(600, 450);
		// 创建动作监听者者
		transferbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				context.append("\r\n");
				String src = srctext.getText();
				String tag = tagtext.getText();
				if (src == null || !src.endsWith(".zip")) {
//					JOptionPane.showMessageDialog(null, "没有找到源文件", "错误提示", JOptionPane.ERROR_MESSAGE);
					context.append("没有找到源文件\r\n");
					return;
				}

				File srcfile = new File(src);
				boolean isfile = srcfile.isFile();
				boolean canRead = srcfile.canRead();
				if (!isfile || !canRead) {
//					JOptionPane.showMessageDialog(null, "没有找到源文件", "错误提示", JOptionPane.ERROR_MESSAGE);
					context.append("没有找到源文件\r\n");
					return;
				}

				File tagfile = new File(tag);
				if (tagfile.isDirectory()) {
					String name = srcfile.getName();
					String defultsaveName = tag + "/" + name.substring(0, name.length() - 3) + "txt";
					tag = defultsaveName;
					return;
				}

				// 执行
				List<String> list = TransferUtil.srcFiletoBase64txt(src, tag, null);

				for (String message : list) {
					context.append(message+"\r\n");
				}
				
//				JOptionPane.showMessageDialog(null, "转换完成", "错误提示", JOptionPane.INFORMATION_MESSAGE);
				context.append("解密完成 \r\n");
				
				context.append("\r\n");
			}
		});

		c.add(transferbutton);

		return c;
	}
	
	
	private static JPanel getTZTabPanel() {
		JPanel c = new JPanel();
		c.setLayout(null);
		
		// 展示信息
		JLabel title = new JLabel("过程:");
		title.setBounds(100, 200, 50, 25);
		c.add(title);
		JTextArea context = new JTextArea();
		context.setLineWrap(true);// 如果内容过长。自动换行
		JScrollPane scr = new JScrollPane(context, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr.setBounds(125, 225, 550, 200);
		// JLabel context = new JLabel("多行输入文本:");
		c.add(scr);
		
		
		// 初始化--往窗体里放其他控件
		JTextField srctext = new JTextField();
		srctext.setBounds(180, 20, 580, 20);
		// 选择目标 文件或者目录
		JTextField tagtext = new JTextField();
		tagtext.setBounds(180, 50, 580, 20);
		JButton srcbutton = new JButton("请选择加密文件..."); // 按钮，单击响应事件，打开文件选择器
		JFileChooser srcfilechooser = new JFileChooser();
		srcfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// 创建动作监听者者
		srcbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TxtFileFilter txtFileFilter = new TxtFileFilter();
				srcfilechooser.setFileFilter(txtFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // 选择中增加
				int result = srcfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = srcfilechooser.getSelectedFile();
					if (selectedFile != null) {
						boolean isfile = selectedFile.isFile();
						if (isfile) {
							String absolutePath = selectedFile.getPath();
							srctext.setText(absolutePath);
							String defultsavepath = absolutePath.substring(0, absolutePath.length() - 3) + "zip";
							tagtext.setText(defultsavepath);
							context.append("选择加密文件："+absolutePath+ "\r\n");
							context.append("默认解密文件："+defultsavepath+ "\r\n");
						} else {
							JOptionPane.showMessageDialog(null, "必须选择txt文件", "操作错误", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}
			}
		});
		srcbutton.setBounds(40, 20, 130, 20);
		c.add(srcbutton); // 面板添加按钮
		c.add(srctext); // 面板添加按钮
		JButton tagbutton = new JButton("请选择保存目录..."); // 按钮，单击响应事件，打开文件选择器
		JFileChooser tagfilechooser = new JFileChooser();
		tagfilechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// 创建动作监听者者
		tagbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DirectoryFileFilter directoryFileFilter = new DirectoryFileFilter();
				tagfilechooser.setFileFilter(directoryFileFilter);
				// filechooser.addChoosableFileFilter(zipFileFilter); // 选择中增加
				int result = tagfilechooser.showOpenDialog(null);
				// /*JFileChooser.APPROVE_OPTION是个整型常量，代表0。就是说当返回0的值我们才执行相关操作，否则什么也不做。
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = tagfilechooser.getSelectedFile();
					if (selectedFile != null) {
						String absolutePath = selectedFile.getPath();
						tagtext.setText(absolutePath);
						context.append("选择保存目录："+absolutePath + "\r\n");
					}
				}
			}
		});
		tagbutton.setBounds(40, 50, 130, 20);
		c.add(tagbutton); // 面板添加按钮
		c.add(tagtext); // 面板添加按钮

		

		JButton transferbutton = new JButton("解码");
		transferbutton.setSize(120, 20);
		transferbutton.setLocation(600, 450);
		// 创建动作监听者者
		transferbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				context.append("\r\n");
				String src = srctext.getText();
				String tag = tagtext.getText();
				if (src == null || !src.endsWith(".txt")) {
//					JOptionPane.showMessageDialog(null, "没有找到源文件", "错误提示", JOptionPane.ERROR_MESSAGE);
					context.append("没有找到加密文件\r\n");
					return;
				}

				File srcfile = new File(src);
				boolean isfile = srcfile.isFile();
				boolean canRead = srcfile.canRead();
				if (!isfile || !canRead) {
//					JOptionPane.showMessageDialog(null, "没有找到源文件", "错误提示", JOptionPane.ERROR_MESSAGE);
					context.append("没有找到加密文件\r\n");
					return;
				}

				File tagfile = new File(tag);
				if (tagfile.isDirectory()) {
					String name = srcfile.getName();
					String defultsaveName = tag + "/" + name.substring(0, name.length() - 3) + "zip";
					tag = defultsaveName;
					return;
				}

				// 执行
				List<String> list = TransferUtil.base64txttoTagFile(src, tag);

				for (String message : list) {
					context.append(message+"\r\n");
				}
				
//				JOptionPane.showMessageDialog(null, "转换完成", "错误提示", JOptionPane.INFORMATION_MESSAGE);
				context.append("解密完成 \r\n");
				
				context.append("\r\n");
			}
		});

		c.add(transferbutton);

		return c;
	}
	

	/**
	 * 设置 table
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
		JTabbedPane tabs = new JTabbedPane();// 存放选项卡的组件
		tabs.setBounds(0, 0, width - widthmenu, height - heightmenu);
		// tabs.setLayout(null);

		if (tabNames == null) {
			throw new Exception("错误");
		}
		// JPanel c = new JPanel();
		// c.setLayout(null);
		// tabs.addTab(tabNames[0], null, c, null);

		// tab.add(tabs);
		return tabs;
	}

	private static JPanel getMenu(int menuwidth, int menuheight) {
		// 创建菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(menuwidth, menuheight);
		// menuBar.setLayout(null);
		// 创建第一个 菜单
		Menu menu = new Menu("文件", 'F');
		// 创建第一个 菜单
		MenuItem menuItemNew = new MenuItem("新建", KeyEvent.VK_N);
		MenuItem menuItemSave = new MenuItem("保存", KeyEvent.VK_S);
		menu.add(menuItemNew);
		menu.add(menuItemSave);
		menuBar.add(menu);
		// 创建第二个 菜单
		Menu menuhelp = new Menu("帮助", 'H');
		// 创建第一个 菜单
		MenuItem menuItemWelcome = new MenuItem("新建");
		MenuItem menuItemSaveAbout = new MenuItem("关于");
		menuhelp.add(menuItemWelcome);
		menuhelp.add(menuItemSaveAbout);
		menuBar.add(menuhelp);

		// 设置位置
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 靠左排版
		// 设置坐标和尺寸
		panel.setBounds(0, 0, menuwidth, menuheight); // 设置坐标和尺寸 x坐标，y坐标，宽度，高度
		panel.add(menuBar);
		return panel;
	}

	public static void main(String[] args) {
		// 显示应用 GUI
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
