import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.l3.R
import com.example.l3.models.WishlistItem

class WishlistItemFragment : Fragment() {
    companion object {
        fun newInstance(item: WishlistItem): WishlistItemFragment {
            val fragment = WishlistItemFragment()
            val args = Bundle()
            args.putParcelable("wishlistItem", item)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val item = arguments?.getParcelable<WishlistItem>("wishlistItem")
        val view = inflater.inflate(R.layout.fragment_wishlist_item, container, false)


        if (item != null) {
            item.title.let { Log.i("ITEM", it) }
            view?.findViewById<TextView>(R.id.titleTextView)?.text = "Title: ${item.title}"
            view?.findViewById<TextView>(R.id.priceTextView)?.text = "Price: ${item.price}"
            view?.findViewById<TextView>(R.id.ratingTextView)?.text = "Rating: ${item.rating}"
            view?.findViewById<TextView>(R.id.dateTextView)?.text = "Date: ${item.date}"
            view?.findViewById<TextView>(R.id.nightsTextView)?.text = "Nights: ${item.nights}"
            view?.findViewById<TextView>(R.id.starsTextView)?.text  = "Stars: ${item.stars}"
        }
        val toolbar: Toolbar? = view?.findViewById(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        toolbar?.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }
}