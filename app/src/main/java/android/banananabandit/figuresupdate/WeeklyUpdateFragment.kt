package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.FragmentWeeklyUpdateBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WeeklyUpdateFragment : Fragment() {
    // Write notes on ViewBinding with Fragments!@!!
    private var _binding : FragmentWeeklyUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentWeeklyUpdateBinding.inflate(inflater, container, false)



        return binding.root
    }
}
