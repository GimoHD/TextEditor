class Random {
    private static final java.util.Random rand = new java.util.Random();

    /**
     * Returns a random number.
     *
     * @param min minimum integer
     * @param max maximum integer
     * @return a random number between min and max
     */
    public static int nextInt(final int min, final int max) {
        final int a = min < max ? min : max, b = max > min ? max : min;
        return a + (b == a ? 0 : rand.nextInt(b - a));
    }

}