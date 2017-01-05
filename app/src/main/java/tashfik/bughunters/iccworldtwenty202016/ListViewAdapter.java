package tashfik.bughunters.iccworldtwenty202016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<Weeks> weeklist = null;
	private ArrayList<Weeks> arraylist;

	public ListViewAdapter(Context context, List<Weeks> weeks) {
		mContext = context;
		this.weeklist = weeks;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<Weeks>();
		this.arraylist.addAll(weeks);
	}

	public class ViewHolder {
		TextView Name;
		TextView Semester;
		TextView Grade;

	 
	}

	@Override
	public int getCount() {
		return weeklist.size();
	}

	@Override
	public Weeks getItem(int position) {
		return weeklist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.custom_list, null);
			// Locate the TextViews in listview_item.xml
			holder.Name = (TextView) view.findViewById(R.id.ename);
			holder.Semester = (TextView) view.findViewById(R.id.esemester);
			holder.Grade = (TextView) view.findViewById(R.id.egrade);

			
			
		 	
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.Name.setText(""+weeklist.get(position).getName());
		holder.Semester.setText(""+weeklist.get(position).getSemester());
		holder.Grade.setText(""+weeklist.get(position).getGrade());

		
		 
	
		
		
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				//Intent intent = new Intent(mContext, SingleItemView.class);
				// Pass all data rank
				//intent.putExtra("title",(weeklist.get(position).getTitle()()));
				// Pass all data country
				//intent.putExtra("description",(weeklist.get(position).getCountry()));
				// Pass all data population
				
				//mContext.startActivity(intent);
			}
		});

		return view;
	}

	
	
	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		weeklist.clear();
		if (charText.length() == 0) {
			weeklist.addAll(arraylist);
		} 
		else 
		{
			for (Weeks w : arraylist) 
			{
				if (w.getName().toLowerCase(Locale.getDefault()).contains(charText)) 
				{
					weeklist.add(w);
				}
			}
		}
		notifyDataSetChanged();
	}

}
