package DigitalTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadLineTest {
	public void runReadLineTest() throws IOException{ 
		File file = new File("C:\read_test.txt"); 
		if(file.exists()) { 
			BufferedReader inFile = new BufferedReader(new FileReader(file)); 
			String sLine = null; 
			while( (sLine = inFile.readLine()) != null ) 
				System.out.println(sLine); //읽어들인 문자열을 출력 합니다. } }
		}
	}

}
