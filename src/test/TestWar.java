package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestWar {
private static String expectedFromData1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StringBuffer fileContents = new StringBuffer();
		Scanner fileInput = new Scanner(new File("expected1.txt"));
		while(fileInput.hasNextLine())
			fileContents.append(fileInput.nextLine() + "\n");
		expectedFromData1 = fileContents.toString();
		fileInput.close();
	}

	@Test
	public void testFileData1() {
		//assertEquals(expectedFromData1, RecordProcessor.processFile("data1.txt"));
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testFileData2() {
		exception.expect(IllegalStateException.class);
		//RecordProcessor.processFile("data2.txt");
	}

	@Test
	public void testFileData3() {
		exception.expect(NumberFormatException.class);
		//RecordProcessor.processFile("data3.txt");
	}
}
