package web.field;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import web.field.helpers.LongRunningHttpRequest;
import web.field.model.LogOnAnswer;
import web.field.model.LogOnModel;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends WebfieldFragmentActivityInner {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private LongRunningHttpRequest mAuthTask = null;
	private Context context;
	
	// UI references.
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	// Ui element refs
	Button login_button;
	EditText etUsername;
	EditText etPassword;
	String username;
	String password;
	
	protected void onStart() {
		super.onStart();
		dismissProgressDialog();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		if (this.getIntent().hasExtra("logout"))
			logOut();
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String token = preferences.getString("user_token", null);
		if (token != null) {
			Intent i = new Intent("web.field.HomeActivity");
			startActivity(i);
		}

		setContentView(R.layout.activity_login);
		prepareUiElements();
//		mPasswordView
//				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//					@Override
//					public boolean onEditorAction(TextView textView, int id,
//							KeyEvent keyEvent) {
//						if (id == R.id.login || id == EditorInfo.IME_NULL) {
//							attemptLogin();
//							return true;
//						}
//						return false;
//					}
//				});
//
//		mLoginFormView = findViewById(R.id.login_form);
//		mLoginStatusView = findViewById(R.id.login_status);
//		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.login_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}
	
	private void prepareUiElements() {
		login_button = (Button)findViewById(R.id.login_button);
		etUsername = (EditText)findViewById(R.id.login_email_edittext);
		etPassword = (EditText)findViewById(R.id.login_password_edittext);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			// settings
			Intent i = new Intent("web.field.SettingsAct");
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	
	public void logOut() {
		mAuthTask = null;
		//TODO clear prefs ?
		SharedPreferences preferences = WebFieldApplication.getSharedPreferences();
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(SharedPreferencesKeys.user_name, null);
		editor.putString(SharedPreferencesKeys.user_token, null);
		editor.commit();
		message(R.string.logged_out);
	}

	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}
		//showProgressDialog();
		
		// Reset errors.
//		mEmailView.setError(null);
//		mPasswordView.setError(null);
//
		// Store values at the time of the login attempt.
		login_button.setBackgroundResource(R.color.app_orange_clicked);
		username = etUsername.getText().toString();
		password = etPassword.getText().toString();
//
		boolean cancel = false;
//		View focusView = null;
//
//		// Check for a valid password.
//		if (TextUtils.isEmpty(mPassword)) {
//			mPasswordView.setError(getString(R.string.error_field_required));
//			focusView = mPasswordView;
//			cancel = true;
//		} else if (mPassword.length() < 4) {
//			mPasswordView.setError(getString(R.string.error_invalid_password));
//			focusView = mPasswordView;
//			cancel = true;
//		}
//
//		// Check for a valid email address.
//		if (TextUtils.isEmpty(mEmail)) {
//			mEmailView.setError(getString(R.string.error_field_required));
//			focusView = mEmailView;
//			cancel = true;
//		} else if (!mEmail.contains("@")) {
//			mEmailView.setError(getString(R.string.error_invalid_email));
//			focusView = mEmailView;
//			cancel = true;
//		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
//			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
//			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
//			showProgress(true);

			final SharedPreferences preferences = WebFieldApplication
					.getSharedPreferences();
			String serviceAddress = preferences.getString(
					SharedPreferencesKeys.connection_service, null);
			if (TextUtils.isEmpty(serviceAddress)) {
				Toast.makeText(getApplicationContext(),
						R.string.error_missing_service_address,
						Toast.LENGTH_SHORT).show();
				//dismissProgressDialog();
				//showProgress(false);
			} else {
				LogOnModel model = new LogOnModel();
				model.setUsername(username);
				model.setPassword(password);
				String json = new Gson().toJson(model);
				if (!serviceAddress.endsWith("/")) {
					serviceAddress += "/";
				}

				HttpPost post = new HttpPost(serviceAddress + "LogIn/");

				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");
				try {
					post.setEntity(new StringEntity(json));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					KillLogin();
					return;
				}
				mAuthTask = new LongRunningHttpRequest(json, post) {
					
					@Override
					protected void onPreExecute() {
						super.onPreExecute();
						showProgressDialog();
					}

					@Override
					protected void onPostExecute(String results) {
						if (results != null && !"false".equals(results)) {

							LogOnAnswer answer = null;
							try {
								answer = new Gson().fromJson(results,
										LogOnAnswer.class);
							} catch (JsonSyntaxException e) {
								mAuthTask = null;
								KillLogin();
								GoToSettings();
								return;
							}

							// save user name to shared properties
							SharedPreferences.Editor editor = preferences
									.edit();
							editor.putString(
									SharedPreferencesKeys.user_name,
									answer.getFirstname() + " "
											+ answer.getLastname());
							editor.putString(SharedPreferencesKeys.user_token,
									answer.getToken());
							editor.commit();
							
							// see if pin is set
							String user_token = SharedPreferencesKeys.user_token;
							SharedPreferences preferences = WebFieldApplication.getSharedPreferences();
							if (preferences.getString("pin_" + user_token, null) == null) {
								GoToSettings();
							}
							Intent home = new Intent(getApplicationContext(), HomeActivity.class);
							startActivity(home);

						} else {
							message(R.string.error_login_failed);
							mAuthTask = null;
							login_button.setBackgroundResource(R.color.app_orange);
							//dismissProgressDialog();
						}
						//showProgress(false);
					}
				};
				mAuthTask.execute((Void) null);
			}
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private void KillLogin() {
		dismissProgressDialog();
		message(getApplication().getResources().getString(R.string.login_fail));
	}
	
	private void GoToSettings() {
		Intent settings = new Intent("web.field.SettingsAct");
		startActivity(settings);
	}
}
