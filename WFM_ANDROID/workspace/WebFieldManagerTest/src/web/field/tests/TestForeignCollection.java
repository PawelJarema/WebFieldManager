package web.field.tests;

import java.sql.SQLException;
import java.util.*;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.ForeignCollection;

@SuppressWarnings("serial")
public class TestForeignCollection<T> extends ArrayList<T> implements ForeignCollection<T> {

	@Override
	public CloseableIterator<T> closeableIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeLastIterator() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CloseableWrappedIterable<T> getWrappedIterable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEager() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CloseableIterator<T> iteratorThrow() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int refresh(T arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int refreshAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int refreshCollection() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
