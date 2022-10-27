package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.ActivityMainBinding
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var result: Int? = null
    private var atvInput: Double? = null
    private var finalAtvFigure: String? = null
    private var numberOfCustomers: String? = null
    private var resultMessage: String? = null

    // Binding refactoring Stage 1
    lateinit var figureInput: EditText
    lateinit var numberOfCust: EditText
    lateinit var figureResult: TextView
    lateinit var atvResult: TextView
    lateinit var figuresResetButton: ImageButton
    lateinit var shareButton: Button
    lateinit var toggleButtonGroup: MaterialButtonToggleGroup

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewBinding refactoring stage 2 -- after this i can remove all of the binding. calls
        binding.let {
            figureInput = it.figureInput
            numberOfCust = it.numberOfCust
            figureResult = it.figureResult
            atvResult = it.atvResult
            figuresResetButton = it.figuresResetButton
            shareButton = it.shareButton
            toggleButtonGroup = it.toggleButtonGroup
        }

        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            when (checkedId) {
                R.id.switchUpdate -> {
                    if (isChecked) {
                        if (figureInput.text.isNotEmpty() && numberOfCust.text.isNotEmpty()){
                            resultMessage =
                                "Update £$result $numberOfCustomers trans £$finalAtvFigure ATV"
                        } else {
                            toggleButtonGroup.clearChecked()
                        }
                    }
                }
                R.id.switchFinal -> {
                    if (isChecked) {
                        if (figureInput.text.isNotEmpty() && numberOfCust.text.isNotEmpty()){
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

        figureInput.addTextChangedListener {
            if (figureInput.text.isNotEmpty()) {
                result = (figureInput.text.toString().toDouble() / 1.2).roundToInt()
                figureResult.text = "Result: ${result.toString()}"
            }
        }

        numberOfCust.addTextChangedListener {
            if (numberOfCust.text.isNotEmpty()) {
                atvInput = result?.div(numberOfCust.text.toString().toDouble())
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                numberOfCustomers = numberOfCust.text.toString()
                atvResult.text = "ATV: ${df.format(atvInput)}"
                finalAtvFigure = df.format(atvInput).toString()
            }
        }

        figuresResetButton.setOnClickListener {
            toggleButtonGroup.clearChecked()

            numberOfCust.text.clear()
            figureInput.text.clear()
            figureInput.requestFocus()

            figureResult.text = "Result:"
            atvResult.text = "ATV:"
        }

        shareButton.setOnClickListener {
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

    override fun onResume() {
        super.onResume()
        figureInput.requestFocus()
    }
}