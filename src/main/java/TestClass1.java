import java.io.*;
import java.util.*;

public class TestClass1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wr = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine().trim());
		for (int t_i = 0; t_i < T; t_i++) {
			int N = Integer.parseInt(br.readLine().trim());
			long[][] Arr = new long[N][3];
			for (int i_Arr = 0; i_Arr < N; i_Arr++) {
				String[] arr_Arr = br.readLine().split(" ");
				for (int j_Arr = 0; j_Arr < arr_Arr.length; j_Arr++) {
					Arr[i_Arr][j_Arr] = Long.parseLong(arr_Arr[j_Arr]);
				}
			}

			long[] out_ = solve(N, Arr);
			System.out.print(out_[0]);
			for (int i_out_ = 1; i_out_ < out_.length; i_out_++) {
				System.out.print(" " + out_[i_out_]);
			}

			System.out.println();

		}

		wr.close();
		br.close();
	}

	static long[] solve(int N, long[][] Arr){
    	
		long maxWeight = Arr[0][0];
		
		int columns = Arr[0].length;

        for (int i=0;i<N;i++){

            long firstColElements = Arr[i][0];
            
            if (firstColElements > maxWeight) {

            	maxWeight = firstColElements;
                long xAxis = Arr[i][1];
                long yAxis = Arr[i][2];
            	int rowNum = i;
            	long[][] newArray = {{maxWeight, xAxis, yAxis}};
			}

			else {

				continue;
			}  
            
            for(int j=0;j<columns;j++) {
            	
            	long nextWeight = Arr[i][j];
            	long xNextAxis = Arr[i][j+1];
            	long yNextAxis = Arr[i][j+2];
            	
            	
            }

        }
        

        long[] result = {0};

        return result;
    
    }
	
	
	
	public void removeMaxWeigtedRow(long maxWeight, int N, long [][] Arr) {
		
		maxWeight = returnMaxWeightxRow(N, Arr);

        List<long[]> rowsToKeep = new ArrayList<long[]>(Arr.length);
        for (long[] row : Arr) {

            boolean found = false;
            for (double testValue : row) {

                if (Double.compare(maxWeight, testValue) == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                rowsToKeep.add(row);
            }
        }


        Arr = new long[rowsToKeep.size()][];
        for (int i = 0; i < rowsToKeep.size(); i++) {
            Arr[i] = rowsToKeep.get(i);
        }
    }
	
	public static long returnMaxWeightxRow(int N, long[][] Arr)
	{
		
		long maxWeight = Arr[0][0];

        for (int i=0;i<N;i++){

            long firstColElements = Arr[i][0];
            
            if (firstColElements > maxWeight) {

            	maxWeight = firstColElements;
                long xAxis = Arr[i][1];
                long yAxis = Arr[i][2];
            	int rowNum = i;
            	long[][] newArray = {{maxWeight, xAxis, yAxis}};
			}

			else {

				continue;
			}           

        }
        
        return maxWeight;
	}

}