package com.example.finalpractical

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.mysqliteexperiment.Student
import example.javatpoint.com.kotlinsqlitecrud.DatabaseHandler

class PlayGround : AppCompatActivity() {
    private lateinit var cpuChoose:TextView
    private lateinit var scoreTotal:TextView
    private lateinit var res:TextView
    private lateinit var youChoose:TextView
    private lateinit var stoneButton: Button
    private lateinit var paperButton:Button
    private lateinit var scissorButton:Button
    private lateinit var databaseHandler: DatabaseHandler
    private  var name=""
    private val STONE=0
    private val PAPER=1
    private val SCISSOR=2
    private val WIN=1
    private val LOSE=-1
    private val DRAW=0
    private var totalScore=0
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_ground)
        val intent= Intent()
        name=intent.getStringExtra("name").toString()
        var choosed=-1;
        var cpuChoosed=-1;
        databaseHandler= DatabaseHandler(this)
        cpuChoose=findViewById(R.id.cpuChoose)
        scoreTotal=findViewById(R.id.score)
        res=findViewById(R.id.res)
        youChoose=findViewById(R.id.selected)
        stoneButton=findViewById(R.id.chooseStone)
        paperButton=findViewById(R.id.choosePaper)
        scissorButton=findViewById(R.id.chooseScissor)
        stoneButton.setOnClickListener {
            choosed=STONE;
            cpuChoosed=returnRandom()
            youChoose.setText("You Choose: Stone")
            cpuChoose.setText("${getStringName(cpuChoosed)}")
            var r=choosedOption(choosed,cpuChoosed)
            result(r)
            scoreTotal.setText("Your Score: $totalScore")
            res.setText("${getResultName(r)}")

        }
        paperButton.setOnClickListener {
            choosed=PAPER;
            cpuChoosed=returnRandom()
            youChoose.setText("You Choose: Paper")
            cpuChoose.setText("${getStringName(cpuChoosed)}")
            var r=choosedOption(choosed,cpuChoosed)
            result(r)
            scoreTotal.setText("Your Score: $totalScore")
            res.setText("${getResultName(r)}")

        }
        scissorButton.setOnClickListener {
            choosed=SCISSOR;
            cpuChoosed=returnRandom()
            youChoose.setText("You Choose: Scissor")
            cpuChoose.setText("${getStringName(cpuChoosed)}")
            var r=choosedOption(choosed,cpuChoosed)
            result(r)
            scoreTotal.setText("Your Score: $totalScore")
            res.setText("${getResultName(r)}")

        }



    }
    fun getStringName(id:Int):String{
        if(id==0){
            return "Stone"
        }
        else if(id==1){
            return "Paper"
        }
        else{
            return "Scissor"
        }
    }
    fun getResultName(id:Int):String{
        if(id==0){
            return "Draw"
        }
        else if(id==1){
            return "Win"
        }
        else{
            return "Loss"
        }
    }
    fun returnRandom():Int{
        val rnds = (0..2).random()
        Log.d("random","random ${rnds}")
        return rnds
    }

    fun choosedOption(you:Int,cpu:Int):Int{
        if(you==STONE){
            if(cpu==STONE){
                return DRAW
            }
            else if(cpu==PAPER){
                return LOSE
            }
            else{
                return WIN
            }
        }
        else if(you==PAPER){
            if(cpu==PAPER){
                return DRAW
            }
            else if(cpu==SCISSOR){
                return LOSE
            }
            else{
                return WIN
            }
        }
        else{
            if(cpu==SCISSOR){
                return DRAW
            }
            else if(cpu==STONE){
                return LOSE
            }
            else{
                return WIN
            }
        }
    }

    fun result(r:Int){
        if(r==WIN){
            totalScore+=10;
        }
        else if(r==LOSE){
            totalScore-=10;
        }
        val s= Student(name,totalScore.toString())
        databaseHandler.updatestu(s)

    }
}