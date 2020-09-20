package com.xander.catfacts.extension

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.xander.catfacts.R
import com.xander.catfacts.extension.bindcontract.setImage
import com.xander.catfacts.model.CatFact

fun Fragment.dialogFact(catFact: CatFact) {
    context?.let {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        inflater?.let {
            val view = inflater.inflate(R.layout.layout_dialog_fact, null)
            val image = view.findViewById<ImageView>(R.id.iv_fact)
            val text = view.findViewById<TextView>(R.id.tv_fact)
            text.text = catFact.fact
            image.setImage(catFact.picture)
            android.app.AlertDialog.Builder(this.context, R.style.Theme_AppCompat_Dialog_Alert)
                .setView(view)
        }?.show()
    }
}