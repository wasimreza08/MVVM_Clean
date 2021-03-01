package com.codeartist.starwar.common.base.activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.codeartist.starwar.R
import kotlin.properties.Delegates


abstract class BaseActivity<DataBinding : ViewDataBinding> : AppCompatActivity() {
    var dataBinding: DataBinding by Delegates.notNull()

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        dataBinding.lifecycleOwner = this
    }

    var mAlert: AlertDialog? = null
    fun showAlertDialog(context: Context?) {
        if (mAlert == null || !(mAlert as AlertDialog).isShowing) {
           // println("result test:" + "show dialog inside if " + context)
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage(context?.getString(R.string.error_text))
            builder.setCancelable(false)
            builder.setPositiveButton(
                context?.getString(R.string.ok)
            ) { dialog, id -> cancelAlertDialog() }
            mAlert = builder.create()
            (mAlert as AlertDialog).show()
        }
    }

    fun cancelAlertDialog() {
        mAlert?.cancel()
        mAlert = null
    }
}