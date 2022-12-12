package wx.isUtilities.pipeline;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.util.ArrayList;
// --- <<IS-END-IMPORTS>> ---

public final class j

{
	// ---( internal utility methods )---

	final static j _instance = new j();

	static j _newInstance() { return new j(); }

	static j _cast(Object o) { return (j)o; }

	// ---( server methods )---




	public static final void getPipeline (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getPipeline)>> ---
		// @sigtype java 3.5
		// [o] record:0:required pipeline
		IDataUtil.put( pipeline.getCursor(), "pipeline", IDataUtil.clone(pipeline));
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static void writeXHTML(String myKey, IData in, int indent, java.io.StringWriter out, String idHTML, boolean bRoot) throws ServiceException
	{
		IDataCursor idc = in.getCursor();
		if(!bRoot)
		{
			out.write("<div class='trigger' style=\"width: 100%\"");
			out.write(" id='t"+idHTML+"' onclick=\"showBranch('b"+idHTML+"')\">");
			out.write("<div class='ei' id=\"Ib"+idHTML+"\">+</div>");
			out.write(myKey);
			out.write("</div>"); 
			out.write("<div class='branch' id='b"+idHTML+"'>");
		}
		for (int i=0; idc.next(); i++)
		{
			String key = (String) idc.getKey();
			Object val = idc.getValue();
			if (val instanceof com.wm.util.coder.IDataCodable)
			{
				val = ((com.wm.util.coder.IDataCodable)val).getIData();
			}
			if (val instanceof String[][])
			{
				out.write("<div class='trigger' style=\"width: 100%\"");
				out.write(" id='t2"+i+idHTML+"' onclick=\"showBranch('b2"+i+idHTML+"')\">");
	    		out.write("<div class='ei' id=\"Ib2"+i+idHTML+"\" >+</div>");
	    	out.write("<div style='color: gray;display: inline'>(java.lang.String[][])</div>");
				out.write(key);
				out.write("</div>"); 
				out.write("<div class='branch' id='b2"+i+idHTML+"'>");
				String[][] st = (String[][])val;
				for (int k=0; k<st.length; k++)
				{
					for (int j=0; j<st[0].length; j++)
					{
						if(key.equalsIgnoreCase("password"))
							st[k][j]="*";
						out.write("<div><div style='color: red;display:inline'>["+k+"]["+j+"]</div> = <div style='color: blue;display:inline'>" + HTMLEncodeEventuallyXML(st[k][j])+"</div></div>");
					}
				}
				out.write("</div>");
	
			}
			else if (val instanceof String[])
			{
				String[] sa = (String[])val;
				out.write("<div class='trigger' style=\"width: 100%\"");
				out.write(" id='t1"+i+idHTML+"' onclick=\"showBranch('b1"+i+idHTML+"')\">");
	    		out.write("<div class='ei' id=\"Ib1"+i+idHTML+"\">+</div>");
	    	out.write("<div style='color: gray;display: inline'>(java.lang.String[])</div>");
				out.write(key);
				out.write("</div>"); 
				out.write("<div class='branch' id='b1"+i+idHTML+"'>");
				for (int k=0; k<sa.length; k++)
				{
					if(key.equalsIgnoreCase("password"))
						sa[k]="*";				
					out.write("<div><div style='color: red;display:inline'>["+k+"]</div> = <div style='color: blue;display:inline'>" + HTMLEncodeEventuallyXML(sa[k])+"</div></div>");
				}
				out.write("</div>");
			}
			else if (val instanceof IData[])
			{
				IData[] ida = (IData[])val;
				for (int l=0; l<ida.length; l++)
				{
					writeXHTML(key, ida[l], indent+1, out, idHTML + "_" + i + "_A_" + l, false);
				}
			}
			else if (val instanceof IData)
			{
				writeXHTML(key, (IData)val, indent+1, out, idHTML + "_0_" + i, false);
			}
			else if (val instanceof com.wm.util.coder.IDataCodable[])
			{
				com.wm.util.coder.IDataCodable[] ida = (com.wm.util.coder.IDataCodable[])val;
				for (int l=0; l<ida.length; l++)
				{
					writeXHTML(key, ida[l].getIData(), indent+1, out, idHTML + "_B_" + i + "_" + l, false);
				}
			}
			else
			{
				if(key.equalsIgnoreCase("password"))
					val="*";
				String className = "null";
			  if(null != val)
			    className = val.getClass().getName();
				out.write("<div style='color: gray'>("+className+")<div style='color: red;display:inline'>"+key + "</div> = <div style='color: blue;display:inline'>" + HTMLEncodeEventuallyXML(""+val)+"</div></div>");
			}
		}
		idc.destroy();
		if(!bRoot)
			out.write("</div>");
	}
	
	private static String HTMLEncodeEventuallyXML(String xmlString)
	{
		if(null==xmlString)
			return "";	
		return com.wm.util.xform.StringDT.HTMLEncode(xmlString);
	}
	
		
	// --- <<IS-END-SHARED>> ---
}

