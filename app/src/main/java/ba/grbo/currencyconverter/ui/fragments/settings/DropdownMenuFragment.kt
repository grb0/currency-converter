package ba.grbo.currencyconverter.ui.fragments.settings

import android.animation.Animator
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ba.grbo.currencyconverter.R
import ba.grbo.currencyconverter.ui.viewmodels.DropdownMenuViewModel
import ba.grbo.currencyconverter.util.FilterBy
import ba.grbo.currencyconverter.util.collectWhenStarted
import ba.grbo.currencyconverter.util.getEnterAndPopExitMaterialSharedXAnimators
import ba.grbo.currencyconverter.util.putString

class DropdownMenuFragment : PreferenceFragmentCompat() {
    private val viewModel: DropdownMenuViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private var currencyKey = ""
    private var currencyCode = ""
    private var currencyName = ""
    private var currencyBothCode = ""


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_dropmenu_menu, rootKey)
        initVariables()
        setListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        collectFlows()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator {
        return getEnterAndPopExitMaterialSharedXAnimators(nextAnim)
    }

    private fun initVariables() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        initCurrencyValues()
    }

    private fun initCurrencyValues() {
        currencyKey = getString(R.string.key_ui_name)
        currencyCode = getString(R.string.code_only_value)
        currencyName = getString(R.string.name_only_value)
        currencyBothCode = getString(R.string.both_code_and_name_value)
    }

    private fun setListeners() {
        observeFilterBy()
    }

    private fun collectFlows() {
        collectWhenStarted(viewModel.filterBy, ::onFilterByChanged, true)
    }

    private fun onFilterByChanged(filterBy: String) {
        synchCurrencyName(filterBy)
    }

    private fun observeFilterBy() {
        findPreference<ListPreference>(getString(R.string.key_filter_by))?.run {
            setOnPreferenceChangeListener { _, newValue ->
                viewModel.onFilterByChanged(value, newValue as String)
            }
        }
    }

    private fun synchCurrencyName(filterBy: String) {
        val currencyNameValue = sharedPreferences.getString(currencyKey, null)
        when (filterBy) {
            FilterBy.CODE.name -> {
                if (currencyNameValue == currencyName) {
                    modifyCurrencyName(currencyCode)
                }
            }
            FilterBy.NAME.name -> {
                if (currencyNameValue == currencyCode) {
                    modifyCurrencyName(currencyName)
                }
            }
            FilterBy.BOTH.name -> {
                if (currencyNameValue == currencyCode || currencyNameValue == currencyName) {
                    modifyCurrencyName(currencyBothCode)
                }
            }
        }
    }

    private fun modifyCurrencyName(value: String) {
        sharedPreferences.putString(currencyKey, value)
    }
}