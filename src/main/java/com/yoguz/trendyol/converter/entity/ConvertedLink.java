package com.yoguz.trendyol.converter.entity;

import com.yoguz.trendyol.converter.enums.ConverterEnums.Direction;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class ConvertedLink {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDateTime date = LocalDateTime.now();
    private Direction direction;
    private Page page;
    private String web_url;
    private String deep_link;

}
