import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

public class Main1462 
{
	//朴素写法
	public static class edge
	{
		int to;
		int dis;
		public edge(int to,int dis)
		{
			this.to=to;this.dis=dis;
		}
	}
	
	public static boolean dijkstra(int max,int n,ArrayList<edge>[] arrayList,int[] cost,int b)
	{
		PriorityQueue<long[]> queue=new PriorityQueue<>((a,c)->Long.compare(a[0], c[0]));
		long[] dist=new long[n+1];
		Arrays.fill(dist, Long.MAX_VALUE);dist[1]=0;
		boolean[] isVisited=new boolean[n+1];
		queue.offer(new long[] {0,1});
		while(!queue.isEmpty())
		{
			long[] a=queue.poll();
			int index=(int)a[1];
			if(isVisited[index])continue;
			isVisited[index]=true;
			for(edge nowEdge : arrayList[index])
			{
				int to=nowEdge.to;
				int dis=nowEdge.dis;
				if(cost[to]>max)continue;
				//只能用小于mid的节点 mid越大越容易成功 不断缩小mid 直到不能到达目的地为止
				//left染色可以到达 right染色不能到达
				if(dist[index]+dis<dist[to])
				{
					dist[to]=dist[index]+dis;
					queue.offer(new long[] {dist[to],to});
				}
			}
		}
		return dist[n]<=b;
	}
	
	
	
	
	public static void main(String[] args) throws IOException 
	{
		int n,m,b;
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer=new StringTokenizer(reader.readLine());
		n=Integer.parseInt(tokenizer.nextToken());
		m=Integer.parseInt(tokenizer.nextToken());
		b=Integer.parseInt(tokenizer.nextToken());
		int[] cost=new int[n+1];
		ArrayList<edge>[] arrayList=new ArrayList[n+1];
		for(int i=1;i<=n;i++)
		{
			arrayList[i]=new ArrayList<>();
			StringTokenizer tokenizer2=new StringTokenizer(reader.readLine());
			cost[i]=Integer.parseInt(tokenizer2.nextToken());
		}
		for(int i=1;i<=m;i++)
		{
			StringTokenizer tokenizer3=new StringTokenizer(reader.readLine());
			int from=Integer.parseInt(tokenizer3.nextToken());
			int to=Integer.parseInt(tokenizer3.nextToken());
			int dis=Integer.parseInt(tokenizer3.nextToken());
			if(from==to)continue;
			arrayList[from].add(new edge(to, dis));
			arrayList[to].add(new edge(from, dis));
		}
		//Dijkstra
		int left=-1;
		int right=Arrays.stream(cost).max().orElse(0)+1;
		if(!dijkstra(right-1, n, arrayList, cost, b))
		{
			System.out.println("AFK");
			System.exit(0);
		}
		while(right!=left+1)
		{
			int mid=(left+right)/2;
			if(dijkstra(mid, n, arrayList, cost, b))
				right=mid;
			else left=mid;
		}
		System.out.println(right);
	}
}
