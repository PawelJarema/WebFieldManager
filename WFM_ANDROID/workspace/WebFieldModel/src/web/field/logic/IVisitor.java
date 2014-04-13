package web.field.logic;

/***
 * Visitor interface
 * @author Bro
 *
 */
public interface IVisitor {
	
	/***
	 * Visits visited
	 * @param visitable Some visited
	 */
	public void visit(IVisited visitable);
}
