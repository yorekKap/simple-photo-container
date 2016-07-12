package photos;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

public class PhotoManager implements FileManager {

	File directory = new File("/home/yuri/JEEWorkspace/mvc-homework1/src/main/resources/photos");
	Map<String, byte[]> map = new HashMap<String, byte[]>();
	
	public PhotoManager(){
		
		buildMap();
		
	}
	
	private void buildMap(){
		
		File [] files =  directory.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name) {
				
				String lowercaseName = name.toLowerCase();
				
				if(lowercaseName.endsWith(".jpg")){
					
					return true;
					
				}else
					
					return false;
			}
		});
		
		FileInputStream fileInputStream;
		
		for(File f : files){
			
			String name = f.getName();
			name = name.substring(0, name.length() - 4);  //cut off file format
		
			try{
			byte[] data = new byte[(int)f.length()];
			fileInputStream = new FileInputStream(f);
			fileInputStream.read(data);
			fileInputStream.close();
			
			map.put(name, data);
			}catch(IOException e){
				
				System.err.println("File" + name + " can't be uploaded into map");
				
			}
		}
		
	}

	public void addFile(String name, byte[] file) throws IOException{
	
		map.put(name, file);
		
		File newFile = new File(directory, name+".jpg");
	    FileOutputStream fos = new FileOutputStream(newFile);
	    fos.write(file);
	    fos.close();
		
	}

	public byte[] getFile(String name) {
		
		return map.get(name);
		
	}
	
	public boolean isEmpty(){
		
		if(map.isEmpty())
			return true;
		else
			return false;
		
	}
	
	
	public boolean containsFile(String fileName) {
	
		if(map.containsKey(fileName))
			return true;
		else
			return false;
	
	}
	
	public byte[] remove(String fileName) {
	
		byte[] deleted = map.remove(fileName);
	    
		File fileToRemove = new File(directory, fileName+".jpg");
		fileToRemove.delete();
		
		return deleted;
		
		
	}

	public Iterator<Entry<String, byte[]>> iterator() {
		
		return map.entrySet().iterator();
	}
	
}
