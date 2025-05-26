package sync2app.com.syncapplive.additionalSettings.cloudAppsync.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

import sync2app.com.syncapplive.R;
import sync2app.com.syncapplive.databinding.LocalFileItemBinding;

public class LocalFileAdapter extends RecyclerView.Adapter<LocalFileAdapter.LocalFileViewHolder>{

    private List<File> fileList;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;

    public LocalFileAdapter(List<File> fileList, Context context, Activity activity) {
        this.fileList = fileList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public LocalFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LocalFileItemBinding binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.local_file_item,
                parent,
                false
        );

        return new LocalFileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalFileViewHolder holder, int position) {
        holder.bindFile(fileList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class LocalFileViewHolder extends RecyclerView.ViewHolder{

        private LocalFileItemBinding binding;

        public LocalFileViewHolder(LocalFileItemBinding binding){

            super(binding.getRoot());
            this.binding = binding;

        }

        @SuppressLint("RestrictedApi")
        public void bindFile(File file, int position){

            String fileName = file.getName();

            binding.setFileName(fileName);
            binding.executePendingBindings();


        }
    }

}
