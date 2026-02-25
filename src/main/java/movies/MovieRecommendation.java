package movies;

import java.util.*;

public class MovieRecommendation {

    public static void main(String[] args) {

        // Step 1: Sample Movie Ratings
        // userID -> (movieID -> rating)
        Map<Integer, Map<Integer, Integer>> userRatings = new HashMap<>();

        userRatings.put(1, Map.of(1, 5, 2, 3, 3, 4));
        userRatings.put(2, Map.of(1, 4, 2, 5, 4, 3));
        userRatings.put(3, Map.of(1, 2, 3, 5, 4, 4));
        userRatings.put(4, Map.of(2, 5, 3, 3, 4, 4));

        int targetUser = 1;

        System.out.println("Finding recommendations for User " + targetUser);

        // Step 2: Find most similar user
        int mostSimilarUser = -1;
        double highestSimilarity = 0;

        for (int otherUser : userRatings.keySet()) {

            if (otherUser == targetUser)
                continue;

            double similarity = calculateSimilarity(
                    userRatings.get(targetUser),
                    userRatings.get(otherUser));

            if (similarity > highestSimilarity) {
                highestSimilarity = similarity;
                mostSimilarUser = otherUser;
            }
        }

        System.out.println("Most similar user: " + mostSimilarUser);

        // Step 3: Recommend movies
        Map<Integer, Integer> targetMovies = userRatings.get(targetUser);
        Map<Integer, Integer> similarUserMovies = userRatings.get(mostSimilarUser);

        System.out.println("\nRecommended Movies:");

        for (int movie : similarUserMovies.keySet()) {
            if (!targetMovies.containsKey(movie)) {
                System.out.println("Movie ID: " + movie);
            }
        }
    }

    // Simple similarity calculation
    public static double calculateSimilarity(
            Map<Integer, Integer> user1,
            Map<Integer, Integer> user2) {

        int commonMovies = 0;

        for (int movie : user1.keySet()) {
            if (user2.containsKey(movie)) {
                commonMovies++;
            }
        }

        return commonMovies; // simple similarity score
    }
}