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

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->

            when (checkedId) {
                R.id.switchUpdate -> {
                    if (isChecked) {
                        if (binding.figureInput.text.isNotEmpty() && binding.numberOfCust.text.isNotEmpty()){
                            resultMessage =
                                "Update £$result $numberOfCustomers trans £$finalAtvFigure ATV"
                        } else {
                            toggleButtonGroup.clearChecked()
                        }
                    }
                }
                R.id.switchFinal -> {
                    if (isChecked) {
                        if (binding.figureInput.text.isNotEmpty() && binding.numberOfCust.text.isNotEmpty()){
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

        binding.figureInput.addTextChangedListener {
            if (binding.figureInput.text.isNotEmpty()) {
                result = (binding.figureInput.text.toString().toDouble() / 1.2).roundToInt()
                binding.figureResult.text = "Result: ${result.toString()}"
            }
        }

        binding.numberOfCust.addTextChangedListener {
            if (binding.numberOfCust.text.isNotEmpty()) {
                atvInput = result?.div(binding.numberOfCust.text.toString().toDouble())
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                numberOfCustomers = binding.numberOfCust.text.toString()
                binding.atvResult.text = "ATV: ${df.format(atvInput)}"
                finalAtvFigure = df.format(atvInput).toString()
            }
        }

        binding.figuresResetButton.setOnClickListener {
            binding.toggleButtonGroup.clearChecked()

            binding.numberOfCust.text.clear()
            binding.figureInput.text.clear()
            binding.figureInput.requestFocus()

            binding.figureResult.text = "Result:"
            binding.atvResult.text = "ATV:"
        }

        binding.shareButton.setOnClickListener {
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
        binding.figureInput.requestFocus()
    }
}