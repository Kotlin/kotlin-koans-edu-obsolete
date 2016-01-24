<answer>fun Int.r(): RationalNumber = <taskWindow>RationalNumber(this, 1)</taskWindow>
fun Pair<Int, Int>.r(): RationalNumber = <taskWindow>RationalNumber(first, second)</taskWindow></answer>

data class RationalNumber(val numerator: Int, val denominator: Int)