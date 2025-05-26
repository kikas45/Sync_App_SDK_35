package sync2app.com.syncapplive.additionalSettings.myCompleteDownload

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import sync2app.com.syncapplive.R
import sync2app.com.syncapplive.additionalSettings.myApiDownload.FilesApi
import sync2app.com.syncapplive.additionalSettings.myFailedDownloadfiles.DnFailedApi
import sync2app.com.syncapplive.databinding.ItemSavedDownloadRowsBinding

class CopyDownloadsAdapter(): RecyclerView.Adapter<CopyDownloadsAdapter.MyViewHolder>() {

    private var userList = emptyList<FilesApi>()

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


    }

    override fun getItemCount(): Int {
        return userList.size
    }


    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        val filename = currentItem.FileName
        val folderName  = currentItem.FolderName
       // val Sn  = currentItem.SN
        val get_status  = currentItem.Status

        val title = "$folderName/ $filename"
       // holder.fileName.text = " $Sn: $filename"
        holder.fileName.text = filename
        holder.filePath.text = title

        if (get_status.equals("true")) {
            // Set back color for the divider to be green
            holder.divider.setBackgroundColor(ContextCompat.getColor(holder.divider.context, R.color.logo_green))
        } else {
            // Set back color for the divider to be red
            holder.divider.setBackgroundColor(ContextCompat.getColor(holder.divider.context, R.color.red))
        }


        }


    @SuppressLint("NotifyDataSetChanged")
    fun setDataApi(user: List<FilesApi>){
        this.userList = user
        notifyDataSetChanged()
    }



}
