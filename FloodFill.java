// In this problem, we are using 2 queues for BFS, one for adding the row indice and other for adding column indice. So, we start with the given
// row and column image, put it's indices in the 2 queues and start bfs. So, in each iteration we look at it's 4 adjacent neighbors, check if it's
// color is same color, yes than add it's indices to the queue and change it's color to new color.

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[sr][sc] == color) {
            return image;
        }
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        int m = image.length;
        int n = image[0].length;
        int oldcolor = image[sr][sc];
        image[sr][sc] = color;
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        row.add(sr);
        col.add(sc);
        while (!row.isEmpty()) {
            int cr = row.poll();
            int cc = col.poll();
            for (int[] dir : dirs) {
                int nr = cr + dir[0];
                int nc = cc + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == oldcolor) {
                    image[nr][nc] = color;
                    row.add(nr);
                    col.add(nc);

                }
            }
        }
        return image;
    }
}

// DFS:
class Solution {
    int m, n;
    int[][] dirs;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null || image.length == 0 || image[sr][sc] == color) {
            return image;
        }
        m = image.length;
        n = image[0].length;
        dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        dfs(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int oldcolor) {
        if (sr < 0 || sr == m || sc < 0 || sc == n || image[sr][sc] != oldcolor) {
            return;
        }
        image[sr][sc] = color;
        for (int[] dir : dirs) {
            int nr = sr + dir[0];
            int nc = sc + dir[1];
            dfs(image, nr, nc, color, oldcolor);
        }
    }
}