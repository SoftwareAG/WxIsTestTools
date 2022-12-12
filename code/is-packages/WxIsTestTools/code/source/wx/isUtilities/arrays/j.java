package wx.isUtilities.arrays;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void dedupeStringArray (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(dedupeStringArray)>> ---
		// @sigtype java 3.5
		// [i] field:1:required strArray
		// [o] field:1:required strArrayResult
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[]	strArray = IDataUtil.getStringArray( pipelineCursor, "strArray" );
		pipelineCursor.destroy();
		
		HashSet<String> hs = new HashSet<String>();
		if(null != strArray && strArray.length>0)
			for(int i=0; i<strArray.length;i++)
				hs.add(strArray[i]);
		
		String[] ka= hs.toArray(new String[0]);
		Arrays.sort(ka);
					
		if(0<hs.size())
		{
			// pipeline
			pipelineCursor = pipeline.getCursor();
			IDataUtil.put( pipelineCursor, "strArrayResult", ka );
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void joinAndDedupeStringArrays (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(joinAndDedupeStringArrays)>> ---
		// @sigtype java 3.5
		// [i] field:1:required strArray1
		// [i] field:1:required strArray2
		// [o] field:1:required strArrayResult
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[]	strArray1 = IDataUtil.getStringArray( pipelineCursor, "strArray1" );
			String[]	strArray2 = IDataUtil.getStringArray( pipelineCursor, "strArray2" );
		pipelineCursor.destroy();
		
		HashSet<String> hs = new HashSet<String>();
		if(null != strArray1 && strArray1.length>0)
			for(int i=0; i<strArray1.length;i++)
				hs.add(strArray1[i]);
		if(null != strArray2 && strArray1.length>0)
			for(int i=0; i<strArray2.length;i++)
				hs.add(strArray2[i]);
		
		String[] ka= hs.toArray(new String[0]);
		Arrays.sort(ka);
					
		if(0<hs.size())
		{
			// pipeline
			pipelineCursor = pipeline.getCursor();
			IDataUtil.put( pipelineCursor, "strArrayResult", ka );
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static PipelineElementInfo getObjectForKeyFromIData(String key, IData record){
		PipelineElementInfo pei = new PipelineElementInfo();
		
		int idof = key.indexOf("/");
		if(0<idof)
		{
			String thisIdataKey = key.substring(0, idof);
			String nextKey = key.substring(idof+1);
		}
		
		return pei;
	}
	
	protected static class PipelineElementInfo {
		protected boolean bAbsent = true;
		protected boolean bNull = false;
		protected Object obj = null;
	}
	
	private static void appendKeys(ArrayList<String> arrKeys, IData in, String fatherKey, java.io.StringWriter out) throws ServiceException
	{
		IDataCursor idc = in.getCursor();
		String baseKey="";
		if(!fatherKey.contentEquals(baseKey))
			baseKey = fatherKey + "/";
		for (int i=0; idc.next(); i++)
		{
			String key = (String) idc.getKey();
			arrKeys.add(baseKey+key);
			Object val = idc.getValue();
			if (val instanceof com.wm.util.coder.IDataCodable)
			{
				val = ((com.wm.util.coder.IDataCodable)val).getIData();
				appendKeys(arrKeys, (IData)val, baseKey + key, out);
			}
			else if (val instanceof IData[])
			{
				IData[] ida = (IData[])val;
				for (int l=0; l<ida.length; l++)
				{
					appendKeys(arrKeys, ida[l], baseKey + key + "[" + l + "]", out);
				}
			}
			else if (val instanceof IData)
			{
				appendKeys(arrKeys, (IData)val, baseKey + key, out);
			}
			else if (val instanceof com.wm.util.coder.IDataCodable[])
			{
				com.wm.util.coder.IDataCodable[] ida = (com.wm.util.coder.IDataCodable[])val;
				for (int l=0; l<ida.length; l++)
				{
					appendKeys(arrKeys, ida[l].getIData(), baseKey + key + "[" + l + "]", out);
				}
			}
		}
		idc.destroy();
	}
			
	// --- <<IS-END-SHARED>> ---
}

