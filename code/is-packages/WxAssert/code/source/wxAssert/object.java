package wxAssert;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void isNumber (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(isNumber)>> ---
		// @sigtype java 3.5
		// [i] object:0:optional object
		// [o] field:0:required bAssertionResult
		boolean bResult=false;
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			boolean bExists = pipelineCursor.first("object");
			Object	object = null;
			if(bExists)
				object = IDataUtil.get( pipelineCursor, "object" );
		pipelineCursor.destroy();
		
		if (!bExists){
			com.wm.util.JournalLogger.log(LOG_CODE, LOG_FACILITY, com.wm.util.JournalLogger.WARNING, LOG_PREFIX, "No input received");
		}
		else if( null == object ){
			com.wm.util.JournalLogger.log(LOG_CODE, LOG_FACILITY, com.wm.util.JournalLogger.LOGLEVEL_TRACE, LOG_PREFIX, "Null object received");
		}else if (object instanceof Number ){
			bResult = true;
		}else if(object instanceof String ){
			try{
				double d=Double.parseDouble((String)object);
				bResult = true;
			}
			catch(NumberFormatException nfe){
				System.out.println("String is not a number: " + object);
			}
		}else{
			System.out.println("Received object is not a number, class is " + object.getClass().getCanonicalName());
		}
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "bAssertionResult", "" + bResult );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private final static int LOG_CODE=4;
	private final static int LOG_FACILITY=90;
	private final static String LOG_PREFIX="Object Assertions";
	// --- <<IS-END-SHARED>> ---
}

