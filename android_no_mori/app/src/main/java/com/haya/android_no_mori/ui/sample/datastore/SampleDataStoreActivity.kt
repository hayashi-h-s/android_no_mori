package com.haya.android_no_mori.ui.sample.datastore

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.haya.android_no_mori.databinding.ActivitySampleDataStoreBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sample_preference")
val TEXT_KEY = stringPreferencesKey("edit_text")
val EDIT_TEXT_LENGTH_KEY = intPreferencesKey("edit_text_length")
val IS_EDIT_TEXT_LENGTH_FIVE_CHARACTERS_OR_MORE = intPreferencesKey("is_edit_text_length_five_characters_or_more")
// TODO: 5文字以上の場合  true

class SampleDataStoreActivity : AppCompatActivity() {
    private var _binding: ActivitySampleDataStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySampleDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                saveText(this@SampleDataStoreActivity)
            }
        }

        lifecycleScope.launch {
            val textFlow: Flow<String> = dataStore.data.map { p -> p[TEXT_KEY] ?: "" }
            // 値を取得 get the value
            textFlow.collect {
                binding.datastoreText.text = if (it.isNotEmpty())  it else "空文字"
            }
        }

        lifecycleScope.launch {
            val editTextLengthFlow: Flow<Int> =
                dataStore.data.map { p -> p[EDIT_TEXT_LENGTH_KEY] ?: 0 }
            editTextLengthFlow.collect {
                binding.datastoreTextLength.text = if (it > 0) it.toString() else "0"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private suspend fun saveText(context: Context) {
        // 値を保存 Save the value
        context.dataStore.edit { settings ->
            settings[TEXT_KEY] = binding.editText.text.toString()
            settings[EDIT_TEXT_LENGTH_KEY] = binding.editText.text.toString().length
        }
    }
}