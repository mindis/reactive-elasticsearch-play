import services.AuthenticationImpl;
import services.AuthenticationService;
import services.ConfigurationImpl;
import services.ConfigurationService;
import services.ESConstantImpl;
import services.ESConstantService;
import services.ESSearchImpl;
import services.ESSearchService;
import services.ZipImpl;
import services.ZipService;
import services.i18n.I18nImpl;
import services.i18n.I18nService;
import utils.EncodeUtils;
import utils.FileUtils;
import utils.IEncodeUtils;
import utils.IFileUtils;
import utils.eslasticsearch.ESServerEmbedded;
import utils.eslasticsearch.IESServerEmbedded;

import com.google.inject.AbstractModule;

import configuration.GlobalConfiguration;

public class Global extends GlobalConfiguration {

    @Override
    protected AbstractModule buildAbstractModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bind(IESServerEmbedded.class).to(ESServerEmbedded.class);
                bind(IEncodeUtils.class).to(EncodeUtils.class);
                bind(IFileUtils.class).to(FileUtils.class);
                
                bind(ESSearchService.class).to(ESSearchImpl.class);
                bind(ESConstantService.class).to(ESConstantImpl.class);
                bind(ConfigurationService.class).to(ConfigurationImpl.class);
                bind(AuthenticationService.class).to(AuthenticationImpl.class);
                bind(ZipService.class).to(ZipImpl.class);
                bind(I18nService.class).to(I18nImpl.class);
            }
        };
    }
}
