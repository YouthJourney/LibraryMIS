package edu.yangtzeu.lmis.gui.commons;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter{
	public final static String jpeg = "jpeg";
	public final static String jpg = "jpg";
	public final static String gif = "gif";
	public final static String tiff = "tiff";
	public final static String tif = "tif";
	public final static String png = "png";
	
	public String getExtension(File f) {
		String ext = null;
		String str = f.getName();
		int i = str.lastIndexOf('.');
		if(i>0 && i<str.length()-1) {
			ext = str.substring(i+1).toLowerCase();
		}
		return ext;
	}
	
	// Accept all directories and all gif, jpg, tiff, or png files.
	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return true;
		}
		String extension = getExtension(f);
		if(extension != null) {
			if(extension.equals(tiff) || extension.equals(tif)
					|| extension.equals(gif) || extension.equals(jpeg)
					|| extension.equals(jpg) || extension.equals(png)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "images";
	}

}
