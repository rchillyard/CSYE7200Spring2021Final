package probability

import org.scalatest.flatspec
import org.scalatest.matchers.should
import Probability.evens
import scala.util.Random

class WheelOfFortuneSPec extends flatspec.AnyFlatSpec with should.Matchers {

    behavior of "apply"

    it should "yield a WheelOfFortune based on a discrete PDF" in {
        implicit val r: Random = new Random(0L)
        val coinFlip = new DiscretePDF(Probability.evens -> "Heads", Probability.evens -> "Tails")
        val wheel = WheelOfFortune(coinFlip)
        wheel.events shouldBe Seq(Probability.evens -> "Heads", Probability.evens -> "Tails")
    }

    behavior of "spin"

    it should "work for a coin flip" in {
        implicit val r: Random = new Random(0L)
        val wheel = WheelOfFortune(evens -> "Heads", evens -> "Tails")
        wheel.spin shouldBe "Tails"
        wheel.spin shouldBe "Heads"
        wheel.spin shouldBe "Tails"
        wheel.spin shouldBe "Heads"
        wheel.spin shouldBe "Heads"
        wheel.spin shouldBe "Heads"
        wheel.spin shouldBe "Heads"
        wheel.spin shouldBe "Tails"
        wheel.spin shouldBe "Tails"
    }

    it should "work for a set of events" in {
        implicit val r: Random = new Random(0L)
        val wheel = WheelOfFortune((1 :: 1) -> "A", (1 :: 3) -> "B", (1 :: 3) -> "C")
        val result: Seq[String] = for (_ <- 0 to 1000; x = wheel.spin) yield x
        result.count(_ == "A") shouldBe 477
        result.count(_ == "B") shouldBe 272
        result.count(_ == "C") shouldBe 252
    }
}
