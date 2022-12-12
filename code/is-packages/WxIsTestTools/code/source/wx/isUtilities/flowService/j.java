package wx.isUtilities.flowService;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
import java.util.*;
import com.wm.app.b2b.server.*;
import com.wm.lang.ns.*;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void invokeFlow (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invokeFlow)>> ---
		// @sigtype java 3.5
		// [i] field:0:required flowDir
		// [i] field:0:required flowName
		// [i] record:0:optional flowInput
		// [o] record:0:optional flowOutput
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	flowDir = IDataUtil.getString( pipelineCursor, "flowDir" );
			String	flowName = IDataUtil.getString( pipelineCursor, "flowName" );
		 
			// flowInput
			IData	flowInput = IDataUtil.getIData( pipelineCursor, "flowInput" );
			if ( flowInput != null)
			{ 
				//TODO: check if null is ok or not
		 	}
		
		// flowOutput
		IData	flowOutput;
		
		try{
			flowOutput = IDataUtil.clone(Service.doInvoke( flowDir, flowName, IDataUtil.clone(flowInput)));
		}catch( Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		 
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		IDataUtil.put(pipelineCursor_1, "flowOutput", flowOutput );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void invokeFlowInNewThread (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invokeFlowInNewThread)>> ---
		// @sigtype java 3.5
		// [i] field:0:required flowDir
		// [i] field:0:required flowName
		// [i] record:0:optional flowInput
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	flowDir = IDataUtil.getString( pipelineCursor, "flowDir" );
		String	flowName = IDataUtil.getString( pipelineCursor, "flowName" );
		 
		// flowInput
		IData	flowInput = IDataUtil.getIData( pipelineCursor, "flowInput" );
		if ( flowInput != null)
		{ 
			//TODO: check if null is ok or not
		}
		
		try{
			Service.doThreadInvoke( flowDir, flowName, IDataUtil.clone(flowInput));
		}catch( Exception e)
		{
			throw new ServiceException(e.getMessage());
		}		 			
		// --- <<IS-END>> ---

                
	}
}

