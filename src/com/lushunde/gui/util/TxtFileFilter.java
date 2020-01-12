package com.lushunde.gui.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TxtFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		
		if(f.isDirectory() || f.getName().endsWith(".txt")  ){
			return true;
		}else{
			
			return false;
		}
		
		
	}

	@Override
	public String getDescription() {
		return "txt file";
	}

}
