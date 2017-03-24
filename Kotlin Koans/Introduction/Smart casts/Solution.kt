package introduction.smart_casts

/*<answer>*/fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> /*<taskWindow>*/expr.value/*</taskWindow>*/
            is Sum -> /*<taskWindow>*/eval(expr.left) + eval(expr.right)/*</taskWindow>*/
            else -> throw IllegalArgumentException("Unknown expression")
        }/*</answer>*/

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
