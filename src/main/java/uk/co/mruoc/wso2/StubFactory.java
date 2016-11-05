package uk.co.mruoc.wso2;

import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
import org.wso2.carbon.application.mgt.stub.ApplicationAdminStub;
import org.wso2.developerstudio.eclipse.carbonserver.base.capp.uploader.CarbonAppUploaderStub;

import java.rmi.RemoteException;

import static org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING;

public class StubFactory {

    private final String serverUrl;
    private final String sessionCookie;

    public StubFactory(String serverUrl, String sessionCookie) {
        this.serverUrl = serverUrl;
        this.sessionCookie = sessionCookie;
    }

    public CarbonAppUploaderStub buildCarbonAppUploaderStub() throws RemoteException {
        CarbonAppUploaderStub carbonAppUploaderStub = new CarbonAppUploaderStub(serverUrl + "services/CarbonAppUploader");
        configureStubWithCookie(carbonAppUploaderStub);
        return carbonAppUploaderStub;
    }

    public ApplicationAdminStub buildApplicationAdminStub() throws RemoteException {
        ApplicationAdminStub applicationAdminStub = new ApplicationAdminStub(serverUrl + "services/ApplicationAdmin");
        configureStubWithCookie(applicationAdminStub);
        return applicationAdminStub;
    }

    private void configureStubWithCookie(Stub stub) {
        ServiceClient serviceClient = stub._getServiceClient();
        Options options = serviceClient.getOptions();
        options.setManageSession(true);
        options.setProperty(COOKIE_STRING, sessionCookie);
    }

}
