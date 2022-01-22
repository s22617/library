package com.s22617.library;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class LibraryRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_exampleLibrary() throws Exception {
        mockMvc.perform(get("/library/oxfordLibrary"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Bodleian Libraries\",\"location\":\"Broad St, Oxford OX1 3BG, Great Britain\",\"rating\":97,\"books\":[{\"id\":1,\"title\":\"Romeo and Juliet\",\"author\":\"William Shakespeare\",\"genre\":\"TRAGEDY\",\"cover\":\"HARDCOVER\",\"available\":false,\"isbn\":\"9782123456803\"},{\"id\":2,\"title\":\"The Insiders\",\"author\":\"Paul Knight\",\"genre\":\"HORROR\",\"cover\":\"DUST_JACKET\",\"available\":false,\"isbn\":\"9782123456745\"}]}"));
    }

    @Test
    void shouldReturnSuccess_emptyLibraryForName() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/library/emptyLibraryWithName")
                .param("name", "testLibrary");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":null,\"name\":\"testLibrary\",\"location\":\"Wales, Banglor\",\"rating\":87,\"books\":null}"));
    }

    @Test
    @Disabled
    void shouldReturnSuccess_getAllLibraries() throws Exception {
        mockMvc.perform(get("/library/oxfordLibrary"))
                        .andDo(print());
        mockMvc.perform(get("/library/getAllLibraries"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("[{\"id\":1,\"name\":\"Bodleian Libraries\",\"location\":\"Broad St, Oxford OX1 3BG, Great Britain\",\"rating\":97,\"books\":[{\"id\":1,\"title\":\"Romeo and Juliet\",\"author\":\"William Shakespeare\",\"genre\":\"TRAGEDY\",\"cover\":\"HARDCOVER\",\"available\":false,\"isbn\":\"9782123456803\"},{\"id\":2,\"title\":\"The Insiders\",\"author\":\"Paul Knight\",\"genre\":\"HORROR\",\"cover\":\"DUST_JACKET\",\"available\":false,\"isbn\":\"9782123456745\"}]}]"));
    }

    @Test
    void shouldReturnFail_deleteByIdPathVariable() throws Exception {
        mockMvc.perform(get("/library/deleteById"))
                .andExpect(status().is(404));
    }

    @Test
    void shouldReturnSuccess_deleteById() throws Exception {
        mockMvc.perform(get("/library/oxfordLibrary"));
        mockMvc.perform(get("/library/deleteById/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void shouldReturnSuccess_newLibrary() throws Exception {
        mockMvc.perform(get("/library/provideNewLibrary")
                .param("name", "Gdansk Library For Nerds"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"name\":\"Gdansk Library For Nerds\",\"location\":null,\"rating\":0,\"books\":null}"));
    }

    @Test
    void shouldReturnSuccess_existsByIdFalse() throws Exception {
        mockMvc.perform(get("/library/existsById/1"))
                .andExpect(status().isOk()).andExpect(content().string("false"));
    }

}
