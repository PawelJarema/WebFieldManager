package web.field;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflater;
	private String[] navs;
	private String[] icons;
	private int listRowId;

	public DrawerListAdapter(Context c, int resourceId) {
		context = c;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listRowId = resourceId;
		navs = c.getResources().getStringArray(R.array.nav_titles);
		icons = c.getResources().getStringArray(R.array.nav_icons);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(listRowId, null);
		}

		String nav = navs[position];
		String icon = icons[position];

		ImageView image = (ImageView) v.findViewById(R.id.drawer_nav_image);
		TextView text = (TextView) v.findViewById(R.id.drawer_nav_text);

		int image_id = context.getResources().getIdentifier(icon, "drawable",
				context.getPackageName());
		image.setImageDrawable(context.getResources().getDrawable(image_id));
		text.setText(nav);

		// style list depending on position
		if (position % 2 == 0)
			v.setBackgroundColor(context.getResources().getColor(
					R.color.list_dark));
		else
			v.setBackgroundColor(context.getResources().getColor(
					R.color.list_light));

		return v;
	}

	@Override
	public int getCount() {
		return navs.length;
	}

	@Override
	public Object getItem(int position) {
		return navs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
