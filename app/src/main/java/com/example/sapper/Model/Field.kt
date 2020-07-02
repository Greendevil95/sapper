import kotlin.random.Random
import kotlin.random.nextInt

class Field( val length: Int,  val height: Int,  val bombCount: Int){


    var allBlocks: ArrayList<Block> = ArrayList(0)
    private val bomb = '*'



    fun checkField() {
        //val field = generateAllBlocks(bombCount, length, height, indexOfFirstClick)
        for (i in 1..length * height) {
            print(allBlocks[i - 1].value)
            if (i % length == 0) println()
            }
        }

     fun generateField(): ArrayList<Block> {
        for (i in 1..length * height) {
            allBlocks.add(Block())
        }
        //allBlocks = randomGenerateBomb(allBlocks,indexOfFirstClick)
        //generateNumbersBombNear(allBlocks)
        return allBlocks

    }

    fun randomGenerateBomb( indexOfFirstClick: Int) {
        while (allBlocks.count{it.value == bomb} != bombCount)
        {
            val randomIndex = Random.nextInt(0 until length*height)
            if (randomIndex != indexOfFirstClick)
            allBlocks.set(randomIndex,Block(bomb))
        }
        generateNumbersBombNear(allBlocks)
    }

    private fun generateNumbersBombNear(allBlock: ArrayList<Block>) {
        for (index in allBlock.indices){
            if (allBlock.get(index).value == bomb) {
                if ( index > length && ((index % length != 0)) && allBlock.get(index - (length + 1)).value is Int)
                    allBlock.set(index - (length + 1), (Block((allBlock.get(index - (length + 1)).value as Int).inc())))
                if (index >= length ) {
                     if ((((index + 1) % length != 0)) && allBlock.get(index - (length - 1)).value is Int)
                        allBlock.set(index - (length - 1), (Block((allBlock.get(index - (length - 1)).value as Int).inc())))
                     if (allBlock.get(index - (length)).value is Int)
                        allBlock.set(index - (length), (Block((allBlock.get(index - length).value as Int).inc())))
                }
                if (index + length < length*height ){
                    if (((index % length != 0)) && allBlock.get(index + (length - 1)).value is Int)
                        allBlock.set(index + (length - 1), (Block((allBlock.get(index + (length - 1)).value as Int).inc())))
                    if ((((index + 1) % length != 0)) && allBlock.get(index + (length + 1)).value is Int)
                        allBlock.set(index + (length + 1), (Block((allBlock.get(index + (length + 1)).value as Int).inc())))
                    if (allBlock.get(index + (length)).value is Int)
                        allBlock.set(index + (length), (Block((allBlock.get(index + length).value as Int).inc())))
                }
                if (index % length != 0 && allBlock.get(index - 1).value is Int)
                    allBlock.set(index - 1,(Block((allBlock.get(index - 1).value as Int).inc())))
                if ((index+1) % length != 0 && allBlock.get(index + 1).value is Int)
                    allBlock.set(index + 1,(Block((allBlock.get(index + 1).value as Int).inc())))
            }
            }

    }


}


