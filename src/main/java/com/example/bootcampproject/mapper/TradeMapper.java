package com.example.bootcampproject.mapper;

import com.example.bootcampproject.dto.HoldingsDto;
import com.example.bootcampproject.entity.Trade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeMapper {
    TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);
    @Mappings(
            {

                    @Mapping(target = "quantity", source = "quantity"),
                    @Mapping(target = "stockId", source = "stock.stockId"),
                    @Mapping(target = "stockName", source = "stock.stockName"),
                    @Mapping(target = "buyPrice", source = "price")

            }
    )
    HoldingsDto tradeToHoldingsDto(Trade trade);
}
