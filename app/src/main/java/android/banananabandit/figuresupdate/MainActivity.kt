package android.banananabandit.figuresupdate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var figureInput : EditText? = null
    private var numberOfCustInput : EditText? = null
    private var switchFinal : Button? = null
    private var switchUpdate : Button? = null
    private var shareButton : Button? = null
    private var figureResult : TextView? = null
    private var atvResult : TextView? = null
    private var result : Int? = null
    private var atvInput : Double? = null
    private var finalAtvFigure : String? = null
    private var numberOfCustomers : String? = null
    private var resultMessage : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        figureInput = findViewById(R.id.figureInput)
        numberOfCustInput = findViewById(R.id.numberOfCust)
        switchFinal = findViewById(R.id.switchFinal)
        switchUpdate = findViewById(R.id.switchUpdate)
        shareButton = findViewById(R.id.shareButton)
        figureResult = findViewById(R.id.figureResult)
        atvResult = findViewById(R.id.atvResult)

        val toggleButtonGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggleButtonGroup)
        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            when (checkedId) {
                R.id.switchUpdate -> {
                    if (isChecked) {
                        resultMessage = "Update £$result $numberOfCustomers trans $finalAtvFigure ATV"
                    }
                }
                R.id.switchFinal -> {
                    if (isChecked) {
                        resultMessage = "Finished today on £$result $numberOfCustomers trans $finalAtvFigure ATV"
                    }
                }
                else -> resultMessage = "Can you please choose"
            }
    }

        figureInput?.addTextChangedListener {
            if (figureInput?.text?.isNotEmpty() == true) {
                result = (figureInput?.text.toString().toDouble() / 1.2).roundToInt()
                figureResult?.text = result.toString()
            }
        }

        numberOfCustInput?.addTextChangedListener {
            if (numberOfCustInput?.text?.isNotEmpty() == true) {
                atvInput = result?.div(numberOfCustInput?.text.toString().toDouble())
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                numberOfCustomers = numberOfCustInput?.text.toString()
                atvResult?.text = df.format(atvInput).toString()
                finalAtvFigure = df.format(atvInput).toString()
            }
        }

        shareButton?.setOnClickListener {
            whatsAppResultShare()
        }
    }
    private fun whatsAppResultShare() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, resultMessage)
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }
}