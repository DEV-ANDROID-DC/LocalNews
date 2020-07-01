package com.debin.localnews.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.transition.Fade
import android.transition.Scene
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.debin.localnews.R
import com.debin.localnews.model.Category
import com.debin.localnews.model.NewsData
import com.debin.localnews.util.ProgressBarTransition
import com.debin.localnews.viewmodel.AddNewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_addnews.*
import kotlinx.android.synthetic.main.scene_item_image.*
import kotlinx.android.synthetic.main.scene_upload.*

class AddNewsActivity : AppCompatActivity() , TextWatcher,
    AdapterView.OnItemSelectedListener{

    private lateinit var addNewsViewModel: AddNewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnews)

        addNewsViewModel = ViewModelProvider(this).get(AddNewsViewModel::class.java)

        categorySpinner.adapter = ArrayAdapter<Category>(
            this,
            android.R.layout.simple_spinner_item,
            Category.values()
        )
        categorySpinner.onItemSelectedListener = this

        titleEditText.addTextChangedListener(this)
        nameEditText.addTextChangedListener(this)
        descriptionEditText.addTextChangedListener(this)


    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        titleTextInput.error = null
        nameTextInput.error = null
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        imageButton.setImageResource(imageFromCategory(categorySpinner.selectedItem as Category))
    }

    fun onClickAddNews(view: View?) {
        if (hasValidInput()) {
            // Apply Scene transition for uploading
            val uploadScene: Scene = Scene.getSceneForLayout(sceneContainer, R.layout.scene_upload, this)
            TransitionManager.go(uploadScene, Fade())

            // Simulate uploading using custom ProgressBarTransition
            val uploadTime: Long = 3000
            val statusInterval: Long = 600

            object : CountDownTimer(uploadTime, statusInterval) {
                val maxProgress = 100
                var uploadProgress = 0

                override fun onTick(millisUntilFinished: Long) {
                    // Show transition animation
                    if (uploadProgress < maxProgress) {
                        uploadProgress += 20
                        TransitionManager.beginDelayedTransition(sceneContainer, ProgressBarTransition())
                        progressBar.progress = uploadProgress
                    }
                }

                override fun onFinish() {
                    // Add Item and show status
                    uploadStatus.text = getString(R.string.text_uploaded)
                    addItemData()
                    showAddNewsConfirmation()
                }
            }.start()
        }
    }

    fun addItemData() {
        val selectedCategory = categorySpinner.selectedItem as Category
        Log.e("TAG_X",  "Category :: " +selectedCategory)
        var categoryId : String = ""
        if(selectedCategory.equals(Category.TOP_NEWS)) {
            categoryId = "1"
        } else if(selectedCategory.equals(Category.CITY_NEWS)) {
            categoryId = "2"
        } else if (selectedCategory.equals(Category.COUNTY_NEWS)) {
            categoryId = "3"
        }
        val random = (0..100).random()
        Log.e("TAG_X",  "random :: " +random)
        Log.e("TAG_X",  "Category Id :: " +categoryId)
        val newsData = NewsData(random,titleEditText.text.toString(), descriptionEditText.text.toString()
            ,nameEditText.text.toString(),categoryId)
        addNewsViewModel.insertNews(newsData)
    }

    private fun hasValidInput(): Boolean {
        val title = titleEditText.text.toString()
        if (title.isNullOrBlank()) {
            titleTextInput.error = getString(R.string.error_message_title)
            return false
        }

        val name = nameEditText.text.toString()
        if (name.isNullOrBlank()) {
            nameEditText.error = getString(R.string.error_message_name)
            return false
        }

        return true
    }

    private fun imageFromCategory(category: Category): Int {
        return when (category) {
            Category.TOP_NEWS -> R.drawable.ic_launcher_foreground
            Category.CITY_NEWS -> R.drawable.ic_launcher_foreground
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun showAddNewsConfirmation() {
        Snackbar.make(addNewsRootView, getString(R.string.add_news_successful), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.ok)) {
                navigateBackToItemList()
            }
            .show()
    }

    private fun navigateBackToItemList() {
        val mainIntent = Intent(this, NewsListActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(mainIntent)
        finish()
    }

}