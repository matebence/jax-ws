package handlers;

import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPHeader;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;
import javax.xml.soap.Node;
import java.util.Iterator;
import java.util.Set;

public class SiteHandler implements SOAPHandler<SOAPMessageContext> {

    private Logger logger = Logger.getLogger(SiteHandler.class);

    /* <soapenv:Header>
        <SiteName mustunderstand="1">Amazon</SiteName>
    </soapenv:Header> */

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        logger.info("handleMessage");

        Boolean isResponse = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isResponse) {
            logger.info("Request on the way");
            SOAPMessage soapMsg = context.getMessage();
            try {
                SOAPEnvelope envelope = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                Iterator childElements = header.getChildElements();

                while (childElements.hasNext()) {
                    Node eachNode = (Node) childElements.next();
                    String name = eachNode.getLocalName();
                    if (name != null && name.equals("SiteName")) {
                        logger.info("Site Name Is " + eachNode.getValue());
                    }
                }

            } catch (SOAPException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Response on the way");
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        logger.info("handleFault");
        return false;
    }

    @Override
    public void close(MessageContext context) {
        logger.info("close");
    }

    @Override
    //The getHeaders method must be implement only then when we send the mustUnderstand attribute in header
    public Set<QName> getHeaders() {
        logger.info("getHeaders");
        return null;
    }
}