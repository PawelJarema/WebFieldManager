package web.field.order.processing;

import android.os.AsyncTask;

/**
 * Process order basing on product prices
 * 
 * @author Bro
 * 
 */
public class ProcessOrderTask extends
		AsyncTask<OrderCalculationRequest, Void, OrderCalculationResult> {

	private IProcessOrderStrategy processStrategy;

	@Override
	protected OrderCalculationResult doInBackground(
			OrderCalculationRequest... requests) {

		return processStrategy.process(requests[0]);

	}

}
