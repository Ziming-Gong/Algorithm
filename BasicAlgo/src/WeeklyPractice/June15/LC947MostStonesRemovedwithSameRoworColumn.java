package WeeklyPractice.June15;

import java.util.HashMap;

//https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class LC947MostStonesRemovedwithSameRoworColumn {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        HashMap<Integer, Integer> rowMap = new HashMap<>();
        HashMap<Integer, Integer> colMap = new HashMap<>();
        for (int index = 0; index < n; index++) {
            int i = stones[index][0];
            int j = stones[index][1];
            if (!rowMap.containsKey(i)) {
                rowMap.put(i, index);
            } else {
                uf.union(index, rowMap.get(index));
            }
            if (!colMap.containsKey(j)) {
                colMap.put(j, index);
            } else {
                uf.union(index, colMap.get(index));
            }
        }
        return uf.size();
    }

    public class UnionFind {
        public int[] father;
        public int[] size;
        public int[] help;
        public int sets;

        public UnionFind(int n) {
            father = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        public void union(int from, int to) {
            int fatherA = find(from);
            int fatherB = find(to);
            if (fatherA != fatherB) {
                if (size[fatherA] >= size[fatherB]) {
                    father[fatherB] = fatherA;
                    size[fatherA] += size[fatherB];
                } else {
                    father[fatherA] = fatherB;
                    size[fatherB] += size[fatherA];
                }
                sets--;
            }

        }

        /**
         *
         */
        public int find(int index) {
            int helpPoint = 0;
            while (father[index] != index) {
                index = father[index];
                help[helpPoint++] = index;
            }
            while (helpPoint != 0) {
                father[help[--helpPoint]] = index;
            }
            return index;

        }

        public int size() {
            return sets;
        }


    }
}
