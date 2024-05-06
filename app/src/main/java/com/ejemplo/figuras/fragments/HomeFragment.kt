package com.ejemplo.figuras.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.ejemplo.figuras.Constants
import com.ejemplo.figuras.Figure
import com.ejemplo.figuras.views.CircleView
import com.ejemplo.figuras.R
/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            val circle = CircleView(it)
            circle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val triangleView = view.findViewById<View>(R.id.triangle_view)
        val squareView = view.findViewById<View>(R.id.square_view)
        val circleView = view.findViewById<View>(R.id.circle_view)
        triangleView.setOnClickListener(this)
        squareView.setOnClickListener(this)
        circleView.setOnClickListener(this)

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment Home.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onClick(view: View?) {
        var figureFragment: FigureFragment = FigureFragment()
        when(view?.id) {
            R.id.square_view -> {
                figureFragment = FigureFragment.newInstance(Constants.SQUARE_VIEW)
            }
            R.id.circle_view -> {
                figureFragment = FigureFragment.newInstance(Constants.CIRCLE_VIEW)
            }
            R.id.triangle_view -> {
                figureFragment = FigureFragment.newInstance(Constants.TRIANGLE_VIEW)
            }
        }
        getBaseActivity().loadFragment(figureFragment,this::class.java.simpleName)
    }
}