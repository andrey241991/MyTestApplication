package com.example.home_pc.mytestapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.home_pc.mytestapplication.eventbus.GetViewPagerPosition
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my, container, false)
    }

    companion object {
        fun newInstance(): MyFragment {
            val fragment = MyFragment()
            Log.v("TAG", "currentFrag in MyFragment = " + fragment)
            return fragment
        }
    }

    fun setText(text: String) {
        var textView = requireActivity().findViewById<TextView>(R.id.text_view)
        textView.text = text
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onViewPagerPositionEvent(event: GetViewPagerPosition) {
        Log.v("TAG", "MyFragment onViewPagerPositionEvent = " + event.viewPagerPosition)
        when (event.viewPagerPosition) {
            0 -> {
                setText("Changed text on First Tab - 1")
            }
            1 -> {
                setText("Changed text on Second Tab - 2")
            }
        }
    }


}
