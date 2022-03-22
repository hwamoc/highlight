package com.liner.example.theme.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liner.example.common.BaseTimeEntity;
import com.liner.example.theme.api.dto.ThemeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Theme extends BaseTimeEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Long createdBy;

    private String first;

    private String second;

    private String third;

    private String fourth;

    private String fifth;

    private String sixth;

    public Theme setUpdate(ThemeRequestDto themeRequestDto) {
        this.first = themeRequestDto.getFirst();
        this.second = themeRequestDto.getSecond();
        this.third = themeRequestDto.getThird();
        this.fourth = themeRequestDto.getFourth();
        this.fifth = themeRequestDto.getFifth();
        this.sixth = themeRequestDto.getSixth();
        return this;
    }

    public Map<String, Object> getThemeMap(Theme theme) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Map<String, Object> map = objectMapper.convertValue(theme, Map.class);
        return map;
    }

    public String getColorOrdinal(Theme theme, String hex) {
        String ordinal = "";
        Map<String, Object> map = this.getThemeMap(theme);
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null && value.getClass() == String.class && String.valueOf(value).equals(hex)) {
//                System.out.println("Iterating, key: " + key);
                ordinal = key;
            }
        }
        return ordinal;
    }


    public String getColorHex(Theme theme, String ordinal) {
        String colorHex = "";
        Map<String, Object> map = this.getThemeMap(theme);
        if (map.containsKey(ordinal)) {
            colorHex = String.valueOf(map.get(ordinal));
        }
        return colorHex;
    }


    public List<String> getColorHexList(Theme theme) {
        List<String> hexList = new ArrayList<String>();
        Map<String, Object> map = this.getThemeMap(theme);
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null && value.getClass() == String.class && !(String.valueOf(value).equals("Default") || String.valueOf(value).equals("Custom"))) {
//                System.out.println("Iterating, value: " + value);
                hexList.add((String) value);
            }
        }
        return hexList;
    }

    public boolean checkColor(Theme theme, String colorHex) {
        boolean contain = false;
        List<String> colorHexList = theme.getColorHexList(theme);
        System.out.println("@Color List: " + colorHexList);
        if (colorHexList.contains(colorHex)) {
            contain = true;
        }
        return contain;
    }
}
