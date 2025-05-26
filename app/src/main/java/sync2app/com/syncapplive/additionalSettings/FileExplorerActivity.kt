package sync2app.com.syncapplive.additionalSettings

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.additionalSettings.autostartAppOncrash.Methods
import sync2app.com.syncapplive.additionalSettings.fileMnager.FileManagerAdapter
import sync2app.com.syncapplive.additionalSettings.urlchecks.checkStoragePermission
import sync2app.com.syncapplive.additionalSettings.urlchecks.getFilesList
import sync2app.com.syncapplive.additionalSettings.urlchecks.openFile
import sync2app.com.syncapplive.additionalSettings.urlchecks.renderItem
import sync2app.com.syncapplive.additionalSettings.urlchecks.renderParentLink
import sync2app.com.syncapplive.additionalSettings.utils.Constants
import sync2app.com.syncapplive.additionalSettings.utils.Utility
import sync2app.com.syncapplive.databinding.ActivityFileExplorerBinding
import java.io.File
import java.util.Objects

class FileExplorerActivity : AppCompatActivity() {

    private var hasPermission = false
    private lateinit var binding: ActivityFileExplorerBinding
    private lateinit var currentDirectory: File
    private lateinit var filesList: List<File>
    private lateinit var adapter: FileManagerAdapter


    var openCounts = 0

