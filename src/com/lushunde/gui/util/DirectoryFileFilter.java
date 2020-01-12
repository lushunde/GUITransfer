package com.lushunde.gui.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DirectoryFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public String getDescription() {
		return "Description";
	}

}
