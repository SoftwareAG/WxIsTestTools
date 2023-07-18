package wx.isUtilities.testAutomation;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Map;
// --- <<IS-END-IMPORTS>> ---

public final class localCache

{
	// ---( internal utility methods )---

	final static localCache _instance = new localCache();

	static localCache _newInstance() { return new localCache(); }

	static localCache _cast(Object o) { return (localCache)o; }

	// ---( server methods )---




	public static final void declareSuite (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(declareSuite)>> ---
		// @sigtype java 3.5
		// [i] field:0:required suiteName
		// [i] field:0:required suiteFolder
		//if(suites.containsKey(pipeline))
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	suiteName = IDataUtil.getString( pipelineCursor, "suiteName" );
			String	suiteFolder = IDataUtil.getString( pipelineCursor, "suiteFolder" );
		pipelineCursor.destroy();
		
		suitesHashMap.put(suiteName, suiteFolder);
		// pipeline
			
		// --- <<IS-END>> ---

                
	}



	public static final void getSuites (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSuites)>> ---
		// @sigtype java 3.5
		// [o] record:1:required suites
		// [o] - field:0:required name
		// [o] - field:0:required folder
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		// suites
		IData[]	suites = new IData[suitesHashMap.size()];
		int i=0;
		for(Map.Entry<String, String> entry: suitesHashMap.entrySet()){
			suites[i] = IDataFactory.create();
			IDataCursor suitesCursor = suites[i++].getCursor();
			IDataUtil.put( suitesCursor, "name", entry.getKey() );
			IDataUtil.put( suitesCursor, "folder", entry.getValue() );
			suitesCursor.destroy();
		}
		
		
		IDataUtil.put( pipelineCursor, "suites", suites );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void putTestResult (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(putTestResult)>> ---
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private final static java.util.HashMap<String, String> suitesHashMap = new java.util.HashMap<String, String>();
	// --- <<IS-END-SHARED>> ---
}

