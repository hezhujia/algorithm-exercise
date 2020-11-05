package com.algrothm.exercise.unionfindset;

import com.algrothm.exercise.search.Pair;
import com.algrothm.exercise.utils.ExecTestCases;
import com.algrothm.exercise.utils.ParseArgs;
import com.algrothm.exercise.utils.TestCase;

import java.util.*;

public class MatrixRankTransform {

    public static int[][] matrixRankTransform(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[] parent = new int[row * col];
        int[] size = new int[row * col];
        for (int i = 0; i < row * col; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < row; i++) {
            Map<Integer, List<Integer>> mp = new HashMap<>();
            for (int j = 0; j < col; j++) {
                if (mp.containsKey(matrix[i][j])) {
                    mp.get(matrix[i][j]).add(i * col + j);
                } else {
                    mp.put(matrix[i][j], new ArrayList<>(i * col + j));
                }
            }
            mp.forEach((key, value) -> {
                for (int j = 0; j < value.size() - 1; j++) {
                    connect(value.get(j), value.get(j+1), parent, size);
                }
            });
        }

        for (int j = 0; j < col; j++) {
            Map<Integer, List<Integer>> mp = new HashMap<>();
            for (int i = 0; i < col; i++) {
                if (mp.containsKey(matrix[i][j])) {
                    mp.get(matrix[i][j]).add(i * col + j);
                } else {
                    mp.put(matrix[i][j], new ArrayList<>(i * col + j));
                }
            }
            mp.forEach((key, value) -> {
                for (int i = 0; i < value.size() - 1; i++) {
                    connect(value.get(i), value.get(i+1), parent, size);
                }
            });
        }

        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[row * col];
        for (int i = 0; i < row; i++) {
            List<Pair<Integer, Integer>> sortedCol = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                sortedCol.add(new Pair<>(matrix[i][j], j));
            }
            sortedCol.sort(Comparator.comparingInt(Pair::getKey));
            for (int j = 0; j < col - 1; j++) {
                if (!sortedCol.get(j).getKey().equals(sortedCol.get(j + 1).getKey())) {
                    int uu = findParent(i * col + sortedCol.get(j).getValue(), parent);
                    int vv = findParent(i * col + sortedCol.get(j+1).getValue(), parent);
                    if (!adj.containsKey(uu)) {
                        adj.put(uu, new ArrayList<>());
                    }
                    adj.get(uu).add(vv);
                    indegree[vv]++;
                }
            }
        }
        for (int j = 0; j < col; j++) {
            List<Pair<Integer, Integer>> sortedCol = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                sortedCol.add(new Pair<>(matrix[i][j], i));
            }
            sortedCol.sort(Comparator.comparingInt(Pair::getKey));
            for (int i = 0; i < row - 1; i++) {
                if (!sortedCol.get(i).getKey().equals(sortedCol.get(i + 1).getKey())) {
                    int uu = findParent(sortedCol.get(i).getValue() * col + j, parent);
                    int vv = findParent(sortedCol.get(i+1).getValue() * col + j, parent);
                    if (!adj.containsKey(uu)) {
                        adj.put(uu, new ArrayList<>());
                    }
                    adj.get(uu).add(vv);
                    indegree[vv]++;
                }
            }
        }
        int[] ans = new int[row * col];
        Arrays.fill(ans, 1);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < row * col; i++) {
            if (findParent(i, parent) == i && indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                ans[v] = Math.max(ans[v], ans[u] + 1);
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = ans[findParent(i * col + j, parent)];
            }
        }

        return result;
    }

    private static int findParent(int idx, int[] parent) {
        if (parent[idx] == idx) {
            return idx;
        }
        return findParent(parent[idx], parent);
    }

    private static void connect(int a, int b, int[]parent, int[] size) {
        int parentA = findParent(a, parent);
        int parentB = findParent(b, parent);
        if (parentA != parentB) {
            if (size[parentA] > size[parentB]) {
                parent[parentB] = parentA;
                size[parentA] += size[parentB];
            } else {
                parent[parentA] = parentB;
                size[parentB] += size[parentA];
            }
        }
    }

    static class TempTestCase implements TestCase<int[][]> {

        int[][] testMatrix;
        int[][] exceptedResult;

        TempTestCase(String testMatrix, String exceptedResult) {
            this.testMatrix = ParseArgs.changeStringToTwoDimensionIntArray(testMatrix);
            this.exceptedResult = ParseArgs.changeStringToTwoDimensionIntArray(exceptedResult);
        }

        @Override
        public int[][] getExceptedResult() {
            return exceptedResult;
        }

        @Override
        public String resultToString(int[][] ints) {
            return ParseArgs.changeTwoDimensionIntArrayToString(ints);
        }

        @Override
        public String toString() {
            return "TempTestCase{" +
                    "testMatrix=" + ParseArgs.changeTwoDimensionIntArrayToString(testMatrix) +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<TempTestCase> testCases = new ExecTestCases.TestCaseArrayBuilder<TempTestCase>()
                .addCase(new TempTestCase("[[1,2],[3,4]]", "[[1,2],[2,3]]"))
                .addCase(new TempTestCase("[[7,7],[7,7]]", "[[1,1],[1,1]]"))
                .addCase(new TempTestCase("[[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]", "[[4,2,3],[1,3,4],[5,1,6],[1,3,4]]"))
                .addCase(new TempTestCase("[[7,3,6],[1,4,5],[9,8,2]]", "[[5,1,4],[1,2,3],[6,3,1]]"))
                .build();
    }
}
