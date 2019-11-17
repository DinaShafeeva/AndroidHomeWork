package io.apptitude.premiumparking.fragments.popups

//
//import android.app.Dialog
//import android.content.Context
//import android.os.Bundle
//import android.view.ContextThemeWrapper
//import android.view.LayoutInflater
//import android.view.WindowManager
//import androidx.appcompat.app.AlertDialog
//import androidx.fragment.app.DialogFragment
//import androidx.fragment.app.FragmentManager
//import io.apptitude.premiumparking.R
//import io.apptitude.premiumparking.utils.constants.NUMBER_TEN
//import kotlinx.android.synthetic.main.dialog_refill_enter_other_amount.view.*
//import java.text.DecimalFormat
//
//class EnterRefillAmountPopup : DialogFragment() {
//
//    companion object {
//        private const val KEY_LAST_AMOUNT: String = "LAST_AMOUNT"
//
//        fun show(fragmentManager: FragmentManager, lastAmount: Double?): EnterRefillAmountPopup =
//            EnterRefillAmountPopup().apply {
//                arguments = Bundle().apply {
//                    lastAmount?.let { putDouble(KEY_LAST_AMOUNT, it) }
//                }
//                show(fragmentManager, EnterRefillAmountPopup::class.java.name)
//            }
//    }
//
//    private var listener: PopupClickListener? = null
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        listener = context as? PopupClickListener
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_refill_enter_other_amount, null, false)
//        arguments?.getDouble(KEY_LAST_AMOUNT)?.takeIf { it > 0.0 }?.also {
//            DecimalFormat("#").run {
//                maximumFractionDigits = NUMBER_TEN
//                dialogView?.et_amount?.setText(format(it))
//            }
//        }
//        val dialog = AlertDialog.Builder(ContextThemeWrapper(checkNotNull(context), R.style.MaterialAlertDialogStyle))
//            .setPositiveButton(R.string.save) { _, _ ->
//                listener?.onPositiveClick(dialogView?.et_amount?.text.toString().toDoubleOrNull())
//            }
//            .setNegativeButton(R.string.cancel) { _, _ ->
//                dismiss()
//            }
//            .setView(dialogView)
//            .create()
//        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
//        return dialog
//    }
//
//    interface PopupClickListener {
//        fun onPositiveClick(amount: Double?)
//    }
//}
