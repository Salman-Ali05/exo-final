package com.example.kotlin_tp_3.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.kotlin_tp_3.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Load preferences from the XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey)

        // Reacting to changes in the username preference
        val usernamePreference = findPreference<Preference>("username")
        usernamePreference?.setOnPreferenceChangeListener { _, newValue ->
            Toast.makeText(requireContext(), "Username updated to $newValue", Toast.LENGTH_SHORT).show()
            true // Return true to update the preference value
        }

        // Reacting to changes in the notifications toggle
        val notificationsPreference = findPreference<Preference>("notifications")
        notificationsPreference?.setOnPreferenceChangeListener { _, newValue ->
            val message = if (newValue as Boolean) {
                "Notifications Enabled"
            } else {
                "Notifications Disabled"
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            true
        }
    }
}
