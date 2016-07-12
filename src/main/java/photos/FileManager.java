package photos;

import java.io.IOException;
import java.util.Map.Entry;

public interface FileManager extends Iterable<Entry<String, byte[]>> {

	void addFile(String name, byte[] file)throws IOException;
	byte[] getFile(String name);
	boolean isEmpty();
	boolean containsFile(String fileName);
	byte[] remove(String fileName);
	
}
