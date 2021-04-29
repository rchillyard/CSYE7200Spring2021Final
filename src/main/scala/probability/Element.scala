package probability

import probability.WheelOfFortune.multiplier
import scala.util.Random

abstract class Element(implicit r: Random) {
    /**
     * Own Random to ensure no other class is changing the state of our source of random numbers.
     */
    val random: Random = new Random(r.nextLong())
}

/**
 * This class simulates a set of events, each with particular odds of occurrence.
 *
 * @param events any number of tuples of form Double -> Event, separated by commas.
 * @tparam Event the underlying type of an event.
 */
case class WheelOfFortune[Event](events: (Probability, Event)*)(implicit r: Random) extends Element {

    /**
     * This method will "spin" the wheel and generate an event according to the probabilities
     * defined by the given probabilities.
     *
     * @return an Event
     */
    def spin: Event = lookup(random.nextLong(outcomes))

    private val intEvents: Seq[(Long, Event)] = for ((x, e) <- events) yield ((x.toDouble * multiplier).toInt, e)
    private val outcomes: Long = (for ((x, _) <- intEvents) yield x).sum

    private def lookup(i: Long): Event = {
        @scala.annotation.tailrec
        def inner(es: Seq[(Long, Event)], x: Long): Event = es match {
            case Nil => throw new Exception(s"cannot get event for $i in $this")
            case (a, b) :: t => if (x < a) b else inner(t, x - a)
        }

        inner(intEvents.toList, i)
    }
}

object WheelOfFortune {
    val multiplier: Long = 1000000000L

    def apply[Event](pdf: DiscretePDF[Event])(implicit r: Random): WheelOfFortune[Event] =
        WheelOfFortune(pdf.events: _*)
}