package controller;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import guice.GlobalTest;

import org.junit.Ignore;
import org.junit.Test;

import play.mvc.Http.Status;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;

public class TestFileDownloadController {

    @Test
    @Ignore
    public void testDownloadFileWithIdNotExists() {
        FakeApplication fakeApplication = fakeApplication(new GlobalTest(true));
        running(
                fakeApplication, () -> {
                    String id = "1";
                    
                    FakeRequest fakeIndexRequest = fakeRequest("GET", "/file/download/by/id/" + id);
                    fakeIndexRequest.withSession("username", "admin");
                    
                    Result result = route(fakeIndexRequest);
                    
                    assertThat(status(result)).isEqualTo(Status.NOT_FOUND);
                }
        );
    }
    
    @Test
    public void testDownloadFileWithTheNullId() {
        FakeApplication fakeApplication = fakeApplication(new GlobalTest(true));
        running(
                fakeApplication, () -> {
                    FakeRequest fakeIndexRequest = fakeRequest("GET", "/file/download/by/id/");
                    fakeIndexRequest.withSession("username", "admin");
                    
                    assertThat(route(fakeIndexRequest)).isNull();
                }
        );
    }
    
    @Test
    public void testDownloadFileWithTheEmptyId() {
        FakeApplication fakeApplication = fakeApplication(new GlobalTest(true));
        running(
                fakeApplication, () -> {
                    FakeRequest fakeIndexRequest = fakeRequest("GET", "/file/download/by/id/" + "");
                    fakeIndexRequest.withSession("username", "admin");
                    
                    assertThat(route(fakeIndexRequest)).isNull();
                }
        );
    }
}