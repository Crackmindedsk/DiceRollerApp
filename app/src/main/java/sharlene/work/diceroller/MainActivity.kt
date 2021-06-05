package sharlene.work.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import sharlene.work.diceroller.R.id.button

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen
 */
class MainActivity : AppCompatActivity() {

    private lateinit var madView:AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this){}
        madView=findViewById(R.id.ad_view)
        val adRequest=AdRequest.Builder().build()
        madView.loadAd(adRequest)

        val rollButton: Button = findViewById(button)
        rollButton.setOnClickListener { rollDice() }

        //Do a Dice Roll when the app start
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        //Create new dice object with 6 sides and roll it
        val dice1 = Dice(6)
        val dice2=Dice(6)
        val diceRoll = dice1.roll()
        val diceRoll2=dice2.roll()


        //Find the ImageView in the Layout
        val diceImage:ImageView=findViewById(R.id.imageView)
        val diceImage2:ImageView=findViewById(R.id.imageView2)
        //Determine which drawable resources ID to use based on the dice roll
        val drawableResource=when(diceRoll){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        val drawableResource2=when(diceRoll2){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }


        //Update the Image View with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)
        diceImage2.setImageResource(drawableResource2)
        //Update the content description
        diceImage.contentDescription=diceRoll.toString()
        diceImage2.contentDescription=diceRoll2.toString()


    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}