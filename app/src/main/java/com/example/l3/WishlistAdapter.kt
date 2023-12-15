import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.l3.R
import com.example.l3.models.WishlistItem

class WishlistAdapter(private val wishlist: List<WishlistItem>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = wishlist[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        fun bind(wishlistItem: WishlistItem) {
            titleTextView.text = wishlistItem.title
            priceTextView.text = "Price: ${wishlistItem.price}"
        }
    }
    interface OnItemClickListener {
        fun onItemClick(item: WishlistItem)
    }

    var onItemClickListener: OnItemClickListener? = null
}
