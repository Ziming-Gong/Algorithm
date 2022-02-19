package com.zim.Graph;

import javax.crypto.CipherSpi;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
    public static class edgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2){
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> Prim(Graph graph){
        //存储已经用过的node;
        HashSet<Node> set = new HashSet<>();
        //存储发现的边
        PriorityQueue<Edge> edges = new PriorityQueue<>(new edgeComparator());

        Set<Edge> ans = new HashSet<>();
        for(Node node : graph.nodes.values()){
            if(!set.contains(node)){
                set.add(node);
                for(Edge edge : node.edges){
                    edges.add(edge);
                }
                while(!edges.isEmpty()){
                    Edge curEdge = edges.poll();
                    Node to = curEdge.to;
                    if( !set.contains(to)){
                        ans.add(curEdge);
                        set.add(to);
                        for(Edge nextEdge : to.edges){
                            edges.add(nextEdge);
                        }
                    }
                }
            }
            //break;
        }
        return ans;
    }
    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distance = new int[size];
        boolean[] visit = new boolean[size];
        for(int i = 0; i < size; i++){
            distance[i] = graph[0][i];
        }
        visit[0] = true;
        int sum = 0;
        for(int i = 1; i < size; i ++){
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int j = 0; j < size; j ++){
                if(!visit[j] && distance[j] < minPath){
                    minIndex = j;
                    minPath = distance[j];
                }
            }
            if(minIndex == -1){
                return sum;
            }
            sum += minPath;
            visit[minPath] = true;
            for( int k = 0; k < size; k ++){
                if(!visit[k] && distance[k] < graph[minIndex][k]){
                    distance[k] = graph[minIndex][k];
                }
            }
        }
        return sum;


    }





















}
