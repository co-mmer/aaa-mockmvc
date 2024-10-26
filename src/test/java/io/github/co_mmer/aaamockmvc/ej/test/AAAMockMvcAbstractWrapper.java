package io.github.co_mmer.aaamockmvc.ej.test;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestDelete;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestGet;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestOption;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPost;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPut;

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
