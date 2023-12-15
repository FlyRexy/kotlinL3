import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.l3.R
import com.example.l3.databinding.FragmentWishlistItemBinding
import com.example.l3.models.WishlistItem
import com.example.l3.models.WishlistResponse
import com.google.gson.Gson
import java.io.InputStreamReader

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistItemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wishlist_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishlist = readJsonFile(requireContext())
        val adapter = wishlist?.let { WishlistAdapter(it) }
        if (adapter != null) {
            adapter.onItemClickListener = object : WishlistAdapter.OnItemClickListener {
                override fun onItemClick(item: WishlistItem) {
                    openDetailFragment(item)
                }
            }
        }
        val rv = view.findViewById<RecyclerView>(R.id.wishlistRecyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter
    }

    private fun openDetailFragment(item: WishlistItem) {
        val detailFragment = WishlistItemFragment.newInstance(item)

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun readJsonFile(context: Context): List<WishlistItem>? {
        try {
            val inputStream = context.resources.openRawResource(R.raw.response)
            val reader = InputStreamReader(inputStream)
            val response = Gson().fromJson(reader, WishlistResponse::class.java)
            return response?.contents
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}