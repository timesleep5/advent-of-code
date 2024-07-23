package day_11;

import java.math.BigInteger;
import java.util.HashSet;

class UniverseManager {
    private final Universe universe;
    private final HashSet<GalaxyPair> galaxyPairs;
    private final int expansionFactor;
    private BigInteger shortestPathSum;

    UniverseManager(String[] input, int expansionFactor) {
        universe = new Universe(input);
        galaxyPairs = new HashSet<>();
        shortestPathSum = BigInteger.ZERO;
        this.expansionFactor = expansionFactor;
    }

    BigInteger getShortestPathSum() {
        collectPairs();
        calculateShortestPaths();
        return shortestPathSum;
    }

    private void collectPairs() {
        var galaxies = universe.getGalaxies();
        for (var first : galaxies) {
            for (var second : galaxies) {
                if (!first.equals(second)) {
                    galaxyPairs.add(new GalaxyPair(first, second));
                }
            }
        }
    }

    private void calculateShortestPaths() {
        for (GalaxyPair galaxyPair : galaxyPairs) {
            int naivePath = galaxyPair.shortestPath();
            int expansionsInPath = universe.expansionsBetween(galaxyPair);
            BigInteger additionalStepsForExpansions = BigInteger.valueOf(expansionsInPath)
                    .multiply(BigInteger.valueOf(expansionFactor - 1)); // since 1 step per expansion is already contained in naive path
            BigInteger totalPath = BigInteger.valueOf(naivePath).add(additionalStepsForExpansions);
            shortestPathSum = shortestPathSum.add(totalPath);
        }
    }

    @Override
    public String toString() {
        return universe.toString();
    }
}
