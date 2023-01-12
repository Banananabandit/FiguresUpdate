package android.banananabandit.figuresupdate

import android.app.Dialog
import android.banananabandit.figuresupdate.databinding.CreateNewWeekDialogBinding
import android.banananabandit.figuresupdate.databinding.FragmentWeeklyUpdateBinding
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class WeeklyUpdateFragment : Fragment() {
    private var _binding: FragmentWeeklyUpdateBinding? = null
    private val binding get() = _binding!!
    private var listOfWeeks = ArrayList<FinancialWeek>()
    private lateinit var adapter: TargetNumbersAdapter

    private var financialWeekDao = FinancialWeeksDb.getDaoInstance(WeeksApplication.getAppContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeeklyUpdateBinding.inflate(inflater, container, false)

        listOfWeeks = retrieveFinancialWeeks()

        adapter = TargetNumbersAdapter(listOfWeeks)
        binding.rvWeekTargets.adapter = adapter
        binding.rvWeekTargets.layoutManager = LinearLayoutManager(context)

        val fab = binding.fab
        fab.setOnClickListener {
            addNewWeekDialog()
        }
        return binding.root
    }

    private fun retrieveFinancialWeeks() : ArrayList<FinancialWeek>{
        val newList = ArrayList<FinancialWeek>()

        lifecycleScope.launch {
            var list = financialWeekDao.getAll()
            for (i in list) {
                newList.add(i)
            }
        }
        return newList
    }


    private fun addNewWeekDialog() {
        val addWeekDialog = Dialog(requireContext(), R.style.Theme_AppCompat_Dialog)
        addWeekDialog.setCancelable(false)

        val binding = CreateNewWeekDialogBinding.inflate(layoutInflater)
        addWeekDialog.setContentView(binding.root)

        binding.tvWeekNumber.text = getCurrentWeek().toString()

        binding.imgbtnEditWeekNumber.setOnClickListener {
            if (binding.tvWeekNumber.isVisible) {
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

            val currentWeek = getCurrentWeek()

            val weekNumber = if (binding.etWeekNumber.text.isBlank()) {
                currentWeek.toInt()
            } else {
                binding.tvWeekNumber.text.toString().toInt()
            }

            val weekBudget = if (binding.etBudgetAmount.text.isBlank()) {
                0
            } else {
                binding.etBudgetAmount.text.toString().toInt()
            }

            val weekAchievedAmount = if (binding.etAchievedAmount.text.isBlank()) {
                0
            } else {
                binding.etAchievedAmount.text.toString().toInt()
            }

            var found = false

            for (item in listOfWeeks) {
                if (item.weekNumber == weekNumber) {
                    found = true
                    break
                }
            }

            if (found) {
                Toast.makeText(context, "Week already exists", Toast.LENGTH_SHORT).show()
            } else {
                val newWeek = FinancialWeek(
                    0,
                    weekNumber,
                    weekBudget,
                    weekAchievedAmount,
                    false,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    false,
                    false
                )
                lifecycleScope.launch {
                    financialWeekDao.insert(newWeek)
                }

                listOfWeeks = retrieveFinancialWeeks()
                Log.d(TAG, "addNewWeekDialog: dfkl;adasklfjasdlfjkas;klfjkladjfkl;jklsdfkl ${listOfWeeks.size}")
                sortWeeks()
                adapter.notifyDataSetChanged()
                addWeekDialog.dismiss()
            }


        }

        binding.btnCancel.setOnClickListener {
            addWeekDialog.dismiss()
        }
        addWeekDialog.show()
    }

    private fun sortWeeks() {
        val sortedWeeks = listOfWeeks.sortedWith(compareBy { it.weekNumber })
        listOfWeeks.clear()
        for (i in sortedWeeks) {
            listOfWeeks.add(i)
        }
    }

    private fun getCurrentWeek(): Long {
        val currentTime = System.currentTimeMillis()
        val c = Calendar.getInstance()
        // TODO: And also add logic to start week from Sunday
        // To set the month and the day, it whould start with 0 as the first day
        // Later on will need logic to set the year to a current financial year
        c.set(2022, 3, 0)
        val startOfFinancialYear = c.timeInMillis
        return (currentTime - startOfFinancialYear) / 604800000
    }

}
