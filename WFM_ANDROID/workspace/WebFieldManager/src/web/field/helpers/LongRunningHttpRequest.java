package web.field.helpers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import android.util.Log;

public class LongRunningHttpRequest extends AsyncTask<Void, Void, String> {
	private static final String TAG = "LongRunningHttpRequest";

	protected String json;
	protected HttpRequestBase request;

	public LongRunningHttpRequest(String json, HttpRequestBase request) {
		this.json = json;
		this.request = request;
	}

	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {

		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];

			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));

		}

		return out.toString();

	}

	@Override
	protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		String text = null;
		try {

			HttpResponse response = httpClient.execute(request, localContext);

			HttpEntity entity = response.getEntity();

			text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();

		}

		return text;

	}

	@Override
	protected void onPostExecute(String results) {
		if (results != null) {
			Log.d(TAG, results);
		}
	}
}
