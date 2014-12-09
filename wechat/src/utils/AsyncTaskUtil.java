package utils;

import android.os.AsyncTask;

public abstract class AsyncTaskUtil {

	abstract protected void doInBackground();

	abstract protected void onPreExecute();

	abstract protected void onPostExecute();
	
	private String currentStdClass;
   
	
	
	public AsyncTaskUtil(String mycurrentStdClass) {
		super();
		
		this.setCurrentStdClass(mycurrentStdClass);
		// TODO Auto-generated constructor stub
	}
	

	public AsyncTaskUtil() {
		super();
		
	}

	public void execute() {
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				AsyncTaskUtil.this.doInBackground();
				return null;
			}

			protected void onPreExecute() {
				AsyncTaskUtil.this.onPreExecute();
			};

			protected void onPostExecute(Void result) {
				AsyncTaskUtil.this.onPostExecute();
			};

		}.execute();

	}

	public String getCurrentStdClass() {
		return currentStdClass;
	}

	public void setCurrentStdClass(String currentStdClass) {
		this.currentStdClass = currentStdClass;
	}

}
