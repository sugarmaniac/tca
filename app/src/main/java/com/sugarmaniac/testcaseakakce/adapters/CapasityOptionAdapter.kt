import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sugarmaniac.data.HorizontalItem
import com.sugarmaniac.testcaseakakce.databinding.CapasityOptionLayoutBinding
import com.sugarmaniac.testcaseakakce.databinding.HorizontalItemLayoutBinding

class CapasityOptionAdapter(private var capasities : List<String>, private val listener : (input: String) -> Unit) : RecyclerView.Adapter<CapasityOptionAdapter.CapasityHolder>() {

    private var selectedCapasity = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapasityHolder {
        val binding = CapasityOptionLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CapasityHolder(binding)
    }

    override fun getItemCount() = capasities.size

    override fun onBindViewHolder(holder: CapasityHolder, position: Int) {
        holder.binding.capasity.text = capasities[position]
        holder.binding.capasity.isSelected = position == selectedCapasity
        holder.binding.root.setOnClickListener{
            selectCapasity(position)
        }
    }

    fun setList(list : List<String>){
        capasities = list
        notifyDataSetChanged()
    }

    private fun selectCapasity(position: Int){
        val temp = selectedCapasity
        selectedCapasity = position
        notifyItemChanged(position)
        notifyItemChanged(temp)
    }

    inner class CapasityHolder(val binding: CapasityOptionLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

}