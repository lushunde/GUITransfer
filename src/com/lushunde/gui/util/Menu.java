package com.lushunde.gui.util;

import javax.swing.JMenu;

public class Menu extends JMenu {

	public Menu(String menuName,char mnemonic) {
			super(menuName+"("+mnemonic+")");
			setMnemonic(mnemonic);  //���ò˵��ļ��̲�����ʽ��Alt + F��
	}
	public Menu(String menuName) {
		super(menuName);
	}

}