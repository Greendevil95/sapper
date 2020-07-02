class Game(var field: Field) {

    var gameOver: Boolean = false


    fun onFirstClick( indexOfTheFirstClick: Int){
        field.randomGenerateBomb(indexOfTheFirstClick)
    }

    fun onClick(index: Int){
        if (field.allBlocks[index].value != '*')
            chanheHidden(index)
        else gameOver = true
    }

    fun changeFlag(index: Int){
        field.allBlocks[index].flag = !field.allBlocks[index].flag
    }

    fun chanheHidden(index: Int){
        field.allBlocks[index].hidden = !field.allBlocks[index].hidden
    }


    fun newGame(){
        gameOver = false
        field.allBlocks.clear()
        field.generateField()
        field.checkField()
    }











}