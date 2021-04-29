package probability

/**
 * Class to represent a probability with a real number between zero and one.
 *
 * @param self the Double value of this Probability.
 */
class Probability(private val self: Double) extends AnyVal {
    /**
     * The sum of this probability and x.
     *
     * @param p a Probability.
     * @return a Probability.
     */
    def +(p: Probability): Probability = ??? // TODO implement me 3 points

    /**
     * The difference of this probability and x.
     *
     * @param p a Probability.
     * @return a Probability (this - p)
     */
    def -(p: Probability): Probability = Probability(self - p.self)

    /**
     * The product of this probability and x.
     *
     * @param p a Probability.
     * @return a Probability.
     */
    def *(p: Probability): Probability = new Probability(self * p.self)

    /**
     * The conjunction of this probability and x.
     *
     * @param p a Probability.
     * @return a Probability (this * p).
     */
    def &(p: Probability): Probability = this * p

    /**
     * The disjunction of this probability and x.
     *
     * @param p a Probability.
     * @return a Probability (this + p - this * p).
     */
    def |(p: Probability): Probability = this + p - this * p

    /**
     * Method to get this Probability in the form of a Double.
     *
     * @return self.
     */
    def toDouble: Double = self

    override def toString: String = self.toString
}

/**
 * Companion object to class Probability.
 */
object Probability {
    val impossible: Probability = 0 :: 1
    val certain: Probability = Probability(1)
    val evens: Probability = 1 :: 1
    val twoToOneAgainst: Probability = 2 :/ 1
    val twoToOneOn: Probability = 2 :: 1

    implicit def convertFromDouble(x: Double): Probability = ???  // TODO implement me 3 points

    /**
     * Method to yield an (optional) Probability.
     *
     * @param x a Double of any value.
     * @return Some(p) if x is within the range 0..1 otherwise return None.
     */
    def maybe(x: Double): Option[Probability] = ??? // TODO implement me 6 points

    def apply(x: Double): Probability = new Probability(math.max(math.min(x, 1), 0))

}
