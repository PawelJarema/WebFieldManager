package web.field.logic;

/***
 * Visited interface
 * @author Bro
 *
 */
public interface IVisited {
	
	/***
	 * Accepts visitor
	 * @param visitor Some Visitor
	 */
	public void accept(IVisitor visitor);
}
