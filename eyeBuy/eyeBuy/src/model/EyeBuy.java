package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import net.coobird.thumbnailator.Thumbnails;

public class EyeBuy extends Observable{
	
	public String mostradata(GregorianCalendar data) {
		return data.get(GregorianCalendar.DAY_OF_MONTH)+"/"+data.get(GregorianCalendar.MONTH)+"/"+data.get(GregorianCalendar.YEAR);
	}
	
	public boolean isDouble(String str) {
		   boolean isDouble = true;
		   try {
		     Double.parseDouble(str);
		   } catch(NumberFormatException nfe) {
		      isDouble = false;
		   }
		   return isDouble;
		}
	
	public boolean isInt(String str) {
		   boolean isInt = true;
		   try {
		     Integer.parseInt(str);
		   } catch(NumberFormatException nfe) {
		      isInt = false;
		   }
		   return isInt;
		}
	
	public Blob fileToBlob(File f) throws SerialException, SQLException, IOException{
		return new SerialBlob(getFileBytes(f));  
	}
	
	public Blob biToBlob (BufferedImage bi, String ext) throws IOException, SerialException, SQLException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bi, ext, baos );
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		
		return new SerialBlob(imageInByte);  
	}
	
	public Blob novaImagemUtilizador(File f) throws IOException, SerialException, SQLException{
		BufferedImage bi = ImageIO.read(f);
		Blob res;
		
		BufferedImage dimg = resize(bi,185,204);
		
		if(f.getName().endsWith(".jpg")){
			res = biToBlob(dimg, "jpg");
		}else{
			res = biToBlob(dimg, "png");
		}
		return res;
	}
	
	public Blob novaImagemProduto(File f) throws IOException, SerialException, SQLException{
		BufferedImage bi = ImageIO.read(f);
		Blob res;
		
		BufferedImage dimg = resize(bi,455,390);
		
		if(f.getName().endsWith(".jpg")){
			res = biToBlob(dimg, "jpg");
		}else{
			res = biToBlob(dimg, "png");
		}
		return res;
	}

	public static byte[] getFileBytes(File file) throws IOException {
	    ByteArrayOutputStream ous = null;
	    InputStream ios = null;
	    try {
	        byte[] buffer = new byte[4096];
	        ous = new ByteArrayOutputStream();
	        ios = new FileInputStream(file);
	        int read = 0;
	        while ((read = ios.read(buffer)) != -1)
	            ous.write(buffer, 0, read);
	    } finally {
	        try {
	            if (ous != null)
	                ous.close();
	        } catch (IOException e) {
	            // swallow, since not that important
	        }
	        try {
	            if (ios != null)
	                ios.close();
	        } catch (IOException e) {
	            // swallow, since not that important
	        }
	    }
	    return ous.toByteArray();
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
		  return Thumbnails.of(img).size(newW, newH).asBufferedImage();
		}

	public String mostradataehora(GregorianCalendar data) {
		return data.get(GregorianCalendar.DAY_OF_MONTH)+"/"+data.get(GregorianCalendar.MONTH)+"/"+data.get(GregorianCalendar.YEAR)+"  "+data.get(GregorianCalendar.HOUR_OF_DAY)+":"+data.get(GregorianCalendar.SECOND);
		
	}
}