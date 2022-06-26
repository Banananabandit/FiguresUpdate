package android.banananabandit.figuresupdate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    var figureInput : EditText? = null
    var numberOfCustInput : EditText? = null

    var switchFinal : ToggleButton? = null
    var switchUpdate : ToggleButton? = null

    var updateButton : Button? = null

    var figureResult : TextView? = null
    var atvResult : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instantiate all
    }
}