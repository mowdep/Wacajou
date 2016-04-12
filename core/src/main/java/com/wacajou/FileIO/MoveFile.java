package com.wacajou.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MoveFile {

	public boolean moveFile(String source, String target) {
		InputStream inStream = null;
		OutputStream outStream = null;

		try {
			source.replace("\\", "\\\\");
			target.replace("\\", "\\\\");
			File afile = new File(source);
			File bfile = new File(target);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			afile.delete();

			System.out.println("File is copied successful!");
			return true;
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
