package com.lushunde.gui.util;



import java.awt.event.InputEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuItem extends JMenuItem{

	    public MenuItem(String menuItemName) {
	    	super(menuItemName);
	    }
	    public MenuItem(String menuItemName,int mnemonic) {
	    	super(menuItemName+"("+(char)mnemonic+")");
	    	KeyStroke ctrl_cutKey = KeyStroke.getKeyStroke(mnemonic, InputEvent.CTRL_MASK);
	    	setAccelerator(ctrl_cutKey);//设置菜单的键盘操作方式是Alt + F键
	}

	}