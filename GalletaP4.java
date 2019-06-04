import java.util.*;
import java.lang.*;
import java.io.*;
/* Stanley Galleta - Problem 4:Vertex Cost????*/
public class GalletaP4
{
	static int cases,V,E,u,v;
  static LinkedList<Node> adj[];
  static int vis[];
  static PriorityQueue<Node> pq = new PriorityQueue<Node>();
  @SuppressWarnings("unchecked")
	public static void main(String args[]) throws java.lang.Exception
	{	
		Scanner scan=new Scanner(System.in);
		cases = scan.nextInt();
		for(int num = 0; num<cases; num++)
		{
			V=scan.nextInt(); // num of vertices
			E=scan.nextInt(); // num of edges
			u=scan.nextInt(); // source
			v=scan.nextInt(); // goal

			adj=new LinkedList[V];
      for(int i=0; i<V; i++) adj[i]=new LinkedList();
        
			for(int i=0; i<E; i++)
      {
        int m,n,e;
        m=scan.nextInt(); // vertex 1
        n=scan.nextInt(); // vertex 2 
        e=scan.nextInt(); // edge cost
        //this line means that node u is connected to node v with edge weight e
        adj[m].add(new Node(n,e+1));
        //if the graph is undirectional, this line is important as well since it shows the reverse
        adj[n].add(new Node(m,e+1));
      }
      vis=new int[V];
      for(int i=0; i<V; i++) vis[i]=9999999;
      //start with any node first, like 0
      vis[u]=0;
      //adds source to priority queue
      pq.offer(new Node(u,1));
      while(pq.size()>0)
      {
        	Node n=pq.poll();
          //node n represents (vertex, time from source to vertex)
          Iterator<Node> i=adj[n.V].listIterator();
          //goes through all adges
            while(i.hasNext())
            {
                Node v=i.next();
                //checks if new path's total cost beats the old path
                if(n.E+v.E<vis[v.V])
                {
                    vis[v.V]=n.E+v.E;
                    pq.offer(new Node(v.V,vis[v.V]));
                }
            }
      }

      System.out.println(vis[v]);
		}

	}
}

//Node class structure - assumes weighed
class Node implements Comparable<Node>
{
    public int V,E;
    public Node(int v, int e)
    {
        this.V=v;
        this.E=e;
    }
    public int compareTo( Node N ){
        return E - N.E;
    }
}
