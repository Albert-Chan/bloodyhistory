package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//MappedByteBuffer read/write VS IO read/write
public class MappedByteBufferTest {

	public static void main(String[] args) throws IOException {
		//mapped();
		new MappedThread().start();
		// wait for the thread to return.
		//...
		System.gc();
	}

	private static void noMapByteBuffer() throws FileNotFoundException,
			IOException {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
		byte[] bbb = new byte[14 * 1024 * 1024];
		FileInputStream fis = new FileInputStream("d:/test");
		FileOutputStream fos = new FileOutputStream("d:/testoutFile.txt");
		FileChannel fc = fis.getChannel();
		FileChannel fc2 = fos.getChannel();

		long timeStart, timeEnd = 0;

		// read
		timeStart = System.currentTimeMillis();
		fc.read(byteBuf);
		timeEnd = System.currentTimeMillis();
		System.out.println("Read time :" + (timeEnd - timeStart) + "ms");

		// write
		timeStart = System.currentTimeMillis();
		byteBuf.flip();
		fc2.write(byteBuf);
		timeEnd = System.currentTimeMillis();
		System.out.println("Write time :" + (timeEnd - timeStart) + "ms");
		fos.flush();
		fc.close();
		fc2.close();
		fis.close();
	}

	private static void mappedByteBuffer() throws FileNotFoundException,
			IOException {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
		byte[] bbb = new byte[14 * 1024 * 1024];
		FileInputStream fis = new FileInputStream("d:/test");
		FileOutputStream fos = new FileOutputStream("d:/testoutFile.txt");
		FileChannel fc = fis.getChannel();
		FileChannel fc2 = fos.getChannel();

		long timeStart, timeEnd = 0;

		// read
		timeStart = System.currentTimeMillis();
		fc.read(byteBuf);
		timeEnd = System.currentTimeMillis();
		System.out.println("Read time :" + (timeEnd - timeStart) + "ms");

		// write
		timeStart = System.currentTimeMillis();
		byteBuf.flip();
		fc2.write(byteBuf);
		timeEnd = System.currentTimeMillis();
		System.out.println("Write time :" + (timeEnd - timeStart) + "ms");
		fos.flush();

		// // io map read/write
		// timeStart = System.currentTimeMillis();
		// MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
		// new File("d:/test").length());
		// timeEnd = System.currentTimeMillis();
		// System.out.println("Read time :" + (timeEnd - timeStart) + "ms");
		//
		// // write
		// timeStart = System.currentTimeMillis();
		// mbb.flip();
		// timeEnd = System.currentTimeMillis();
		// System.out.println("Write time :" + (timeEnd - timeStart) + "ms");

		fc.close();
		fis.close();
	}

	
	
	public static void mapped() throws IOException {

		int mem_map_size = 14 * 1024 * 1024;
		String fn = "d:/testoutFile.txt";
		RandomAccessFile memoryMappedFile = new RandomAccessFile(fn, "rw");

		// Mapping a file into memory
		MappedByteBuffer out = memoryMappedFile.getChannel().map(
				FileChannel.MapMode.READ_WRITE, 0, mem_map_size);

		// Writing into Memory Mapped File
		for (int i = 0; i < mem_map_size; i++) {
			out.put((byte) 'A');
		}
		System.out.println("File '" + fn + "' is now "
				+ Integer.toString(mem_map_size) + " bytes full.");

		// Read from memory-mapped file.
		for (int i = 0; i < 30; i++) {
			System.out.print((char) out.get(i));
		}
		System.out.println("\nReading from memory-mapped file '" + fn
				+ "' is complete.");
		
		
		memoryMappedFile.close();
	}

}


class MappedThread extends Thread
{
	public void run()
	{
		
		try {
			int mem_map_size = 14 * 1024 * 1024;
			String fn = "d:/testoutFile.txt";
			RandomAccessFile memoryMappedFile = new RandomAccessFile(fn, "rw");

			// Mapping a file into memory
			MappedByteBuffer out = memoryMappedFile.getChannel().map(
					FileChannel.MapMode.READ_WRITE, 0, mem_map_size);

			// Writing into Memory Mapped File
			for (int i = 0; i < mem_map_size; i++) {
				out.put((byte) 'A');
			}
			System.out.println("File '" + fn + "' is now "
					+ Integer.toString(mem_map_size) + " bytes full.");

			// Read from memory-mapped file.
			for (int i = 0; i < 30; i++) {
				System.out.print((char) out.get(i));
			}
			System.out.println("\nReading from memory-mapped file '" + fn
					+ "' is complete.");
			
			
			memoryMappedFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}