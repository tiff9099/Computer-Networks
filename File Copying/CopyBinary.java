/** * A simple Java program that is passed two parameters: * a source URL and a destination file the URL will be copied to. 
*  
* Tiffany Taghvaiee 
* Computer Networks Fall 2020 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBinary {    
  public static void main(String[] args){        
    String input;        
    String output;        
    int length=args.length;        
    int i;   
    
    //variables needed for passing 2 parameters on command line        
    if(length==2) {                
    
    //source and destination file locations for binary                
    input=args[0];                 
    input=args[0];                 
    output=args[1];                 
      
    //define input and output streams                
    FileInputStream in = null;                
    FileOutputStream out = null;                
      
    try{                        
    
    //initialize input and output streams                        
    File inputStream =new File(input);                        
    File outputStream =new File(output);                        
    
    //open input and output streams                        
    in = new FileInputStream(inputStream);                        
    out = new FileOutputStream(outputStream);                        
    
    //read text file into buffer of 1024 Bytes                         
    //these bytes are then written to the output stream                        
    //we use a long buffer because we don't know how long the input stream will be                        
    byte[] buffer = new byte[1024];                        
    while ((i = in.read(buffer)) > 0){                                
    out.write(buffer, 0, i);                        
    }                        
    
    //close input and output streams                        
    in.close();                        
    out.close();                        
    System.out.println("File was copied! (:");                
    }
    
    catch(IOException ioe){                        
    System.out.println("File was unable to copy! ):");                
    }          
  }        
    
    else {                
    System.out.println("Only two inputs allowed in command line!");        
    }
  }
}
