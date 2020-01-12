package com.lushunde.gui.util;

import javax.swing.JMenu;

public class Menu extends JMenu {

	public Menu(String menuName,char mnemonic) {
			super(menuName+"("+mnemonic+")");
			setMnemonic(mnemonic);  //设置菜单的键盘操作方式是Alt + F键
	}
	public Menu(String menuName) {
		super(menuName);
	}

}