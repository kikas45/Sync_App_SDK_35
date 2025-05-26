package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.databinding.ItemSavedDownloadRowsBinding

class SavedDownloadsAdapter : RecyclerView.Adapter<SavedDownloadsAdapter.MyViewHolder>() {

    private var userList = emptyList<DnApi>()
    private var preferences: SharedPreferences? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSavedDownloadRowsBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_saved_download_rows, parent, false)
        )

        return MyViewHolder(binding)
    }


    inner class MyViewHolder(private val binding: ItemSavedDownloadRowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val fileName = binding.fileName
        val filePath = binding.filePath
        val divider = binding.divider
        val fileDownloadProgress = binding.fileDownloadProgress
        val mlayout = binding.mlayout


    }

    override fun getItemCount(): Int {
        return userList.size
    }


    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        preferences = PreferenceManager.getDefaultSharedPreferences(holder.divider.context)

        val currentItem = userList[position]

        val filename = currentItem.FileName
        val folderName  = currentItem.FolderName
       // val Sn  = currentItem.SN
        val get_status  = currentItem.Status

        val title = "$folderName/ $filename"
       // holder.fileName.text = " $Sn: $filename"
        holder.fileName.text = filename
        holder.filePath.text = title

        if (preferences!!.getBoolean("darktheme", false)) {
            holder.fileName.setTextColor(ContextCompat.getColor(holder.divider.context, R.color.white))
            holder.filePath.setTextColor(ContextCompat.getColor(holder.divider.context, R.color.white))
            holder.mlayout.setBackgroundColor(holder.divider.resources.getColor(R.color.dark_layout_for_ui))

        }


        if (get_status.equals("true")) {
            holder.divider.setImageResource(R.drawable.ic_light_checked)
            holder.divider.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.divider.context, R.color.deep_green))
            holder.fileDownloadProgress.setBackgroundColor(ContextCompat.getColor(holder.divider.context, R.color.logo_blue))

        } else {
            // Set back color for the divider to be red
            holder.divider.setImageResource(R.drawable.ic_failed_circle)
            holder.divider.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.divider.context, R.color.red))
            holder.fileDownloadProgress.setBackgroundColor(ContextCompat.getColor(holder.divider.context, R.color.red))
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    fun setDataApi(user: List<DnApi>){
        this.userList = user
        notifyDataSetChanged()
    }



}
