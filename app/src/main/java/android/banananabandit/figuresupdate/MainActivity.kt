package android.banananabandit.figuresupdate

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var figureInput: EditText? = null
    private var numberOfCustInput: EditText? = null
    private var switchFinal: Button? = null
    private var switchUpdate: Button? = null
    private var shareButton: Button? = null
    private var figureResult: TextView? = null
    private var atvResult: TextView? = null
    private var result: Int? = null
    private var atvInput: Double? = null
    private var finalAtvFigure: String? = null
    private var numberOfCustomers: String? = null
    private var resultMessage: String? = null
    private var toggleButtonGroup: MaterialButtonToggleGroup? = null
    private var resetFiguresButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        figureInput = findViewById(R.id.figureInput)
        numberOfCustInput = findViewById(R.id.numberOfCust)
        switchFinal = findViewById(R.id.switchFinal)
        switchUpdate = findViewById(R.id.switchUpdate)
        shareButton = findViewById(R.id.shareButton)
        resetFiguresButton = findViewById(R.id.figuresResetButton)
        figureResult = findViewById(R.id.figureResult)
        atvResult = findViewById(R.id.atvResult)

        toggleButtonGroup = findViewById(R.id.toggleButtonGroup)
        toggleButtonGroup?.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            when (checkedId) {
                R.id.switchUpdate -> {
                    if (isChecked) {
                        if (figureInput?.text!!.isNotEmpty() && numberOfCustInput?.text!!.isNotEmpty()){
                            resultMessage =
                                "Update £$result $numberOfCustomers trans £$finalAtvFigure ATV"
                        } else {
                            toggleButtonGroup.clearChecked()
                        }
                    }
                }
                R.id.switchFinal -> {
                    if (isChecked) {
                        if (figureInput?.text!!.isNotEmpty() && numberOfCustInput?.text!!.isNotEmpty()){
                            resultMessage =
                                "Finished today on £$result $numberOfCustomers trans £$finalAtvFigure ATV"
                        } else {
                            toggleButtonGroup.clearChecked()
                        }

                    }
                }
                else -> resultMessage = "Can you please choose"
            }
        }

        figureInput?.addTextChangedListener {
            if (figureInput?.text?.isNotEmpty() == true) {
                result = (figureInput?.text.toString().toDouble() / 1.2).roundToInt()
                figureResult?.text = "Result: ${result.toString()}"
            }
        }

        numberOfCustInput?.addTextChangedListener {
            if (numberOfCustInput?.text?.isNotEmpty() == true) {
                atvInput = result?.div(numberOfCustInput?.text.toString().toDouble())
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                numberOfCustomers = numberOfCustInput?.text.toString()
                atvResult?.text = "ATV: ${df.format(atvInput)}"
                finalAtvFigure = df.format(atvInput).toString()
            }
        }

        resetFiguresButton?.setOnClickListener {
            toggleButtonGroup?.clearChecked()

            numberOfCustInput?.text?.clear()
            figureInput?.text?.clear()
            figureInput?.requestFocus()

            figureResult?.text = "Result:"
            atvResult?.text = "ATV:"
        }

        shareButton?.setOnClickListener {
            whatsAppResultShare()
            Toast.makeText(this, "$result $numberOfCustomers $finalAtvFigure", Toast.LENGTH_SHORT)
                .show()
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

    override fun onResume() {
        super.onResume()
        figureInput?.requestFocus()
    }
}