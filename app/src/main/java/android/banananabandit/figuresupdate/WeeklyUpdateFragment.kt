package android.banananabandit.figuresupdate

import android.app.Dialog
import android.banananabandit.figuresupdate.databinding.CreateNewWeekDialogBinding
import android.banananabandit.figuresupdate.databinding.FragmentWeeklyUpdateBinding
import android.content.ContentValues.TAG
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

class WeeklyUpdateFragment : Fragment() {
    private var _binding : FragmentWeeklyUpdateBinding? = null
    private val binding get() = _binding!!
    private var listOfWeeks = ArrayList<FinancialWeek>()
    private lateinit var adapter : TargetNumbersAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeeklyUpdateBinding.inflate(inflater, container, false)

        adapter = TargetNumbersAdapter(listOfWeeks)
        binding.rvWeekTargets.adapter = adapter
        binding.rvWeekTargets.layoutManager = LinearLayoutManager(context)

        val fab = binding.fab
        fab.setOnClickListener {
            addNewWeekDialog()
        }
        return binding.root
    }

    private fun addNewWeekDialog() {
        val addWeekDialog = Dialog(requireContext(), R.style.Theme_AppCompat_Dialog)
        addWeekDialog.setCancelable(false)
        val binding = CreateNewWeekDialogBinding.inflate(layoutInflater)
        addWeekDialog.setContentView(binding.root)
        // Here ween need to take info from the edit texts and put it into our array
        binding.imgbtnEditWeekNumber.setOnClickListener {
            if (binding.tvWeekNumber.isVisible){
                binding.tvWeekNumber.visibility = View.GONE
                binding.etWeekNumber.visibility = View.VISIBLE
                binding.etWeekNumber.requestFocus()
            } else {
                binding.tvWeekNumber.visibility = View.VISIBLE
                binding.etWeekNumber.visibility = View.GONE
            }
        }

        binding.btnDone.setOnClickListener {
            binding.tvWeekNumber.text = binding.etWeekNumber.text.toString()
            // If the ET has the value then pass it on if not set it to zero
            var weekNumber = 0
            var weekBudget= 0
            var weekAchievedAmount= 0
            weekNumber = if (binding.etWeekNumber.text.isBlank()) {
                0
            } else {
                binding.tvWeekNumber.text.toString().toInt()
            }

            weekBudget = if (binding.etBudgetAmount.text.isBlank()) {
                0
            } else {
                binding.etBudgetAmount.text.toString().toInt()
            }

            weekAchievedAmount = if (binding.etAchievedAmount.text.isBlank()) {
                0
            } else {
                binding.etAchievedAmount.text.toString().toInt()
            }

//            val weekNumber = binding.tvWeekNumber.text.toString().toInt()
//            val weekBudget = binding.etBudgetAmount.text.toString().toInt()
//            val weekAchievedAmount = binding.etAchievedAmount.text.toString().toInt()

            val newWeek = FinancialWeek(weekNumber, weekBudget, weekAchievedAmount, false, 0, 0, 0, 0, 0, 0, 0, false, false)
            listOfWeeks.add(newWeek)
            // notify adapter dataset changed
            adapter.notifyDataSetChanged()
            addWeekDialog.dismiss()

        }
        binding.btnCancel.setOnClickListener {
            addWeekDialog.dismiss()
        }
        addWeekDialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
