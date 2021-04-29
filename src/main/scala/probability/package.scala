package object probability {

    /**
     * Implicit class to represent the "odds" of an event.
     *
     * @param x the value.
     */
    implicit class Odds(x: Int) {
        /**
         * Method to yield the probability represented by x chances in (x + y).
         * Note that the double-colon is necessary because a single colon cannot be a method.
         * NOTE: because : methods associate to the right, in the expression a :: b,
         * a will be y and b will be x.
         *
         * @param y the chances of the desired event (whereas x is the chances of the "zone").
         * @return a Probability which is y/(x+y)
         */
        def ::(y: Int): Probability = Probability(y.toDouble / (x + y))

        /**
         * Method to yield the probability represented by y chances in (x + y).
         * The "slash" form of odds is used by bookmakers.
         * 5/1 (against) means that the probability of the even is 1 in 6.
         * However, if the event occurs, the punter will receive back 5 times his stake plus his stake.
         * In other words, if the probability was correct, the punter will expect to break even.
         *
         * @param y the chances of the "zone" (i.e. not a desired event).
         * @return a Probability which is y/(x+y)
         */
        def :/(y: Int): Probability = Probability(y.toDouble / (x + y))
    }

}
