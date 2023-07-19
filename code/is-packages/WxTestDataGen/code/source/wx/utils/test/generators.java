package wx.utils.test;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class generators

{
	// ---( internal utility methods )---

	final static generators _instance = new generators();

	static generators _newInstance() { return new generators(); }

	static generators _cast(Object o) { return (generators)o; }

	// ---( server methods )---




	public static final void getNull (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getNull)>> ---
		// @sigtype java 3.5
		// [o] object:0:required nullObject
		final IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "nullObject", null );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

