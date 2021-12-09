package com.example.myapplication.Recipe

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class FilterDialogsFragment(private val filterArray: Array<String>,
                            private val name: String,
                            private val selectedItems: BooleanArray,
                            private var checkedItems: Array<String>
): DialogFragment() {

    private lateinit var listener: FilterDialogListener

    interface FilterDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, selectedItems: Array<String>, name: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as FilterDialogListener
        }catch (e: ClassCastException){
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val processedItems = checkedItems.toMutableList()
        return activity?.let {
             // Where we track the selected items
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle(name)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(filterArray, selectedItems)
                {dialog, which, isChecked ->
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        processedItems += filterArray[which]
                    } else if (processedItems.contains(filterArray[which])) {
                        //Else, if the item is already in the array, remove it
                        for ((j, i) in processedItems.withIndex()) {
                            if (i == filterArray[which]) {
                                processedItems.removeAt(j)
                                break
                            }
                        }
                        println("loop broke")
                    }
                }
                // Set the action buttons
                .setPositiveButton("done"){ dialog, id ->
                        // User clicked OK, so save the selectedItems results somewhere
                        listener.onDialogPositiveClick(this, processedItems.toTypedArray(), name)
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")


    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(true)
    }

}