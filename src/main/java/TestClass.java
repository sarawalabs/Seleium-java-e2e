

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import for Scanner and other utility classes
import java.util.*;

class TestClass {	
    
    public static int finalvalue;
	
	public static void main(String[] args) throws IOException {

		// Scanner
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		PrintWriter wr = new PrintWriter(System.out);
	
		String[] arr_ch = br.readLine().split("");
		
		char[] ch = new char[N];
		
		for (int i_ch=0; i_ch<arr_ch.length; i_ch++) {
			
			ch[i_ch] = arr_ch[i_ch].charAt(0);
		}
		
		String out_ = solve(N, ch);
		System.out.println(out_);
		
		wr.close();
		br.close();
		
	}
		
		static String solve(int N, char[] ch) {
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0;i<N;i++) {
				
				sb.append(ch[i]);
			}
			
			String result = sb.toString();
			
			return result;
		}
		
		
 }
