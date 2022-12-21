package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.FragmentDailyUpdateBinding
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.text.set
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButtonToggleGroup
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DailyUpdateFragment : Fragment() {

    private var result: Int? = null
    private var atvInput: Double? = null
    private var finalAtvFigure: String? = null
    private var numberOfCustomers: String? = null
    private var resultMessage: String? = null

    lateinit var figureInput: EditText
    lateinit var numberOfCust: EditText
    lateinit var figureResult: TextView
    lateinit var atvResult: TextView
    lateinit var figuresResetButton: ImageButton
    lateinit var shareButton: Button
    lateinit var toggleButtonGroup: MaterialButtonToggleGroup

    private var _binding : FragmentDailyUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentDailyUpdateBinding.inflate(inflater, container, false)

        // Setting up binding
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

            val entriesNotEmpty = figureInput.text.isNotEmpty() && numberOfCust.text.isNotEmpty()

            when (checkedId) {
                R.id.switchUpdate -> {
                    if (isChecked) {
                        if (entriesNotEmpty){
                            resultMessage =
                                "Update £$result $numberOfCustomers trans £$finalAtvFigure ATV"
                        } else {
                            toggleButtonGroup.clearChecked()
                        }
                    }
                }
                R.id.switchFinal -> {
                    if (isChecked) {
                        if (entriesNotEmpty){
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
                updateFiguresTextView()
            }
        }

        numberOfCust.addTextChangedListener {
            if (numberOfCust.text.isNotEmpty()) {
                updateAtvTextView()
            }
        }

        figuresResetButton.setOnClickListener {
            resetScreen()
        }

        shareButton.setOnClickListener {
            whatsAppResultShare()
        }

        return binding.root
    }


    private fun resetScreen() {
        toggleButtonGroup.clearChecked()

        numberOfCust.text.clear()
        figureInput.text.clear()
        figureInput.requestFocus()

        figureResult.text = "Result:"
        atvResult.text = "ATV:"
    }

    private fun updateAtvTextView() {
        // Extract into a method
        atvInput = result?.div(numberOfCust.text.toString().toDouble())
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        numberOfCustomers = numberOfCust.text.toString()
        atvResult.text = "ATV: ${df.format(atvInput)}"
        finalAtvFigure = df.format(atvInput).toString()
    }

    private fun updateFiguresTextView() {
        result = (figureInput.text.toString().toDouble() / 1.2).roundToInt()
        figureResult.text = "Result: ${result.toString()}"
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}