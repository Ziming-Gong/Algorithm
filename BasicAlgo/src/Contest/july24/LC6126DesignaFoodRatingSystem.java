package Contest.july24;

import Questions.code_5.Hash;
import basicAlgo.heapgreater.HeapGreater;
import basicAlgo.heapgreater.ShowBoss;
import basicAlgo.mergesorted.ans;
import sun.jvm.hotspot.debugger.remote.arm.RemoteARMThread;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LC6126DesignaFoodRatingSystem {

    public class FoodRatings {
        ArrayList<PriorityQueue<Food>> list = new ArrayList<>();
        HashMap<String, Integer> cuisinesMap = new HashMap<>();
        HashMap<String, Food> foodMap = new HashMap<>();
        HashMap<String, String> foodCuisineMap = new HashMap<>();
        int heapSize = 0;


        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; i++) {
                if (!cuisinesMap.containsKey(cuisines[i])) {
                    cuisinesMap.put(cuisines[i], heapSize++);
                    list.add(new PriorityQueue<>((a, b) -> (a.rating != b.rating ? b.rating - a.rating : a.name.compareTo(b.name))));
                }
                int cuisineIndex = cuisinesMap.get(cuisines[i]);
                Food food = new Food(ratings[i], foods[i]);
                list.get(cuisineIndex).add(food);
                foodMap.put(foods[i], food);
                foodCuisineMap.put(foods[i], cuisines[i]);
            }
        }

        public void changeRating(String foodName, int newRating) {
            Food food = foodMap.get(foodName);
            String cuisine = foodCuisineMap.get(foodName);
            int cuisineIndex = cuisinesMap.get(cuisine);
            list.get(cuisineIndex).remove(food);
            food.rating = newRating;
            list.get(cuisineIndex).add(food);
        }

        public String highestRated(String cuisine) {
            int cuisineIndex = cuisinesMap.get(cuisine);
            return list.get(cuisineIndex).peek().name;
        }

        public class Food {
            public int rating;
            public String name;

            public Food(int r, String n) {
                rating = r;
                name = n;
            }
        }


    }

}
