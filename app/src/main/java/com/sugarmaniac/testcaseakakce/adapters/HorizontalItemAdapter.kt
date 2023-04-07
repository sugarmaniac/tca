import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sugarmaniac.data.HorizontalItem
import com.sugarmaniac.testcaseakakce.databinding.HorizontalItemLayoutBinding

class HorizontalItemAdapter(private var horizontalItems: List<HorizontalItem>, private val listener : (input: String) -> Unit) : RecyclerView.Adapter<HorizontalItemAdapter.HorizontalItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalItemHolder {
        val binding = HorizontalItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalItemHolder(binding)
    }

    override fun getItemCount() = horizontalItems.size

    override fun onBindViewHolder(holder: HorizontalItemHolder, position: Int) {
        with(holder){
            with(horizontalItems[position]) {
                binding.name.text = horizontalItems[position].name
                binding.price.text = horizontalItems[position].price.toString()
                binding.discount.text = "%" + horizontalItems[position].dropRatio.toString()
                binding.follower.text = horizontalItems[position].followCount.toString() + "+ takip"
                binding.seller.text = horizontalItems[position].countOfPrices.toString() + " satici >"


                Glide.with(holder.itemView.context)
                    .load(horizontalItems[position].imageUrl)
                    .into(binding.image)
            }

            holder.binding.root.setOnClickListener {
                listener.invoke(horizontalItems[position].code.toString())
            }

            holder.binding.image.setOnClickListener {
                listener.invoke(horizontalItems[position].code.toString())
            }
        }
    }

    fun setList(list : List<HorizontalItem>){
        horizontalItems = list
        notifyDataSetChanged()
    }


    inner class HorizontalItemHolder(val binding: HorizontalItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

}