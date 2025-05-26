package sync2app.com.syncapplive.additionalSettings.cloudAppsync.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import sync2app.com.syncapplive.R;
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.models.Schedule;
import sync2app.com.syncapplive.additionalSettings.cloudAppsync.schedules.ScheduleList;
import sync2app.com.syncapplive.databinding.ScheduleItemBinding;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>{

    private List<Schedule> scheduleList;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;

    public ScheduleAdapter(List<Schedule> scheduleList, Context context, Activity activity) {
        this.scheduleList = scheduleList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ScheduleItemBinding binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.schedule_item,
                parent,
                false
        );

        return new ScheduleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.bindSchedule(scheduleList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder{

        private ScheduleItemBinding binding;

        public ScheduleViewHolder(ScheduleItemBinding binding){

            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindSchedule(Schedule schedule, int position){

            binding.setSchedule(schedule);
            binding.executePendingBindings();

            //check
            if (schedule.isOneTime()){
                binding.spec.setText("One-Time on " + schedule.getDate());
            } else

            if (schedule.isDaily()){
                binding.spec.setText("Daily");
            } else

            if (schedule.isWeekly()){
                binding.spec.setText(schedule.getDay() + "s Weekly");
            }

            //delete
            binding.deleteSchedule.setOnClickListener(v -> {
                if (context instanceof ScheduleList){
                    ((ScheduleList)context).deleteSchedule(schedule, position);
                }
            });


        }
    }

}
