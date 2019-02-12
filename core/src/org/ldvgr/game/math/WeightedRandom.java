package org.ldvgr.game.math;

import java.util.Random;

public class WeightedRandom<T> {
    private static final Random random = new Random();
    private T[] objects;
    private float[] objectWeights;

    public WeightedRandom(T[] objects, float[] objectWeights) {
        this.objects = objects;
        this.objectWeights = objectWeights;
    }


    float rnd = Rnd.nextFloat(0, 1);


}
