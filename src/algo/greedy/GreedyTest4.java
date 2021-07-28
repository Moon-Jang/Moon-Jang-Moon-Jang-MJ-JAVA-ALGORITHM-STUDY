package algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GreedyTest4 {

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
        List<Person> p = Arrays.stream(people).mapToObj(m -> new Person(m)).collect(Collectors.toList());
        p.sort((a, b) -> a.getWeight() - b.getWeight());
        List<Boat> successBoat = new ArrayList<>();
        rescue(p, limit, successBoat);
        return successBoat.size();
    }

    private void rescue(List<Person> people, int limit, List<Boat> successBoat) {
        int peopleSize = people.size();
        int j = peopleSize-1;
        Boat boat = new Boat(limit);
        for (int i = 0; i < peopleSize; i++) {
            Person person = people.get(i);
            if (person.getIsRescue()) {
                continue;
            }
            boat = new Boat(limit);
            person.ride(boat);
            j = rideWithPartner(people, boat, i, j);
            successBoat.add(boat);
        }
    }


    private int rideWithPartner(List<Person> people, Boat boat, int i, int j) {
        for (; j > i; j--) {
            Person person = people.get(j);
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
            int totalWeight = passengers.stream().mapToInt(Person::getWeight).sum() + person.getWeight();
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

        public void rollback() {
            passengers.stream().forEach(p -> p.setRescue(false));
            passengers.clear();
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
