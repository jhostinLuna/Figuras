package com.ejemplo.figuras.fragments

import androidx.fragment.app.Fragment
import com.ejemplo.figuras.MainActivity

abstract class BaseFragment: Fragment() {
    fun getBaseActivity(): MainActivity = activity as MainActivity
}