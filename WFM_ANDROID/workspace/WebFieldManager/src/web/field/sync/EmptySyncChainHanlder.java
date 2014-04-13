package web.field.sync;

import web.field.helpers.ISyncChainHandler;
import web.field.helpers.SyncCommand;

public class EmptySyncChainHanlder implements ISyncChainHandler{

	@Override
	public void setNext(ISyncChainHandler handler) {
		// do nothing, this is empty handler
	}

	@Override
	public void handle(SyncCommand command) {
		// do nothing, this is empty handler, just call end
		end();
	}
	
	public void end(){
		// override this to handle end of process
	}

}
