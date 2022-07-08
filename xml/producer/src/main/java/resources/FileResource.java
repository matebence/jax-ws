package resources;

import org.apache.tika.mime.MimeTypeException;
import org.apache.commons.io.FileUtils;
import javax.activation.FileDataSource;
import org.apache.tika.mime.MimeTypes;
import javax.activation.DataHandler;
import java.net.URLConnection;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.io.File;
import java.net.URL;

import javax.xml.ws.BindingType;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebParam;

@BindingType(value = "http://schemas.xmlsoap.org/wsdl/soap/http?mtom=true")
@WebService(portName = "FileResourcePort", serviceName = "FileResourcePort", wsdlLocation = "WEB-INF/wsdl/File.wsdl")
public class FileResource {

    // the throws NullPointerException, IOException, MimeTypeException will be described as Fault
    @WebMethod
    public @WebResult(name = "upload")
    void upload(@WebParam(name = "attachment") DataHandler attachment) throws NullPointerException, IOException, MimeTypeException {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource("images")).getPath();
        String mimeType = URLConnection.guessContentTypeFromStream(attachment.getInputStream());
        String extension = MimeTypes.getDefaultMimeTypes().forName(mimeType).getExtension();
        File targetFile = new File(path + UUID.randomUUID() + extension);

        FileUtils.copyInputStreamToFile(attachment.getInputStream(), targetFile);
    }

    @WebMethod
    public @WebResult(name = "download")
    DataHandler download() throws NullPointerException {
        URL url = getClass().getClassLoader().getResource("images/java.jpg");
        File file = new File(Objects.requireNonNull(url).getPath());
        FileDataSource fileDataSource = new FileDataSource(file);

        return new DataHandler(fileDataSource);
    }
}
