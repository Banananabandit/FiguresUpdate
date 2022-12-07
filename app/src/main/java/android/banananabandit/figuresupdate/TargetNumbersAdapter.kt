package android.banananabandit.figuresupdate

import android.banananabandit.figuresupdate.databinding.WeekListItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
// I wonder if the adapter will work the same way for tha fragment
class TargetNumbersAdapter(private val mWeekTargets: ArrayList<FinancialWeek>): RecyclerView.Adapter<TargetNumbersAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: WeekListItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(week: FinancialWeek) {
            itemBinding.weekNumber.text = week.weekNumber.toString()
            itemBinding.budgetAmountNumber.text = week.weekTarget.toString()
            itemBinding.budgetAchievedNumber.text = week.weekTargetAchieved.toString()
            // Need to get the logic for changing the color of the iv- possibly a separate fun
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WeekListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weeks = mWeekTargets[position]
        holder.bindItem(weeks)
    }

    override fun getItemCount(): Int {
        return mWeekTargets.size
    }
}