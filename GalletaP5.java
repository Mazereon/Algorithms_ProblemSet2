import java.util.*;
import java.lang.*;
import java.io.*;
/* Stanley Galleta - Floyd-Warshall Algorithm*/
public class GalletaP5 
{
	static int cases,V,E;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		Scanner scan=new Scanner(System.in);
		cases = scan.nextInt();
		for(int num = 0; num<cases; num++)
		{
			V=scan.nextInt(); // num of vertices
			E=scan.nextInt(); // num of edges
			int M[][]=new int[V][V];
			for(int i = 0; i < V; i++ )
			{
				for ( int j = 0; j < V ; j++ )
				{
					M[i][j] = 0;  // Vertex path to itself has weight 0.
				}
			}
			for( int i = 0; i < E; i++ )
			{
				int m,n,e;
				m=scan.nextInt(); // vertex 1
        		n=scan.nextInt(); // vertex 2 
        		e=scan.nextInt(); // edge cost
        		M[m][n] = e;
			}
			printResult(FloydAlgo(M));
		}
	}

	public static int[][] FloydAlgo(int[][] M) 
	{
		for (int k = 0; k < V; k++) 
		{
			for (int i = 0; i < V; i++) 
			{
				for (int j = 0; j < V; j++) 
				{
					if (M[i][k] == 0 || M[k][j] == 0) continue;
					int total = M[i][k] + M[k][j];
					if (M[i][j] != 0) 
					{
						M[i][j] = min(M[i][j],total);
					}
					else
					{
						M[i][j] = total;
					}
					
				}
			}
		}
		return M;
	}

	public static int min(int i, int j) 
	{
		if (i > j) 
		{
			return j;
		}
		return i;
	}

	public static void printResult(int[][] M)
	{
		for (int k = 0; k < V; k++)
			{
				for(int i = 0; i < V; i++)
				{
					System.out.println("The shortest path from " + k + " to " + i + " is " + M[k][i]);
				}
			}
	}
	
}