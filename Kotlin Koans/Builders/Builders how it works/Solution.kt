package builders.builders_how_it_works

import builders.builders_how_it_works.Answer.*

enum class Answer { a, b, c }

val answers = mapOf<Int, Answer?>(
        /*<answer><taskWindow>*/1 to c, 2 to b, 3 to b, 4 to c/*</taskWindow></answer>*/
)