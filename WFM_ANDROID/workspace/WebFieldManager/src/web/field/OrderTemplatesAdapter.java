package web.field;

import java.util.ArrayList;
import java.util.List;

import web.field.model.simple.OrderTemplateSimple;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderTemplatesAdapter extends ArrayAdapter<OrderTemplateSimple> {
	Context context;
	int layoutResourceId;
	List<OrderTemplateSimple> data = new ArrayList<OrderTemplateSimple>();

	public OrderTemplatesAdapter(Context context, int layoutResourceId,
			List<OrderTemplateSimple> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TemplateHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new TemplateHolder();
			holder.tvName = (TextView) row.findViewById(R.id.template_name);
			holder.tvId = (TextView) row.findViewById(R.id.template_id);
			holder.tvDescription = (TextView) row
					.findViewById(R.id.template_description);
			row.setTag(holder);
		} else {
			holder = (TemplateHolder) row.getTag();
		}
		OrderTemplateSimple template = data.get(position);
		holder.tvName.setText(template.getTemplateName());
		holder.tvId.setText(String.valueOf(template.getTemplateId()));
		holder.tvDescription.setText(template.getTemplateDescription());
		// holder.cbStatus.setChecked(customer.isActive());
		return row;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public OrderTemplateSimple getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return data.get(index).getTemplateId();
	}

	static class TemplateHolder {
		TextView tvId;
		TextView tvName;
		TextView tvDescription;
	}
}
