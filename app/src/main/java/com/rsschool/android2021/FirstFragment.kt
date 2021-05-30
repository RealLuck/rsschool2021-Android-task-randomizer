package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.NumberFormatException

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    private var onFirstFragmentListener: OnFirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFirstFragmentListener = context as OnFirstFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"


        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)
        generateButton?.setOnClickListener {


            try {
                val min = minEditText?.text.toString().toInt()
                val max = maxEditText?.text.toString().toInt()
                if (min<max&& max>0&&min>=0)
                    onFirstFragmentListener?.onFirstFragmentListener(min, max)
                else
                {
                    Toast.makeText(context, "Wrong numbers", Toast.LENGTH_LONG).show()
                }
            }
            catch (e:NumberFormatException)
            {
                Toast.makeText(context, "Need more data", Toast.LENGTH_LONG).show()
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        interface OnFirstFragmentListener {
            fun onFirstFragmentListener(min: Int, max: Int)
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}