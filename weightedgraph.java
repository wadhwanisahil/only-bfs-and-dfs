import java.util.*;

public class weightedgraph {
    static class Edge {
        int source;
        int dest;
        int weight;

        public Edge(int s, int d, int w) {
            this.weight = w;
            this.source = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, int v) {
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 2, 6));
        graph[1].add(new Edge(1, 3, 5));
        graph[2].add(new Edge(2, 0, 7));
        graph[2].add(new Edge(2, 1, 2));
        graph[2].add(new Edge(2, 3, 1));
        graph[3].add(new Edge(3, 1, 10));
        graph[3].add(new Edge(3, 2, 15));
    }

    public static void bfs(ArrayList<Edge>[] graph, int v) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[v];

        q.add(0);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                System.out.println(curr);
                visited[curr] = true;
            }
            for (Edge e : graph[curr]) {
                q.add(e.dest);
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean visited[]) {
        System.out.println(curr);
        visited[curr] = true;
        for (Edge e : graph[curr]) {
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void printAllPaths(ArrayList<Edge>[] graph, boolean vis[], int curr, String path, int tar) {
        if (curr == tar) {
            System.out.println(path + tar);
            return;
        }
        vis[curr] = true;
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                printAllPaths(graph, vis, e.dest, path + curr + " -> ", tar);
            }
        }
        vis[curr] = false;
    }

    public static void main(String[] args) {
        int v = 4;
        ArrayList<Edge>[] graph = (ArrayList<Edge>[]) new ArrayList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }

        createGraph(graph, v);

        int src = 0;
        int tar = 3;
        printAllPaths(graph, new boolean[v], src, "", tar);
        bfs(graph, v);
        dfs(graph, src, new boolean[v]);
    }
}
