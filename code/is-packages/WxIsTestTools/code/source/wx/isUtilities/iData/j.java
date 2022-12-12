package wx.isUtilities.iData;

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




	public static final void executeAssertion (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(executeAssertion)>> ---
		// @sigtype java 3.5
		// [i] recref:0:required assertionInfo wx.isUtilities.iData.doc:assertionInfo
		// [i] record:0:required documentToBeAsserted
		// [o] field:0:required bAssertPassed
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		boolean bAssertPassed = false;
		
		String	fieldPath = null;
		String	operator = null;
		String	value = null;
			// assertionInfo
		
		IData	assertionInfo = IDataUtil.getIData( pipelineCursor, "assertionInfo" );
		if ( assertionInfo != null)
		{
			IDataCursor assertionInfoCursor = assertionInfo.getCursor();
				fieldPath = IDataUtil.getString( assertionInfoCursor, "fieldPath" );
				operator = IDataUtil.getString( assertionInfoCursor, "operator" );
				value = IDataUtil.getString( assertionInfoCursor, "value" );
			assertionInfoCursor.destroy();
		}
		
		// documentToBeAsserted
		IData	documentToBeAsserted = IDataUtil.getIData( pipelineCursor, "documentToBeAsserted" );
		if ( documentToBeAsserted != null)
		{
			java.io.StringWriter s = new java.io.StringWriter();
			PipelineElementInfo pei = getObjectForKeyFromIData(fieldPath, documentToBeAsserted, s);
			if(null == pei)
				bAssertPassed = false;
			else if(operator.equals("=")){
				bAssertPassed = pei.obj.equals(value);
			}
		}
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "bAssertPassed", ""+bAssertPassed );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getAllDocumentKeysAsArray (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getAllDocumentKeysAsArray)>> ---
		// @sigtype java 3.5
		// [i] record:0:required record
		// [o] field:1:required keys
		IDataCursor id = pipeline.getCursor();
		
		// get the optional string parameter. only will use the first occurrence
		// of the message element
		IData	  rec = IDataUtil.getIData( id, "record" );
		id.destroy();
		
		
		
		if ( null != rec )
		{
			ArrayList<String> arrKeys = new ArrayList<String>();
			
			// see the Shared tab for dumpIData that walks the IData tree
			java.io.StringWriter s = new java.io.StringWriter();
			
			appendKeys(arrKeys, rec, "", s);
			
			// pipeline
			IDataCursor pipelineCursor = pipeline.getCursor();
			IDataUtil.put( pipelineCursor, "htmlOut", s.toString() );
			String[]	keys = new String[arrKeys.size()];
			int i = 0;
			Iterator<String> foreach = arrKeys.iterator();
			while (foreach.hasNext()) keys[i++] = ""+foreach.next();
			IDataUtil.put( pipelineCursor, "keys", keys );
			pipelineCursor.destroy();
		}			
		// --- <<IS-END>> ---

                
	}



	public static final void getObjectByKeyFromDocument (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getObjectByKeyFromDocument)>> ---
		// @sigtype java 3.5
		// [i] record:0:required record
		// [i] field:0:required key
		// [o] recref:0:required objectInfo wx.isUtilities.iData.doc:pipelineObjectInfo
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	key = IDataUtil.getString( pipelineCursor, "key" );
		IData	record = IDataUtil.getIData( pipelineCursor, "record" );
		pipelineCursor.destroy();
		
		
		java.io.StringWriter trace = new java.io.StringWriter();
		
		// record
		if ( record != null)
		{
			if(null!=key && !key.equals("")){
				
				if (key.charAt(0) == '/') key = key.substring(1);
				
				trace.append("Looking for key: " + key);
				java.io.StringWriter s = new java.io.StringWriter();
				
				PipelineElementInfo pei = getObjectForKeyFromIData(key, record, s);
		
				
		
				// pipeline
				pipelineCursor = pipeline.getCursor();
		
				// objectInfo
				IData	objectInfo = IDataFactory.create();
				IDataCursor objectInfoCursor = objectInfo.getCursor();
				IDataUtil.put( objectInfoCursor, "bAbsent"     , pei.bAbsent?"true":"false" );
				IDataUtil.put( objectInfoCursor, "bNull"       , pei.bNull?"true":"false" );
				if(!pei.bNull && !pei.bAbsent)
					IDataUtil.put( objectInfoCursor, "instanceOf"  , pei.obj.getClass().toString() );
				IDataUtil.put( objectInfoCursor, "actualObject", pei.obj );
				objectInfoCursor.destroy();
				IDataUtil.put( pipelineCursor, "objectInfo", objectInfo );
				//IDataUtil.put( objectInfoCursor, "trace", s.toString() );
				pipelineCursor.destroy();
		
			}
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static PipelineElementInfo getObjectForKeyFromIData(String key, IData record, java.io.StringWriter s) throws ServiceException{
		PipelineElementInfo pei = new PipelineElementInfo();
		
		if(key.startsWith("/")) key = key.substring(1); // remove leading slash if any
		int idof = key.indexOf("/");
		s.append("\nidof: " + idof);
		if(0<idof) // We need to dive deeper
		{
			String thisIdataKey = key.substring(0, idof);
			String nextKey = key.substring(idof+1);
			s.append("\nneed to dive into: " + thisIdataKey + " with subkey: " + nextKey);
			IDataCursor idc = record.getCursor();
			if(idc.first(thisIdataKey)){
				Object nextRecord = idc.getValue();
				if(null != nextRecord){
					s.append("\n Next record class: " + nextRecord.getClass().toString());
					if (nextRecord instanceof com.wm.util.coder.IDataCodable)
					{
						nextRecord = ((com.wm.util.coder.IDataCodable)nextRecord).getIData();
					}
					if (nextRecord instanceof IData){
						s.append("\n Recursing for key " + nextKey);
						pei = getObjectForKeyFromIData(nextKey, (IData) nextRecord, s);
						
					}
					else
						s.append("\nnextRecord is not an IData.");
					
				}
				else
					s.append("\nnextRecord is null.");
			}
			else
			{
				s.append("\nnextRecord is absent. Key was " + thisIdataKey);
				int arrayIdx1 = key.indexOf("[");
				if(0<arrayIdx1){
					int arrayIdx2 = key.indexOf("]");
					if(0<arrayIdx2){
						int arrayIndex = Integer.parseInt(key.substring(arrayIdx1+1, arrayIdx2));
						String crtRecordKey = thisIdataKey.substring(0, arrayIdx1<thisIdataKey.length()?arrayIdx1:thisIdataKey.length());
						if(idc.first(crtRecordKey)){
							Object nextRecord = idc.getValue();
							if(null != nextRecord){
								s.append("\n Next record class: " + nextRecord.getClass().toString());
								if (nextRecord instanceof com.wm.util.coder.IDataCodable[])
								{
									nextRecord = ((com.wm.util.coder.IDataCodable)nextRecord).getIData();
								}
								if (nextRecord instanceof IData[]){
									s.append("\n Recursing for key " + nextKey);
									if(((IData[])nextRecord).length>arrayIndex)
										pei = getObjectForKeyFromIData(nextKey, ((IData[])nextRecord)[arrayIndex], s);
									else
										s.append("\nnextRecord is not present in the array: array is too short.");
								}
								else
									s.append("\nnextRecord is not an IData.");
							}
							else
								s.append("\nnextRecord is null.");
						}
						else
							s.append("\nNext record not found... Key was " + crtRecordKey);
					}
					else
						throw new ServiceException("Key " + thisIdataKey + " does not have a closing ]");
				}
			}
			idc.destroy();
		}
		else // We need to extract the key. To do: add support for string matrix
		{
			int arrayIdx = key.indexOf("[");
			s.append("\nidof: " + idof);
			if(0<arrayIdx){
				s.append("\narray identified");
				int arrayIdx2 = key.indexOf("]");
				if(0<arrayIdx2){
					String crtKey = key.substring(0,arrayIdx);
					s.append("\ncurrentkey is: " + crtKey);
					int arrayIndex = Integer.parseInt(key.substring(arrayIdx+1, arrayIdx2));
					s.append("\narray index is " + arrayIndex);
					IDataCursor idc = record.getCursor();
					if(idc.first(crtKey)){
						Object crtObject = idc.getValue();
						if(crtObject instanceof Object[]){
							if(((Object[])crtObject).length>arrayIndex){
								pei.bAbsent=false;
								pei.obj = ((Object[])crtObject)[arrayIndex];
								pei.bNull = (null==pei.obj); 
							}
						}
						else
							s.append("\nnot an array");
					}
					else
						s.append("\nkey not found");
					idc.destroy();
				}
				else
					throw new ServiceException("Wrong expression: first [ does not have a matching ]. Key is " + key);
			}
			else{
				s.append("\nReturning simple key info");
				IDataCursor idc = record.getCursor();
				if(idc.first(key)){
					pei.bAbsent=false;
					pei.obj = idc.getValue();
					pei.bNull = (null==pei.obj); 
				}
				else
					pei.bAbsent = true;
				idc.destroy();
			}
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
			Object val = idc.getValue();
			arrKeys.add(baseKey+key);
			/*if (val instanceof String[][]){
				String[][] sMatrix = (String[][]) val;
				
				for(int j=0; j<sMatrix.length; j++){
					arrKeys.add(baseKey+key+"["+j+"]");
					for(int k=0; k<sMatrix[j].length;k++)
						arrKeys.add(baseKey+key+"["+j+"]["+k+"]");
				}
			}
			else */ 
			if (val instanceof String){
				//nothing to do, already taken care of
			}
			else if (val instanceof com.wm.util.coder.IDataCodable)
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
			else if (val instanceof Object[][]){
				Object[][] sMatrix = (Object[][]) val;
				
				for(int j=0; j<sMatrix.length; j++){
					arrKeys.add(baseKey+key+"["+j+"]");
					for(int k=0; k<sMatrix[j].length;k++)
						arrKeys.add(baseKey+key+"["+j+"]["+k+"]");
				}
			}
			else if (val instanceof Object[]){
				int aSize=((Object[]) val).length;
				for(int j=0; j<aSize; j++)
					arrKeys.add(baseKey+key+"["+j+"]");
			}
		}
		idc.destroy();
	}
			
	// --- <<IS-END-SHARED>> ---
}

