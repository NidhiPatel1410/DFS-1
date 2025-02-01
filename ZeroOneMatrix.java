// In this approach, we are trying to put the indices of all 0s which are independent in the queue. The distance of nearest 0 of a cell having
// value 0 is 0, no need to calculate because it is itself zero. So, start bfs after putting all indices of all 0s. Calculate the size and run a 
// loop. Poll the element, check it's 4 adjacent neighbors, if any of them is 1, that means we can say there nearest zero distance is 1 i.e level
// + 1. Initially turn all 1 to -1, so now when -1 located turn it back to level+1 and put it's indice in queue. 

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        // Base Case
        if (mat == null || mat.length == 0) {
            return mat;
        }
        // Declare queue for BFS
        Queue<int[]> q = new LinkedList<>();
        // Declare m and n
        int m = mat.length;
        int n = mat[0].length;
        // Declare level to calculate the distance
        int level = 0;
        // Dirs array for looking in 4 directions, U D L R
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        // Traverse the matrix and put all 0s in queue and change all 1s to -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[] { i, j });
                } else if (mat[i][j] == 1) {
                    mat[i][j] = -1;
                }
            }
        }
        // Loop till queue is empty
        while (!q.isEmpty()) {
            // Calculate size for differentiating between levels, for distance
            int size = q.size();
            // Run a for loop till size
            for (int i = 0; i < size; i++) {
                // Poll the current zero
                int[] curr = q.poll();
                // Look at it's neighbors
                for (int[] dir : dirs) {
                    // Calc new row and new col
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    // Check if it is a valid row and col and if it's value is -1
                    if ((nr >= 0 && nr < m) && (nc >= 0 && nc < n) && mat[nr][nc] == -1) {
                        // Add it's indices to the queue
                        q.add(new int[] { nr, nc });
                        // And change it's value to level + 1
                        mat[nr][nc] = level + 1;// or mat[curr[0]][curr[1]] + 1; if we dont declare level
                    }
                }

            }
            // After for loop, increase the level
            level++;
        }
        // Return mat
        return mat;
    }
}