class Solution {
    public int trap(int[] height) {
        if(height.length < 3) {
            return 0;
        }
        var values = new ArrayList<Pair<Integer,Integer>>(height.length);
        for (int i = 0; i < height.length; i++) {
            values.add(new Pair(i, height[i]));
        }
        values.sort((a, b) -> b.getValue() - a.getValue());
        
        int totalArea = 0;
        boolean[] used = new boolean[height.length];
        var leftMost = values.remove(0);
        var rightMost = leftMost;
        used[leftMost.getKey()] = true;
        while (!values.isEmpty()) {
            var val = values.remove(0);
            if (used[val.getKey()]) {
                continue;
            }

            used[val.getKey()] = true;
            if (val.getKey() < leftMost.getKey()) {
                totalArea += calcArea(val, leftMost, height, used);
                leftMost = val;
            } else {
                totalArea += calcArea(rightMost, val, height, used);
                rightMost = val;
            }
        }
        return totalArea;
    }

    int calcArea(Pair<Integer,Integer> left, 
                Pair<Integer,Integer> right,
                int[] height,
                boolean[] used) {
        int area = 0;
        int minHeight = Math.min(left.getValue(),
                                    right.getValue()); 
        // System.out.println("leftMost: " + leftMost);
        // System.out.println("rightMost: " + rightMost);
        // System.out.println("used: " + Arrays.toString(used));
        for (int i = left.getKey() + 1; i < right.getKey(); i++) {
            used[i] = true;
            area += minHeight - height[i];
        }
        return area;
    }
}
