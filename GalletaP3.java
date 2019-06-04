import java.util.*;
import java.lang.*;
import java.io.*;
/* Stanley Galleta - Problem 3:Coordinate Connection*/
public class GalletaP3
{
	static int cases,N;
	static LinkedList<Node> adj[];
	static int value1[];
	static int value2[];
	static int vis[];
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();

	public static double distance(int x1, int y1, int x2, int y2)
	{
		return (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

	}
    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws java.lang.Exception
	{	
		int value1[]=new int[150];
		int value2[]=new int[150];
		Scanner scan=new Scanner(System.in);
		cases = scan.nextInt();
		for(int num = 0; num<cases; num++)
		{
			   N = scan.nextInt();
			   adj=new LinkedList[N]; //initializing the adjacency list
			   for (int i=0; i<N; i++)
			   {
			   		adj[i]=new LinkedList();
                    value1[i] =scan.nextInt();
                    value2[i] =scan.nextInt();

			   }
			   for(int i=0; i<N; i++)
           	   {
           	   		for(int j=0; j<N; j++)
           	   		{
           	   			if(j==i) continue;
           	   			adj[i].add(new Node(j,Double.valueOf(distance(value1[i],value2[i],value1[j],value2[j]))));
           	   		}
               }
        vis=new int[N];
        for(int i=0; i<N; i++) vis[i]=0;
        //start with any node first, like 0
        vis[0]=1;
        //add all the connected nodes to the priority queue pq
        Iterator<Node> i=adj[0].listIterator();
        while(i.hasNext())
        {
            Node v=i.next();
            if(vis[v.V]==0)
            {
                pq.offer(v);
            }
        }
        //variable to store the total cost
        double tot=0;
        //prim's algorithm
        while(pq.size()>0)
        {
            Node n=pq.poll();
            //first checks if i visited node n.V before
            if(vis[n.V]==1) continue;
            //if not, then this is the first time, meaning this is the cheapest way
            vis[n.V]=1;
            //add the edge weight to the running total cost
            tot+=n.E;
            //add the edges of the new node to the priority queue if it hasn't been visited before
            i=adj[n.V].listIterator();
            while(i.hasNext())
            {
                Node v=i.next();
                if(vis[v.V]==0)
                {
                    pq.offer(v);
                }
            }
        }
        //prints out the minimum cost of the spanning tree
        System.out.println((double) Math.round(tot*1000d)/1000d);  // 3 decimal places
    	}
	}
}

//Node class structure - assumes weighed
class Node implements Comparable<Node>
{
    public int V;
    public double E;

    public Node(int v, double e)
    {
        this.V=v;
        this.E=e;
    }
    public int compareTo( Node N ){
        if(E<N.E)        return -1;
        else if(N.E<E)   return 1;
        return 0;
    }
}
