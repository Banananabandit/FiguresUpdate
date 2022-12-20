package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.FragmentWeeklyUpdateBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

class WeeklyUpdateFragment : Fragment() {
    private var _binding : FragmentWeeklyUpdateBinding? = null
    private val binding get() = _binding!!
    private val listOfWeeks = ArrayList<FinancialWeek>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeeklyUpdateBinding.inflate(inflater, container, false)

        val adapter = TargetNumbersAdapter(listOfWeeks)
        binding.rvWeekTargets.adapter = adapter
        binding.rvWeekTargets.layoutManager = LinearLayoutManager(context)

        val fab = binding.fab
        fab.setOnClickListener {
            val week = FinancialWeek.generateWeek()
            listOfWeeks.add(week)
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }
}
