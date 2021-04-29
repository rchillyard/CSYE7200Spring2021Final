package probability

/**
 * A trait which models a probability density function.
 *
 * @tparam T the type of the parameter to the pdf method.
 */
trait ProbabilityDensityFunction[T] {
    /**
     * Method to return the probability value for the event t.
     *
     * @param t an event: either a discrete value or,
     *          if the PDF is continuous, an instantaneous value of T, together with a value of deltaT.
     * @return a Probability.
     */
    def pdf(t: T): Probability
}

class DiscretePDF[T](val events: (Probability, T)*) extends ProbabilityDensityFunction[T] {

    /**
     * Method to return the probability value for the event t.
     *
     * @param t an event: either a discrete value or,
     *          if the PDF is continuous, an instantaneous value of T, together with a value of deltaT.
     * @return a Probability.
     */
    def pdf(t: T): Probability = eventProbabilities.getOrElse(t, Probability.impossible)

    /**
     * Method to yield the Cartesian Product of this discrete PDF with another discrete PDF.
     * @param pdf the other DiscretePDF.
     * @tparam U the underlying type of the other PDF.
     * @return a DiscretePDF which is the Cartesian Product and is of type: DiscretePDF[(T,U)].
     */
    def cartesianProduct[U](pdf: DiscretePDF[U]): DiscretePDF[(T, U)] = {
        val z: Seq[(Probability, (T, U))] = ??? // TODO implement me 12 points
        new DiscretePDF(z: _*)
    }

    val eventProbabilities: Map[T, Probability] = (for ((p, t) <- events) yield t -> p).toMap
}

object DiscretePDF {

    /**
     * Create a discrete PDF such that
     * @param ts
     * @tparam T
     * @return
     */
    def uniformPDF[T](ts: Seq[T]): DiscretePDF[T] = {
        val events: Seq[(Probability, T)] = ??? // TODO implement me 7 points
        new DiscretePDF(events: _*)
    }
}

abstract class ContinuousDoublePDF[X: Numeric](f: X => Double) extends ProbabilityDensityFunction[(X, X)] {
    /**
     * Method to return the probability value for the event t.
     *
     * @param t an event: either a discrete value or, if the PDF is continuous, an instantaneous value of T.
     * @return a Probability.
     */
    def pdf(t: (X, X)): Probability = f(t._1) * nx.toDouble(t._2)

    private val nx: Numeric[X] = ??? // TODO implement me 5 points
}

case class Gaussian(mean: Double, stdDev: Double) extends ContinuousDoublePDF[Double](Gaussian.pdf(mean, stdDev))

object Gaussian {
    /**
     * Method to yield the Normal pdf function, given mean and std. deviation.
     *
     * @param mean   the mean of the distribution.
     * @param stdDev the standard deviation (i.e. the square root of the variance) of the distribution.
     * @return a probability density between 0 and 1.
     */
    def pdf(mean: Double, stdDev: Double): Double => Double = x => math.exp(-sqr((x - mean) / stdDev) / 2) / stdDev / math.sqrt(2 * math.Pi)

    private def sqr(x: Double): Double = x * x
}