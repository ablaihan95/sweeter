package com.ablaihan.springlearning;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ablaihan.springlearning.controller.MainController;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Test
    void contextLoads() throws Exception {
        int[] nums = new int[5];
        List.of(nums);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
        assertThat(mainController).isNotNull();
    }

    @Test
    public void main1(){
        System.out.println(Arrays.toString(getConcatenation(new int[] {1, 2, 412, 213})));
    }

    public static int[] getConcatenation(int[] nums) {
        List<Integer> a = new ArrayList<>();
        Arrays.stream(nums).forEach(a::add);
        int[] newarr = Arrays.copyOf(nums, nums.length * 2);
        for (int i = 0; i < nums.length; i++) {
            newarr[nums.length + i] = nums[i];
        }
        return newarr;
    }
}

