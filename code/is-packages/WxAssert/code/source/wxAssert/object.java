package wxAssert;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.JournalLogger;
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
		// [i] field:0:optional label
		// [o] field:0:required bAssertionResult
		boolean bResult=false;
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			final boolean bExists = pipelineCursor.first("object");
			Object	object = null;
			if(bExists)
				object = IDataUtil.get( pipelineCursor, "object" );
			final String label = pipelineCursor.first("label")?(String)IDataUtil.get( pipelineCursor, "label" ):"isNumber";
		pipelineCursor.destroy();
		
		
		if (!bExists){
			JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.WARNING, LOG_PREFIX+" "+label, "Failed: No input received");
		}
		else if( null == object ){
			JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.WARNING, LOG_PREFIX+" "+label, "Failed: Null object received");
		}else if (object instanceof Number ){
			bResult = true;
			JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.INFO, LOG_PREFIX+" "+label, "OK: Assertion passed!");
		}else if(object instanceof String ){
			try{
				double d=Double.parseDouble((String)object);
				bResult = true;
				JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.INFO, LOG_PREFIX+" "+label, "OK: Assertion passed!");
			}
			catch(NumberFormatException nfe){
				JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.WARNING, LOG_PREFIX+" "+label, "Failed: String is not a number: " + object);
			}
		}else{
			JournalLogger.log(LOG_CODE, LOG_FACILITY, JournalLogger.WARNING, LOG_PREFIX+" "+label
										 , "Failed: Received object is not a number, class is " + object.getClass().getCanonicalName());
		
		}
		// pipeline
		pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "bAssertionResult", "" + bResult );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private final static int LOG_CODE=4;
	private final static int LOG_FACILITY=90;
	private final static String LOG_PREFIX="Object Assertions";
	// --- <<IS-END-SHARED>> ---
}

