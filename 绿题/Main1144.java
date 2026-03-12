import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingQueue;

public class Main1144 
{ 
	public static class edge
	{
		int from;int to;int dis;
		public edge(int from,int to,int dis)
		{
			this.from=from;this.to=to;this.dis=dis;
		}
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer=new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer t=new StringTokenizer(reader.readLine());
		int n=Integer.parseInt(t.nextToken());
		int m=Integer.parseInt(t.nextToken());
		int s=Integer.parseInt(t.nextToken());
		boolean[] isVisited=new boolean[n+1];
		long[] dist=new long[n+1];Arrays.fill(dist,Integer.MAX_VALUE);
		Queue<long[]> queue=new PriorityQueue<>((a,b)->Long.compare(a[0], b[0]));//存放[到起点的距离,到哪个点]
		ArrayList<edge>[] map=new ArrayList[n+1];//顶点->边集合
		for(int i=0;i<=n;i++)map[i]=new ArrayList<>();
		for(int i=0;i<m;i++)
		{
			StringTokenizer tokenizer=new StringTokenizer(reader.readLine());
			int from=Integer.parseInt(tokenizer.nextToken());
			int to=Integer.parseInt(tokenizer.nextToken());
			int dis=Integer.parseInt(tokenizer.nextToken());
			map[from].add(new edge(from, to, dis));
		}
		dist[s]=0;
		queue.offer(new long[]{0,s});
		//Dijkstra算法
		while(!queue.isEmpty())
		{
			long[] cur=queue.poll();//选最小的
			long dis=cur[0];
			long index=cur[1];
			if(isVisited[(int)index])continue;//已经看过了
			isVisited[(int)index]=true;
			for(edge nowEdge : map[(int)index])
			{
				if(dist[(int)index]+nowEdge.dis<dist[nowEdge.to])
				{
					dist[nowEdge.to]=dist[(int)index]+nowEdge.dis;
					queue.add(new long[] {dist[nowEdge.to],nowEdge.to});
				}
			}
		}
		for(int i=1;i<=n;i++)writer.print(dist[i]+" ");
		writer.flush();
	}
	
	
}
	
