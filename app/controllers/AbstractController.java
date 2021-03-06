package controllers;

import play.mvc.Controller;
import services.configuration.ConfigurationService;
import utils.json.JsonHelper;

import com.google.inject.Singleton;

@Singleton
public class AbstractController extends Controller {

    protected static final String _TITLE = "Reactive AngularApp's - Elasticsearch 1.4.1";

    protected final ConfigurationService configurationService;
    protected final String hostName;

    protected AbstractController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
        this.hostName = configurationService.getHostName();
    }
    
    protected Status _internalServerError(String key) {
        return internalServerError(
                JsonHelper.toResultExceptionModel(key));
    }
    
    protected Status _preconditionFailed(String key) {
        return status(PRECONDITION_FAILED,
                JsonHelper.toResultExceptionModel(key));
    }
    
    protected void clearSession() {
        session().clear();
    }

    protected void flashSuccess(String message) {
        flash("success", message);
    }
}
