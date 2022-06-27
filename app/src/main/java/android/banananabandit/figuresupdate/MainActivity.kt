package android.banananabandit.figuresupdate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.script.ScriptEngineManager
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var figureInput : EditText? = null
    private var numberOfCustInput : EditText? = null


    var switchFinal : Button? = null
    var switchUpdate : Button? = null

    var shareButton : Button? = null

    var figureResult : TextView? = null
    var atvResult : TextView? = null

    private var result : Double? = null
    private var roundResult : Int? = null
    private var atvInput : Double? = null
    private var finalAtvFigure : String? = null
    private var numberOfCustomers : String? = null

    private var resultMessage : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toggleButtonGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggleButtonGroup)
        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            when (checkedId) {
                R.id.switchUpdate -> resultMessage = "Update £$roundResult $numberOfCustomers trans $finalAtvFigure ATV"
                R.id.switchFinal -> resultMessage = "Finished today on £$roundResult $numberOfCustomers trans $finalAtvFigure ATV"
            }
    }

        figureInput = findViewById(R.id.figureInput)
        numberOfCustInput = findViewById(R.id.numberOfCust)
        switchFinal = findViewById(R.id.switchFinal)
        switchUpdate = findViewById(R.id.switchUpdate)
        shareButton = findViewById(R.id.shareButton)
        figureResult = findViewById(R.id.figureResult)
        atvResult = findViewById(R.id.atvResult)

        figureInput?.addTextChangedListener {

            result = (figureInput?.text.toString().toDouble() / 1.2)
            roundResult = result?.roundToInt()
            figureResult?.text = roundResult.toString()
        }

        numberOfCustInput?.addTextChangedListener {
            atvInput = roundResult?.div(numberOfCustInput?.text.toString().toDouble())
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            numberOfCustomers = numberOfCustInput?.text.toString()
            atvResult?.text = df.format(atvInput).toString()
            finalAtvFigure = df.format(atvInput).toString()
        }

        shareButton?.setOnClickListener {
            whatsApp()
        }

    }
    private fun whatsApp() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, resultMessage)
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }

}