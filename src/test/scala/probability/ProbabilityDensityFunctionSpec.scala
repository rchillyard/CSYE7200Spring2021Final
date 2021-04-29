package probability

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class ProbabilityDensityFunctionSpec extends AnyFlatSpec with should.Matchers {

    behavior of "Gaussian"

    it should "pdf" in {
        val pdf: ((Double, Double)) => Probability = Gaussian(1, 1).pdf
        pdf((1, 0.01)).toDouble shouldBe 0.00398942280401433 +- 0.00000000000000001
    }

    behavior of "DiscretePDF"

    it should "pdf" in {
        val coinFlip = new DiscretePDF(Probability.evens -> "Heads", Probability.evens -> "Tails")
        coinFlip.pdf("Heads") shouldBe Probability.evens
        coinFlip.pdf("Tails") shouldBe Probability.evens
    }

    it should "do uniform" in {
        val suits = DiscretePDF.uniformPDF(Seq("clubs", "diamonds", "hearts", "spades"))
        suits.eventProbabilities("clubs") shouldBe 1 :: 3
    }

    it should "do cartesianProduct" in {
        val suits = DiscretePDF.uniformPDF(Seq("clubs", "diamonds", "hearts", "spades"))
        val ranks = DiscretePDF.uniformPDF(Seq("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"))
        val cards = suits.cartesianProduct(ranks)
        cards.eventProbabilities("clubs"->"A") shouldBe 1 :: 51
    }
}
