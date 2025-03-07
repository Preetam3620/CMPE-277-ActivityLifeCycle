package com.example.cmpe_277_activitylifecycle


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog

class DialogActivity : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Dialog Activity")
            .setMessage("This is a dialog. Activity A remains in focus.")
            .setPositiveButton("OK") { _, _ -> }
            .create()
    }
}
