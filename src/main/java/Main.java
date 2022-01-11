import java.util.*; 
import java.io.*;

class Main {

  public static int WordCount(String str) {
	  
	str = str.replaceAll("\\s+","");
    int len = str.length(); 
    return len;
  }

  public static void main (String[] args) {     
         
    Scanner s = new Scanner(System.in);
    System.out.print(WordCount(s.nextLine())); 
  }

}