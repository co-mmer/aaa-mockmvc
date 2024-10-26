package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataMockMvc {

  public static final MockMvc MOCK_MVC = Mockito.mock(MockMvc.class);
}
