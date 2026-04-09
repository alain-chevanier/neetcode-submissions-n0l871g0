class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // if "h == size" then "k = max(piles)"
        // if h is greater than piles.length (size)
        // we still have "size - h" remaining moves
        
        // if a piles has "x" bananas, koko spends "ceil(x/k)" hours to eat all the bananas
        // sum(ceil(x/k) for all x in piles) = h
        // k*h >= sum(piles) such that k is minimum
        // then "k = ceil(sum(piles) / h)"" if we can eat at least that amount each hour
        // "k" in "[ceil(sum(piles) / h), max(piles)]"
        long sum = 0;
        int maxPile = 0;
        for (int x : piles) {
            maxPile = Math.max(maxPile, x);
            sum += x;
        }
        int minK = (int) Math.ceil(sum * 1.0 / h);
        int maxK = maxPile;
        // minK =   666,666,667
        // maxK = 1,000,000,000

        return search(minK, maxK, piles, h);
    }

    int search(int min, int max, int[] piles, int target) {
        if (min == max) {
            return min;
        }
        int midValue = (max + min) / 2;
        int nededHoursToEat = timeToEatAll(midValue, piles);
        if (nededHoursToEat > target) {
            return search(midValue+1, max, piles, target);
        } else if(nededHoursToEat <= target) {
            return search(min, midValue, piles, target);
        }
        return min;
    }

    int timeToEatAll(int k, int[] piles) {
        int time = 0;
        for (int p : piles) {
            time += (int) Math.ceil(p*1.0 / k);
        }
        return time;
    }
}