package web.field.helpers;

/***
 * Handles commands, or calls next handler
 * @author adamj_000
 *
 */
public interface ISyncChainHandler {
	//reference to the next handler in the chain
	public void setNext(ISyncChainHandler handler);
	
	
	//handle request
	public void handle(SyncCommand command);
}
