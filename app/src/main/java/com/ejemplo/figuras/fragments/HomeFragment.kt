package com.ejemplo.figuras.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ejemplo.figuras.Constants
import com.ejemplo.figuras.views.CircleView
import com.ejemplo.figuras.R
import com.ejemplo.figuras.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun initListener() {
        binding?.triangleView?.setOnClickListener(this)
        binding?.triangleView?.setOnClickListener(this)
        binding?.squareView?.setOnClickListener(this)
        binding?.circleView?.setOnClickListener(this)
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
        when(view?.id) {
            R.id.square_view -> {
                val figureFragment = FigureFragment.newInstance(Constants.SQUARE_VIEW)
                getBaseActivity().loadFragment(figureFragment,this::class.java.simpleName)
            }
            R.id.circle_view -> {
                val figureFragment = FigureFragment.newInstance(Constants.CIRCLE_VIEW)
                getBaseActivity().loadFragment(figureFragment,this::class.java.simpleName)
            }
            R.id.triangle_view -> {
                val figureFragment = FigureFragment.newInstance(Constants.TRIANGLE_VIEW)
                getBaseActivity().loadFragment(figureFragment,this::class.java.simpleName)
            }
        }
    }
}