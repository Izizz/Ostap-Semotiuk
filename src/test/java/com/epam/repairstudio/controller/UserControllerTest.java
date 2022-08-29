package com.epam.repairstudio.controller;

import com.epam.repairstudio.config.TestConfig;
import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.exception.UserException;
import com.epam.repairstudio.service.UserService;
import com.epam.repairstudio.util.UserTestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.epam.repairstudio.util.UserTestDataUtil.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDto();

        when(userService.getByEmail(EMAIL)).thenReturn(userDto);

        mockMvc.perform(get("/repair-studio/user/" + EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    void getByRole() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDto();

        when(userService.getByRole(ROLE)).thenReturn(userDto);

        mockMvc.perform(get("/repair-studio/user/role/" + ROLE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.role").value(ROLE));
    }

    @Test
    void getAllTest() throws Exception {

        UserDto userDto1 = UserTestDataUtil.createUserDto();

        UserDto userDto2 = UserTestDataUtil.createUserDto();
        userDto2.setId(2L);

        UserDto userDto3 = UserTestDataUtil.createUserDto();
        userDto3.setId(3L);

        List<UserDto> users = Arrays.asList(userDto1, userDto2, userDto3);

        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.DEFAULT_DIRECTION, "id"));

        when(userService.getAll(eq(pageable))).thenReturn(new PageImpl<>(users.subList(0, 2)));

        mockMvc
                .perform(
                        get("/repair-studio/user/?pageNumber=0&pageSize=1&sortType=id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id").value(ID));
        verify(userService, times(1)).getAll(pageable);
    }


    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteById(UserTestDataUtil.ID);

        mockMvc
                .perform(delete("/repair-studio/user/" + UserTestDataUtil.ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteById(UserTestDataUtil.ID);
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDtoUpdate();

        when(userService.updateById(eq(ID), any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(put("/repair-studio/user/" + ID)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID));

    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDto();
        when(userService.create(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/repair-studio/user/")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(EMAIL));

    }

    @Test
    void updateUserNotValidTest() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDtoUpdate();
        userDto.setPhone("2");
        userDto.setPassword("21");

        when(userService.updateById(eq(ID), any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(put("/repair-studio/user/" + ID)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }


    @Test
    void updateUserNotFoundTest() throws Exception {
        UserDto userDto = UserTestDataUtil.createUserDtoUpdate();

        when(userService.updateById(eq(ID), any(UserDto.class))).thenThrow(new UserException("user not found"));

        mockMvc.perform(put("/repair-studio/user/" + ID)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }


}
