package io;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class WriteTextFile {
	public static void writeTextFile(String content,String title) {
		JFileChooser fs = new JFileChooser();
		fs.setDialogTitle(title);
		fs.setFileFilter(new FileTypeFilter(".txt","Text File"));
		int result = fs.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File fi = fs.getSelectedFile();
			try {
//				FileWriter fw = new FileWriter(fi.getPath());
//				fw.write(content);
//				fw.flush();
//				fw.close();
				FileOutputStream fo = new FileOutputStream(fi.getPath());
				OutputStreamWriter osw = new OutputStreamWriter(fo, "UTF-8");
				osw.write(content);
				osw.flush();
				fo.close();
				
				int confirm = JOptionPane.showConfirmDialog(null, "save this successfully"
				+"\ndo you want to open this now?","Open file",JOptionPane.YES_NO_OPTION);
				if(confirm==1) return;
				
				Desktop d = null;
				if(Desktop.isDesktopSupported()) {
					d = Desktop.getDesktop();
				}
				try {
					d.open(fi);
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
}
