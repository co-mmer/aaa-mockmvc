package io.github.co_mmer.aaamockmvc.ej.testdata.testutil;

import static org.mockito.Mockito.mock;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataMockMvc {

  public static final MockMvc MOCK_MVC = mock(MockMvc.class);
}
