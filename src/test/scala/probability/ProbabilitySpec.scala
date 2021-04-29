package probability

import org.scalatest.flatspec
import org.scalatest.matchers.should

class ProbabilitySpec extends flatspec.AnyFlatSpec with should.Matchers {

    behavior of "Probability"

    it should "$minus" in {
        Probability.evens - Probability.evens shouldBe Probability.impossible
    }

    it should "$plus" in {
        Probability.evens + Probability.evens shouldBe Probability.certain
    }

    it should "$times" in {
        Probability.evens * Probability.evens shouldBe 1 :: 3
    }

    it should "toDouble" in {
        Probability(0.5).toDouble shouldBe 0.5
    }

    it should "$bar" in {
        Probability.evens | Probability.evens shouldBe 3 :: 1
    }

    it should "$amp" in {
        Probability.evens & Probability.evens shouldBe 1 :: 3
    }

    it should "twoToOneAgainst" in {
        3 :/ 1 shouldBe Probability(0.25)
    }

    it should "impossible" in {
        Probability.impossible.toDouble shouldBe 0.0
    }

    it should "certain" in {
        Probability.certain.toDouble shouldBe 1.0
    }

    it should "evens" in {
        Probability.evens.toDouble shouldBe 0.5
    }

    it should "maybe" in {
        Probability.maybe(0.5) shouldBe Some(Probability.evens)
        Probability.maybe(-0.5) shouldBe None
        Probability.maybe(2) shouldBe None
    }

    it should "twoToOneOn" in {
        1 :/ 2 shouldBe 2 :: 1
    }

    it should "toString" in {
        (1 :/ 2).toString shouldBe "0.6666666666666666"
    }
}
