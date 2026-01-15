package es.artyhub.banco_back.controller;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@Disabled
@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private CuentaService cuentaService;

    @Test
    @DisplayName("")
}
