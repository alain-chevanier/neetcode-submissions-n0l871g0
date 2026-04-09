class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        Pair<Integer,Integer>[] orderedCars = new Pair[speed.length];
        for (int i = 0; i < speed.length; i++) {
            orderedCars[i] = new Pair<>(position[i], speed[i]);
        }
        Arrays.sort(orderedCars, (a, b) -> b.getKey() - a.getKey());

        //System.out.println(Arrays.toString(orderedCars));

        int fleets = 0;
        Pair<Integer,Integer> leadingCar = null;
        for (var car : orderedCars) {
            if (leadingCar == null ) {
                leadingCar = car;
                continue;
            } 
            if (isCarReached(leadingCar, car, target)) {
                continue;
            } else {
                fleets++;
                leadingCar = car;
            }
        }
        return fleets + (leadingCar != null ? 1 : 0);
    }

    boolean isCarReached(Pair<Integer,Integer> leadingCar, 
                        Pair<Integer,Integer> car, 
                        int target) {
        
        //System.out.println ("leadingCar: " + leadingCar);
        //System.out.println ("trailingCar: " + car);

        boolean sameSpeed = leadingCar.getValue() == car.getValue();
        
        if (sameSpeed) {
            boolean sameInitialPosition = leadingCar.getKey() == car.getKey();
            //System.out.println ("Same speed reached: " + sameInitialPosition);
            return sameInitialPosition;
        } else {
            float reachedAt = 1.0f * (car.getKey() - leadingCar.getKey()) 
                                / (leadingCar.getValue() - car.getValue());
            
            float dist = car.getKey() + reachedAt*car.getValue();
            //System.out.println ("reachedAt: " + reachedAt);
            //System.out.println ("position: " + dist);
            return (car.getKey() <= dist)
                    && (dist <= target);
        }
    }

}
