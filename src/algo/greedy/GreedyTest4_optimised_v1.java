package algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GreedyTest4_optimised_v1 {

    public void run() {
        final int[] people1 = {70, 50, 80, 50};
        final int[] people2 = {40, 50, 60, 140, 150, 160};
        final int[] people3 = {40, 40, 40, 40, 40, 40, 40};
        final int limit1 = 100;
        final int limit2 = 200;
        final int limit3 = 240;
        int result = solution(people3, limit3);
        System.out.println("result: " + result);
    }


    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        Person[] p = new Person[people.length];
        for( int i = 0; i <  people.length; i++ ) {
            p[i] = new Person(people[i]);
        }
        List<Boat> successBoat = new ArrayList<>();
        rescue(p, limit, successBoat);
        return successBoat.size();

    }


    private void rescue(Person[] people, int limit, List<Boat> successBoat) {
        int j = people.length-1;
        Boat boat = new Boat(limit);
        for (int i = 0; i < people.length; i++) {
            Person person = people[i];
            if (person.getIsRescue()) {
                continue;
            }
            boat = new Boat(limit);
            person.ride(boat);
            if ( i > j) {
                successBoat.add(boat);
                continue;
            }
            j = rideWithPartner(people, boat, i, j);
            successBoat.add(boat);
        }
    }


    private int rideWithPartner(Person[] people, Boat boat, int i, int j) {
        for (; j > i; j--) {
            Person person = people[j];
            if (person.getIsRescue()) {
                continue;
            }
            boolean isRide = person.ride(boat);
            if (isRide) {
                break;
            }
        }
        return j;
    }


    class Boat {
        final private int limit;
        static final int MAX_SHIP_CNT = 2;
        List<Person> passengers;

        Boat(int limit) {
            this.limit = limit;
            this.passengers = new ArrayList<>();
        }


        public int getLimit() {
            return limit;
        }


        public boolean isMax(Person person) {

            int totalWeight = passengers.size() > 0 ? passengers.get(0).getWeight() + person.getWeight() : person.getWeight();
            if (limit >= totalWeight) {
                return false;
            }
            return true;
        }


        public boolean isLimit() {
            if (passengers.size() < MAX_SHIP_CNT) {
                return false;
            }
            return true;
        }


        public List<Person> getPassengers() {
            return passengers;
        }

    }


    class Person {
        final private int weight;
        private boolean isRescue;

        Person(int weight) {
            this.weight = weight;
            this.isRescue = false;
        }


        public boolean ride(Boat boat) {
            if (boat.isLimit() || boat.isMax(this)) {
                return false;
            }
            List<Person> passengers = boat.getPassengers();
            passengers.add(this);
            isRescue = true;

            return true;
        }


        public int getWeight() {
            return weight;
        }


        public boolean getIsRescue() {
            return isRescue;
        }


        public void setRescue(boolean isRescue) {
            this.isRescue = isRescue;
        }
    }
}
