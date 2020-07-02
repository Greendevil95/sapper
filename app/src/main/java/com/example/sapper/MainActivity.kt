package com.example.sapper

import Block
import Field
import Game
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_entry.view.*


@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity() {

    private val TAG = "MyApp"

    var adapter: FieldAdapter? = null

    var game: Game? = null

    var field = ArrayList<Block>()

    var count = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        game = Game(Field(10, 15, 18))
        field = game!!.field.generateField()
        adapter = FieldAdapter(this, field)

        imageButton.setOnClickListener{
            newGame()
        }

        gridView1.adapter = adapter
        gridView1.numColumns = game!!.field.length


        gridView1.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->

            if (field[position].hidden) {
                if (count == 0) {
                    count++
                    game!!.onFirstClick(position)
                    setBlockImage(v, position)
                } else {
                    setBlockImage(v, position)
                    game!!.onClick(position)
                    if (game!!.gameOver)
                        gameOver()
                }
            }
        }


        gridView1.setOnItemLongClickListener { parent, v, position, id ->
            if (field[position].hidden) {
                if (!field[position].flag) {
                    game!!.changeFlag(position)
                    v.blockImg.setBackgroundResource(R.drawable.flag)
                } else {
                    game!!.changeFlag(position)
                    v.blockImg.setBackgroundResource(R.color.colorPrimary)
                }

            }
            true
        }

    }

    fun setBlockImage(v: View, position: Int) {
        if (field[position].value == '*')
            v.blockImg.setBackgroundResource(R.drawable.sap)
        else if (field[position].hidden && field[position].value == 0) v.blockImg.setBackgroundResource(
            R.color.fieldColor
        )
        else if (field[position].hidden && field[position].value == 1) v.blockImg.setBackgroundResource(
            R.drawable.one
        )
        else if (field[position].hidden && field[position].value == 2) v.blockImg.setBackgroundResource(
            R.drawable.two
        )
        else if (field[position].hidden && field[position].value == 3) v.blockImg.setBackgroundResource(
            R.drawable.three
        )
        else if (field[position].hidden && field[position].value == 4) v.blockImg.setBackgroundResource(
            R.drawable.four
        )
        else if (field[position].hidden && field[position].value == 5) v.blockImg.setBackgroundResource(
            R.drawable.five
        )
        else if (field[position].hidden && field[position].value == 6) v.blockImg.setBackgroundResource(
            R.drawable.six
        )
    }

    fun gameOver(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Game over!")
            .setIcon(R.drawable.boom)
            .setCancelable(false)
            .setNegativeButton("Начать новую игру",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                newGame()})
       val alert: AlertDialog = builder.create()
        alert.show()
    }

    fun newGame(){
        count = 0
        game!!.newGame()
        game!!.gameOver = false
        for (index in field.indices)
            gridView1.get(index).blockImg.setBackgroundResource(R.color.colorPrimary)
    }


    class FieldAdapter : BaseAdapter {

        var field = ArrayList<Block>()
        var context: Context? = null

        constructor(context: Context, foodsList: ArrayList<Block>) : super() {
            this.context = context
            this.field = foodsList
        }

        override fun getCount(): Int {
            return field.size
        }

        override fun getItem(position: Int): Any {
            return field[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val block = this.field[position]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflator.inflate(R.layout.food_entry, null)


            return foodView


        }
    }
}




