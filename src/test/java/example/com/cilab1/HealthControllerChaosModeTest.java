package example.com.cilab1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = HealthController.class,
        properties = {"CHAOS_MODE=true", "chaos.failure-rate=1.0"}
)
class HealthControllerChaosModeTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void returnsServerErrorInChaosMode() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value("error"));
    }
}