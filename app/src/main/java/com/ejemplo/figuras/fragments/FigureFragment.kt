package com.ejemplo.figuras.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ejemplo.figuras.Constants
import com.ejemplo.figuras.Figure
import com.ejemplo.figuras.R
import com.ejemplo.figuras.ViewModelFigures
import com.ejemplo.figuras.databinding.FragmentFigureBinding
import com.ejemplo.figuras.views.CircleView
import com.ejemplo.figuras.views.TriangleView
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
/**
 * A simple [Fragment] subclass.
 * Use the [FigureFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FigureFragment : BaseFragment() {
    private var param1: String? = null
    private lateinit var editTextB: EditText
    private lateinit var editTextA: EditText
    private var figure: Figure? = null
    private lateinit var viewModel: ViewModelFigures

    private var _binding: FragmentFigureBinding? = null
    private val binding get() = _binding
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
        _binding = FragmentFigureBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        drawForm()
        drawFigure()
        initListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedFigure.collect{selectedFigure->
                selectedFigure?.let { figure->
                    binding?.textViewResultArea?.text = "Area (cm): "+figure.areas.toString()
                }
            }
        }
    }
    private fun initListeners() {
        binding?.buttonCalcular?.setOnClickListener {
            param1?.let {
                if (param1.equals(Constants.CIRCLE_VIEW) && editTextA.text.toString().isNotEmpty()) {
                    viewModel.updateFigure(it, editTextA.text.toString())
                }else if(editTextB.text.toString().isNotEmpty() && editTextA.text.toString().isNotEmpty()) {
                    viewModel.updateFigure(it, editTextA.text.toString(),editTextB.text.toString())
                } else {
                    Toast.makeText(
                        context,
                        "Tienes que introducir valores enteros o decimales con punto.",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun drawFigure() {

        context?.let { c->
            when(param1) {
                Constants.CIRCLE_VIEW -> {
                    val figureView: View = CircleView(c)
                        .apply {
                            layoutParams = ViewGroup.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                            )
                        }
                    binding?.cardViewFigure?.addView(figureView)
                }
                Constants.SQUARE_VIEW -> {
                    val figureView: View = View(c)
                        .apply {
                            layoutParams = ViewGroup.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                            )
                            setBackgroundColor(Color.GREEN)
                        }
                    binding?.cardViewFigure?.addView(figureView)
                }
                Constants.TRIANGLE_VIEW -> {
                    val figureView: View = TriangleView(c)
                        .apply {
                            layoutParams = ViewGroup.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                            )
                        }
                    binding?.cardViewFigure?.addView(figureView)
                }
                else -> {}
            }

        }

    }
    private fun drawForm(){
        when(param1) {
            Constants.SQUARE_VIEW -> {
                val linearLayoutA = getLinearLayout("Lado A(cm): ", "5 - 5.2",editTextA)
                val linearLayoutB = getLinearLayout("Lado B(cm): ", "5 - 5.2",editTextB)
                binding?.linearVertical?.addView(linearLayoutA)
                binding?.linearVertical?.addView(linearLayoutB)
            }
            Constants.CIRCLE_VIEW -> {
                val linearLayoutRadio = getLinearLayout("Radio(cm): ","5 - 5.5",editTextA)
                binding?.linearVertical?.addView(linearLayoutRadio)
            }
            Constants.TRIANGLE_VIEW -> {
                val linearLayoutBase = getLinearLayout("Base(cm): ","3 - 3.8",editTextA)
                val linearLayoutHeight = getLinearLayout("Altura(cm): ","6 - 6.4",editTextB)
                binding?.linearVertical?.addView(linearLayoutBase)
                binding?.linearVertical?.addView(linearLayoutHeight)
            }
        }
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
            editText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
            this.addView(editText)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
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
}