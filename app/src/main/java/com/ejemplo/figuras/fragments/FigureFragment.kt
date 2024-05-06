package com.ejemplo.figuras.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ejemplo.figuras.Circle
import com.ejemplo.figuras.Constants
import com.ejemplo.figuras.Figure
import com.ejemplo.figuras.R
import com.ejemplo.figuras.Square
import com.ejemplo.figuras.Triangle
import com.ejemplo.figuras.ViewModelFigures

private const val ARG_PARAM1 = "param1"
/**
 * A simple [Fragment] subclass.
 * Use the [FigureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FigureFragment : BaseFragment(),OnClickListener {
    private var param1: String? = null
    private lateinit var editTextB: EditText
    private lateinit var editTextA: EditText
    private var figure: Figure? = null
    private lateinit var viewModel: ViewModelFigures
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        editTextA = EditText(context)
        editTextB = EditText(context)
        viewModel = ViewModelProvider(this)[ViewModelFigures::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_figure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewResult = view.findViewById(R.id.textView_result_area)
        val verticalLinear = view.findViewById<LinearLayout>(R.id.linearVertical)
        when(param1) {
            Constants.SQUARE_VIEW -> {
                val linearLayoutA = getLinearLayout("Lado A: ", "5 - 5.2",editTextA)
                val linearLayoutB = getLinearLayout("Lado B: ", "5 - 5.2",editTextB)
                verticalLinear.addView(linearLayoutA)
                verticalLinear.addView(linearLayoutB)
            }
            Constants.CIRCLE_VIEW -> {
                val linearLayoutRadio = getLinearLayout("Radio: ","5 - 5.5",editTextA)
                verticalLinear.addView(linearLayoutRadio)
            }
            Constants.TRIANGLE_VIEW -> {
                val linearLayoutBase = getLinearLayout("Base: ","3 - 3.8",editTextA)
                val linearLayoutHeight = getLinearLayout("Altura: ","6 - 6.4",editTextB)
                verticalLinear.addView(linearLayoutBase)
                verticalLinear.addView(linearLayoutHeight)
            }
        }
        view.findViewById<Button>(R.id.button_calcular).setOnClickListener(this)


    }
    private fun getLinearLayout(text: String, hint: String,editText: EditText): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            // Crea el TextView
            val textView = TextView(context)
            textView.text = text
            this.addView(textView)

            editText.hint = hint
            this.addView(editText)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Figure.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            FigureFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.button_calcular -> {
                param1?.let {
                    if (param1.equals(Constants.CIRCLE_VIEW) && editTextA.text.toString().isNotEmpty()) {
                        figure = viewModel.getFigure(it, editTextA.text.toString())
                    }else {
                        if (editTextB.text.toString().isNotEmpty() && editTextA.text.toString().isNotEmpty()) {
                            figure = viewModel.getFigure(it, editTextA.text.toString(),editTextB.text.toString())
                        }
                    }
                }

                val text = resources.getText(R.string.text_result_area).toString().replace("$",param1?:"")

                textViewResult?.text = "$text ${figure?.getArea()}"
            }
        }

    }
}