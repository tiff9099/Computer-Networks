/**
 * This is a simple program in Java for copying text files
 * @author Tiffany Taghvaiee
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyText {
	public static void main(String[] args){

		String input;
		String output;

		//finds the length of args
		int length=args.length;

		//make sure only 2 parameters are passed
		if(length==2) {

			//takes the source location and destination location for text
			input=args[0];
			output=args[1];

			//creates input and output streams
			FileInputStream in = null;
			FileOutputStream out = null;

			try{ ////initialize input and output streams

				//initialize input and output streams
				File inputFile =new File(input);
				File outputFile =new File(output);
				
				//open input and output streams
				in = new FileInputStream(inputFile);
				out = new FileOutputStream(outputFile);
				
				//read text file into buffer of 1024 Bytes 
				byte[] buffer = new byte[1024];

				int i;
				//copies contents from input stream -> output stream.
				while ((i = in.read(buffer)) > 0){
					out.write(buffer, 0, i);
				}

				//close 
				in.close();
				out.close();

				System.out.println("File was copied! (:");

			}
			catch(IOException ioe){
				System.out.println("File was unable to copy! ):");
				ioe.printStackTrace();
			}
		}
		else {
			System.out.println("Only two inputs allowed in command line!");
		}
	}
}
