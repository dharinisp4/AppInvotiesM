package com.example.appinvotiesm
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.appinvotiesm.databinding.ActivityPanVerificationBinding
import java.lang.Exception
import java.text.SimpleDateFormat

class PanVerificationActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding : ActivityPanVerificationBinding
    var date :String?=null
    var panNo :String?=null
    var d :String?=null
    var m :String?=null
    var y :String?=null
    var panRegex ="[A-Z]{5}[0-9]{4}[A-Z]{1}"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private  fun initViews()
    {
        binding.btnNext.setOnClickListener(this)
        binding.tvDont.setOnClickListener(this)
        binding.tvTerms.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.btn_next-> {
           verifyInputs()
            }
            R.id.tv_dont-> finish()
        }
    }


    fun verifyInputs()
    {
        panNo = binding.etPan.text.toString().trim()
        d = binding.etD.text?.trim().toString()
        m = binding.etM.text?.trim().toString()
        y = binding.etY.text?.trim().toString()
        if (panNo.isNullOrEmpty())
        {
            binding.etPan.error = resources.getString(R.string.enter_pan)
            binding.layPan.error= resources.getString(R.string.enter_pan)
            binding.etPan.requestFocus()
            binding.layPan.isErrorEnabled=true

        }
        else{
            binding.layPan.isErrorEnabled=false
            binding.layPan.error=null
            if (panNo?.matches(panRegex.toRegex()) == true) {
                if (d.isNullOrEmpty() || d?.toInt() == 0) {
                    binding.etD.error = resources.getString(R.string.enter_d)
                    binding.layD.error = resources.getString(R.string.enter_d)
                    binding.etD.requestFocus()
                    binding.layD.isErrorEnabled = true
                } else {
                    binding.layD.isErrorEnabled = false
                    binding.layD.error = null
                }


                if (m.isNullOrEmpty() || m?.length!! > 2 || m?.toInt() == 0) {
                    binding.etM.error = resources.getString(R.string.enter_m)
                    binding.layM.error = resources.getString(R.string.enter_m)
                    binding.etM.requestFocus()
                    binding.layM.isErrorEnabled = true
                } else {
                    binding.layM.isErrorEnabled = false
                    binding.layM.error = null
                }


                if (y.isNullOrBlank() || y?.length != 4 || y == "0000") {
                    binding.etY.error = resources.getString(R.string.enter_y)
                    binding.layY.error = resources.getString(R.string.enter_y)
                    binding.etY.requestFocus()
                    binding.layY.isErrorEnabled = true
                } else {
                    binding.layY.isErrorEnabled = false
                    binding.layY.error = null
                }
            }
            else
            {
                binding.etPan.error = resources.getString(R.string.valid_pan)
                binding.layPan.error= resources.getString(R.string.valid_pan)
                binding.etPan.requestFocus()
                binding.layPan.isErrorEnabled=true
            }
        }




        if (d.isNullOrEmpty() && m.isNullOrBlank() && y.isNullOrEmpty())
        {
            date = "${binding.etD.text.toString().trim()}/${binding.etM.text.toString().trim()}/${binding.etY.text.toString().trim()}"
            if(isValidDate(date))
            {
                Toast.makeText(this, resources.getString(R.string.complete), Toast.LENGTH_LONG).show()
            }
            else
            {
                binding.etY.requestFocus()
                binding.etM.requestFocus()
                binding.etD.requestFocus()
                binding.layY.isErrorEnabled=true
                binding.layD.isErrorEnabled=true
                binding.layM.isErrorEnabled=true
                Toast.makeText(this, resources.getString(R.string.valid_dob), Toast.LENGTH_LONG).show()

            }


        }
    }


    private fun isValidDate(s:String?):Boolean
    {
        var b = false
        if (s!=null)
        {
        var sdf =    SimpleDateFormat("dd/mm/yyyy")
            sdf.isLenient=false
            try{
                sdf.parse(s)
                b=true
            }
            catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
        else
            b= false
        return b
    }


}