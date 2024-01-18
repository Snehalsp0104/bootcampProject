package com.example.bootcampproject.service;

import com.example.bootcampproject.dto.HoldingsDto;
import com.example.bootcampproject.dto.PortfolioDto;
import com.example.bootcampproject.enums.OutCome;
import com.example.bootcampproject.enums.TradeType;
import com.example.bootcampproject.dto.TradeDto;
import com.example.bootcampproject.entity.Stock;
import com.example.bootcampproject.entity.Trade;
import com.example.bootcampproject.entity.User;
import com.example.bootcampproject.exception.*;
import com.example.bootcampproject.mapper.TradeMapper;
import com.example.bootcampproject.repository.StockRepository;
import com.example.bootcampproject.repository.TradeRepository;
import com.example.bootcampproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    public void addTrade(TradeDto tradeDto) {
        int stockId = tradeDto.getStockId();
        int userId = tradeDto.getUserId();
        int quantity = tradeDto.getQuantity();
        TradeType tradeType = tradeDto.getTradeType();
        // float price = tradeDto.getPrice();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }

        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        if (optionalStock.isEmpty()) {
            throw new StockNotFoundException("Stock not Found");
        }

        Stock stock = optionalStock.get();
        float buyPrice = stock.getPrevClose();
        float sellPrice = stock.getClosingPrice();

        //   if (quantity<=0) throw new InvalidQuantityException("Quantity must be valid");

        Trade trade = tradeRepository.getBuyTrade(userId, stockId);
        if (tradeType == TradeType.Buy) {

            if (trade == null) {
                Trade tradeToBeAdded = new Trade();
                tradeToBeAdded.setQuantity(quantity);
                tradeToBeAdded.setTradeType(tradeType);
                tradeToBeAdded.setUser(optionalUser.get());
                tradeToBeAdded.setStock(stock);
                tradeToBeAdded.setPrice(buyPrice);
                tradeRepository.save(tradeToBeAdded);


            } else {
                trade.setPrice((trade.getPrice() * trade.getQuantity() + buyPrice * quantity) / (trade.getQuantity() + quantity));
                trade.setQuantity(trade.getQuantity() + quantity);
                tradeRepository.save(trade);

            }
        } else if (tradeType == TradeType.Sell) {
            if (trade == null) {
                throw new InvalidSellException("Cannot Sell without Buying");
            }
            Trade sellTrade = tradeRepository.getSellTrade(userId, stockId);

            if (sellTrade == null) {
                if (trade.getQuantity() < quantity)
                    throw new InvalidSellException("Cannot sell more than the stocks bought");
                Trade tradeToBeAdded = new Trade();
                tradeToBeAdded.setQuantity(quantity);
                tradeToBeAdded.setTradeType(tradeType);
                tradeToBeAdded.setUser(optionalUser.get());
                tradeToBeAdded.setStock(stock);
                tradeToBeAdded.setPrice(sellPrice);
                tradeRepository.save(tradeToBeAdded);


            } else {
                if (trade.getQuantity() < quantity + sellTrade.getQuantity())
                    throw new InvalidSellException("Cannot sell more than the stocks bought");
                trade.setPrice((trade.getPrice() * trade.getQuantity() + sellPrice * quantity) / (trade.getQuantity() + quantity));
                trade.setQuantity(trade.getQuantity() + quantity);
                tradeRepository.save(trade);

            }

        } else throw new InvalidTradeTypeException("Invalid Trade Type");


    }

    public PortfolioDto portfolioDetails(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) throw new UserNotFoundException("User not found");
        PortfolioDto portfolioDto = new PortfolioDto();
        List<Trade> trades = tradeRepository.getTradeByUserId(userId);
        float totalPortFolioHolding = 0;
        float totalbuyPrice = 0;
        float totalProfitLoss = 0;


        List<HoldingsDto> holdings = new ArrayList<>();
        for (Trade trade : trades) {
            if (trade.getTradeType() == TradeType.Buy) {
                //  System.out.println(trade);
                totalbuyPrice+=trade.getPrice()*trade.getQuantity();
                totalPortFolioHolding+= trade.getPrice()* trade.getQuantity();
                HoldingsDto singleHolding = TradeMapper.INSTANCE.tradeToHoldingsDto(trade);
                // int stockId = trade.getStock().getStockId();
                float currentPrice = trade.getStock().getLastPrice();
                singleHolding.setCurrentPrice(currentPrice);
                //System.out.println(currentPrice);
                float calculatedEarning = currentPrice - trade.getPrice();
                totalProfitLoss+=calculatedEarning;
                if (calculatedEarning > 0) {
                    singleHolding.setOutCome(OutCome.Profit);
                } else {
                    singleHolding.setOutCome(OutCome.Loss);
                }
                singleHolding.setEarning(calculatedEarning);
               // System.out.println(singleHolding);
                holdings.add(singleHolding);

               // System.out.println(holdings);


            }
            else {
                totalPortFolioHolding-= trade.getPrice()* trade.getQuantity();
            }


            portfolioDto.setHoldings(holdings);

        }
        portfolioDto.setTotalPortfolioHolding(totalPortFolioHolding);
        portfolioDto.setTotalBuyPrice(totalbuyPrice);
        portfolioDto.setTotalProfitLoss(totalProfitLoss);
        portfolioDto.setTotalProfitLossPercentage(totalProfitLoss/100);
        return portfolioDto;
    }


}
