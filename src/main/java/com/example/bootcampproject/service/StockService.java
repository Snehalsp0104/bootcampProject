package com.example.bootcampproject.service;

import com.example.bootcampproject.entity.Stock;
import com.example.bootcampproject.exception.StockNotFoundException;
import com.example.bootcampproject.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> readStocksFromFile(BufferedReader inputReader) throws IOException {
        String line = null;
        boolean firstLine = true;
        List<Stock> inputs = new ArrayList<>();
        while ((line = inputReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] columns = line.split(",");

            String stockName = columns[0];
            float openingPrice = Float.parseFloat((columns[2]));
            float highPrice = Float.parseFloat((columns[3]));
            float lowPrice = Float.parseFloat((columns[4]));
            float closingPrice = Float.parseFloat((columns[5]));
            float lastPrice = Float.parseFloat((columns[6]));
            float prevClose = Float.parseFloat((columns[7]));

            Stock stock = Stock.builder()
                    .stockName(stockName)
                    .openingPrice(openingPrice)
                    .closingPrice(closingPrice)
                    .highPrice(highPrice)
                    .lowPrice(lowPrice)
                    .lastPrice(lastPrice)
                    .prevClose(prevClose)
                    .build();

            inputs.add(stock);
        }
        return inputs;
    }

    public void updateStocks(MultipartFile file) throws IOException {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(file.getInputStream()));
        List<Stock> stocks = readStocksFromFile(inputReader);
        stockRepository.saveAll(stocks);

    }

    public Stock getStockInfo(int stockId) {
       Optional<Stock> optionalStock = stockRepository.findById(stockId);
       if (optionalStock.isEmpty()) throw new StockNotFoundException("Stock does not exist");
       return optionalStock.get();
    }


}
