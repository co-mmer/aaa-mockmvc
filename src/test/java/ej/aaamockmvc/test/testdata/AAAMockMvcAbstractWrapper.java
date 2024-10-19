package ej.aaamockmvc.test.testdata;

import ej.aaamockmvc.test.AAAMockMvc;
import ej.aaamockmvc.test.AAAMockMvcAbstract;
import ej.aaamockmvc.test.web.request.TestRequestDelete;
import ej.aaamockmvc.test.web.request.TestRequestGet;
import ej.aaamockmvc.test.web.request.TestRequestHead;
import ej.aaamockmvc.test.web.request.TestRequestOption;
import ej.aaamockmvc.test.web.request.TestRequestPatch;
import ej.aaamockmvc.test.web.request.TestRequestPost;
import ej.aaamockmvc.test.web.request.TestRequestPut;

public class AAAMockMvcAbstractWrapper extends AAAMockMvcAbstract {

  public AAAMockMvcAbstractWrapper(AAAMockMvc aaaMockMvc) {
    super(aaaMockMvc);
  }

  public TestRequestGet wrapGet() {
    return super.get();
  }

  public TestRequestPost wrapPost() {
    return super.post();
  }

  public TestRequestPut wrapPut() {
    return super.put();
  }

  public TestRequestPatch wrapPatch() {
    return super.patch();
  }

  public TestRequestDelete wrapDelete() {
    return super.delete();
  }

  public TestRequestHead wrapHead() {
    return super.head();
  }

  public TestRequestOption wrapOptions() {
    return super.options();
  }
}
