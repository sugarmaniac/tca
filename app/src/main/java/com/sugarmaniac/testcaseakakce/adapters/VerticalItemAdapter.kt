import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sugarmaniac.data.VerticalItem
import com.sugarmaniac.testcaseakakce.R
import com.sugarmaniac.testcaseakakce.databinding.VerticalItemLayoutBinding

class VerticalItemAdapter(private var verticalItems: List<VerticalItem>, private val listener : (input: String) -> Unit) : RecyclerView.Adapter<VerticalItemAdapter.VerticalItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalItemHolder {
        val binding = VerticalItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return VerticalItemHolder(binding)
    }

    override fun getItemCount() = verticalItems.size

    override fun onBindViewHolder(holder: VerticalItemHolder, position: Int) {
        with(holder){
            with(verticalItems[position]) {
                binding.name.text = verticalItems[position].name
                binding.price.text = holder.itemView.context.getString(R.string.pricing, verticalItems[position].price)
                binding.discount.text = "%" + verticalItems[position].dropRatio.toString()
                binding.follower.text = verticalItems[position].followCount.toString() + "+ takip"
                binding.seller.text = verticalItems[position].countOfPrices.toString() + " satici >"


                Glide.with(holder.itemView.context)
                    .load(verticalItems[position].imageUrl)
                    .into(binding.image)
            }

            holder.binding.root.setOnClickListener {
                listener.invoke(verticalItems[position].code.toString())
            }
        }
    }

    fun setList(list : List<VerticalItem>){
        val temp = verticalItems.size
        verticalItems = list
        notifyDataSetChanged()
    }


    inner class VerticalItemHolder(val binding: VerticalItemLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

}