    private val sharedPreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.file_explorer_prefs,
            Context.MODE_PRIVATE
        )
    }

    private val myDownloadClass: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.MY_DOWNLOADER_CLASS,
            Context.MODE_PRIVATE
        )
    }


    private val sharedBiometric: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.SHARED_BIOMETRIC,
            Context.MODE_PRIVATE
        )
    }


    private val preferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }


    private val sharedTVAPPModePreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            Constants.SHARED_TV_APP_MODE, Context.MODE_PRIVATE
        )
    }


    @SuppressLint("CommitPrefEdits", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_explorer)
        binding = ActivityFileExplorerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyOritenation()


        setUpFullScreenWindows()


        val get_imgToggleImageBackground = sharedBiometric.getString(Constants.imgToggleImageBackground, "")
        val get_imageUseBranding = sharedBiometric.getString(Constants.imageUseBranding, "")
        if (get_imgToggleImageBackground.equals(Constants.imgToggleImageBackground) && get_imageUseBranding.equals(Constants.imageUseBranding) ){
            loadBackGroundImage()
        }




        setupUi()

        //add exception
        Methods.addExceptionHandler(this)


        binding.closeBs.setOnClickListener {

            val sharedPreferences = getSharedPreferences("file_explorer_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            finish()

        }
    }

    private fun setUpFullScreenWindows() {
        val get_INSTALL_TV_JSON_USER_CLICKED = sharedTVAPPModePreferences.getString(Constants.INSTALL_TV_JSON_USER_CLICKED, "").toString()
        if (get_INSTALL_TV_JSON_USER_CLICKED != Constants.INSTALL_TV_JSON_USER_CLICKED) {
            val img_imgImmesriveModeToggle = preferences.getBoolean(Constants.immersive_mode, false)
            if (img_imgImmesriveModeToggle){
                Utility.hideSystemBars(window)
            }else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }


        }else{

            val immersive_Mode_APP = sharedTVAPPModePreferences.getBoolean(Constants.immersive_Mode_APP, false)
            if (immersive_Mode_APP) {
                Utility.hideSystemBars(window)
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }

        }
    }



    override fun onResume() {
        super.onResume()
        setEnviroment()
    }

    private fun setEnviroment() {
        hasPermission = checkStoragePermission(this)
        if (hasPermission) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                if (!Environment.isExternalStorageLegacy()) {
                    return
                }
            }

            binding.filesTreeView.visibility = View.VISIBLE

            val lastOpenedFolderPath = sharedPreferences.getString("LAST_OPENED_FOLDER_KEY", "")
            val lastOpenedFolder = if (lastOpenedFolderPath.isNullOrEmpty()) {
                File(Environment.getExternalStorageDirectory().absolutePath + "/Download/Syn2AppLive/")
            } else {
                File(lastOpenedFolderPath)
            }

            open(lastOpenedFolder)

        } else {
            binding.filesTreeView.visibility = View.GONE
        }
    }


    private fun setupUi() {
        adapter = FileManagerAdapter(this, mutableListOf())
        binding.filesTreeView.adapter = adapter
        binding.filesTreeView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = filesList[position]
            open(selectedItem)
            openCounts++
        }

        binding.filesTreeView.setOnItemLongClickListener { _, _, position, _ ->
            val selectedItem = filesList[position]
            showDeleteDialog(selectedItem)
            true
        }
    }



    private fun open(selectedItem: File) {
       try {
           if (selectedItem.isFile) {
               return openFile(this, selectedItem)
           }

           currentDirectory = selectedItem
           filesList = getFilesList(currentDirectory)

           adapter.clear()
           adapter.addAll(filesList.map {
               if (it.path == selectedItem.parentFile.path) {
                   renderParentLink(this)
               } else {
                   renderItem(this, it)
               }
           })

           adapter.notifyDataSetChanged()

           binding.textNoFolder.visibility = if (filesList.isEmpty()) View.VISIBLE else View.GONE

           val editor = sharedPreferences.edit()
           editor.putString("LAST_OPENED_FOLDER_KEY", selectedItem.absolutePath);
           editor.apply()


       }catch (e:Exception){}
    }


    private fun showDeleteDialog(file: File) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete")
        alertDialog.setMessage("Are you sure you want to delete ${file.name}?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            val parentDirectory = file.parentFile
            val wasFolderEmpty = parentDirectory?.listFiles()?.isEmpty() ?: true
            if (delete(file)) {
                // Call notifyDataSetChanged after successful deletion
                adapter.notifyDataSetChanged()
                // Navigate back to the parent folder if it was not empty
                if (!wasFolderEmpty) {
                    open(parentDirectory!!)
                }
                // Show toast or perform other actions if needed
                Toast.makeText(this, "${file.name} deleted", Toast.LENGTH_SHORT).show()
            } else {
                showAlert("Failed to delete ${file.name}")
            }
        }
        alertDialog.setNegativeButton("No", null)
        alertDialog.show()
    }


    private fun showAlert(message: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("OK", null)
        alertDialog.show()
    }


    fun delete(file: File): Boolean {
        if (file.isFile) {
            return file.delete()
        } else if (file.isDirectory) {
            for (subFile in Objects.requireNonNull(file.listFiles())) {
                if (!delete(subFile)) return false
            }
            return file.delete()
        }
        return false
    }


    override fun onBackPressed() {
        try {
            val directoryPath = Environment.getExternalStorageDirectory().absolutePath + "/Download/${Constants.Syn2AppLive}/"
            val syn2AppLiveFolder = File(directoryPath)

            currentDirectory.let {
                if (it ==syn2AppLiveFolder){
                    super.onBackPressed()
                } else {
                    if (openCounts > 0) {
                        val parentDirectory = currentDirectory.parentFile
                        if (parentDirectory != null) {
                            open(parentDirectory)
                            openCounts--
                        }
                    } else {

                        val sharedPreferences = getSharedPreferences("file_explorer_prefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.apply()
                        super.onBackPressed()
                    }
                }
            }

        }catch (e:Exception){
            showToastMessage(e.message.toString())
        }
    }
    private fun showToastMessage(messages: String) {

        try {
            Toast.makeText(applicationContext, messages, Toast.LENGTH_SHORT).show()
        } catch (_: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try {
            val sharedPreferences = getSharedPreferences("file_explorer_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

        }catch (e:Exception){
            showToastMessage(e.message.toString())
        }
    }

    private fun loadBackGroundImage() {

        val fileTypes = "app_background.png"
        val getFolderClo = myDownloadClass.getString(Constants.getFolderClo, "").toString()
        val getFolderSubpath = myDownloadClass.getString(Constants.getFolderSubpath, "").toString()

        val pathFolder = "/" + getFolderClo + "/" + getFolderSubpath + "/" + Constants.App + "/" + "Config"
        val folder =
            Environment.getExternalStorageDirectory().absolutePath + "/Download/${Constants.Syn2AppLive}/" + pathFolder
        val file = File(folder, fileTypes)

        if (file.exists()) {
            Glide.with(this).load(file).centerCrop().into(binding.backgroundImage)

        }
    }



    @SuppressLint("SourceLockedOrientationActivity")
    private fun applyOritenation() {
        val getState = sharedBiometric.getString(Constants.IMG_TOGGLE_FOR_ORIENTATION, "").toString()

        if (getState == Constants.USE_POTRAIT){
            requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        }else if (getState == Constants.USE_LANDSCAPE){

            requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        }else if (getState == Constants.USE_UNSEPECIFIED){
            requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        }

    }



}

