package rebound.richdatashets.impls.localfile.formats.gds.cbor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import rebound.dataformats.cbor.CBORUtilities;
import rebound.exceptions.BinarySyntaxException;
import rebound.exceptions.GenericDataStructuresFormatException;
import rebound.richdatashets.api.model.RichdatashetsTable;
import rebound.richdatashets.api.operation.RichdatashetsUnencodableFormatException;
import rebound.richdatashets.impls.helpers.gds.RichdatashetsGDSTranscoder;
import rebound.richdatashets.impls.localfile.RichdatashetsLocalFileFormatTranscoder;

public enum RichdatashetsLocalFileFormatTranscoderForCBOR
implements RichdatashetsLocalFileFormatTranscoder
{
	I;
	
	
	@Override
	public RichdatashetsTable read(InputStream in) throws IOException, BinarySyntaxException, RichdatashetsUnencodableFormatException
	{
		Object gds = CBORUtilities.parseCBOR(in);
		
		try
		{
			return RichdatashetsGDSTranscoder.decode(gds);
		}
		catch (GenericDataStructuresFormatException exc)
		{
			throw BinarySyntaxException.inst(exc);
		}
	}
	
	
	@Override
	public void write(RichdatashetsTable data, OutputStream out) throws IOException
	{
		Object gds = RichdatashetsGDSTranscoder.encode(data);
		CBORUtilities.serializeCBORToStream(gds, out);
	}
}
