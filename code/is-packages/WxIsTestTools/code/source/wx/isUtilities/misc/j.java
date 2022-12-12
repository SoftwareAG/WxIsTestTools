package wx.isUtilities.misc;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.util.ArrayList;
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




	public static final void compareObjects (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compareObjects)>> ---
		// @sigtype java 3.5
		// [i] object:0:required objA
		// [i] object:0:required objB
		// [o] field:0:required bEqual
		// [o] field:0:optional diffDetails
		boolean bObjAPresent = false;
		boolean bObjBPresent = false;
		Object	objA = null;
		Object	objB = null;
		boolean bEqual=false;
		String diffDetails=null;
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		bObjAPresent = pipelineCursor.first("objA");
		if(bObjAPresent)
			objA = pipelineCursor.getValue();
		bObjBPresent = pipelineCursor.first("objB");
		if(bObjBPresent)
			objB = pipelineCursor.getValue();
		pipelineCursor.destroy();
		
		if(bObjAPresent){
			if(bObjBPresent){
				if(null!=objA){
					if(null!=objB){
						if(objA.getClass() == objB.getClass()){
							if(objA instanceof com.wm.util.Values){
								bEqual=true;
								diffDetails = "Consider equal at this level, go deeper";
							}
							else if(objA instanceof com.wm.util.Values[]){
								bEqual=true;
								diffDetails = "Consider equal at this level, go deeper";
							}
							else{
								bEqual=objA.equals(objB);
								if(bEqual)
									diffDetails = "Objects are equal";
								else
									diffDetails = "Objects (values) are different";
							}
						}
						else
							diffDetails = "Objects are not of the same class";
					}
					else
						diffDetails = "One object is null and one is instantiated";
				}
				else{
					if(null==objB){
						bEqual=true;
						diffDetails = "Both objects are null";
					}
					else
						diffDetails = "One object is null and one is instantiated";
				}
			}
			else
				diffDetails = "One object is present, the other is absent";
		}
		else if(!bObjBPresent){
			bEqual=true;
			diffDetails = "Both objects are absent";
		}
		else
			diffDetails = "One object is present, the other is absent";
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "bEqual", bEqual?"true":"false" );
		IDataUtil.put( pipelineCursor_1, "diffDetails", diffDetails );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void continuouslyLogUsedMemoryOnOutput (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(continuouslyLogUsedMemoryOnOutput)>> ---
		// @sigtype java 3.5
		for(long i=0; i< 1000000; i++){
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Mem used: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/1024/1024) + "M");
		}
		// --- <<IS-END>> ---

                
	}



	public static final void generateNullObject (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateNullObject)>> ---
		// @sigtype java 3.5
		// [o] object:0:required nullObject
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "nullObject", null );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void nullOrEmpty (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nullOrEmpty)>> ---
		// @sigtype java 3.5
		// [i] object:0:required objToConsider
		// [o] object:0:required objToConsider
		String outcome="__ABSENT__";
		boolean bChangePipe=true;
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			if(pipelineCursor.first("objToConsider")) {
				Object	objToConsider = pipelineCursor.getValue();
				if(null == objToConsider)
					outcome = "__Null__";
				else
					bChangePipe = false;
			}
		pipelineCursor.destroy();
		
		if(bChangePipe){
			// pipeline
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			IDataUtil.put( pipelineCursor_1, "objToConsider", outcome );
			pipelineCursor_1.destroy();
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

